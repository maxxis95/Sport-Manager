package com.foi.air1603.sport_manager.fragments;

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

/**
 * Created by Karlo on 3.12.2016..
 */

public class AllPlacesFragment extends android.app.Fragment implements PlaceView {
    private RecyclerView recyclerView;

    PlacePresenter presenter;

    private String[] name = {"TTTTTS", "Graberje", "Arena",
            "2.gimnazija", "1.gimnazija"};

    private String[] address = {"ivana markovica 22", "Graberje 30", "ivana severa 18",
            "halerova aleja 12", "Petra Preradovića 14"};

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
        recyclerView.setAdapter(new PlaceRecycleAdapter(name, address, this));
    }

    @Override
    public void showTestToast(String message) {

    }
}
