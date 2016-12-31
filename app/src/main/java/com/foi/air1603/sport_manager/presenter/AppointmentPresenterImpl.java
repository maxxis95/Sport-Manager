package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.model.AppointmentInteractor;
import com.foi.air1603.sport_manager.model.AppointmentInteractorImpl;
import com.foi.air1603.sport_manager.view.ReservationView;
import com.foi.air1603.webservice.AirWebServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public class AppointmentPresenterImpl implements AppointmentPresenter, PresenterHandler {

    private static AppointmentPresenterImpl instance;
    AppointmentInteractor appointmentInteractor;
    List<Appointment> allAppointments = null;
    List<Appointment> availableAppointments = new ArrayList<>();
    private ReservationView view;

    private AppointmentPresenterImpl() {
    }

    public static AppointmentPresenterImpl getInstance() {
        if (instance == null) {
            instance = new AppointmentPresenterImpl();
        }
        return instance;
    }

    public AppointmentPresenter Init(ReservationView reservationView) {
        this.view = reservationView;
        this.appointmentInteractor = new AppointmentInteractorImpl(this);
        this.instance = this;
        return this;
    }


    @Override
    public void loadAllAppointments() {
        int id_place = view.getIdPlace();
        appointmentInteractor.getAppointmentsObjects("place_id", id_place + "");
    }

    @Override
    public void showAppointmentsForDate(Integer pickedDate) {

        System.out.println("this.showAppointmentsForDate" + this.allAppointments);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (this.allAppointments == null) {
            return;
        }

        for (final Appointment appointment : this.allAppointments) {
            Date dateTmp = null;

            try {
                dateTmp = dateFormat.parse(appointment.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long unixTime = dateTmp.getTime() / 1000 + 3600; //3600 timezone fix
            if (pickedDate != unixTime) {
                continue;
            }
            availableAppointments.add(appointment);
        }
        view.showAppointmentsForDate(availableAppointments);
        availableAppointments.clear();
    }

    @Override
    public void getResponseData(Object result) {
        System.out.println("----------------->8. AppointmentPresenterImpl:getResponseData");

        AirWebServiceResponse response = (AirWebServiceResponse) result;
        Type collectionType = new TypeToken<List<Appointment>>() {
        }.getType();

        this.allAppointments = (List<Appointment>) new Gson().fromJson(response.getData(), collectionType);

        view.initializeCalendar();
    }
}


