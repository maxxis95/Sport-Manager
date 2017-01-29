package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

import com.foi.air1603.nfc_verification_module.NfcVerificationCaller;
import com.foi.air1603.nfc_verification_module.NfcVerificationHandler;

/**
 * Created by Karlo on 20.1.2017..
 */

class NfcVerification extends Verification implements NfcVerificationHandler {

    @Override
    public void VerifyApp(VerificationListener verificationListener, Activity activity, String challengeText) {
        System.out.println("----------------->4. NfcVerification:VerifyApp");

        mVerificationListener = verificationListener;

        NfcVerificationCaller call = NfcVerificationCaller.getInstance().Init(this);
        if(activity != null){
            call.startActivity(activity, challengeText);
        }
    }

    @Override
    public void onResultArrived(Integer result) {
        System.out.println("----------------->7. NfcVerification:onResultArrived");
      //  mVerificationListener.onVerificationResult(result != null);
    }
}
