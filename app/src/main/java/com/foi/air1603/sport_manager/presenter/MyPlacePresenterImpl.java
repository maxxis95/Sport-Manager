package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.model.MyPlaceInteractor;
import com.foi.air1603.sport_manager.model.MyPlaceInteractorImpl;
import com.foi.air1603.sport_manager.view.MyPlacesView;
import com.foi.air1603.webservice.AirWebServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Korisnik on 28-Dec-16.
 */

public class MyPlacePresenterImpl implements MyPlacePresenter, PresenterHandler {

    MyPlaceInteractor myPlaceInteractor;
    List<Place> places = null;
    private MyPlacesView view;

    public MyPlacePresenterImpl(MyPlacesView myPlacesView) {
        this.view = myPlacesView;
        this.myPlaceInteractor = new MyPlaceInteractorImpl(this);
    }

    @Override
    public void gettingMultipleMyPlaces(int id) {
        String searchBy = "user_id";
        String value = id + "";
        myPlaceInteractor.getAllMyPlacesObjects(this, searchBy, value);

    }

    @Override
    public void getResponseData(Object result) {
        AirWebServiceResponse response = (AirWebServiceResponse) result;

        Type collectionType = new TypeToken<List<Place>>() {
        }.getType();
        places = (List<Place>) new Gson().fromJson(response.getData(), collectionType);

        if (places != null) {
            this.view.showMyPlaces(places);
        }
    }
}
