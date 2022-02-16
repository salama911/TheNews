package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.model.NewsModel;

public class DetailsActivity extends AppCompatActivity {

    private ImageView reportimg;
    private TextView reporttitl, reportreleasedate, reportdescription;
    private Button continuewReadinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        reportimg=findViewById(R.id.reportimg);
        reporttitl=findViewById(R.id.headline);
        reportreleasedate=findViewById(R.id.release_date);
        reportdescription=findViewById(R.id.report_details);

        continuewReadinbtn=findViewById(R.id.continue_reading);
        continuewReadinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        GetDataFromIntent();
        GetDataFromEuroIntent();
        GetDataFromRlaterIntent();

    }



    private void GetDataFromIntent() {

        if(getIntent().hasExtra("News"))
        {

            NewsModel newsModel=getIntent().getParcelableExtra("News");
            reporttitl.setText(newsModel.getTitle());
            reportdescription.setText(newsModel.getDescription());
            reportreleasedate.setText(newsModel.getPublishedAt());
           // description.setMovementMethod(new ScrollingMovementMethod());
            Glide.with(this)
                    .load(newsModel.getUrlToImage())
                    .into(reportimg);

        }

    }
    private void GetDataFromEuroIntent() {

        if(getIntent().hasExtra("EuroNews"))
        {

            NewsModel newsModel=getIntent().getParcelableExtra("EuroNews");
            reporttitl.setText(newsModel.getTitle());
            reportdescription.setText(newsModel.getDescription());
            reportreleasedate.setText(newsModel.getPublishedAt());
            // description.setMovementMethod(new ScrollingMovementMethod());
            Glide.with(this)
                    .load(newsModel.getUrlToImage())
                    .into(reportimg);

        }

    }
    private void GetDataFromRlaterIntent() {

        if(getIntent().hasExtra("RlaterNews"))
        {

            NewsModel newsModel=getIntent().getParcelableExtra("RlaterNews");
            reporttitl.setText(newsModel.getTitle());
            reportdescription.setText(newsModel.getDescription());
            reportreleasedate.setText(newsModel.getPublishedAt());
            // description.setMovementMethod(new ScrollingMovementMethod());
            Glide.with(this)
                    .load(newsModel.getUrlToImage())
                    .into(reportimg);

        }

    }
}
