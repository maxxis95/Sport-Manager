package com.foi.air1603.sport_manager.model;

import android.util.Log;

import com.foi.air1603.sport_manager.entities.Reservation;
import com.foi.air1603.sport_manager.loaders.DataLoadedListener;
import com.foi.air1603.sport_manager.loaders.DataLoader;
import com.foi.air1603.sport_manager.loaders.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;
import com.foi.air1603.webservice.AirWebServiceResponse;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class MyReservationsInteractorImpl implements DataLoadedListener, MyReservationsInteractor {

    private DataLoader dataLoader;
    private PresenterHandler mPresenterHandler;

    public MyReservationsInteractorImpl(PresenterHandler presenterHandler) {
        mPresenterHandler = presenterHandler;
        dataLoader = new WsDataLoader();
    }

    @Override
    public void onDataLoaded(AirWebServiceResponse result) {
        mPresenterHandler.getResponseData(result);
    }

    @Override
    public void getMyReservationsObject(int userId) {
        try {
            dataLoader.loadData(this, "getData", "Reservations,Sports,Appointments.Places,Teams.Users", "Teams.Users.id", userId + "", Reservation.class, null);

        } catch (Exception ex) {
            Log.e("WebService Error: ", ex.getMessage());
        }
    }
}
