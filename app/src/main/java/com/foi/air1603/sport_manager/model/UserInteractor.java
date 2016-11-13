package com.foi.air1603.sport_manager.model;

import com.example.webservice.User;

/**
 * Created by Generalko on 12.11.2016..
 */

public interface UserInteractor {
     interface OnLoginFinishedListener{
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }
    void getUserObject(OnLoginFinishedListener listener, String method, String args);
}
