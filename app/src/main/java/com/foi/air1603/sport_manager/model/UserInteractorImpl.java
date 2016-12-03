package com.foi.air1603.sport_manager.model;

import android.provider.Settings;

import com.foi.air1603.sport_manager.loaders.DataLoadedListener;
import com.foi.air1603.sport_manager.loaders.DataLoader;
import com.foi.air1603.sport_manager.loaders.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;

/**
 * Created by Generalko on 12.11.2016..
 */

public class UserInteractorImpl implements UserInteractor, DataLoadedListener {

    private DataLoader dataLoader;
    private PresenterHandler mPresenterHandler;
    private UserInteractor.OnLoginFinishedListener mListener;

    public UserInteractorImpl(PresenterHandler presenterHandler) {
        mPresenterHandler = presenterHandler;
        this.dataLoader = new WsDataLoader();
    }

    @Override
    public void getUserObject(OnLoginFinishedListener listener, String searchBy, String args) {

        System.out.println("----------------->3. UserInteractorImpl:getUserObject");

        mListener = listener;
        try{
            dataLoader.loadData(this, "getData", "Users", searchBy, args, User.class, null);
        }catch (Exception ex){
            mListener.onWebServiceError(ex.getMessage());
        }
    }

    @Override
    public void setUserObject(User user) {

        System.out.println("----------------->3. UserInteractorImpl:setUserObject");
       // mListener = listener;
        try{
            dataLoader.loadData(this, "setData", "Users", null, null, User.class, user);
        }catch (Exception ex){
          //  mListener.onWebServiceError(ex.getMessage());
        }
    }

    @Override
    public void onDataLoaded(Object result) {
        System.out.println("----------------->7. UserInteractorImpl:onDataLoaded");
        mPresenterHandler.getResponseData(result);
    }

}
