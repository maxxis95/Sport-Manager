package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

import com.foi.air1603.nfc_verification_module.NfcVerificationCaller;
import com.foi.air1603.password_verification_module.PasswordVerificationCaller;

/**
 * Created by Karlo on 20.1.2017..
 */

public class NfcVerification extends Verification {

    @Override
    public void VerifyApp(VerificationListener verificationListener, Activity activity, String challengeText) {
        super.VerifyApp(verificationListener, activity, challengeText);
        System.out.println("----------------->4. PasswordVerification:VerifyApp");

        NfcVerificationCaller.startActivity(activity, challengeText);
    }

}
