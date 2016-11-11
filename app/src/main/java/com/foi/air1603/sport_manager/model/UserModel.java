package com.foi.air1603.sport_manager.model;

import com.example.webservice.DataLoadedListener;
import com.example.webservice.DataLoader;
import com.example.webservice.User;
import com.example.webservice.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.RegisterPresenter;

/**
 * Created by Generalko on 10.11.2016..
 */

public class UserModel implements ModelHandler, DataLoadedListener {

    private User mUserObject;
    private DataLoader dataLoader;


    public UserModel(){

        dataLoader = new WsDataLoader();

    }

    @Override
    public void onDataLoaded(Object result) {
        System.out.println("eto me nazad u viewu");

        if (result instanceof User) {
            mUserObject = (User) result;

        }
    }


    public User getUserObject() {
        return this.mUserObject;
    }

    public void setUserObject(User user){

        dataLoader.loadData(this, "setUserById", "1", User.class);
    }
}
