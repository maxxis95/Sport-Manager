package com.foi.air1603.nfc_verification_module;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Karlo on 20.1.2017..
 */

public class NfcVerificationCaller {

    public NfcVerificationHandler mNfcVerificationHandler;
    private static NfcVerificationCaller instance;

    public NfcVerificationCaller() {
    }

    public static NfcVerificationCaller getInstance() {
        if (instance == null) {
            instance = new NfcVerificationCaller();
        }
        return instance;
    }

    public NfcVerificationCaller Init(NfcVerificationHandler nfcVerificationHandler) {
        mNfcVerificationHandler = nfcVerificationHandler;

        instance = this;
        return this;
    }

    public void startActivity(Activity activity, String pass) {
        System.out.println("----------------->5. NfcVerificationCaller:startActivity");

        Intent intent = new Intent();
        intent.putExtra("pass", pass);
        intent.setClass(activity, NfcMainActivity.class);
        activity.startActivity(intent);
    }
}
