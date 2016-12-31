package com.foi.air1603.sport_manager.view.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.presenter.AppointmentPresenter;
import com.foi.air1603.sport_manager.presenter.AppointmentPresenterImpl;
import com.foi.air1603.sport_manager.presenter.SportPresenter;
import com.foi.air1603.sport_manager.presenter.SportPresenterImpl;
import com.foi.air1603.sport_manager.view.ReservationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public class ReservationFragment extends android.app.Fragment implements ReservationView {

    AppointmentPresenter appointmentPresenter;
    SportPresenter sportPresenter;
    CalendarView calendar;
    List<String> maxPlayers = new ArrayList<String>();
    private int yearGet;
    private int monthGet;
    private int dayGet;
    private int id_place;
    private int currentPickedDate;
    private Place place;
    private TextView appointmentLabel;
    private ImageView appointmentImage;
    private ImageView sportImage;
    private ImageView playersImage;
    private Spinner spinnerAppointment;
    private Spinner spinnerSport;
    private Spinner spinnerMaxPlayers;
    private Switch privateSwitch;
    private Button setAppointmentButton;
    private View view;
    private Context context;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        context = container.getContext();
        View v = inflater.inflate(R.layout.fragment_reservation, null);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String place_serialized = bundle.getString("Place");
            Place place = new Gson().fromJson(place_serialized, Place.class);
            this.place = place;
            id_place = place.id;
        }
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //appointmentPresenter = new AppointmentPresenterImpl(this);
        appointmentPresenter = AppointmentPresenterImpl.getInstance().Init(this);
        appointmentPresenter.loadAllAppointments();

        this.view = view;
        calendar = (CalendarView) view.findViewById(R.id.calendarViewReservation);

        // sets the first day of week according to Calendar.
        calendar.setFirstDayOfWeek(2);

        sportPresenter = new SportPresenterImpl(this);

        spinnerAppointment = (Spinner) view.findViewById(R.id.spinnerAppointments);
        spinnerAppointment.setVisibility(View.GONE);
        appointmentLabel = (TextView) view.findViewById(R.id.tvReservationAppointmentLabel);
        appointmentLabel.setVisibility(View.GONE);
        appointmentImage = (ImageView) view.findViewById(R.id.reservationAppointmentImage);
        appointmentImage.setVisibility(View.GONE);
        sportImage = (ImageView) view.findViewById(R.id.reservationSportImage);
        sportImage.setVisibility(View.GONE);
        playersImage = (ImageView) view.findViewById(R.id.reservationPlayersImage);
        playersImage.setVisibility(View.GONE);
        privateSwitch = (Switch) view.findViewById(R.id.switchAppointmentPrivate);
        privateSwitch.setVisibility(View.GONE);
        setAppointmentButton = (Button) view.findViewById(R.id.buttonSetAppointment);
        setAppointmentButton.setVisibility(View.GONE);

        setAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new InviteFriendsFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        spinnerAppointment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerMaxPlayers.setVisibility(View.VISIBLE);
                spinnerSport.setVisibility(View.VISIBLE);
                appointmentLabel.setVisibility(View.VISIBLE);
                appointmentImage.setVisibility(View.VISIBLE);
                sportImage.setVisibility(View.VISIBLE);
                sportImage.setVisibility(View.VISIBLE);
                playersImage.setVisibility(View.VISIBLE);
                privateSwitch.setVisibility(View.VISIBLE);
                setAppointmentButton.setVisibility(View.VISIBLE);

                int max = Integer.parseInt(maxPlayers.get(position));

                List<Integer> maxPly = new ArrayList<Integer>();

                for (int i = 1; i <= max; i++) {
                    maxPly.add(i);
                }

                ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, maxPly);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMaxPlayers.setAdapter(dataAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinnerSport = (Spinner) view.findViewById(R.id.spinnerSports);
        spinnerSport.setVisibility(View.GONE);
        spinnerMaxPlayers = (Spinner) view.findViewById(R.id.spinnerPlayers);
        spinnerMaxPlayers.setVisibility(View.GONE);
        sportPresenter.getMultipleSports();
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initializeCalendar() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        currentPickedDate = (int) (c.getTimeInMillis() / 1000); //Today

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                //date in unix
                yearGet = year;
                monthGet = month;
                dayGet = day;

                if (currentPickedDate == getDate()) {
                    return;
                }

                maxPlayers.clear();

                spinnerAppointment.setVisibility(View.GONE);
                spinnerSport.setVisibility(View.GONE);
                spinnerMaxPlayers.setVisibility(View.GONE);
                appointmentLabel.setVisibility(View.GONE);
                appointmentImage.setVisibility(View.GONE);
                sportImage.setVisibility(View.GONE);
                sportImage.setVisibility(View.GONE);
                playersImage.setVisibility(View.GONE);
                privateSwitch.setVisibility(View.GONE);
                setAppointmentButton.setVisibility(View.GONE);

                currentPickedDate = getDate();
                appointmentPresenter.showAppointmentsForDate(getDate());
            }

        });
        appointmentPresenter.showAppointmentsForDate(currentPickedDate);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, yearGet);
        c.set(Calendar.MONTH, monthGet);
        c.set(Calendar.DAY_OF_MONTH, dayGet);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return (int) (c.getTimeInMillis() / 1000L);
    }

    @Override
    public int getIdPlace() {
        return id_place;
    }


    @Override
    public void showAppointmentsForDate(List<Appointment> appointments) {
        if (appointments.size() == 0) {
            spinnerAppointment.setVisibility(View.GONE);
            spinnerSport.setVisibility(View.GONE);
            appointmentLabel.setVisibility(View.GONE);
            appointmentImage.setVisibility(View.GONE);
            sportImage.setVisibility(View.GONE);
            sportImage.setVisibility(View.GONE);
            playersImage.setVisibility(View.GONE);
            privateSwitch.setVisibility(View.GONE);
            setAppointmentButton.setVisibility(View.GONE);
            return;
        }

        spinnerAppointment.setVisibility(View.VISIBLE);
        spinnerSport.setVisibility(View.VISIBLE);
        appointmentLabel.setVisibility(View.VISIBLE);
        appointmentImage.setVisibility(View.VISIBLE);
        sportImage.setVisibility(View.VISIBLE);
        sportImage.setVisibility(View.VISIBLE);
        playersImage.setVisibility(View.VISIBLE);
        privateSwitch.setVisibility(View.VISIBLE);
        setAppointmentButton.setVisibility(View.VISIBLE);

        List<String> spinnerAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            spinnerAppointments.add(appointment.start + "-" + appointment.end);
            maxPlayers.add(appointment.maxplayers.toString());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerAppointments);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAppointment.setAdapter(dataAdapter);
    }

    /*@Override
    public void showAppointments(List<Integer> id, List<Integer> placeId, List<String> date, List<String> start, List<String> end, List<Integer> maxplayers) {

        if (id.size() == 0) {
            spinnerAppointment.setVisibility(View.GONE);
            spinnerSport.setVisibility(View.GONE);
            appointmentLabel.setVisibility(View.GONE);
            appointmentImage.setVisibility(View.GONE);
            sportImage.setVisibility(View.GONE);
            sportImage.setVisibility(View.GONE);
            playersImage.setVisibility(View.GONE);
            privateSwitch.setVisibility(View.GONE);
            setAppointmentButton.setVisibility(View.GONE);
            return;
        }

        spinnerAppointment.setVisibility(View.VISIBLE);
        spinnerSport.setVisibility(View.VISIBLE);
        appointmentLabel.setVisibility(View.VISIBLE);
        appointmentImage.setVisibility(View.VISIBLE);
        sportImage.setVisibility(View.VISIBLE);
        sportImage.setVisibility(View.VISIBLE);
        playersImage.setVisibility(View.VISIBLE);
        privateSwitch.setVisibility(View.VISIBLE);
        setAppointmentButton.setVisibility(View.VISIBLE);

        List<String> appointments = new ArrayList<>();

        for (int i = 0; i < start.size(); i++) {
            appointments.add(start.get(i) + "-" + end.get(i));
            maxPlayers.add(maxplayers.get(i).toString());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, appointments);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAppointment.setAdapter(dataAdapter);
    }
*/

    @Override
    public void showSports(List<Integer> id, List<String> name) {

        List<String> sports = new ArrayList<String>();
        for (int i = 0; i < id.size(); i++) {
            sports.add(name.get(i));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, sports);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSport.setAdapter(dataAdapter);
    }


}