package com.foi.air1603.sport_manager.verifications;

import android.app.Activity;

import com.foi.air1603.sport_manager.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Karlo on 28.1.2017..
 */

public class VerificationLoader {
    private static List<String> modules;
    private Verification verification;

    public static CharSequence[] getEnabledModules() {
        modules = new ArrayList<>();

        if (MainActivity.user.nfcModule == 1) {
            modules.add("NFC");
        }
        if (MainActivity.user.passwordModule == 1) {
            modules.add("Password");
        }
        return modules.toArray(new CharSequence[modules.size()]);
    }

    public void startVerification(VerificationListener verificationListener, Activity activity, String challengeText, int verificationMethod){
        if (modules == null) {
            return;
        }

        String module = modules.get(verificationMethod);
        if (Objects.equals(module, "NFC")) {
            verification = new NfcVerification();
            verification.VerifyApp(verificationListener, activity, challengeText);
        } else if (Objects.equals(module, "Password")) {
            verification = new PasswordVerification();
            verification.VerifyApp(verificationListener, activity, challengeText);
        }
    }

    public void initializeNfc(VerificationListener verificationListener){
        verification = new NfcVerification();
        verification.VerifyApp(verificationListener, null, null);
    }
}
