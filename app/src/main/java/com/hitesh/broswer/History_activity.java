package com.hitesh.broswer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class History_activity extends AppCompatActivity {

    ArrayList<String> item;
    ArrayAdapter<String> itemAdapter;
    ListView listItem;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_activity);


        listItem = (ListView) findViewById(R.id.listItem);
        item = new ArrayList<String>();
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        listItem.setAdapter(itemAdapter);
        Intent intent2 = getIntent();
        String text = intent2.getStringExtra(Home.history_url);
        itemAdapter.add(text);
    }
}
