package com.hitesh.broswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;


public class Home extends AppCompatActivity {

    EditText urlField;
    ImageButton goBack;
    ImageButton goForward;
    WebView webView;
    Button enter;
    String text;
    ProgressBar progressBar;
    ImageButton reload;
    public static final String history_url = "com.hitesh.broswer.history_url";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Objects.requireNonNull(this.getSupportActionBar()).hide();


        urlField = (EditText) findViewById(R.id.urlField);
        goBack = (ImageButton) findViewById(R.id.goBack);
        goForward = (ImageButton) findViewById(R.id.goForward);
        webView = (WebView) findViewById(R.id.webView);
        enter = (Button) findViewById(R.id.enter);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        reload = (ImageButton) findViewById(R.id.reload);

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setSupportMultipleWindows(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setBackgroundColor(Color.WHITE);
        }
        webView.setWebViewClient(new Home.MyWebViewClient());

        Intent intent = getIntent();
        text = intent.getStringExtra(MainActivity.Extra_Text);
        launchURL();

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                urlField.setText(view.getUrl());
                progressBar.setProgress(newProgress);
                if (newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);




                }
                if (newProgress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                    // String certificate = view.getCertificate().toString();
                } else {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
            }
        });

    }

    public void launchURL() {

        try {
            if (!Home.NetworkState.connectionAvailable(Home.this)) {
            } else {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(urlField.getWindowToken(), 0);
                webView.loadUrl("https://" + text);
                urlField.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void goTo(View v) {

        try {
            if (!Home.NetworkState.connectionAvailable(Home.this)) {
            } else {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(urlField.getWindowToken(), 0);
                webView.loadUrl("https://" + urlField.getText().toString());
                urlField.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openH(MenuItem item) {
            Intent intent2 = new Intent(this, History_activity.class);
            intent2.putExtra(history_url, text);
            startActivity(intent2);
    }

    public static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            CookieManager.getInstance().setAcceptCookie(true);
            return true;
        }
    }
    public static class NetworkState {

        static boolean connectionAvailable(Context context) {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connectivityManager != null;
            return connectivityManager.getActiveNetworkInfo() != null;
        }
    }
    public void goBack(View view) {
        webView.goBack();
    }

    public void goForward(View view) {
        webView.goForward();
    }
    public void reload(View view) {
        webView.reload();
    }
    public void clearCache(MenuItem item) {
        webView.clearCache(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.history:
                    openHistoryActivity();
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void openHistoryActivity(){
        Intent historyIntent = new Intent(this, History_activity.class);
        startActivity(historyIntent);
    }

}
