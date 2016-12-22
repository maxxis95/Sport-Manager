package com.foi.air1603.sport_manager.view.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.adapters.PlaceRecycleAdapter;
import com.foi.air1603.sport_manager.presenter.PlacePresenter;
import com.foi.air1603.sport_manager.presenter.PlacePresenterImpl;

import com.foi.air1603.sport_manager.view.PlaceView;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by Karlo on 3.12.2016..
 */

public class AllPlacesFragment extends android.app.Fragment implements PlaceView {

    private RecyclerView recyclerView;
    PlacePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_places, null);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new PlacePresenterImpl(this);

        //presenter.testGettingSinglePlace();
        presenter.testGettingMultiplePlaces();
        //recycler
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showTestToast(List<String> name, List<String> address, List<String> contact, List<String> imgUrl , List<String> workingHoursFrom, List<String> workingHoursTo, List<String> lat, List<String> lon) {

        recyclerView.setAdapter(new PlaceRecycleAdapter(name, address, contact,imgUrl, workingHoursFrom,workingHoursTo,lat,lon, this));
    }

    @Override
    public void changeFragment(String place_name, String place_address, String place_contact, String place_imgUrl, String place_workingHoursFrom, String place_workingHoursTo, String place_lat, String place_lon) {

        // Create new fragment and transaction
        Fragment newFragment = new PlaceDetails();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("place_name", place_name);
        bundle.putString("place_address", place_address);
        bundle.putString("place_contact", place_contact);
        bundle.putString("place_imgUrl", place_imgUrl);
        bundle.putString("place_workingHoursFrom", place_workingHoursFrom);
        bundle.putString("place_workingHoursTo", place_workingHoursTo);
        bundle.putString("place_lat", place_lat);
        bundle.putString("place_lon", place_lon);
        newFragment.setArguments(bundle);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }
}