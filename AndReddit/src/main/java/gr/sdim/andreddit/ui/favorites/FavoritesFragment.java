package gr.sdim.andreddit.ui.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import gr.sdim.andreddit.MainActivity;
import gr.sdim.andreddit.R;
import gr.sdim.andreddit.data.Favorite;
import gr.sdim.andreddit.data.FavoritesAdapter;
import gr.sdim.andreddit.databinding.FragmentFavoritesBinding;
import gr.sdim.redditapiclient.Post;
import gr.sdim.redditapiclient.RedditAPIClientListener;


public class FavoritesFragment extends Fragment {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private ChildEventListener mChildEventListener;
    FragmentFavoritesBinding binding ;
    MainActivity mainActivity;
    LinearLayout linearLayout;
    RecyclerView favoritesList;
    private static final int RC_SIGN_IN = 123;
    FavoritesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        mainActivity = (MainActivity) getActivity();
        binding = FragmentFavoritesBinding.inflate(getActivity().getLayoutInflater());
        linearLayout = binding.googleLoginLayout;
        linearLayout.setVisibility(View.GONE); //  for this project authentication is not needed

        adapter = new FavoritesAdapter(this.getContext());
        favoritesList = binding.favoritesList;
        favoritesList.setLayoutManager(new LinearLayoutManager(getContext()));
        favoritesList.setAdapter(adapter);

        binding.firebaseLoginButton.setOnClickListener(v -> onGoogleLoginClick(v));
        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(getString(R.string.favorites));
        mFirebaseAuth = FirebaseAuth.getInstance();
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.addFavorite(snapshot.getValue(Favorite.class));
                adapter.notifyDataSetChanged();
                linearLayout.setVisibility(View.GONE);
                favoritesList.setVisibility(View.VISIBLE);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);
    }

    public void onGoogleLoginClick(View view){
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

}