package com.foi.air1603.sport_manager.model;

import com.example.webservice.DataLoadedListener;
import com.example.webservice.DataLoader;
import com.example.webservice.User;
import com.example.webservice.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

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

        dataLoader.callWebService(this, "setUserData", null, User.class, user);
    }

    @Override
    public void onDataLoaded(Object result) {
        System.out.println("----------------->7. UserModel:onDataLoaded");
        mPresenterHandler.getResponseData(result);

        if (result instanceof User) {
            mUserObject = (User) result;
        }
    }

    public User getUserObject() {
        return this.mUserObject;
    }


}
