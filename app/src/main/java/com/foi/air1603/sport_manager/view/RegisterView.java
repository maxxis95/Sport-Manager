package com.foi.air1603.sport_manager.view;

/**
 * Created by Robert on 11-Nov-16.
 */

public interface RegisterView {
    String getUsernameFromEditText();
    String getPasswordFromEditText();
    String getPassword1FromEditText();
    String getEmailFromEditText();
    String getNameFromEditText();
    String getLastNameFromEditText();
    String getAddressFromEditText();
    String getPhoneNumberFromEditText();
    void displayError(String editTextName, String message);
    void returnResponseCode(int statusCode, String message);
}
