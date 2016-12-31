package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.model.MyPlaceInteractor;
import com.foi.air1603.sport_manager.model.MyPlaceInteractorImpl;
import com.foi.air1603.sport_manager.view.MyPlacesReservationView;
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
    List<Appointment> appointmentList = null;
    private MyPlacesView myPlacesView;
    private MyPlacesReservationView myPlacesReservationView;

    public MyPlacePresenterImpl(MyPlacesView myPlacesView) {
        this.myPlacesView = myPlacesView;
        this.myPlaceInteractor = new MyPlaceInteractorImpl(this);
    }
    public MyPlacePresenterImpl(MyPlacesReservationView myPlacesReservationView) {
        this.myPlacesReservationView = myPlacesReservationView;
        this.myPlaceInteractor = new MyPlaceInteractorImpl(this);
    }

    @Override
    public void gettingMultipleMyPlaces(int id) {
        String searchBy = "user_id";
        String value = id + "";
        myPlaceInteractor.getAllMyPlacesObjects(this, searchBy, value);
        myPlaceInteractor.getAllMyPlacesReservationsObject(this, searchBy, value);

    }

    @Override
    public void getResponseData(Object result) {
        AirWebServiceResponse response = (AirWebServiceResponse) result;

        Type collectionType = new TypeToken<List<Place>>() {
        }.getType();
        Type collectionTypeAppointments = new TypeToken<List<Appointment>>() {
        }.getType();
        places = (List<Place>) new Gson().fromJson(response.getData(), collectionType);
        appointmentList = (List<Appointment>) new Gson().fromJson(response.getData(), collectionTypeAppointments);
        if (places != null) {
            this.myPlacesView.showMyPlaces(places);
        }
        if (appointmentList != null){
            myPlacesReservationView.showPlaceReservations(appointmentList);
        }

    }
}
