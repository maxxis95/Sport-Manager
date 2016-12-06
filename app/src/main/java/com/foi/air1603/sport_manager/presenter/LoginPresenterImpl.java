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

    /**
     * Setter
     */
    public LoginPresenterImpl(LoginView loginView) {
        this.view = loginView;
        this.userInteractor = new UserInteractorImpl(this);
    }

    /**
     * Checks if the Username and Password fields are empty and if they're empty
     * it calls the displayError method
     */
    @Override
    public void checkIfInputDataIsEmpty() {
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

    /**
     * Calls the displayError method in the case the entered username in the Username
     * field doesn't exist
     */
    @Override
    public void onUsernameError() {
        view.displayError(Username, "Korisničko ime ne postoji");
    }

    /**
     * Calls the displayError method in the case the entered password in the Password
     * field doesn't match the actual password stored in the database
     */
    @Override
    public void onPasswordError() {
        view.displayError(Password, "Unijeli ste krivu lozinku");
    }

    /**
     * In the case of an error on the Web service, this method is called which
     * has the task of showing an error to the user
     */
    @Override
    public void onWebServiceError(String message) {
        view.dataLoadingError(message);
    }

    /**
     * Method which is called if the user successfully logs into the application
     */
    @Override
    public void onSuccess() {
        //todo: go to the next screen
    }

    /**
     * Method which is called if the user enters his/her Username and Password correctly;
     * It gets an User object via the getUserByUsername method
     */
    private void compareInputTextToData() {
        userInteractor.getUserObject(this, "username", view.getUsernameFromEditText());
    }

    /**
     * Method which returns an User object from the Web service for the entered Username
     * and Password
     */
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
                view.loginSuccessful();
            }
        }
    }
}
