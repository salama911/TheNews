package com.example.myapplication.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.NewsModel;
import com.example.myapplication.repository.Repository;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {

   public Repository repository ;

   @ViewModelInject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<NewsModel>> newsList = new MutableLiveData<>();

/*
    public MutableLiveData<ArrayList<NewsModel>> getNewsList() {
        return newsList;
    }*/

    //@SuppressLint("CheckResult")
    public void getNews(){
        repository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> newsList.setValue(result.getArticles()),
                        error -> Log.e("ViewMOdel", "" + error.getMessage()));


    }
}
