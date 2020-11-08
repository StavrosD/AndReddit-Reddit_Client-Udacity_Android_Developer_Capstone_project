package gr.sdim.redditapiclient;

import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Post {
    public static String TAG = "reddit_post";
    public String	subreddit_name_prefixed;
    public String	text_body;
    public String	title;
    public String	author;
    public String	url;
    public String   name;
    public String   subreddit_id;
    public String   permalink;
    public JSONArray comments;
    public Post(){}
    public Post(JSONObject root){
        try {
            subreddit_name_prefixed = root.getString("subreddit_name_prefixed");
            title = root.getString("title").replace("\\\n", "").replace("\\\"", "\"");
            text_body = root.getString("selftext_html").replace("\\\n", "").replace("\\\"", "\"");
            author = root.getString("author");
            url = root.getString("url");
            name = root.getString("name");
            permalink = root.getString("permalink");
            subreddit_id = root.getString("subreddit_id");
            if (root.optJSONObject("replies") != null) {
                JSONObject replies = root.getJSONObject("replies");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void loadComments(JSONArray comments){
        if (comments != null) {
            this.comments = comments;
        }
    }
}
