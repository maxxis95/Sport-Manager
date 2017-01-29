package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

import com.foi.air1603.password_verification_module.PasswordVerificationCaller;
import com.foi.air1603.password_verification_module.PasswordVerificationHandler;
import com.foi.air1603.sport_manager.entities.Appointment;

/**
 * Created by Korisnik on 12-Jan-17.
 */

class PasswordVerification extends Verification implements PasswordVerificationHandler {

    @Override
    public void VerifyApp(VerificationListener verificationListener, Activity activity, Appointment appointment) {
        System.out.println("----------------->4. PasswordVerification:VerifyApp");

        mVerificationListener = verificationListener;

        PasswordVerificationCaller call = PasswordVerificationCaller.getInstance().Init(this);
        call.startActivity(activity, appointment.password);
    }

    @Override
    public void onResultArrived(boolean result) {
        System.out.println("----------------->7. PasswordVerification:onResultArrived");
        //mVerificationListener.onVerificationResult(result);
    }
}
