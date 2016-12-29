package com.foi.air1603.sport_manager.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.view.PlaceReservationView;

/**
 * Created by Korisnik on 29-Dec-16.
 */

public class PlaceReservationFragment extends Fragment implements PlaceReservationView {

    private int id_place;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_place_reservation, null);
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id_place = bundle.getInt("place_id");
            System.out.println("idd o nplace reservation fragment:  "+ id_place);
        }
    }
}
