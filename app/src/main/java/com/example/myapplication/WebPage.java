package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.example.myapplication.model.NewsModel;

public class WebPage extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage);

        WebView webView = findViewById(R.id.webid);
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
             url = extras.getString("link");
           // Log.d("tagg", "onCreate: "+url);
        }

        webView.loadUrl(url);



    }

}