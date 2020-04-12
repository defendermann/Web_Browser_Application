package com.hitesh.broswer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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


    Button goToHome;
    EditText urlTextFieldHome;
    public static final String Extra_Text = "com.hitesh.broswer.Extra_Text";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(this.getSupportActionBar()).hide();

        goToHome = (Button) findViewById(R.id.goToHome);
        urlTextFieldHome = (EditText) findViewById(R.id.urlTextFieldHome);
       }
       public void openHomeActivity(View view){

        String urlTextHome = urlTextFieldHome.getText().toString();
        Intent intent = new Intent(this, Home.class);
        intent.putExtra(Extra_Text, urlTextHome);
        startActivity(intent);
       }



}

