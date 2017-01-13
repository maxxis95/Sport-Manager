package com.foi.air1603.password_verification_module.presenter;

import com.foi.air1603.password_verification_module.view.PasswordView;

/**
 * Created by Korisnik on 12-Jan-17.
 */

public class PasswordMainActivityPresenterImpl implements PasswordMainActivityPresenter {
    private final PasswordView view;

    public PasswordMainActivityPresenterImpl(PasswordView passwordView) {
        this.view = passwordView;
    }

    @Override
    public Boolean checkInputPass() {
        if (view.getPassFromApp().equals(view.getPassFromEditText())) {
            System.out.println("tocan");
            return true;
        } else {

            System.out.println("ne tocan");
            return false;
        }
    }
}
