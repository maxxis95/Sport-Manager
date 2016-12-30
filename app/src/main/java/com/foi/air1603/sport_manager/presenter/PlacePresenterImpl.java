package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.model.PlaceInteractor;
import com.foi.air1603.sport_manager.model.PlaceInteractorImpl;
import com.foi.air1603.sport_manager.view.PlaceView;
import com.foi.air1603.webservice.AirWebServiceResponse;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karlo on 3.12.2016..
 */

public class PlacePresenterImpl implements PlacePresenter, PresenterHandler {

    private static PlacePresenterImpl instance;
    PlaceInteractor placeInteractor;
    List<Place> places = null;
    private PlaceView view; // TODO: Ne znam jel ovo treba uopće

    private PlacePresenterImpl() {
    }

    // Implementacija singletona da se ne skidaju placesi kod svakog otvaranja fragmenta
    public static PlacePresenterImpl getInstance() {
        if (instance == null) {
            instance = new PlacePresenterImpl();
        }
        return instance;
    }

    public PlacePresenter Init(PlaceView placeView) {
        this.view = placeView;
        this.placeInteractor = new PlaceInteractorImpl(this);
        this.instance = this;
        return this;
    }

    public void testGettingSinglePlace() {
        System.out.println("----------------->2. PlacePresenterImpl:testGettingSinglePlace");
        placeInteractor.getPlaceObject(this, "id", "1");
    }

    public void testGettingMultiplePlaces() {
        System.out.println("----------------->2. PlacePresenterImpl:testGettingMultiplePlaces");

        if (this.places == null) {
            placeInteractor.getAllPlacesObjects(this);
        } else {
            getResponseData(places);
        }
    }

    @Override
    public void getResponseData(Object result) {

        System.out.println("----------------->8. PlacePresenterImpl:getResponseData");
        Boolean placesAlreadyLoaded = false;

        if (result.getClass() == ArrayList.class && ((ArrayList) result).size() > 1) {
            placesAlreadyLoaded = true;
        }

        if (!placesAlreadyLoaded) {
            AirWebServiceResponse response = (AirWebServiceResponse) result;

            System.out.println(((AirWebServiceResponse) result).getData());
            try {
                Type collectionType = new TypeToken<List<Place>>() {
                }.getType();
                places = (List<Place>) new Gson().fromJson(response.getData(), collectionType);
            } catch (JsonParseException e) {
                System.out.println("[ERROR] " + e);
            }
        }
        if (places != null) {
            view.showAllPlaces(places);
        }
    }
}
