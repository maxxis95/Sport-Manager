package com.foi.air1603.sport_manager.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air1603.sport_manager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyReservationsFragment extends android.app.Fragment {


    public MyReservationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_reservations, container, false);
    }

}
