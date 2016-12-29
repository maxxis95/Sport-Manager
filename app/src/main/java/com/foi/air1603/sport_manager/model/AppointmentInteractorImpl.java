package com.foi.air1603.sport_manager.model;

import com.foi.air1603.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.loaders.DataLoadedListener;
import com.foi.air1603.sport_manager.loaders.DataLoader;
import com.foi.air1603.sport_manager.loaders.WsDataLoader;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public class AppointmentInteractorImpl implements AppointmentInteractor, DataLoadedListener {

    private DataLoader dataLoader;
    private PresenterHandler mPresenterHandler;
    private AppointmentInteractor mListener;

    public AppointmentInteractorImpl(PresenterHandler presenterHandler) {
        mPresenterHandler = presenterHandler;
        this.dataLoader = new WsDataLoader();
    }

    @Override
    public void getAppointmentsObjects(Object listener, String searchBy, String value) {
        dataLoader.loadData(this, "getData", "Appointments", searchBy, value, Appointment.class, null);
    }

    @Override
    public void onDataLoaded(AirWebServiceResponse result) {
        mPresenterHandler.getResponseData(result);
    }

}
