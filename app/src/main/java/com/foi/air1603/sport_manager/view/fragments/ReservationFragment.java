package com.foi.air1603.sport_manager.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public class ReservationFragment extends android.app.Fragment {


    CalendarView calendar;
    int id_place;
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


    public void initializeCalendar(View view) {
        calendar = (CalendarView) view.findViewById(R.id.calendarView2);

        // sets the first day of week according to Calendar.

        calendar.setFirstDayOfWeek(2);

        //sets the listener to be notified upon selected date change.

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                month++;
                System.out.println("tessssssssssss:"+id_place);
                System.out.println(day + "/" + month + "/" + year);

            }

        });
    }
}




