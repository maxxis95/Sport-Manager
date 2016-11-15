package com.foi.air1603.sport_manager.model;

import com.foi.air1603.sport_manager.loaders.DataLoadedListener;
import com.foi.air1603.sport_manager.loaders.DataLoader;
import com.foi.air1603.sport_manager.loaders.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;

/**
 * Created by Generalko on 10.11.2016..
 */

public class UserModel implements ModelHandler, DataLoadedListener {

    private User mUserObject;
    private DataLoader dataLoader;
    private PresenterHandler mPresenterHandler;


    public UserModel(PresenterHandler presenterHandler){

        mPresenterHandler = presenterHandler;
        dataLoader = new WsDataLoader();

    }

    public void setUserObject(User user){
        System.out.println("----------------->3. UserModel:setUserObject");

        dataLoader.loadData(this, "setUserData", null, User.class, user);
    }

    @Override
    public void onDataLoaded(Object result) {
        System.out.println("----------------->7. UserModel:onDataLoaded");
        mPresenterHandler.getResponseData(result);

        if (result instanceof User) {
            mUserObject = (User) result;
        }
    }
}
