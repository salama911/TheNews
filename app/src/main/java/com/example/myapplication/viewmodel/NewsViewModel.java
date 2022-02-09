package com.example.myapplication.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.NewsModel;
import com.example.myapplication.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {

   public Repository repository ;

   @ViewModelInject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<NewsModel>> newsList = new MutableLiveData<>();

    public MutableLiveData<ArrayList<NewsModel>> EuropenewsList = new MutableLiveData<>();

    public LiveData<List<NewsModel>>ReadLaterList=null;





    public void getNews(){
        repository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> newsList.setValue(result.getArticles()),
                        error -> Log.e("ViewModel", "" + error.getMessage()));


    }

    public void getEuropeNews(){
        repository.getEuropeNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> EuropenewsList.setValue(result.getArticles()),
                        error -> Log.e("ViewModel", "" + error.getMessage()));


    }



    public void InsertAnew(NewsModel newsModel){
            repository.InsertAnew(newsModel);
    }

    public void DaleteAnew(String newtitle){
        repository.DeleteAnew(newtitle);
    }

    public void getReadlaterNews(){
        repository.getRlatter();
    }

}
