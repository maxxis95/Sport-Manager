package com.foi.air1603.sport_manager.presenter;

import com.example.webservice.AirWebServiceResponse;
import com.foi.air1603.sport_manager.model.User;
import com.foi.air1603.sport_manager.model.UserModel;

import com.foi.air1603.sport_manager.view.RegisterView;

import static com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums.AddressR;
import static com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums.EmailR;
import static com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums.LastNameR;
import static com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums.NameR;
import static com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums.PasswordR;
import static com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums.PasswordR1;
import static com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums.PhoneNumberR;
import static com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums.UsernameR;

/**
 * Created by Robert on 11-Nov-16.
 */

public class RegisterPresenterImpl implements RegisterPresenter, PresenterHandler {

    private final RegisterView view;
    boolean valid = false;
    boolean e, u, p, p1, n, l, a, pn = false;
    UserModel userModel;

    public RegisterPresenterImpl(RegisterView registerView) {

        this.view = registerView;
        this.userModel = new UserModel(this);
    }

    @Override
    public void validateUserRegister() {

        if (view.getEmailFromEditText().isEmpty()) {
            view.displayError(EmailR, "Polje je obavezno");
            e = false;

        } else {
            valid = isValidEmailAddress(view.getEmailFromEditText());

            if (valid) {
                view.removeError(EmailR);
                e = true;
            } else {
                view.displayError(EmailR, "Email nije validan");
                e = false;
            }
        }

        if (view.getUsernameFromEditText().isEmpty()) {
            view.displayError(UsernameR, "Polje je obavezno");
            u = false;
        } else {
            view.removeError(UsernameR);
            u = true;
        }

        if (view.getPasswordFromEditText().isEmpty()) {
            view.displayError(PasswordR, "Polje je obavezno");
            p = false;
        } else {
            view.removeError(PasswordR);
            p = true;
        }

        if (view.getPassword1FromEditText().isEmpty()) {
            view.displayError(PasswordR1, "Polje je obavezno");
            p1 = false;
        } else {
            if (view.getPasswordFromEditText().equals(view.getPassword1FromEditText())) {
                p1 = true;
                view.removeError(PasswordR1);
            } else {
                p1 = false;
                view.displayError(PasswordR1, "Lozinke se ne podudaraju");
            }
        }

        if (view.getNameFromEditText().isEmpty()) {
            view.displayError(NameR, "Polje je obavezno");
            n = false;
        } else {
            n = true;
            view.removeError(NameR);
        }

        if (view.getLastNameFromEditText().isEmpty()) {
            view.displayError(LastNameR, "Polje je obavezno");
            l = false;
        } else {
            view.removeError(LastNameR);
            l = true;
        }

        if (view.getAddressFromEditText().isEmpty()) {
            view.displayError(AddressR, "Polje je obavezno");
            a = false;
        } else {
            view.removeError(AddressR);
            a = true;
        }

        if (view.getPhoneNumberFromEditText().isEmpty()) {
            view.displayError(PhoneNumberR, "Polje je obavezno");
            pn = false;
        } else{
            view.removeError(PhoneNumberR);
            pn = true;
        }

        if(e && u && p && p1 && n && l && a && pn){
            System.out.println("----------------->2. RegisterPresenterImpl:validateUserRegister");
            userModel.setUserObject(createNewUserObject());
        }

    }

    private boolean isValidEmailAddress(String emailFromEditText) {
        String ePattern = "^[^@]+@[^@]+\\.[^@]+$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(emailFromEditText);
        return m.matches();
    }

    private User createNewUserObject() {
        User user = new User();
        user.username = view.getUsernameFromEditText();
        user.address = view.getAddressFromEditText();
        user.email = view.getEmailFromEditText();
        user.firstName = view.getNameFromEditText();
        user.lastName = view.getLastNameFromEditText();
        user.phone = view.getPhoneNumberFromEditText();
        user.password = view.getPasswordFromEditText();

        return user;
    }

    @Override
    public void getResponseData(Object result) {
        System.out.println("----------------->7. RegisterPresenterImpl:getResponseData");
        AirWebServiceResponse test = (AirWebServiceResponse) result;
        view.returnResponseCode(test.getStatusCode(), test.getMessage());
    }
}



