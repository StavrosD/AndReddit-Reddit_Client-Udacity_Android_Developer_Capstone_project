package gr.sdim.andreddit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.io.IOException;

import gr.sdim.andreddit.databinding.ActivityLoginBinding;
import gr.sdim.redditapiclient.RedditAPIClient;
import gr.sdim.redditapiclient.RedditAPIClientListener;

//import com.google.firebase.


// useful tutorial for reddit OAuth2
// https://progur.com/2016/10/how-to-use-reddit-oauth2-in-android-apps.html

public class LoginActivity extends AppCompatActivity implements RedditAPIClientListener {
    private static final String TAG = "login_activity" ;
    ActivityLoginBinding binding;
    private InterstitialAd mInterstitialAd;

    private static final String AUTH_URL =
            "https://www.reddit.com/api/v1/authorize.compact?client_id=%s" +
                    "&response_type=code&state=%s&redirect_uri=%s&" +
                    "duration=temporary&scope=identity mysubreddits read";


    private static final String REDIRECT_URI ="http://example.com/StavrosD";

    private static final String STATE = "StavrosD";

    private static final String ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token";
    private RedditAPIClient redditAPIClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        // Show interstitial advertisement
        MobileAds.initialize(this);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.show();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                redirectToRedditLogin();
            }

        });        setContentView(binding.getRoot());

        redditAPIClient = new RedditAPIClient(getString(R.string.reddit_client_id),this);

    }

    public void onLoginClick(View view) {
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        } else {  // if the ad is not loaded for some reason, proceed to login
            redirectToRedditLogin();
        }
    }

    private void redirectToRedditLogin(){
        String url = String.format(AUTH_URL, getString(R.string.reddit_client_id), STATE, REDIRECT_URI);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        showProgressBar();
        startActivity(intent);
    }

    private void showProgressBar(){
        // I use this on an async function, however it should run on the main UI thread.
        runOnUiThread(() -> {
            binding.buttonLogin.setVisibility(View.GONE);
            binding.textBody.setVisibility(View.GONE);
            binding.textTitle.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.VISIBLE);
        });
    }

    private void hideProgressBar(){
        // I use this on an async function, however it should run on the main UI thread.
        runOnUiThread(() -> {
            binding.buttonLogin.setVisibility(View.VISIBLE);
            binding.textBody.setVisibility(View.VISIBLE);
            binding.textTitle.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent()!=null && getIntent().getAction().equals(Intent.ACTION_VIEW)) {
            Uri uri = getIntent().getData();
            if(uri.getQueryParameter("error") != null) {
                String error = uri.getQueryParameter("error");
                Log.e(TAG, "An error has occurred : " + error);
            } else {
                String state = uri.getQueryParameter("state");
                if(state.equals(STATE)) {
                    String code = uri.getQueryParameter("code");
                    showProgressBar();
                    redditAPIClient.getAccessToken(code);
                }
            }
        }
    }

    @Override
    public void onSuccessfulAuthResponse(String accessToken, String refreshToken) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        // Start main activity
        intent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accessToken);
        intent.putExtra(MainActivity.EXTRA_REFRESH_TOKEN, refreshToken);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onFailure(Request request, IOException e) {
        Log.e(TAG, "ERROR: " + e);
        hideProgressBar();
    }

    @Override
    public void onFailure(Request request, JSONException e) {
        Log.e(TAG, "ERROR: " + e);
        hideProgressBar();
    }


}