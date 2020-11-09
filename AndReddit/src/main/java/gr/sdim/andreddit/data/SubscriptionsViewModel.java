package gr.sdim.andreddit.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import gr.sdim.redditapiclient.Post;
import gr.sdim.redditapiclient.Reddit;

public class SubscriptionsViewModel extends ViewModel {

    private MutableLiveData<List<Reddit>> subscribedReddits;
    private MutableLiveData<List<Post>> posts;
    private int currentRedditIndex;

    public SubscriptionsViewModel() {
        subscribedReddits = new MutableLiveData<>();
        currentRedditIndex = 0;
    }

    public void setData(Reddit[] data) {
        subscribedReddits.postValue(Arrays.asList(data));
        currentRedditIndex = 0;
    }

    public LiveData<List<Reddit>> getSubscribedReddits() {
        return subscribedReddits;
    }

    public Integer getCurrentIndex() {
        return currentRedditIndex;
    }

    public void setCurrentIndex(Integer index) {
        currentRedditIndex = index;
    }

    public Boolean hasPost() {
        if (subscribedReddits.getValue() == null) return false;
        if (subscribedReddits.getValue().size() > currentRedditIndex - 1) return false;
        if (subscribedReddits.getValue().get(currentRedditIndex).current_post == null) return false;
        return true;
    }

    private void increaseIndex() {
        Integer redditCount = subscribedReddits.getValue().size();
        if (currentRedditIndex < redditCount - 1) {
            currentRedditIndex++;
        } else {
            currentRedditIndex = 0;
        }
    }

    public Reddit getNextReddit() {
        increaseIndex();
        return getCurrentReddit();
    }

    public Reddit getCurrentReddit() {
        if (subscribedReddits.getValue().size() == 0) return null;
        return subscribedReddits.getValue().get(currentRedditIndex);
    }

    public Post getCurrentPost() {
        if (subscribedReddits.getValue().size() == 0) return null;
        return subscribedReddits.getValue().get(currentRedditIndex).current_post;
    }

    public Post moveToNextReditPost() {
        increaseIndex();
        return getCurrentPost();
    }

    public void addPost(Post post) {
        for (Reddit reddit : subscribedReddits.getValue()) {
            if (post.subreddit_id.equals(reddit.name)) {
                if (reddit.current_post == null) { // this is the first received post for this reddit
                    reddit.current_post = post;
                } else if (reddit.nextPost == null) { // this is the next received post for this reddit. It is prefetched for better UI experiance
                    reddit.nextPost = post;
                } else { // A new post is prefetched
                    reddit.current_post = reddit.nextPost;
                    reddit.nextPost = post;
                }
            }
        }
    }

    public void addComments(JSONArray comments) {
        if (comments != null) {
            if (comments.length() > 0) {
                try {
                    JSONObject firstComment = comments.getJSONObject(0).getJSONObject("data");
                    String parent_id = firstComment.getString("parent_id");
                    String subreddit_id = firstComment.getString("subreddit_id");
                    for (Reddit reddit : subscribedReddits.getValue()) {
                        if (reddit.name.equals(subreddit_id)) {
                            if (reddit.current_post.name.equals(parent_id)) {
                                reddit.current_post.comments = comments;
                            } else if (reddit.nextPost.name.equals(parent_id)) {
                                reddit.nextPost.comments = comments;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}