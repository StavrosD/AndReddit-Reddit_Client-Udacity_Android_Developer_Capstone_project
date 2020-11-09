package gr.sdim.andreddit.data;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gr.sdim.andreddit.R;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {
    public static String TAG = "favorites_tag";
    private List<Favorite> favorites;
    private Context context;

    public FavoritesAdapter(Context context) {
        this.context = context;
        favorites = new ArrayList<Favorite>() {
        };
    }

    public FavoritesAdapter(Context context, ArrayList<Favorite> favorites) {
        this.context = context;
        this.favorites = favorites;
    }

    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_list_item_view, parent, false);
        final FavoriteViewHolder vh = new FavoriteViewHolder(view);
        view.setOnClickListener(view1 -> {
            Integer currentPosition = vh.getAdapterPosition();
            Bundle bundle = new Bundle();
            bundle.putString(TAG, favorites.get(currentPosition).getUrl());
            NavController navController = Navigation.findNavController((AppCompatActivity) view1.getContext(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_nav_favorites_to_nav_subreddit, bundle);
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favorite favorite = favorites.get(position);
        holder.titleTextView.setText(favorite.getTitle());
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

}
