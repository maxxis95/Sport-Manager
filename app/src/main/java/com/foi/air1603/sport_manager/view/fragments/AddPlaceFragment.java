package com.foi.air1603.sport_manager.view.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.helper.enums.AddPlaceViewEnums;
import com.foi.air1603.sport_manager.presenter.AddPlacePresenter;
import com.foi.air1603.sport_manager.presenter.AddPlacePresenterImpl;
import com.foi.air1603.sport_manager.presenter.MyPlacePresenterImpl;
import com.foi.air1603.sport_manager.presenter.PlacePresenterImpl;
import com.foi.air1603.sport_manager.view.AddPlaceView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marko on 24.12.2016..
 */

public class AddPlaceFragment extends Fragment implements AddPlaceView {

    static Map<String, Integer> inputs = new HashMap<>();

    static {
        inputs.put("PlaceName", R.id.txiPlaceName);
        inputs.put("PlaceAddress", R.id.txiPlaceAddress);
        inputs.put("PlaceNumber", R.id.txiPlaceNumber);
        inputs.put("PlaceWorkingHoursStart", R.id.txiPlaceWorkingHoursStart);
        inputs.put("PlaceWorkingHoursStop", R.id.txiPlaceWorkingHoursStop);
    }

    private User user;
    private AddPlacePresenter addPlacePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        user = activity.getIntent().getExtras().getParcelable("User");

        getActivity().setTitle("Dodaj sportski objekt");

        View v = inflater.inflate(R.layout.fragment_add_place, null);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPlacePresenter = new AddPlacePresenterImpl(this);
        Button btnAddPlace = (Button) getActivity().findViewById(R.id.buttonPlaceAdd);

        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                addPlacePresenter.checkInputData(user.id);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                AddAppointmentFragment addAppointmentFragment = new AddAppointmentFragment();
                fragmentTransaction.add(R.id.fragment_container, addAppointmentFragment);
                fragmentTransaction.commit();
            }

        });

        final EditText placeStartInput = (EditText) getActivity().findViewById(R.id.etPlaceWorkingHoursStart);
        final EditText placeStopInput = (EditText) getActivity().findViewById(R.id.etPlaceWorkingHoursStop);

        placeStartInput.setFocusable(false);
        placeStopInput.setFocusable(false);

        placeStartInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String selH = String.valueOf(selectedHour);
                        String selM = String.valueOf(selectedMinute);
                        if (selectedHour < 10) selH = "0" + selectedHour;
                        if (selectedMinute < 10) selM = "0" + selectedMinute;
                        placeStartInput.setText(selH + ":" + selM);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        placeStopInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String selH = String.valueOf(selectedHour);
                        String selM = String.valueOf(selectedMinute);
                        if (selectedHour < 10) selH = "0" + selectedHour;
                        if (selectedMinute < 10) selM = "0" + selectedMinute;
                        placeStopInput.setText(selH + ":" + selM);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    public String getInputText(AddPlaceViewEnums textView) {
        TextInputLayout textInputLayout = (TextInputLayout) getActivity().findViewById(inputs.get(textView.toString()));
        EditText editText = textInputLayout.getEditText();
        return editText.getText().toString();
    }

    @Override
    public String getPlaceImageFromEditText() {
        return null;
    }


    @Override
    public void displayError(AddPlaceViewEnums textView, String message) {
        ((TextInputLayout) getActivity().findViewById(inputs.get(textView.toString()))).setError(message);
    }

    @Override
    public void removeError(AddPlaceViewEnums textView) {
        ((TextInputLayout) getActivity().findViewById(inputs.get(textView.toString()))).setErrorEnabled(false);
    }

    @Override
    public void returnResponseCode(int statusCode, String message) {
        if (statusCode == 200) {
            PlacePresenterImpl.updateData = true;
            Toast.makeText(getActivity(),
                    "Uspješno ste dodali novi objekt", Toast.LENGTH_LONG).show();

            getFragmentManager().popBackStack();
        } else {
            Toast.makeText(getActivity(),
                    "Dodavanje objekta nije uspjelo:" + message, Toast.LENGTH_LONG).show();
        }
    }
}
