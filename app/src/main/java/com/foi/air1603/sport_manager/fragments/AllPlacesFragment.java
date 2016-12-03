package com.foi.air1603.sport_manager.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.presenter.PlacePresenter;
import com.foi.air1603.sport_manager.presenter.PlacePresenterImpl;

import com.foi.air1603.sport_manager.view.PlaceView;

/**
 * Created by Karlo on 3.12.2016..
 */

public class AllPlacesFragment extends android.app.Fragment implements PlaceView {

    PlacePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_places, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new PlacePresenterImpl(this);

        //presenter.testGettingSinglePlace();
        presenter.testGettingMultiplePlaces();
    }

    @Override
    public void showTestToast(String message) {

    }
}
