package gr.sdim.redditapiclient;

import android.util.Log;

import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public interface RedditAPIClientListener
{
    // empty defaults = optional
    // I set everything as default to avoid adding empty implementations just to satisfy the compiler warnings/errors.
    String TAG = "RedditAPIClientListener";
    default void onSuccessfulAuthResponse(String accessToken, String refreshToken) {}
    default void onFailure(Request request, IOException e){
        Log.d(TAG, e.getLocalizedMessage());
    }
    default void onFailure(Request request, JSONException e){ Log.d(TAG, e.getLocalizedMessage()); }
    default void onFailure(Exception e){ Log.d(TAG, e.getLocalizedMessage()); }
    default void onCommentPosted(){}
    default void onGetPostFromUrlResponse(Post post){}
    default void onGetPostResponse(Post post){}
    default void onGetPostFromUrlCommentsResponse(JSONArray comments){}
    default void onGetSubscribedReddits(Reddit[] reddits){}
    default void onGetCommentsResponse(JSONArray comments){}
}