package com.foi.air1603.sport_manager.presenter;

import android.content.Context;

import com.example.webservice.User;
import com.foi.air1603.sport_manager.model.ModelHandler;
import com.foi.air1603.sport_manager.model.UserModel;
import com.foi.air1603.sport_manager.view.LoginView;

/**
 * Created by Generalko on 10.11.2016..
 */

public class LoginPresenterImpl implements LoginPresenter {
    private final LoginView view;
    UserModel userModel;


    public LoginPresenterImpl(LoginView loginView) {
        this.view = loginView;
        this.userModel = new UserModel();
    }

    @Override
    public void validateUserLogin() {
        if (view.getUsernameFromEditText().isEmpty()
                || view.getPasswordFromEditText().isEmpty()) {
            view.displayError();
        } else{
            compareViewAndModelText();
        }
    }

    private void compareViewAndModelText() {
        User user = userModel.getUserObject();
        if(user != null){
            if (!view.getPasswordFromEditText().equals(user.password)
                    || !view.getUsernameFromEditText().equals(user.username)) {
                view.displayError();
            }
        }
    }
}
