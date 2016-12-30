package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.helper.enums.AddAppointmentViewEnums;
import com.foi.air1603.sport_manager.model.AppointmentInteractor;
import com.foi.air1603.sport_manager.model.AppointmentInteractorImpl;
import com.foi.air1603.sport_manager.view.AddAppointmentView;
import com.foi.air1603.webservice.AirWebServiceResponse;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Korisnik on 30-Dec-16.
 */

public class AddAppointmentPresenterImpl implements AddAppointmentPresenter, PresenterHandler {

    private final AddAppointmentView view;
    Boolean checkTimeStart, checkTimeEnd, checkMaxPlayers, checkPlayersSize = true;
    Appointment appointmentModel;
    AppointmentInteractor appointmentInteractor;


    public AddAppointmentPresenterImpl(AddAppointmentView addAppointmentView) {
        this.view = addAppointmentView;
        this.appointmentInteractor = new AppointmentInteractorImpl(this);
    }


    @Override
    public void validateAppointment() {
        if (view.getAppointmentStartFromEditText().isEmpty()) {
            view.displayError(AddAppointmentViewEnums.AppointmentTimeStart, "Polje je obavezno");
            checkTimeStart = false;
        }
        if (view.getAppointmentEndFromEditText().isEmpty()) {
            view.displayError(AddAppointmentViewEnums.AppointmentTimeEnd, "Polje je obavezno");
            checkTimeEnd = false;
        }
        if (view.getMaxPlayer().isEmpty()) {
            view.displayError(AddAppointmentViewEnums.MaxPlayers, "Polje je obavezno");
            checkMaxPlayers = false;
        }
        if(Integer.parseInt(view.getMaxPlayer()) < 1){
            view.displayError(AddAppointmentViewEnums.MaxPlayers, "Mora biti barem jedan sudionik");
            checkPlayersSize = false;
        }

        if(checkTimeStart && checkTimeEnd && checkMaxPlayers && checkPlayersSize){
            view.removeError(AddAppointmentViewEnums.AppointmentTimeStart);
            view.removeError(AddAppointmentViewEnums.AppointmentTimeEnd);
            view.removeError(AddAppointmentViewEnums.MaxPlayers);
        }
    }

    private Appointment createNewAppointmentObject() {
        Appointment appointment = new Appointment();
        appointment.placeId = view.getIdPlace();
        int dat = view.getCurrentDate();

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        appointment.date = sqlDate.toString();
        System.out.println("---------------"+sqlDate.toString());
        appointment.start = view.getAppointmentStartFromEditText();
        appointment.end = view.getAppointmentEndFromEditText();
        String maxPly = view.getMaxPlayer();
        appointment.maxplayers = Integer.parseInt(maxPly);

        return appointment;
    }

    @Override
    public void getResponseData(Object result) {

        AirWebServiceResponse test = (AirWebServiceResponse) result;

        view.returnResponseCode(test.getStatusCode(), test.getMessage());

    }
}
