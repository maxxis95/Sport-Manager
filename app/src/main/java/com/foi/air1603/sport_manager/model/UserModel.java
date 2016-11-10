package com.foi.air1603.sport_manager.model;

import com.example.webservice.DataLoadedListener;
import com.example.webservice.DataLoader;
import com.example.webservice.User;
import com.example.webservice.WsDataLoader;

/**
 * Created by Generalko on 10.11.2016..
 */

public class UserModel implements ModelHandler, DataLoadedListener {

    private User mUserObject;

    public UserModel(){
        callWsDataLoader();
    }

    @Override
    public void onDataLoaded(Object result) {
        System.out.println("eto me nazad u viewu");

        if (result instanceof User) {
            mUserObject = (User) result;
        }
    }

    @Override
    public User getUserObject() {
        return this.mUserObject;
    }

    private void callWsDataLoader(){
        DataLoader dataLoader;
        dataLoader = new WsDataLoader();
        dataLoader.loadData(this, "getUserById", "1", User.class);
    }
}
