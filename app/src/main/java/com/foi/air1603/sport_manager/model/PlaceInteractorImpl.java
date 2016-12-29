package com.foi.air1603.sport_manager.model;

import com.foi.air1603.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.loaders.DataLoadedListener;
import com.foi.air1603.sport_manager.loaders.DataLoader;
import com.foi.air1603.sport_manager.loaders.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PlacePresenterImpl;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Karlo on 3.12.2016..
 */

public class PlaceInteractorImpl implements PlaceInteractor, DataLoadedListener {

    private DataLoader dataLoader;
    private PresenterHandler mPresenterHandler;
    private PlaceInteractor mListener;


    public PlaceInteractorImpl(PresenterHandler mPresenterHandler) {
        this.mPresenterHandler = mPresenterHandler;
        this.dataLoader = new WsDataLoader();
    }



    @Override
    public void getPlaceObject(Object listener, String searchBy, String value) {
        System.out.println("----------------->3. PlaceInteractorImpl:getPlaceObject");

        dataLoader.loadData(this, "getData", "Places", searchBy, value, Place.class, null);
    }

    @Override
    public void getAllPlacesObjects(Object listener) {
        System.out.println("----------------->3. PlaceInteractorImpl:getAllPlacesObjects");

        dataLoader.loadData(this, "getData", "Places", null, null, Place.class, null);
    }

    @Override
    public void onDataLoaded(AirWebServiceResponse result) {
        System.out.println("----------------->7. PlaceInteractorImpl:onDataLoaded");
        mPresenterHandler.getResponseData(result);
    }

}
