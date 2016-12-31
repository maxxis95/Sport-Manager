package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.entities.Reservation;
import com.foi.air1603.sport_manager.model.MyReservationsInteractor;
import com.foi.air1603.sport_manager.model.MyReservationsInteractorImpl;
import com.foi.air1603.sport_manager.view.MyReservationsView;
import com.foi.air1603.webservice.AirWebServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class MyReservationsPresenterImpl implements MyReservationsPresenter, PresenterHandler {

    MyReservationsView myReservationsView;
    MyReservationsInteractor myReservationsInteractor;

    public MyReservationsPresenterImpl(MyReservationsView reservationsView) {
        myReservationsView = reservationsView;
        myReservationsInteractor = new MyReservationsInteractorImpl(this);
    }

    @Override
    public void getResponseData(Object result) {
        System.out.println("----------------MyReservationPresenterImpl:getResponseData");
        AirWebServiceResponse response = (AirWebServiceResponse) result;

        Type collectionType = new TypeToken<List<Reservation>>() {
        }.getType();
        List<Reservation> reservationsList = (List<Reservation>) new Gson().fromJson(response.getData(), collectionType);

        myReservationsView.loadRecycleViewData(reservationsList);
    }

    @Override
    public void getUserReservationsData() {
        myReservationsInteractor.getMyReservationsObject(MainActivity.user.id);
    }
}
