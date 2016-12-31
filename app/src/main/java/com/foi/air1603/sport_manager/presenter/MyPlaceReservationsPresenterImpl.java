package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.model.MyPlaceReservationsInteractorImpl;
import com.foi.air1603.sport_manager.view.MyPlacesReservationView;
import com.foi.air1603.webservice.AirWebServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Generalko on 31-Dec-16.
 */

public class MyPlaceReservationsPresenterImpl implements PresenterHandler, MyPlaceReservationsPresenter {
    private MyPlacesReservationView myPlacesReservationView;
    private MyPlaceReservationsInteractorImpl myPlaceReservationInteractor;
    List<Appointment> appointmentList = null;

    public MyPlaceReservationsPresenterImpl(MyPlacesReservationView myPlacesReservationView) {
        this.myPlacesReservationView = myPlacesReservationView;
        this.myPlaceReservationInteractor = new MyPlaceReservationsInteractorImpl(this);
    }

    @Override
    public void getResponseData(Object result) {
        AirWebServiceResponse response = (AirWebServiceResponse) result;

        Type collectionTypeAppointments = new TypeToken<List<Appointment>>() {
        }.getType();
        appointmentList = (List<Appointment>) new Gson().fromJson(response.getData(), collectionTypeAppointments);

        if (appointmentList != null){
            myPlacesReservationView.showPlaceReservations(appointmentList);
        }
    }

    @Override
    public void getAllAppointmentsByPlaceId(int placeId) {
        String placeID = placeId+"";
        myPlaceReservationInteractor.getAllMyPlacesReservationsObject(this, placeID);
    }
}
