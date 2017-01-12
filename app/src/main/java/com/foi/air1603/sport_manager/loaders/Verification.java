package com.foi.air1603.sport_manager.loaders;

/**
 * Created by Korisnik on 12-Jan-17.
 */

public abstract class Verification {

    protected VerificationListener mVerificationListener;

    public void VerifyApp(VerificationListener verificationListener) {
        this.mVerificationListener = verificationListener;
    }

}
