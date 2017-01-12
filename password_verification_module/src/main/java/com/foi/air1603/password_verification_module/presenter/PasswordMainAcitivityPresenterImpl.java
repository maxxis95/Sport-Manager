package com.foi.air1603.password_verification_module.presenter;

import android.widget.Toast;

import com.foi.air1603.password_verification_module.PasswordMainActivity;
import com.foi.air1603.password_verification_module.view.PasswordView;

/**
 * Created by Korisnik on 12-Jan-17.
 */

public class PasswordMainAcitivityPresenterImpl implements PasswordMainAcitivityPresenter {
    private final PasswordView view;

    public PasswordMainAcitivityPresenterImpl(PasswordView passwordView) {
        this.view = passwordView;
    }

    @Override
    public Boolean checkInputPass() {
        if(view.getPassFromApp().equals(view.getPassFromEditText())){
            System.out.println("tocan");
            return true;
        } else {

            System.out.println("ne tocan");
            return false;
        }

    }
}
