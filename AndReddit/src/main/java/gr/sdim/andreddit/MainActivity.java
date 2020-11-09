package gr.sdim.andreddit;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import gr.sdim.andreddit.data.Favorite;
import gr.sdim.andreddit.data.SubscriptionsViewModel;
import gr.sdim.andreddit.databinding.ActivityMainBinding;
import gr.sdim.andreddit.ui.subreddit.SubredditFragment;
import gr.sdim.redditapiclient.Post;
import gr.sdim.redditapiclient.Reddit;
import gr.sdim.redditapiclient.RedditAPIClient;
import gr.sdim.redditapiclient.RedditAPIClientListener;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class MainActivity extends AppCompatActivity implements RedditAPIClientListener {

    public static final String EXTRA_ACCESS_TOKEN = "access_token";
    public static final String EXTRA_REFRESH_TOKEN = "refresh_token";
    public String accessToken;
    public String refreshToken;
    public NavController navController;
    public RedditAPIClient redditAPIClient;
    // true: display post from URL (favorites)
    public Boolean getPostFromUrl;
    public Post postFromUrl;
    FirebaseDatabase database = getInstance();
    DatabaseReference myRef = database.getReference("message");
    private Menu menu;
    private SubscriptionsViewModel subscriptionsViewModel;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        subscriptionsViewModel = new ViewModelProvider(this).get(SubscriptionsViewModel.class);

        setContentView(mainBinding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = mainBinding.drawerLayout;
        NavigationView navigationView = mainBinding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_subscribed, R.id.nav_subreddit, R.id.nav_favorites, R.id.nav_about)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if (savedInstanceState == null) {
            if (getIntent() != null) {
                accessToken = getIntent().getStringExtra(EXTRA_ACCESS_TOKEN);
                refreshToken = getIntent().getStringExtra(EXTRA_REFRESH_TOKEN);
                redditAPIClient = new RedditAPIClient(this, accessToken);
                redditAPIClient.getSubscribedReddits(true);
            } else {
                accessToken = savedInstanceState.getString(EXTRA_ACCESS_TOKEN);
                refreshToken = savedInstanceState.getString(EXTRA_REFRESH_TOKEN);
                redditAPIClient = new RedditAPIClient(this, accessToken);
            }
        }
    }

    public void showRedditAtIndex(Integer index) {
        runOnUiThread(() -> navController.navigate(R.id.nav_subreddit));
        subscriptionsViewModel.setCurrentIndex(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main_activity, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putString(EXTRA_ACCESS_TOKEN, accessToken);
        outState.putString(EXTRA_REFRESH_TOKEN, refreshToken);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onGetSubscribedReddits(Reddit[] reddits) {
        subscriptionsViewModel.setData(reddits);
    }

    @Override
    public void onGetPostResponse(Post post) {

        subscriptionsViewModel.addPost(post);
        redditAPIClient.getComments(post);
    }

    @Override
    public void onGetCommentsResponse(JSONArray comments) {
        subscriptionsViewModel.addComments(comments);
    }

    @Override
    public void onFailure(@NotNull Exception e) {
        Snackbar.make(mainBinding.navView, "Error: Could not retrieve data! Please check your internet connection.", BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Post post;
        switch (item.getItemId()) {
            case R.id.action_add_favorite:
                post = subscriptionsViewModel.getCurrentPost();
                Favorite favorite = new Favorite(post.title, post.url);
                FirebaseDatabase.getInstance().getReference().child(getString(R.string.favorites))
                        .push().setValue(favorite)
                        .addOnSuccessListener(aVoid -> Snackbar.make(mainBinding.navView, "Favorite added!", BaseTransientBottomBar.LENGTH_LONG).show())
                        .addOnCanceledListener(() -> Snackbar.make(mainBinding.navView, "Error: Could not add favorite!", BaseTransientBottomBar.LENGTH_LONG).show());

            case R.id.action_catch_up:
                redditAPIClient.getSubscribedReddits(true);
                return true;

            case R.id.action_share:
                post = subscriptionsViewModel.getCurrentReddit().current_post;
                if (post != null) {
                    ShareCompat.IntentBuilder.from(this)
                            .setType("text/plain")
                            .setChooserTitle("Share reddit: " + post.title)
                            .setText(post.url)
                            .startChooser();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showPostMenu() {
        menu.findItem(R.id.action_add_favorite).setVisible(true);
        menu.findItem(R.id.action_share).setVisible(true);
    }

    public void hidePostMenu() {
        menu.findItem(R.id.action_add_favorite).setVisible(false);
        menu.findItem(R.id.action_share).setVisible(false);
    }

    @Override
    public void onGetPostFromUrlResponse(Post post) {
        redditAPIClient.getPostFromUrlComments(post);
        SubredditFragment fragment = (SubredditFragment) getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager().getPrimaryNavigationFragment();
        runOnUiThread(() -> fragment.setPost(post));
    }

    @Override
    public void onGetPostFromUrlCommentsResponse(JSONArray comments) {
        SubredditFragment fragment = (SubredditFragment) getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager().getPrimaryNavigationFragment();
        runOnUiThread(() -> fragment.setComments(comments));
    }

    @Override
    public void onCommentPosted() {
        Snackbar.make(mainBinding.navView, "Commend added!", BaseTransientBottomBar.LENGTH_LONG).show();
        SubredditFragment fragment = (SubredditFragment) getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager().getPrimaryNavigationFragment();
        redditAPIClient.getPostFromUrlComments(fragment.currentPost);
    }
}