package gr.sdim.andreddit.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gr.sdim.andreddit.R;

public class CommentsAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private ListComment[] comments;
    private Context context;

    public CommentsAdapter(Context context) {
        this.context = context;
    }

    public CommentsAdapter(Context context, JSONArray comments) {
        this.context = context;
        loadComments(comments);
    }

    public void loadComments(JSONArray comments) {
        if (comments != null) {
            ArrayList<ListComment> mComments = getComments(comments, 0);
            this.comments = mComments.toArray(new ListComment[mComments.size()]);
        }
    }

    public void clearComments() {
        comments = new ListComment[]{};
    }

    private ArrayList<ListComment> getComments(JSONArray comment, Integer level) {
        if (comment != null) {
            ArrayList<ListComment> list = new ArrayList<>();
            JSONObject data;
            Integer i;

            for (i = 0; i < comment.length(); i++) {
                try {
                    data = comment.getJSONObject(i).getJSONObject("data");
                    if (data.optJSONObject("replies") != null) {
                        JSONObject replies = data.getJSONObject("replies");
                        list.add(new ListComment(data.getString("body"), level, true));
                        list.addAll(getComments(replies.getJSONObject("data").getJSONArray("children"), level + 1));
                    } else {
                        list.add(new ListComment(data.getString("body"), level, false));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            return list;
        }
        return null;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_list_item_view, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        ListComment comment = comments[position];
        if (comment.hasReplies) {
            holder.commentImage.setVisibility(View.GONE);
            holder.arrowImage.setVisibility(View.VISIBLE);
        } else {
            holder.commentImage.setVisibility(View.VISIBLE);
            holder.arrowImage.setVisibility(View.GONE);
        }
        holder.commentTextView.setText(comment.text);
        holder.level = comment.level;
    }

    @Override
    public int getItemCount() {
        if (comments != null) {
            return comments.length;
        } else {
            return 0;
        }
    }

}
