package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

import com.foi.air1603.password_verification_module.PasswordVerificationCaller;
import com.foi.air1603.password_verification_module.PasswordVerificationHandler;

/**
 * Created by Korisnik on 12-Jan-17.
 */

public class PasswordVerification extends Verification implements PasswordVerificationHandler {

    @Override
    public void VerifyApp(VerificationListener verificationListener, Activity activity, String challengeText) {
        super.VerifyApp(verificationListener, activity, challengeText);
        System.out.println("----------------->4. PasswordVerification:VerifyApp");

        PasswordVerificationCaller call;
        call = PasswordVerificationCaller.getInstance().Init(this);
        call.startActivity(activity, challengeText);
    }

    @Override
    public void onResultArrived(boolean result) {
        System.out.println("----------------->7. PasswordVerification:responseHandler");
        mVerificationListener.onVerificationResult(result);
    }
}
