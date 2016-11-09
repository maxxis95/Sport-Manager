package com.example.webservice;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Karlo on 9.11.2016..
 */
public abstract class DataLoader {


    public User user;

    protected DataLoadedListener mDataLoadedListener;

    public void loadData(DataLoadedListener dataLoadedListener, String method, String args, Type entityType) {
        this.mDataLoadedListener = dataLoadedListener;
    }

    public boolean dataLoaded(){
        if(user == null){
            return false;
        }
        else{
            return true;
        }
    }

}
