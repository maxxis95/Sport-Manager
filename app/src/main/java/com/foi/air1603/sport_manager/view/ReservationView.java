package com.foi.air1603.sport_manager.view;

import java.util.List;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public interface ReservationView {

    int getDate();

    int getIdPlace();

    void showAppointments(List<Integer> id, List<Integer> placeId, List<String> date, List<String> start, List<String> end, List<Integer> maxplayers);

    void showSports(List<Integer> id, List<String> name);

    void initializeCalendar();
}
