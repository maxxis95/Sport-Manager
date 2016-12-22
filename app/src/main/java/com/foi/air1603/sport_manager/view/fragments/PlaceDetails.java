package com.foi.air1603.sport_manager.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.presenter.PlacePresenter;
import com.foi.air1603.sport_manager.view.PlaceDetailsView;


/**
 * Created by Korisnik on 06-Dec-16.
 */

public class PlaceDetails extends android.app.Fragment implements PlaceDetailsView {

    PlacePresenter presenter;
    private TextView txtViewName;
    private TextView txtViewRadnoVrijeme;
    private TextView txtViewKontakt;
    private TextView txtViewAdresa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details_place, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String place_name = bundle.getString("place_name",null);
            String place_address = bundle.getString("place_address",null);
            String place_contact = bundle.getString("place_contact",null);
            String place_imgUrl = bundle.getString("place_imgUrl",null);
            String place_workingHoursFrom = bundle.getString("place_workingHoursFrom",null);
            String place_workingHoursTo = bundle.getString("place_workingHoursTo",null);
            String place_lat = bundle.getString("place_lat",null);
            String place_lon = bundle.getString("place_lon",null);
            showPlace(place_name, place_address, place_contact, place_imgUrl, place_workingHoursFrom, place_workingHoursTo, place_lat, place_lon);
        }






    }

    @Override
    public void showPlace(String place_name, String place_address, String place_contact, String place_imgUrl, String place_workingHoursFrom, String place_workingHoursTo, String place_lat, String place_lon) {

        txtViewAdresa = (TextView) getView().findViewById(R.id.textViewAdress);
        txtViewName = (TextView) getView().findViewById(R.id.textViewName);
        txtViewRadnoVrijeme = (TextView) getView().findViewById(R.id.textViewRadnoVrijeme);
        txtViewKontakt = (TextView) getView().findViewById(R.id.textViewKontakt);

        txtViewAdresa.setText("Adresa: " + place_address);
        txtViewName.setText(place_name);
        txtViewRadnoVrijeme.setText("Radno vrijeme: " + place_workingHoursFrom+" - " + place_workingHoursTo);
        txtViewKontakt.setText("Kontakt: " + place_contact);

    }
}
