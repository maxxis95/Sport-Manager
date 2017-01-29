package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

import com.foi.air1603.sport_manager.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Korisnik on 12-Jan-17.
 */

abstract class Verification {

     VerificationListener mVerificationListener;

    public void VerifyApp(VerificationListener verificationListener, Activity activity, String challengeText) {
       mVerificationListener = verificationListener;
    }
}
