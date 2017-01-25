package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

import com.foi.air1603.nfc_verification_module.NfcVerificationCaller;
import com.foi.air1603.nfc_verification_module.NfcVerificationHandler;
import com.foi.air1603.password_verification_module.PasswordVerificationCaller;

/**
 * Created by Karlo on 20.1.2017..
 */

public class NfcVerification extends Verification implements NfcVerificationHandler{

    @Override
    public void VerifyApp(VerificationListener verificationListener, Activity activity, String challengeText) {
        super.VerifyApp(verificationListener, activity, challengeText);
        System.out.println("----------------->4. PasswordVerification:VerifyApp");

        NfcVerificationCaller call;
        call = NfcVerificationCaller.getInstance().Init(this);
        call.startActivity(activity, challengeText);
    }

    @Override
    public void onResultArrived(Integer result) {
        System.out.println("----------------->7. NfcVerification:onResultArrived");
        mVerificationListener.onVerificationResult(result != null);
    }
}
