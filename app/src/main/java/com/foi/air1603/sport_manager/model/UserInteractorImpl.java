package com.foi.air1603.sport_manager.model;

import android.net.Uri;

import com.example.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.loaders.DataLoadedListener;
import com.foi.air1603.sport_manager.loaders.DataLoader;
import com.foi.air1603.sport_manager.loaders.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;
import com.google.gson.Gson;

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
    public void getUserObject(OnLoginFinishedListener listener, String searchBy, String value) {

        System.out.println("----------------->3. UserInteractorImpl:getUserObject");

        mListener = listener;
        try{
            dataLoader.loadData(this, "getData", "Users", searchBy, value, User.class, null);
        }catch (Exception ex){
            mListener.onWebServiceError(ex.getMessage());
        }
    }

    public void changeUserPicture(String fileUri, Integer user_id){
        System.out.println("----------------->3. UserInteractorImpl:changeUserPicture");
        dataLoader.uploadFile(this, fileUri, user_id);

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
    public void onDataLoaded(AirWebServiceResponse result) {
        System.out.println("----------------->7. UserInteractorImpl:onDataLoaded");

        mPresenterHandler.getResponseData(result);
    }

}
