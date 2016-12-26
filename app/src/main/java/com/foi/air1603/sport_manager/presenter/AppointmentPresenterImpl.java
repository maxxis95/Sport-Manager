package com.foi.air1603.sport_manager.presenter;

import com.example.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.model.AppointmentInteractor;
import com.foi.air1603.sport_manager.model.AppointmentInteractorImpl;
import com.foi.air1603.sport_manager.view.ReservationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public class AppointmentPresenterImpl implements AppointmentPresenter, PresenterHandler {

    AppointmentInteractor appointmentInteractor;
    private final ReservationView view;
    List<Integer> id = new ArrayList<Integer>();
    List<Integer> placeId = new ArrayList<Integer>();
    List<String> date = new ArrayList<String>();
    List<String> start = new ArrayList<String>();
    List<String> end = new ArrayList<String>();
    List<Appointment> appointments = null;
    private Appointment appointmentss;


    public AppointmentPresenterImpl(ReservationView reservationView) {
        this.view = reservationView;
        this.appointmentInteractor = new AppointmentInteractorImpl(this);
    }


    @Override
    public void getMultipleAppointments() {
        int date = view.getDate();
        int id_place = view.getIdPlace();
        String searchBy = "place_id;date";
        String value = id_place+";"+date;
        appointmentInteractor.getAppointmentsObjects(this, searchBy, value);

    }

    @Override
    public void getResponseData(Object result) {
        System.out.println("----------------->8. LoginPresenterImpl:getResponseData");

        AirWebServiceResponse response = (AirWebServiceResponse) result;
        appointmentss = new Gson().fromJson(response.getData(), Appointment.class);
       // Type collectionType = new TypeToken<List<Appointment>>(){}.getType();
        //this.appointments = (List<Appointment>) new Gson().fromJson(response.getData() , collectionType);

        if(appointmentss !=null){
            System.out.println("eeeeradi"+appointmentss.getStart());

        }

       /* if(this.appointments != null)  {
            for (final Appointment appointment : this.appointments) {
                id.add(appointment.getId());
                placeId.add(appointment.getPlaceId());
                date.add(appointment.getDate());
                System.out.println("id:"+appointment.getId()+"start:::"+appointment.getStart());
                start.add(appointment.getStart());
                end.add(appointment.getEnd());

            }*/
          // this.view.showAppointments(id, placeId, date, start, end);
    }

    }



