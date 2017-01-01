package com.foi.air1603.sport_manager.view;

import com.foi.air1603.sport_manager.helper.enums.AddPlaceViewEnums;

/**
 * Created by marko on 24.12.2016..
 */

public interface AddPlaceView {

   /* String getPlaceNameFromEditText();

    String getPlaceAddressFromEditText();

    String getPlaceNumberFromEditText();

    String getPlaceWHStartFromEditText();

    String getPlaceWHStopFromEditText();*/

    String getPlaceImageFromEditText();

    String getInputText(AddPlaceViewEnums textView);

    void displayError(AddPlaceViewEnums textView, String message);

    void removeError(AddPlaceViewEnums textView);

    void returnResponseCode(int statusCode, String message);
}
