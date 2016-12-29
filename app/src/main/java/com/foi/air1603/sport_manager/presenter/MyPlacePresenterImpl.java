package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.model.MyPlaceInteractor;
import com.foi.air1603.sport_manager.model.MyPlaceInteractorImpl;
import com.foi.air1603.sport_manager.view.MyPlacesView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Korisnik on 28-Dec-16.
 */

public class MyPlacePresenterImpl implements MyPlacePresenter, PresenterHandler {

    private MyPlacesView view;
    MyPlaceInteractor myPlaceInteractor;
    List<Integer> id = new ArrayList<Integer>();
    List<String> name = new ArrayList<String>();
    List<String> address = new ArrayList<String>();
    List<String> contact = new ArrayList<String>();
    List<String> workingHoursFrom = new ArrayList<String>();
    List<String> workingHoursTo = new ArrayList<String>();
    List<String> img = new ArrayList<String>();
    List<String> lat = new ArrayList<String>();
    List<String> lon = new ArrayList<String>();
    List<Place> places = null;


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

        if (this.places != null) {
            for (final Place place : this.places) {

                id.add(place.getId());
                name.add(place.getName());
                address.add(place.getAddress());
                contact.add(place.getContact());
                img.add(place.getImg());
                workingHoursFrom.add(place.getWorkingHoursFrom());
                workingHoursTo.add(place.getWorkingHoursTo());
                lat.add(place.getLat());
                lon.add(place.getLon());
                System.out.println("moji objekit::::"+place.getId()+place.getName()+place.getContact()+place.getAddress());

            }
            this.view.showMyPlaces(id, name, address, contact, img, workingHoursFrom, workingHoursTo, lat, lon);


        }


    }
}
