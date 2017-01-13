package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

/**
 * Created by Korisnik on 12-Jan-17.
 */

public abstract class Verification {
    protected VerificationListener mVerificationListener;

    public void VerifyApp(VerificationListener verificationListener, Activity activity, String challengeText) {
        this.mVerificationListener = verificationListener;
    }

    //checkForEnabledModules -
    // U postavkam će se moći turn on/off moduli a ova funkcija će vračati koji su to
    // enablani i njih prikazati u popupu za odabir
    // http://stackoverflow.com/a/16389626
}
