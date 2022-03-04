package com.example.myapplication.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.AppExecuters;
import com.example.myapplication.db.Dao;
import com.example.myapplication.model.Credentials;
import com.example.myapplication.model.NewsModel;
import com.example.myapplication.model.NewsResponce;
import com.example.myapplication.network.NewsApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Response;

public class Repository {
    public NewsApiService newsApiService;
    public Dao dao;
    Application application;


   // RetrieveMovieRunnable retrieveMovieRunnable;

    @Inject
    public Repository(NewsApiService newsApiService,Dao dao) {
        this.newsApiService = newsApiService;
        this.dao=dao;
    }

    //search
    public MutableLiveData<ArrayList<NewsModel>> searchAnewlist = new MutableLiveData<>();

    public Repository(Application application) {
        this.application=application;
    }



    //News Of Egypt
    public Observable<NewsResponce> getNews(){
        return newsApiService.getNews("eg",Credentials.API_Key);
    }

    //News of europe
    public Observable<NewsResponce> getEuropeNews(){
        return newsApiService.getNews("gb",Credentials.API_Key);
    }

    //News Of Egypt
    public Observable<NewsResponce> searchNews(String query){
        return newsApiService.searchAnew(query,"2022-02-02","publishedAt",Credentials.API_Key);
    }


    public void InsertAnew(NewsModel newsModel){dao.InsertNews(newsModel);}

    public void DeleteAnew(String newsTitle){dao.deleteAnew(newsTitle);}

    public LiveData<List<NewsModel>>getRlatter(){
        return dao.getRlatterNews();
    }



//api of searching for news::
//https://newsapi.org/v2/everything?q=tesla&from=2022-02-02
// &sortBy=publishedAt&apiKey=f4de97aa77ca4dd692729ea3087f191e

    /*//Search A news

    public void searchAnew(String query){
        if (retrieveMovieRunnable!=null){
            retrieveMovieRunnable=null;

        }

        retrieveMovieRunnable=new RetrieveMovieRunnable(query);
        final Future myHandler = AppExecuters.getInstance().getmNetworkIO().submit(retrieveMovieRunnable);
        AppExecuters.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //for cutting the flow
                myHandler.cancel(true);

            }
        },300, TimeUnit.MILLISECONDS);

    }


    public class RetrieveMovieRunnable implements  Runnable{
        private String query;
        boolean cancelRequet;

        public RetrieveMovieRunnable(String query) {
            this.query = query;
           cancelRequet = false;
        }

        @Override
        public void run() {
            try {

                Response<NewsResponce> response=searchNews(query).execute();

                if(cancelRequet){
                    return;
                }

                if(response.code()==200){
                    List<NewsModel>list=new ArrayList<>(((NewsResponce)response.body()).getArticles());
                    searchAnewlist.postValue((ArrayList<NewsModel>) list);

                }
                else{

                    String error=response.errorBody().string();
                    Log.v("Tag","Error"+error);
                    searchAnewlist.postValue(null);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //search method
        private Call<NewsResponce>searchNews(String query){
            return (Call<NewsResponce>) newsApiService.searchAnew(query,"2022-02-02","publishedAt", Credentials.API_Key);
        }
        private void cacelRequest(){
            Log.v("Tag","Cancel Serach Request ");

            cancelRequet=true;

        }
    }
*/

}

//api of searching for news::
//https://newsapi.org/v2/everything?q=tesla&from=2022-02-02
// &sortBy=publishedAt&apiKey=f4de97aa77ca4dd692729ea3087f191e