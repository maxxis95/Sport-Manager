package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

import com.foi.air1603.sport_manager.entities.Appointment;

/**
 * Created by Korisnik on 12-Jan-17.
 */

interface Verification {

    void VerifyApp(VerificationListener verificationListener, Activity activity, Appointment appointment);

}
