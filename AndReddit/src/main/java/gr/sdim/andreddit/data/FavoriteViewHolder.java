package gr.sdim.andreddit.data;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import gr.sdim.andreddit.databinding.FavoriteListItemViewBinding;

public class FavoriteViewHolder extends RecyclerView.ViewHolder {
    public MaterialTextView titleTextView;
    public ImageView bookmarkImage;
    public FavoriteViewHolder(View view) {
        super(view);
        FavoriteListItemViewBinding binding = FavoriteListItemViewBinding.bind(view);
        titleTextView = binding.favoriteText;
    }
}
