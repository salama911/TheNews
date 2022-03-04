package com.example.myapplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecuters {
    private static AppExecuters instance;

    public static AppExecuters getInstance(){
        if(instance==null){
            instance=new AppExecuters();

        }
        return instance;

    }

    private ScheduledExecutorService mNetworkIO= Executors.newScheduledThreadPool(1);

    public ScheduledExecutorService getmNetworkIO(){
        return mNetworkIO;

    }
}
