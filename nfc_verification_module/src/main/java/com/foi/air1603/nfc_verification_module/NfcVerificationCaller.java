package com.foi.air1603.nfc_verification_module;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Karlo on 20.1.2017..
 */

public class NfcVerificationCaller {

    static public void startActivity(Activity activity, String pass) {

        System.out.println("----------------->5. NfcVerificationCaller:startActivity");

        Intent intent = new Intent();
        intent.putExtra("pass", pass);
        intent.setClass(activity, NfcMainActivity.class);
        activity.startActivity(intent);

    }
}
