package gr.sdim.andreddit.data;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gr.sdim.andreddit.MainActivity;
import gr.sdim.andreddit.R;
import gr.sdim.andreddit.databinding.SubredditListItemViewBinding;
import gr.sdim.redditapiclient.Reddit;

public class SubscriptionsAdapter extends RecyclerView.Adapter<SubscriptionViewHolder> {
        private Reddit[] subscribedReddits;
        private Context mContext;

    public SubscriptionsAdapter(Reddit[] subscribedReddits, Context context )
        {
            this.subscribedReddits = subscribedReddits;
            this.mContext = context;
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.subreddit_list_item_view, parent, false);
            final SubscriptionViewHolder vh = new SubscriptionViewHolder(view);

            view.setOnClickListener(view1 -> {
                Integer currentPosition = vh.getAdapterPosition();
                MainActivity mainActivity = (MainActivity) view1.getContext();
                mainActivity.showRedditAtIndex(currentPosition);
            });
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull SubscriptionViewHolder holder, int position) {
                holder.prefixTextView.setText(subscribedReddits[position].prefix);
                holder.titleTextView.setText(subscribedReddits[position].title);
                holder.detailsTextView.setText(subscribedReddits[position].details);
        }

        @Override
        public int getItemCount() {
            if (subscribedReddits == null) return 0;

            return subscribedReddits.length;
        }
}

