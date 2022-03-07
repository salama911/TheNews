package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.viewmodel.NewsViewModel;

import java.util.List;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    private ImageView reportimg;
    private TextView reporttitl, reportreleasedate, reportdescription;
    private Button continuewReadinbtn;
    private String link;
    private NewsViewModel favVmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        reportimg = findViewById(R.id.reportimg);
        reporttitl = findViewById(R.id.headline);
        reportreleasedate = findViewById(R.id.release_date);
        reportdescription = findViewById(R.id.report_details);

        continuewReadinbtn = findViewById(R.id.continue_reading);
        continuewReadinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, WebPage.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });

        GetDataFromIntent();
        GetDataFromEuroIntent();
        GetDataFromRlaterIntent();
        GetDataFromSearchedIntent();

    }


    private void GetDataFromIntent() {

        if (getIntent().hasExtra("News")) {

            NewsModel newsModel = getIntent().getParcelableExtra("News");
            reporttitl.setText(newsModel.getTitle());
            reportdescription.setText(newsModel.getDescription());
            reportreleasedate.setText(newsModel.getPublishedAt());
            reportdescription.setMovementMethod(new ScrollingMovementMethod());
            Glide.with(this)
                    .load(newsModel.getUrlToImage())
                    .into(reportimg);

            link = newsModel.getUrl();
            Log.d("tagE", "onCreate: " + link);


        }

    }

    private void GetDataFromEuroIntent() {

        if (getIntent().hasExtra("EuroNews")) {

            NewsModel newsModel = getIntent().getParcelableExtra("EuroNews");
            reporttitl.setText(newsModel.getTitle());
            reportdescription.setText(newsModel.getDescription());
            reportreleasedate.setText(newsModel.getPublishedAt());
            Glide.with(this)
                    .load(newsModel.getUrlToImage())
                    .into(reportimg);

            link = newsModel.getUrl();
            Log.d("tagu", "onCreate: " + link);


        }

    }

    private void GetDataFromRlaterIntent() {

        if (getIntent().hasExtra("RlaterNews")) {

            NewsModel newsModel = getIntent().getParcelableExtra("RlaterNews");
            reporttitl.setText(newsModel.getTitle());
            reportdescription.setText(newsModel.getDescription());
            reportreleasedate.setText(newsModel.getPublishedAt());
            Glide.with(this)
                    .load(newsModel.getUrlToImage())
                    .into(reportimg);

            link = newsModel.getUrl();
            Log.d("tagr", "onCreate: " + link);

        }


    }
    private void GetDataFromSearchedIntent() {

        if(getIntent().hasExtra("searchedNews"))
        {

            NewsModel newsModel=getIntent().getParcelableExtra("searchedNews");
            reporttitl.setText(newsModel.getTitle());
            reportdescription.setText(newsModel.getDescription());
            reportreleasedate.setText(newsModel.getPublishedAt());
            Glide.with(this)
                    .load(newsModel.getUrlToImage())
                    .into(reportimg);

            link= newsModel.getUrl();
            Log.d("tagsearcheddetails", "onCreate: "+link);

        }





    }
}