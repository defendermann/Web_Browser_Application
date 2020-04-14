package com.hitesh.broswer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {


    Button goToHome;
    EditText urlTextFieldHome;
    public static final String Extra_Text = "com.hitesh.broswer.Extra_Text";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Objects.requireNonNull(this.getSupportActionBar()).hide();

        goToHome = (Button) findViewById(R.id.goToHome);
        urlTextFieldHome = (EditText) findViewById(R.id.urlTextFieldHome);
       }
       public void openHomeActivity(View view){

        String urlTextHome = urlTextFieldHome.getText().toString();
        Intent intent2 = new Intent(this, Home.class);
        intent2.putExtra(Extra_Text, urlTextHome);
        startActivity(intent2);
       }



}

