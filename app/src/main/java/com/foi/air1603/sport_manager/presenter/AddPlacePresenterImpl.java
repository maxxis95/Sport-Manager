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

    public AddPlacePresenterImpl(AddPlaceView addAppointmentView) {
        this.view = addAppointmentView;
        this.placeInteractor = new PlaceInteractorImpl(this);
    }

    @Override
    public void checkInputData(Integer userId) {
        Boolean checkWorkingHoursStart;
        Boolean checkWorkingHoursStop;
        Boolean checkWorkingHoursDifference;
        Boolean requiredFieldsNotEmpty = checkWorkingHoursStart = checkWorkingHoursStop = checkWorkingHoursDifference = true;

        for (AddPlaceViewEnums input_id : AddPlaceViewEnums.values()) {
            if (view.getInputText(input_id).isEmpty()) {
                view.displayError(input_id, "Polje je obavezno");
                requiredFieldsNotEmpty = false;
            } else {
                view.removeError(input_id);
            }
        }

        if (checkWorkingHoursStart && checkWorkingHoursStop) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            try {
                Date start = sdf.parse(view.getInputText(AddPlaceViewEnums.PlaceWorkingHoursStart));
                Date stop = sdf.parse(view.getInputText(AddPlaceViewEnums.PlaceWorkingHoursStop));

                if (start.compareTo(stop) >= 0) {
                    view.displayError(AddPlaceViewEnums.PlaceWorkingHoursStop, "Kraj radnog vremena je prije početka");
                    checkWorkingHoursDifference = false;
                } else {
                    view.removeError(AddPlaceViewEnums.PlaceWorkingHoursStop);
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }

        if (requiredFieldsNotEmpty && checkWorkingHoursDifference) {
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

        place.name = view.getInputText(AddPlaceViewEnums.PlaceName);
        place.address = view.getInputText(AddPlaceViewEnums.PlaceAddress);
        place.contact = view.getInputText(AddPlaceViewEnums.PlaceNumber);
        place.workingHoursFrom = view.getInputText(AddPlaceViewEnums.PlaceWorkingHoursStart);
        place.workingHoursTo = view.getInputText(AddPlaceViewEnums.PlaceWorkingHoursStop);
        place.userId = userId;

        return place;
    }
}
