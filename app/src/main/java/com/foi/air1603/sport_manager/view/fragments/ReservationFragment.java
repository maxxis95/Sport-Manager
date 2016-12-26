package com.foi.air1603.sport_manager.view.fragments;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.presenter.AppointmentPresenter;
import com.foi.air1603.sport_manager.presenter.AppointmentPresenterImpl;
import com.foi.air1603.sport_manager.view.ReservationView;

import java.util.List;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public class ReservationFragment extends android.app.Fragment implements ReservationView {

    AppointmentPresenter presenter;
    CalendarView calendar;
    private int yearGet;
    private int monthGet;
    private int dayGet;
    private int id_place;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.fragment_reservation, null);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id_place = bundle.getInt("place_id");
        }
        initializeCalendar(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       presenter = new AppointmentPresenterImpl(this);

    }

    public void initializeCalendar(View view) {

        calendar = (CalendarView) view.findViewById(R.id.calendarView2);
        // sets the first day of week according to Calendar.
        calendar.setFirstDayOfWeek(2);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                //date in unix
                yearGet = year;
                monthGet = month;
                dayGet = day;

                int date = getDate();

                presenter.getMultipleAppointments();

                System.out.println(date);


            }

        });

    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getDate() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, yearGet);
        c.set(Calendar.MONTH, monthGet);
        c.set(Calendar.DAY_OF_MONTH, dayGet);
        return (int) (c.getTimeInMillis() / 1000L);
    }


    @Override
    public int getIdPlace() {
        return id_place;
    }

    @Override
    public void showAppointments(List<Integer> id, List<Integer> placeId, List<String> date, List<String> start, List<String> end) {


    }

}




