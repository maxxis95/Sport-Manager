package com.foi.air1603.sport_manager.model;

import com.example.webservice.AirWebServiceResponse;
import com.example.webservice.DataLoadedListener;
import com.example.webservice.DataLoader;
import com.example.webservice.User;
import com.example.webservice.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;
import com.google.gson.Gson;

/**
 * Created by Generalko on 12.11.2016..
 */

public class UserInteractorImpl implements UserInteractor, DataLoadedListener {

    private User mUserObject;
    private DataLoader dataLoader;
    private PresenterHandler mPresenterHandler;
    private OnLoginFinishedListener mListener;

    public UserInteractorImpl(PresenterHandler presenterHandler) {
        mPresenterHandler = presenterHandler;
        this.dataLoader = new WsDataLoader();
    }


    @Override
    public void getUserObject(OnLoginFinishedListener listener, String method, String args) {
        //todo: get webservice to work to get object.
        mListener = listener;
        dataLoader.callWebService(this, method, args, User.class, null);


    }

    /**
     * U onDataLoaded se vraćaju rezultati s webservisa tipa AirWebServiceResponse
     * @param result
     */
    @Override
    public void onDataLoaded(Object result) {
        AirWebServiceResponse response = (AirWebServiceResponse) result;

        if(response.getStatusCode() != 200){
            //todo: ako nije status code 200 onda znaći da ne postoji user takav u bazi
            mListener.onUsernameError();
        } else {

        }



        if(mUserObject.username.isEmpty()){
            mListener.onUsernameError();

        }else if (mUserObject.password.isEmpty()){
            mListener.onPasswordError();
        }

        /**
         * Zadnja linija vraća podatke nazad na LoginPresenterImp
         */
        mPresenterHandler.getResponseData(result);
    }

}
