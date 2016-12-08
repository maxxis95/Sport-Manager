package com.foi.air1603.sport_manager.presenter;


import com.example.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.model.UserInteractor;
import com.foi.air1603.sport_manager.model.UserInteractorImpl;
import com.foi.air1603.sport_manager.view.LoginView;
import com.google.gson.Gson;

import static com.foi.air1603.sport_manager.helper.enums.LoginViewEnums.Password;
import static com.foi.air1603.sport_manager.helper.enums.LoginViewEnums.Username;

/**
 * Created by Generalko on 10.11.2016..
 */

public class LoginPresenterImpl implements LoginPresenter, UserInteractor.OnLoginFinishedListener, PresenterHandler {
    private final LoginView view;
    private UserInteractor userInteractor;
    private User user;


    public LoginPresenterImpl(LoginView loginView) {
        this.view = loginView;
        this.userInteractor = new UserInteractorImpl(this);
    }

    @Override
    public void checkInputData() {
        if (view.getUsernameFromEditText().isEmpty()) {
            view.displayError(Username, "Unesite vrijednost");
        }
        if (view.getPasswordFromEditText().isEmpty()) {
            view.displayError(Password, "Unesite vrijednost");
        }
        if (!view.getUsernameFromEditText().isEmpty() && !view.getPasswordFromEditText().isEmpty()){
            view.removeError(Username);
            view.removeError(Password);
            compareInputTextToData();
        }
    }


    @Override
    public void onUsernameError() {
        view.displayError(Username, "Korisničko ime ne postoji");
    }

    @Override
    public void onPasswordError() {
        view.displayError(Password, "Unijeli ste krivu lozinku");
    }

    @Override
    public void onWebServiceError(String message) {
        view.dataLoadingError(message);
    }

    private void compareInputTextToData() {
        userInteractor.getUserObject(this, "username", view.getUsernameFromEditText());
    }

    @Override
    public void getResponseData(Object result) {
        System.out.println("----------------->8. LoginPresenterImpl:getResponseData");

        AirWebServiceResponse response = (AirWebServiceResponse) result;
        user = new Gson().fromJson(response.getData(), User.class);


        if (user == null) {
            onUsernameError();
        } else{
            view.removeError(Username);
            if(!view.getPasswordFromEditText().equals(user.password)){
                onPasswordError();
            } else{
                view.removeError(Password);
                view.loginSuccessful(user);
            }
        }
    }
}
