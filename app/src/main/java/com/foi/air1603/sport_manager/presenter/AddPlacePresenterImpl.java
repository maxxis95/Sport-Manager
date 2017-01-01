package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.helper.enums.AddPlaceViewEnums;
import com.foi.air1603.sport_manager.model.PlaceInteractor;
import com.foi.air1603.sport_manager.model.PlaceInteractorImpl;
import com.foi.air1603.sport_manager.view.AddPlaceView;
import com.foi.air1603.webservice.AirWebServiceResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by marko on 24.12.2016..
 */

public class AddPlacePresenterImpl implements AddPlacePresenter, PresenterHandler {

    private final AddPlaceView view;
    private PlaceInteractor placeInteractor;
    private Boolean checkName, checkAddress, checkNumber, checkWorkingHoursStart, checkWorkingHoursStop, checkWorkingHoursDifference;

    public AddPlacePresenterImpl(AddPlaceView addAppointmentView) {
        this.view = addAppointmentView;
        this.placeInteractor = new PlaceInteractorImpl(this);
    }

    @Override
    public void checkInputData(Integer userId) {
        checkName = checkAddress = checkNumber = checkWorkingHoursStart = checkWorkingHoursStop = checkWorkingHoursDifference = true;
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date start = sdf.parse(view.getPlaceWHStartFromEditText().toString());
            Date stop = sdf.parse(view.getPlaceWHStopFromEditText());

            if(start.compareTo(stop) >= 0){
                view.displayError(AddPlaceViewEnums.PlaceWorkingHoursStop, "Kraj radnog vremena je prije početka");
                checkWorkingHoursDifference = false;
            } else {
                view.removeError(AddPlaceViewEnums.PlaceWorkingHoursStop);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (view.getPlaceNameFromEditText().isEmpty()) {
            view.displayError(AddPlaceViewEnums.PlaceName, "Polje je obavezno");
            checkName = false;
        } else {
            view.removeError(AddPlaceViewEnums.PlaceName);
        }

        if (view.getPlaceAddressFromEditText().isEmpty()) {
            view.displayError(AddPlaceViewEnums.PlaceAddress, "Polje je obavezno");
            checkAddress = false;
        } else {
            view.removeError(AddPlaceViewEnums.PlaceAddress);
        }

        if (view.getPlaceNumberFromEditText().isEmpty()) {
            view.displayError(AddPlaceViewEnums.PlaceNumber, "Polje je obavezno");
            checkNumber = false;
        } else {
            view.removeError(AddPlaceViewEnums.PlaceNumber);
        }

        if (view.getPlaceWHStartFromEditText().isEmpty()) {
            view.displayError(AddPlaceViewEnums.PlaceWorkingHoursStart, "Polje je obavezno");
            checkWorkingHoursStart = false;
        } else {
            view.removeError(AddPlaceViewEnums.PlaceWorkingHoursStart);
        }

        if (view.getPlaceWHStopFromEditText().isEmpty()) {
            view.displayError(AddPlaceViewEnums.PlaceWorkingHoursStop, "Polje je obavezno");
            checkWorkingHoursStop = false;
        } else {
            view.removeError(AddPlaceViewEnums.PlaceWorkingHoursStop);
        }

        if (checkName && checkAddress && checkNumber && checkWorkingHoursStart && checkWorkingHoursStop) {
            placeInteractor.setPlaceObject(createNewPlaceObject(userId));
        }
    }

    @Override
    public void getResponseData(Object result) {
        AirWebServiceResponse test = (AirWebServiceResponse) result;

        view.returnResponseCode(test.getStatusCode(), test.getMessage());
    }

    private Place createNewPlaceObject(Integer userId) {
        Place place = new Place();

        place.name = view.getPlaceNameFromEditText();
        place.address = view.getPlaceAddressFromEditText();
        place.contact = view.getPlaceNumberFromEditText();
        place.workingHoursFrom = view.getPlaceWHStartFromEditText();
        place.workingHoursTo = view.getPlaceWHStopFromEditText();
        place.userId = userId;

        return place;
    }
}
