package gr.sdim.andreddit.ui.subreddit;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import gr.sdim.andreddit.MainActivity;
import gr.sdim.andreddit.data.CommentsAdapter;
import gr.sdim.andreddit.data.FavoritesAdapter;
import gr.sdim.andreddit.databinding.FragmentSubredditBinding;
import gr.sdim.andreddit.data.SubscriptionsViewModel;
import gr.sdim.redditapiclient.Post;
import gr.sdim.redditapiclient.Reddit;
import gr.sdim.redditapiclient.RedditAPIClient;

public class SubredditFragment extends Fragment {
    private SubscriptionsViewModel subscriptionsViewModel;
    private FragmentSubredditBinding binding;
    private FloatingActionButton fab;
    private MaterialTextView mTitle;
    private MaterialTextView mBody;
    private MaterialTextView mAuthor;
    private RecyclerView commentsList;
    private EditText mCommentEditor;
    private MainActivity mainActivity;
    RedditAPIClient client;
    private CommentsAdapter commentsAdapter;
    private String url;
    public Post currentPost;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        subscriptionsViewModel = new ViewModelProvider(getActivity()).get(SubscriptionsViewModel.class);
        mainActivity = (MainActivity) getActivity();
        client = mainActivity.redditAPIClient;
        binding = FragmentSubredditBinding.inflate(inflater, container, false);
        mTitle = binding.textTitle;
        mBody = binding.textBody;
        mCommentEditor = binding.textInsertComment;
        mCommentEditor.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mCommentEditor.setRawInputType(InputType.TYPE_CLASS_TEXT);
        mAuthor = binding.textAuthor;
        mainActivity.showPostMenu();
        commentsAdapter = new CommentsAdapter(this.getContext());
        commentsList = binding.commentList;
        commentsList.setLayoutManager(new LinearLayoutManager(getContext()));
        commentsList.setAdapter(commentsAdapter);
        fab = binding.fab;

        if (getArguments() == null){   // fragment will display the next post
            fab.setOnClickListener(view -> {
                client.getNextPost(subscriptionsViewModel.getCurrentPost());//prefetch the next post from the displayed reddit
                subscriptionsViewModel.moveToNextReditPost(); // display a prefetched post from the next reddit
                updateUi();
            });

            mCommentEditor.setOnEditorActionListener((v, actionId, event) -> {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    client.postComment(currentPost.name,v.getText().toString());
                }
                return handled;
            });
            subscriptionsViewModel.getSubscribedReddits().observe(getViewLifecycleOwner(), reddits -> updateUi());
        } else {            // fragment will display a favorite
            url = getArguments().getString(FavoritesAdapter.TAG);
            fab.setVisibility(View.GONE);
            client.getPostFromUrl(url);
        }
        return  binding.getRoot();
    }

    private void updateUi() {
        Post post = subscriptionsViewModel.getCurrentPost();
        if (post != null) {
            mTitle.setText(post.title);
            if (!post.text_body.equals("null")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mBody.setText(Html.fromHtml(post.text_body, Html.FROM_HTML_MODE_LEGACY));
                } else {
                    mBody.setText(Html.fromHtml(post.text_body));
                }
            }
            mAuthor.setText(post.author);
            if (post.comments != null) {
                commentsAdapter.loadComments(post.comments);
                commentsAdapter.notifyDataSetChanged();
            }
            binding.textBody.setMovementMethod(LinkMovementMethod.getInstance());
            currentPost = post;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainActivity.hidePostMenu();
        binding = null;
    }


    public void setPost(Post post) {
        mTitle.setText(post.title);
        if (!post.text_body.equals("null")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mBody.setText(Html.fromHtml(post.text_body, Html.FROM_HTML_MODE_LEGACY));
            } else {
                mBody.setText(Html.fromHtml(post.text_body));
            }
        }
        mAuthor.setText(post.author);
        if (post.comments != null) {
            commentsAdapter.loadComments(post.comments);
            commentsAdapter.notifyDataSetChanged();
        }
        binding.textBody.setMovementMethod(LinkMovementMethod.getInstance());
        currentPost = post;
    }
    public void setComments(JSONArray comments){
        if (comments != null) {
            commentsAdapter.loadComments(comments);
            commentsAdapter.notifyDataSetChanged();
        }
    }

}