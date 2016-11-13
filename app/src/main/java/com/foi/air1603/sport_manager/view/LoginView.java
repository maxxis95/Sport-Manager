package com.foi.air1603.sport_manager.view;

import com.foi.air1603.sport_manager.helper.enums.LoginViewEnums;

/**
 * Created by Generalko on 10.11.2016..
 */

public interface LoginView {
     String getUsernameFromEditText();
     String getPasswordFromEditText();
     void displayError(LoginViewEnums textView, String message);
}
