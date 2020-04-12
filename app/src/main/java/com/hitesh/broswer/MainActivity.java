package com.hitesh.broswer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText urlField;
    ImageButton goBack;
    ImageButton goForward;
    WebView webView;
    Button enter;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(this.getSupportActionBar()).hide();


        urlField = (EditText) findViewById(R.id.urlField);
        goBack = (ImageButton) findViewById(R.id.goBack);
        goForward = (ImageButton) findViewById(R.id.goForward);
        webView = (WebView) findViewById(R.id.webView);
        enter = (Button) findViewById(R.id.enter);

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
        webView.setWebViewClient(new MyWebViewClient());

    }

    public void goTo(View v) {

        try {
            if(!NetworkState.connectionAvailable(MainActivity.this)){
            }else {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(urlField.getWindowToken(), 0);
                webView.loadUrl("https://" + urlField.getText().toString());
                urlField.setText("");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void goBack(View view){
        webView.goBack();
    }

    public void goForward(View view){
        webView.goForward();
    }
    static class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            CookieManager.getInstance().setAcceptCookie(true);
            return true;
        }
    }
    public static class NetworkState {

        static boolean connectionAvailable(Context context){

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connectivityManager != null;
            return connectivityManager.getActiveNetworkInfo() !=null;
        }
    }
}
