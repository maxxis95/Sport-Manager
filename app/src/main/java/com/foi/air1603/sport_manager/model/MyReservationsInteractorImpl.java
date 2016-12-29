package com.foi.air1603.sport_manager.model;

import android.util.Log;

import com.example.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.entities.Reservations;
import com.foi.air1603.sport_manager.loaders.DataLoadedListener;
import com.foi.air1603.sport_manager.loaders.DataLoader;
import com.foi.air1603.sport_manager.loaders.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;

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
        try{
            System.out.println("");

            dataLoader.loadData(this, "getData", "Reservations,Sports", null, null, Reservations.class, null);
           // dataLoader.loadData(this, "getData", "Reservations,Sports", "Teams.Users.user_id", userId+"", Reservations.class, null);
        }catch (Exception ex){
            Log.e("WebService Error: ",ex.getMessage());
        }
    }
}
