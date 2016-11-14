package com.foi.air1603.sport_manager.loaders;

import java.lang.reflect.Type;

/**
 * Created by Karlo on 9.11.2016..
 */
public abstract class DataLoader {

    protected DataLoadedListener mDataLoadedListener;

    public void callWebService(DataLoadedListener dataLoadedListener, String method, String args, Type entityType, Object data) {
        this.mDataLoadedListener = dataLoadedListener;
    }
}
