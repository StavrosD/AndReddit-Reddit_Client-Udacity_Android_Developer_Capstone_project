package gr.sdim.andreddit.data;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textview.MaterialTextView;
import gr.sdim.andreddit.databinding.SubredditListItemViewBinding;

public class SubscriptionViewHolder extends RecyclerView.ViewHolder {
    public MaterialTextView prefixTextView;
    public MaterialTextView titleTextView;
    public MaterialTextView detailsTextView;
    public SubscriptionViewHolder(View view) {
        super(view);
        SubredditListItemViewBinding binding = SubredditListItemViewBinding.bind(view);
        prefixTextView = binding.subredditListItemPrefix;
        titleTextView = binding.subredditListItemTitle;
        detailsTextView = binding.subredditListItemDetails;
    }
}
