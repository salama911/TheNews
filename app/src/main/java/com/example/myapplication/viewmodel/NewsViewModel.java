package com.example.myapplication.viewmodel;

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
   MutableLiveData<ArrayList<NewsModel>>newsList=new MutableLiveData<>();

   @ViewModelInject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<NewsModel>> getNewsList() {
        return newsList;
    }

    public void getNews(){
       repository.getNews()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(result->newsList.setValue((ArrayList<NewsModel>) result)
                       ,error-> Log.e("viewmodel",error.getMessage()) );

       ///sasa
        ///sadsad

    }
}
