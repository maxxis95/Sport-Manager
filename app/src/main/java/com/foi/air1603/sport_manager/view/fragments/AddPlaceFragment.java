package com.foi.air1603.sport_manager.view.fragments;

import android.app.Fragment;
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
import com.foi.air1603.sport_manager.view.AddPlaceView;

/**
 * Created by marko on 24.12.2016..
 */

public class AddPlaceFragment extends Fragment implements AddPlaceView {

    public User user;
    private MainActivity activity;
    private Button btnAddPlace;
    private AddPlacePresenter addPlacePresenter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        user = activity.getIntent().getExtras().getParcelable("User");

        View v = inflater.inflate(R.layout.fragment_add_place, null);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addPlacePresenter = new AddPlacePresenterImpl(this);
        this.view = view;

        btnAddPlace = (Button) getActivity().findViewById(R.id.buttonPlaceAdd);

        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                addPlacePresenter.checkInputData(user.id);
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

    @Override
    public String getPlaceNameFromEditText() {
        EditText placeName = (EditText) getActivity().findViewById(R.id.etPlaceName);
        return placeName.getText().toString();
    }

    @Override
    public String getPlaceAddressFromEditText() {
        EditText placeAddress = (EditText) getActivity().findViewById(R.id.etPlaceAddress);
        return placeAddress.getText().toString();
    }

    @Override
    public String getPlaceNumberFromEditText() {
        EditText placeNumber = (EditText) getActivity().findViewById(R.id.etPlaceNumber);
        return placeNumber.getText().toString();
    }

    @Override
    public String getPlaceWHStartFromEditText() {
        EditText placeWHStart = (EditText) getActivity().findViewById(R.id.etPlaceWorkingHoursStart);
        return placeWHStart.getText().toString();
    }

    @Override
    public String getPlaceWHStopFromEditText() {
        EditText placeWHStop = (EditText) getActivity().findViewById(R.id.etPlaceWorkingHoursStop);
        return placeWHStop.getText().toString();
    }

    @Override
    public String getPlaceImageFromEditText() {
        return null;
    }

    @Override
    public void displayError(AddPlaceViewEnums textView, String message) {
        final TextInputLayout placeNameWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceName);
        final TextInputLayout placeAddressWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceAddress);
        final TextInputLayout placeNumberWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceNumber);
        final TextInputLayout placeWHStartWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceWorkingHoursStart);
        final TextInputLayout placeWHStopWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceWorkingHoursStop);

        if (textView == AddPlaceViewEnums.PlaceName) {
            placeNameWrapper.setError(message);
        } else if (textView == AddPlaceViewEnums.PlaceAddress) {
            placeAddressWrapper.setError(message);
        } else if (textView == AddPlaceViewEnums.PlaceNumber) {
            placeNumberWrapper.setError(message);
        } else if (textView == AddPlaceViewEnums.PlaceWorkingHoursStart) {
            placeWHStartWrapper.setError(message);
        } else if (textView == AddPlaceViewEnums.PlaceWorkingHoursStop) {
            placeWHStopWrapper.setError(message);
        }
    }

    @Override
    public void removeError(AddPlaceViewEnums textView) {
        final TextInputLayout placeNameWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceName);
        final TextInputLayout placeAddressWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceAddress);
        final TextInputLayout placeNumberWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceNumber);
        final TextInputLayout placeWHStartWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceWorkingHoursStart);
        final TextInputLayout placeWHStopWrapper = (TextInputLayout) getActivity().findViewById(R.id.txiPlaceWorkingHoursStop);

        placeNameWrapper.setErrorEnabled(false);
        placeAddressWrapper.setErrorEnabled(false);
        placeNumberWrapper.setErrorEnabled(false);
        placeWHStartWrapper.setErrorEnabled(false);
        placeWHStopWrapper.setErrorEnabled(false);
    }

    @Override
    public void returnResponseCode(int statusCode, String message) {
        if (statusCode == 200) {
            Toast.makeText(getActivity(),
                    "Uspješno ste dodali novi objekt", Toast.LENGTH_LONG).show();

            getFragmentManager().popBackStack();

        } else {
            Toast.makeText(getActivity(),
                    "Dodavanje objekta nije uspjelo:" + message, Toast.LENGTH_LONG).show();
        }
    }
}
