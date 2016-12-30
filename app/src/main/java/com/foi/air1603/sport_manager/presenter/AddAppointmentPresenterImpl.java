package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.model.AppointmentInteractor;
import com.foi.air1603.sport_manager.model.AppointmentInteractorImpl;
import com.foi.air1603.sport_manager.view.AddAppointmentView;
import com.foi.air1603.webservice.AirWebServiceResponse;

/**
 * Created by Korisnik on 30-Dec-16.
 */

public class AddAppointmentPresenterImpl implements AddAppointmentPresenter, PresenterHandler {

    private final AddAppointmentView view;
    boolean valid = false;
    boolean appointmentStartFlag, appointmentMaxPlayer, appointmentEndFlag = true;
    Appointment appointmentModel;
    AppointmentInteractor appointmentInteractor;


    public AddAppointmentPresenterImpl(AddAppointmentView addAppointmentView) {
        this.view = addAppointmentView;
        this.appointmentInteractor = new AppointmentInteractorImpl(this);
    }


    @Override
    public void validateAppointment() {
        appointmentStartFlag = appointmentMaxPlayer = appointmentEndFlag = true;


        if (view.getAppointmentStartFromEditText().isEmpty()) {
            // view.displayError(, "Polje je obavezno");
            appointmentStartFlag = false;

        } else {

            appointmentStartFlag = true;
        }


        if (view.getAppointmentEndFromEditText().isEmpty()) {
            // view.displayError(, "Polje je obavezno");
            appointmentEndFlag = false;

        } else {

            appointmentEndFlag = true;
        }


        if (view.getMaxPlayer().isEmpty()) {
            // view.displayError(, "Polje je obavezno");
            appointmentMaxPlayer = false;

        } else {

            appointmentMaxPlayer = true;
        }
        if(appointmentMaxPlayer && appointmentEndFlag && appointmentStartFlag){
            System.out.println("----------------->2. RegisterPresenterImpl:validateUserRegister");
            appointmentInteractor.setAppointmentObject(createNewAppointmentObject());
        }
    }

    private Appointment createNewAppointmentObject() {
        Appointment appointment = new Appointment();
        appointment.placeId = view.getIdPlace();
        int dat = view.getCurrentDate();
        appointment.date = Integer.toString(dat);
        appointment.start = view.getAppointmentStartFromEditText();
        appointment.end = view.getAppointmentEndFromEditText();
        String maxply;
        maxply = view.getMaxPlayer();
        appointment.maxplayers = Integer.parseInt(maxply);

        return appointment;
    }

    @Override
    public void getResponseData(Object result) {

        AirWebServiceResponse test = (AirWebServiceResponse) result;

        view.returnResponseCode(test.getStatusCode(), test.getMessage());

    }
}
