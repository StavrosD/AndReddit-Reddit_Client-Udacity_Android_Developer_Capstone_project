package gr.sdim.andreddit.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gr.sdim.andreddit.R;
import gr.sdim.andreddit.databinding.SubredditListItemViewBinding;

public class SubscriptionsAdapter extends RecyclerView.Adapter<SubscriptionViewHolder> {
    private JSONArray registeredSubreddits;
    private Context mContext;
        public SubscriptionsAdapter(String json, Context context )
        {
            try {
                registeredSubreddits = new JSONObject(json).getJSONObject("data").getJSONArray("children");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mContext = context;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          //  View view =  getLayoutInflater().inflate(R.layout.paragraph, parent, false);
            //final SubscriptionViewHolder vh = new SubscriptionViewHolder(view);
            View view = LayoutInflater.from(mContext).inflate(R.layout.subreddit_list_item_view, parent, false);
            final SubscriptionViewHolder vh = new SubscriptionViewHolder(view);
            return vh;
        }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionViewHolder holder, int position) {
        holder.textView.setText("paragraphs[position]");

    }
        @Override
        public int getItemCount() {
            return registeredSubreddits.length();
        }


}

