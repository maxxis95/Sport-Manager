package com.example.webservice;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Karlo on 9.11.2016..
 */
public abstract class DataLoader {


   // public Object result;

    protected DataLoadedListener mDataLoadedListener;

    public void callWebService(DataLoadedListener dataLoadedListener, String method, String args, Type entityType, Object data) {
        this.mDataLoadedListener = dataLoadedListener;
    }

    /*public boolean dataLoaded(){
        if(result == null){
            return false;
        }
        else{
            return true;
        }
    }*/

}
