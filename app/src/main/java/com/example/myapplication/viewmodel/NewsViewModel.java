package com.example.myapplication.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.NewsModel;
import com.example.myapplication.repository.Repository;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {

    public Repository repository;
    private String mQuery;
    public Application application;

    @ViewModelInject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
    }
    public void init(Application application){
        repository = new Repository(application);
        //mAllWords = repository.getAllWords();
    }


    public MutableLiveData<ArrayList<NewsModel>> newsList = new MutableLiveData<>();

    public MutableLiveData<ArrayList<NewsModel>> EuropenewsList = new MutableLiveData<>();

    public LiveData<List<NewsModel>> ReadLaterList = null;

    public MutableLiveData<ArrayList<NewsModel>> searchAnewlist = new MutableLiveData<>();



    public LiveData<List<NewsModel>> getReadLaterList() {
        return ReadLaterList;
    }


        public void getNews() {
        repository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> newsList.setValue(result.getArticles()),
                        error -> Log.e("ViewModel", "" + error.getMessage()));


    }


    public void getEuropeNews() {
        repository.getEuropeNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> EuropenewsList.setValue(result.getArticles()),
                        error -> Log.e("ViewModel", "" + error.getMessage()));


    }

    public void searrchNews(String query) {
        repository.searchNews(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> searchAnewlist.setValue(result.getArticles()),
                        error -> Log.e("ViewModel", "" + error.getMessage()));


    }


  /*  public void searchAnew(String query) {
        mQuery=query;
        repository.searchAnew(mQuery);

    }*/


    public void InsertAnew(NewsModel newsModel) {
        repository.InsertAnew(newsModel);
    }

    public void DaleteAnew(String newtitle) {
        repository.DeleteAnew(newtitle);
    }

    public void getReadlaterNews() {
        ReadLaterList = repository.getRlatter();

    }

}
