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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karlo on 3.12.2016..
 */

public class PlacePresenterImpl implements PlacePresenter, PresenterHandler{

    private final PlaceView view; // TODO: Ne znam jel ovo treba uopće
    PlaceInteractor placeInteractor;
    List<String> name = new ArrayList<String>();
    List<String> address = new ArrayList<String>();
    List<String> contact = new ArrayList<String>();
    List<Time> workingHoursFrom = new ArrayList<Time>();
    List<Time> workingHoursTo = new ArrayList<Time>();
    List<String> imgUrl = new ArrayList<String>();
    List<Integer> lat = new ArrayList<Integer>();
    List<Integer> lon = new ArrayList<Integer>();



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
                name.add(place.getName());
                address.add(place.getAddress());
                contact.add(place.getContact());
                imgUrl.add(place.getImgUrl());
                workingHoursFrom.add(place.getWorkingHoursFrom());
                workingHoursTo.add(place.getWorkingHoursTo());
                lat.add(place.getLat());
                lon.add(place.getLon());
                System.out.println(place.getAddress());
                System.out.println(place.getWorkingHoursTo());
                System.out.println(place.getContact());
                System.out.println(place.getWorkingHoursTo());
                System.out.println(place.getImgUrl());
                System.out.println(place.getLat());
                System.out.println(place.getLon());
            }
            view.showTestToast(name, address, contact, imgUrl, workingHoursFrom, workingHoursTo,  lat, lon);
        }


    }
}
