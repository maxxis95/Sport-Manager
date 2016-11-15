package com.foi.air1603.sport_manager.model;

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
    public void getUserObject(OnLoginFinishedListener listener, String method, String args) {
        //todo: get webservice to work to get object.
        mListener = listener;
        try{
            dataLoader.callWebService(this, null, args, User.class, null);
        }catch (Exception ex){
            mListener.onWebServiceError(ex.getMessage());
        }
    }

    @Override
    public void onDataLoaded(Object result) {
        mPresenterHandler.getResponseData(result);
    }

}
