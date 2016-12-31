package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.model.UserInteractor;
import com.foi.air1603.sport_manager.model.UserInteractorImpl;
import com.foi.air1603.sport_manager.view.InviteFriendsView;
import com.foi.air1603.webservice.AirWebServiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Karlo on 31.12.2016..
 */

public class InviteFriendsPresenterImpl implements InviteFriendsPresenter, PresenterHandler {
    private final InviteFriendsView view;
    private final UserInteractor userInteractor;

    public InviteFriendsPresenterImpl(InviteFriendsView view) {
        this.view = view;
        this.userInteractor = new UserInteractorImpl(this);
    }

    @Override
    public void loadAllUserEmails() {
        userInteractor.getUsersEmails();
    }

    @Override
    public void getResponseData(Object result) {
        System.out.println("----------------->8. InviteFriendsPresenterImpl:getResponseData");

        AirWebServiceResponse response = (AirWebServiceResponse) result;

        Type collectionType = new TypeToken<Map<Integer, String>>() {
        }.getType();
        Map<Integer, String> userEmails = new Gson().fromJson(response.getData(), collectionType);
        view.initializeAutoComplete(userEmails);
    }
}
