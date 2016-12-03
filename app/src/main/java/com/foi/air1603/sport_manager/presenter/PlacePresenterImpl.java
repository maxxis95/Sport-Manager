package com.foi.air1603.sport_manager.presenter;

import com.example.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.model.PlaceInteractor;
import com.foi.air1603.sport_manager.model.PlaceInteractorImpl;
import com.foi.air1603.sport_manager.view.PlaceView;
import com.foi.air1603.sport_manager.view.RegisterView;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Karlo on 3.12.2016..
 */

public class PlacePresenterImpl implements PlacePresenter, PresenterHandler{

    private final PlaceView view; // TODO: Ne znam jel ovo treba uopće
    PlaceInteractor placeInteractor;

    public PlacePresenterImpl(PlaceView placeView) {
        this.view = placeView;
        this.placeInteractor = new PlaceInteractorImpl(this);

    }

    public void testGettingSinglePlace(){
        System.out.println("----------------->2. PlacePresenterImpl:constructor");
        placeInteractor.getPlaceObject(this, "id", "1");
    }
    public void testGettingMultiplePlaces(){
        System.out.println("----------------->2. PlacePresenterImpl:constructor");
        placeInteractor.getAllPlacesObjects(this);
    }

    @Override
    public void getResponseData(Object result) {
        System.out.println("----------------->8. PlacePresenterImpl:getResponseData");

        AirWebServiceResponse response = (AirWebServiceResponse) result;
        Place singlePlace = null;
        List<Place> places = null;

        try {
            singlePlace = new Gson().fromJson(response.getData(), Place.class);
        }
        catch (JsonParseException e) {
            System.out.println("[ERROR] " + e);
        }
        if(singlePlace != null) {
            System.out.println(singlePlace.getName());
        }

        try {
            Type collectionType = new TypeToken<List<Place>>(){}.getType();
            places = (List<Place>) new Gson().fromJson( response.getData() , collectionType);
        }
        catch (JsonParseException e) {
            System.out.println(e);
        }
        if(places != null)  {
            for (final Place place : places) {
                System.out.println(place.getName());
            }
        }


    }
}
