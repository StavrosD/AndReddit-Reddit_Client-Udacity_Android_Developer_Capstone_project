package gr.sdim.andreddit.ui.subscribed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gr.sdim.andreddit.MainActivity;
import gr.sdim.andreddit.data.SubscriptionsAdapter;
import gr.sdim.andreddit.data.SubscriptionsViewModel;
import gr.sdim.andreddit.databinding.FragmentSubscribedBinding;
import gr.sdim.redditapiclient.Post;
import gr.sdim.redditapiclient.Reddit;
import gr.sdim.redditapiclient.RedditAPIClientListener;

public class SubscriptionsFragment extends Fragment implements RedditAPIClientListener {

    private FragmentSubscribedBinding binding;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private static final String EXTRA_RECyCLERVIEW = "recyclerview";
    private SubscriptionsViewModel subscriptionsViewModel;
    SubscriptionsAdapter adapter;
    MainActivity mainActivity;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        subscriptionsViewModel =  new ViewModelProvider(getActivity()).get(SubscriptionsViewModel.class);
        binding = FragmentSubscribedBinding.inflate(getLayoutInflater(),container,false);
        progressBar=binding.progressBar;
        recyclerView = binding.subscribedRecyclerView;
        mainActivity = (MainActivity)getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        subscriptionsViewModel.getSubscribedReddits().observe(getViewLifecycleOwner(), reddits -> {
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new SubscriptionsAdapter((Reddit[])reddits.toArray(),mainActivity);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        });
        if (savedInstanceState != null) {
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(EXTRA_RECyCLERVIEW));

        } else {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        return binding.getRoot();
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(EXTRA_RECyCLERVIEW, recyclerView.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);

    }

}