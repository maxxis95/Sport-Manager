package com.foi.air1603.sport_manager.model;

import com.foi.air1603.sport_manager.entities.User;

/**
 * Created by Generalko on 12.11.2016..
 */

public interface UserInteractor {
    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onWebServiceError(String message);

    }

    void getUserObject(OnLoginFinishedListener listener, String searchBy, String value);
    void setUserObject(User user);
}
