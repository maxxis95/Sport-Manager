package com.foi.air1603.sport_manager.presenter;

import com.example.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.model.AppointmentInteractor;
import com.foi.air1603.sport_manager.model.AppointmentInteractorImpl;
import com.foi.air1603.sport_manager.view.ReservationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public class AppointmentPresenterImpl implements AppointmentPresenter, PresenterHandler {

    AppointmentInteractor appointmentInteractor;
    private ReservationView view;
    List<Integer> id = new ArrayList<Integer>();
    List<Integer> placeId = new ArrayList<Integer>();
    List<String> date = new ArrayList<String>();
    List<String> start = new ArrayList<String>();
    List<String> end = new ArrayList<String>();
    List<Integer> maxplayers = new ArrayList<Integer>();
    List<Appointment> appointments = null;

    private static AppointmentPresenterImpl instance;

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
        int date = view.getDate();
        int id_place = view.getIdPlace();
        String searchBy = "place_id";
        String value = id_place + "";
        appointmentInteractor.getAppointmentsObjects(this, searchBy, value);

    }

    @Override
    public void showAppointmentsForDate(Integer pickedDate) {

        System.out.println("this.showAppointmentsForDate" + this.appointments);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (final Appointment appointment : this.appointments) {
            Date dateTmp = null;
            try {
                dateTmp = dateFormat.parse(appointment.date);
                //System.out.println("pickedDate "+pickedDate+"   === datetmp " + dateTmp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long unixTime = (long) dateTmp.getTime() / 1000 + 3600; //3600 timezone fix

            if (pickedDate != unixTime) {
                continue;
            }

            id.add(appointment.getId());
            placeId.add(appointment.getPlaceId());
            date.add(appointment.getDate());
            start.add(appointment.getStart());
            end.add(appointment.getEnd());
            maxplayers.add((appointment.getMaxplayers()));

        }

        this.view.showAppointments(id, placeId, date, start, end, maxplayers);

        id.clear();
        placeId.clear();
        date.clear();
        start.clear();
        end.clear();
        maxplayers.clear();
    }

    @Override
    public void getResponseData(Object result) {
        System.out.println("----------------->8. AppointmentPresenterImpl:getResponseData");

        AirWebServiceResponse response = (AirWebServiceResponse) result;
        Type collectionType = new TypeToken<List<Appointment>>() {
        }.getType();

        this.appointments = (List<Appointment>) new Gson().fromJson(response.getData(), collectionType);


        view.initializeCalendar();


       /* for (final Appointment appointment : this.appointments) {
            id.add(appointment.getId());
            placeId.add(appointment.getPlaceId());
            date.add(appointment.getDate());
            start.add(appointment.getStart());
            end.add(appointment.getEnd());
            maxplayers.add((appointment.getMaxplayers()));

        }

        this.view.showAppointments(id, placeId, date, start, end, maxplayers);
       // appointments.clear();
        id.clear();
        placeId.clear();
        date.clear();
        start.clear();
        end.clear();
        maxplayers.clear();*/


    }
}


