package gr.sdim.redditapiclient;

import android.util.Base64;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

import okio.BufferedSink;
// the structure of the JSON objects is described here
// https://github.com/reddit-archive/reddit/wiki/JSON

public class RedditAPIClient {

    private RedditAPIClientListener listener;

    private String clientId;  // Open values/api_keys.xml and fill in your client ID

    private static final String REDIRECT_URI ="http://example.com/StavrosD";
    private static final String STATE = "StavrosD";
    private static final String ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token";
    private static final String BASE_URL = "https://oauth.reddit.com";
    private static final String GET_MY_REDDITS_URL = BASE_URL + "/subreddits/mine/subscriber.json?raw_json=1&include_over_18=0";
    //private static final String AUTH_URL = "https://www.reddit.com/api/v1/authorize.compact?client_id=%s&response_type=code&state=%s&redirect_uri=%s&duration=temporary&scope=identity mysubreddits read submit";
    private static final String GET_NEXT_POST_URL = BASE_URL + "%snew.json?before=%s&limit=2&include_over_18=0";
    private static final String GET_LATEST_POST_URL = BASE_URL + "%snew/.json?include_over_18=0&limit=1&raw_json=1";
    private static final String POST_COMMENT_URL = BASE_URL + "/api/comment";
    private static OkHttpClient client ;
    private static String accessToken;
    private static String refreshToken;
    private Reddit[] subscribedReddits;
    private AtomicInteger requestedPostsCount;

    public RedditAPIClient(@NotNull RedditAPIClientListener listener, @NotNull String accessToken){
        this.listener = listener;
        this.accessToken = accessToken;
    }

    public RedditAPIClient(@NotNull String clientId, @NotNull RedditAPIClientListener listener) {
        super();
        if (client == null) { client = new OkHttpClient(); }
        this.clientId = clientId;
        this.listener = listener;
    }


