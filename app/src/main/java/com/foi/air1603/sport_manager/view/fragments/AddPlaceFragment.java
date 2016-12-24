package com.foi.air1603.sport_manager.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.helper.enums.AddPlaceViewEnums;
import com.foi.air1603.sport_manager.view.AddPlaceView;

/**
 * Created by marko on 24.12.2016..
 */

public class AddPlaceFragment extends Fragment implements AddPlaceView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_place, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public String getPlaceNameFromEditText() {
        return null;
    }

    @Override
    public String getPlaceAddressFromEditText() {
        return null;
    }

    @Override
    public String getPlaceNumberFromEditText() {
        return null;
    }

    @Override
    public String getPlaceWHStartFromEditText() {
        return null;
    }

    @Override
    public String getPlaceWHStopFromEditText() {
        return null;
    }

    @Override
    public String getPlaceImageFromEditText() {
        return null;
    }

    @Override
    public void displayError(AddPlaceViewEnums textView, String message) {

    }

    @Override
    public void removeError(AddPlaceViewEnums textView) {

    }

    @Override
    public void returnResponseCode(int statusCode, String message) {

    }
}
