package gr.sdim.andreddit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;

import gr.sdim.andreddit.databinding.ActivityLoginBinding;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

// useful tutorial for reddit OAuth2
// https://progur.com/2016/10/how-to-use-reddit-oauth2-in-android-apps.html

public class login extends AppCompatActivity {
    public static final String LOGIN_EXTRA_MESSAGE = "gr.sdim.andreddit.LOGIN_EXTRA_MESSAGE";
    private static final String TAG = "login_activity" ;
    ActivityLoginBinding binding;
    private static final String AUTH_URL =
            "https://www.reddit.com/api/v1/authorize.compact?client_id=%s" +
                    "&response_type=code&state=%s&redirect_uri=%s&" +
                    "duration=permanent&scope=identity";

    private static final String CLIENT_ID = "joypN5grPYIn5A";  // Enter your clientID, this cliend ID will be probable invalid when you see this code.

    private static final String REDIRECT_URI ="http://example.com/StavrosD";

    private static final String STATE = "StavrosD";

    private static final String ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


    }


    public void onLoginClick2(View view) {


    }

    public void onLoginClick(View view) {
        String url = String.format(AUTH_URL, CLIENT_ID, STATE, REDIRECT_URI);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
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
                    getAccessToken(code);
                }
            }
        }
    }
    private void getAccessToken(String code) {
        OkHttpClient client = new OkHttpClient();

        String authString = CLIENT_ID + ":";
        String encodedAuthString = Base64.encodeToString(authString.getBytes(), Base64.NO_WRAP);

         Request request = new Request.Builder()
                .addHeader("User-Agent", "AndReddited")
                .addHeader("Authorization", "Basic " + encodedAuthString)
                .url(ACCESS_TOKEN_URL)
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),"grant_type=authorization_code&code=" + code +"&redirect_uri=" + REDIRECT_URI))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, "ERROR: " + e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();

                JSONObject data = null;
                try {
                    data = new JSONObject(json);
                    String accessToken = data.optString("access_token");
                    String refreshToken = data.optString("refresh_token");
                    Log.d(TAG, "Access Token = " + accessToken);
                    Log.d(TAG, "Refresh Token = " + refreshToken);
                    Intent intent = new Intent(login.this, MainActivity.class);
                    intent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accessToken);
                    intent.putExtra(MainActivity.EXTRA_REFRESH_TOKEN, refreshToken);

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } //http://localhost:8080/?state=StavrosD&code=qz_hgWnJCOcqgCOqxpsntceX9UE
        });
    }

}