    public void getAccessToken(@NotNull String code) {
        String authString = clientId + ":";
        String encodedAuthString = Base64.encodeToString(authString.getBytes(), Base64.NO_WRAP);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "androidAndredditClient/1 by StavrosD")
                .addHeader("Authorization", "Basic " + encodedAuthString)
                .addHeader("state",STATE)
                .addHeader("scope","*") //"identity mysubreddits read submit")
                .url(ACCESS_TOKEN_URL)
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),"grant_type=authorization_code&code=" + code +"&redirect_uri=" + REDIRECT_URI))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onFailure(request, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                JSONObject data;
                try {
                    data = new JSONObject(json);

                    accessToken = data.optString("access_token");
                    refreshToken = data.optString("refresh_token");

                    listener.onSuccessfulAuthResponse(accessToken, refreshToken);
                } catch (JSONException e) {
                    listener.onFailure(request,e);
                }
            }
        });
    }
    // if getPosts is true, getSubscribedReddits requests the each reddits' latest post
    public void getSubscribedReddits(Boolean getPosts){

        Request request = new Request.Builder()
                .addHeader("User-Agent", "androidAndredditClient/1 by StavrosD")
                .addHeader("Authorization", "bearer " + accessToken)
                .url(GET_MY_REDDITS_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response)  {
                JSONArray registeredSubreddits;
                try {
                    requestedPostsCount = new AtomicInteger();
                    String body = response.body().string();
                    registeredSubreddits = new JSONObject(body).getJSONObject("data").getJSONArray("children");
                    subscribedReddits = new Reddit[registeredSubreddits.length()];
                    int i;
                    for (i=0;i<registeredSubreddits.length();i++){
                        JSONObject data = registeredSubreddits.getJSONObject(i).getJSONObject("data");
                        subscribedReddits[i] = new Reddit(
                                data.getString("display_name_prefixed") + "  ", // I could also modify the textview padding instead of adding some space here
                                data.getString("title"),
                                data.getString("public_description") ,
                                null,
                                null,
                                data.getString("url"),
                                data.getString("name")
                        );
                        requestedPostsCount.incrementAndGet();
                        if (getPosts){
                            getLatestPost(subscribedReddits[i]);
                            Log.d("getLatestPost",subscribedReddits[i].title);
                        }
                    }
                    listener.onGetSubscribedReddits(subscribedReddits);
                } catch (JSONException e) {
                    listener.onFailure(request,e);
                } catch (IOException e){
                    listener.onFailure(request,e);
                }
            }
        });
    }


    public void getLatestPost(Reddit reddit){
        Request request = new Request.Builder()
                .addHeader("User-Agent", "androidAndredditClient/1 by StavrosD")
                .addHeader("Authorization", "bearer " + accessToken)
                .url(String.format(GET_LATEST_POST_URL,reddit.reddit_url))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        String result = response.body().string();
                        JSONObject postJSON = new JSONObject(result).getJSONObject("data").getJSONArray("children").getJSONObject(0).getJSONObject("data");
                        Post post = new Post(postJSON);
                        for (Reddit reddit:subscribedReddits){
                            if (reddit.name.equals(post.subreddit_id)){
                                reddit.current_post = post;
                                reddit.nextPost = null;
                            }
                        }
                        listener.onGetPostResponse(post);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void getNextPost(Post post){ // fetch the next post
        Request request = new Request.Builder()
                .addHeader("User-Agent", "androidAndredditClient/1 by StavrosD")
                .addHeader("Authorization", "bearer " + accessToken)
                .url(String.format(GET_NEXT_POST_URL, post.subreddit_name_prefixed, post.name))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        // get the request response body
                        String reddit = response.body().string();
                        // get the post in JSONObject variable
                        JSONObject postJSON = new JSONArray(reddit).getJSONObject(0).getJSONObject("data");
                        // call the onGetPostResponse callback to return the post variable as a Post variable, not in JSONObject format
                        listener.onGetPostResponse(new Post(postJSON));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void getPostFromUrl(String url){ // fetch the next post
        Request request = new Request.Builder()
                .addHeader("User-Agent", "androidAndredditClient/1 by StavrosD")
                .addHeader("Authorization", "bearer " + accessToken)
                .url(url.replace("//www","//oauth") +".json?include_over_18=0")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        // get the request response body
                        String reddit = response.body().string();
                        // get the post in JSONObject variable
                        JSONObject postJSON = new JSONArray(reddit).getJSONObject(0).getJSONObject("data").getJSONArray("children").getJSONObject(0).getJSONObject("data");
                        // call the onGetPostResponse callback to return the post variable as a Post variable, not in JSONObject format
                        listener.onGetPostFromUrlResponse(new Post(postJSON));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void getComments(Post post){
        Request request = new Request.Builder()
                .addHeader("User-Agent", "androidAndredditClient/1 by StavrosD")
                .addHeader("Authorization", "bearer " + accessToken)
                .url(BASE_URL + post.permalink)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        // get the request response body
                        String responseString = response.body().string();
                        // get the post in JSONObject variable
                        JSONArray commentsJSON = new JSONArray(responseString).getJSONObject(1).getJSONObject("data").getJSONArray("children");
                        // call the onGetPostResponse callback to return the post variable as a Post variable, not in JSONObject format
                        listener.onGetCommentsResponse(commentsJSON);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void getPostFromUrlComments(Post post){
        Request request = new Request.Builder()
                .addHeader("User-Agent", "androidAndredditClient/1 by StavrosD")
                .addHeader("Authorization", "bearer " + accessToken)
                .url(BASE_URL + post.permalink)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        // get the request response body
                        String responseString = response.body().string();
                        // get the post in JSONObject variable
                        JSONArray commentsJSON = new JSONArray(responseString).getJSONObject(1).getJSONObject("data").getJSONArray("children");
                        // call the onGetPostResponse callback to return the post variable as a Post variable, not in JSONObject format
                        listener.onGetPostFromUrlCommentsResponse(commentsJSON);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void postComment(String thing_name, String text){
        try{
            Request request = new Request.Builder()
                    .addHeader("User-Agent", "androidAndredditClient/1 by StavrosD")
                    .addHeader("Authorization", "bearer " + accessToken)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .url(POST_COMMENT_URL)
                    .post(new MultipartBuilder()
                            .type(MultipartBuilder.FORM)
                            .addFormDataPart("api_type", "json")
                            .addFormDataPart("thing_id", thing_name)
                            .addFormDataPart("text", URLEncoder.encode(text, StandardCharsets.UTF_8.toString()))
                            .build()
                    )
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        listener.onCommentPosted();
                    }
                }
            });
        } catch (UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
    }

}