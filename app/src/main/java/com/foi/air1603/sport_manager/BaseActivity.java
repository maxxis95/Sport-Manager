package com.foi.air1603.sport_manager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.loaders.DataLoadedListener;
import com.foi.air1603.sport_manager.loaders.DataLoader;
import com.foi.air1603.sport_manager.loaders.WsDataLoader;
import com.foi.air1603.sport_manager.view.fragments.LoginFragment;
import com.foi.air1603.webservice.AirWebServiceResponse;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Karlo on 3.12.2016..
 */

public class BaseActivity extends AppCompatActivity implements DataLoadedListener {

    private static ProgressDialog progressDialog;
    static public BaseActivity activity;
    private CallbackManager callbackManager;

    public static void showProgressDialog(String message) {
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        callbackManager = CallbackManager.Factory.create();
        progressDialog = new ProgressDialog(this);
        activity = this;

        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginFragment login = new LoginFragment();
        fragmentTransaction.add(R.id.fragment_container, login, "HELLO");
        fragmentTransaction.commit();
    }


    public static String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    static public void unlinkDevice(){
        if(activity != null){
            DataLoader dataLoader = new WsDataLoader();
            String android_id = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
            dataLoader.loadData(activity, "updateToken", android_id, "", "", User.class, null);
        }
    }
    @Override
    public void onDataLoaded(AirWebServiceResponse result) {
        System.out.println("MyFirebaseInstanceIDService:onDataLoaded");
        System.out.println(result);
    }
}