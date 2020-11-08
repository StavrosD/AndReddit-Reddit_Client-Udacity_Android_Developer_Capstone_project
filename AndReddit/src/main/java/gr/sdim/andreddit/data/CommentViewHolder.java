package gr.sdim.andreddit.data;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import gr.sdim.andreddit.databinding.CommentListItemViewBinding;


public class CommentViewHolder extends RecyclerView.ViewHolder {
    public MaterialTextView commentTextView;
    public ImageView arrowImage;
    public ImageView commentImage;
    public Integer level = 0;
    public CommentViewHolder(View view) {
        super(view);
        CommentListItemViewBinding binding = CommentListItemViewBinding.bind(view);
        arrowImage = binding.commentArrow;
        commentImage = binding.commentImage;
        commentTextView = binding.commentText;
        binding.getRoot().setPadding(8+level*24,8,8,8);
    }
}
