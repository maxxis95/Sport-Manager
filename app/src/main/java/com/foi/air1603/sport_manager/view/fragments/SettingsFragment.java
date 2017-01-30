package com.foi.air1603.sport_manager.view.fragments;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.User;
import com.google.gson.Gson;

import java.util.Locale;

public class SettingsFragment extends android.app.Fragment {
    private User user;
    private int passModul;
    private int nfcModul;
    private Switch switchPass;
    private Switch switchNfc;
    private SharedPreferences pref;

    private Spinner spinnerctrl;
    private Locale myLocale;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.titleSettingsActivity));
        pref = this.getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        user = getActivity().getIntent().getExtras().getParcelable("User");
        this.passModul = user.passwordModule;
        this.nfcModul = user.nfcModule;
        View v = inflater.inflate(R.layout.fragment_settings, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.switchPass = (Switch) getActivity().findViewById(R.id.switchPassword);
        this.switchNfc = (Switch) getActivity().findViewById(R.id.switchNFC);
        setSettings();
        switchNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value;
                if (user.nfcModule == 0) {
                    value = 1;
                } else {
                    value = 0;
                }
                updateSession("nfc", value);
            }
        });
        switchPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value;
                if (user.passwordModule == 0) {
                    value = 1;
                } else {
                    value = 0;
                }
                updateSession("pass", value);
                setSettings();
            }
        });

        spinnerctrl = (Spinner) getActivity().findViewById(R.id.spinner);
        spinnerctrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Toast.makeText(parent.getContext(), "You have selected Croatian", Toast.LENGTH_SHORT).show();
                    //setLocale("ta");
                } else if (position == 2) {
                    Toast.makeText(parent.getContext(), "You have selected English", Toast.LENGTH_SHORT).show();
                    //setLocale("hi");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSettings() {
        if (user.passwordModule == 0) {
            switchPass.setChecked(false);
        } else {
            switchPass.setChecked(true);
        }
        if (user.nfcModule == 0) {
            switchNfc.setChecked(false);
        } else {
            switchNfc.setChecked(true);
        }
    }

    private void updateSession(String item, int value) {

        if (item.equals("nfc")) {
            user.nfcModule = value;
            MainActivity.user.nfcModule = value;

            int option = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
            if(value == 1){
                option = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
            }

            PackageManager pm  = getActivity().getApplicationContext().getPackageManager();
            ComponentName componentName = new ComponentName("com.foi.air1603.sport_manager",
                    "com.foi.air1603.nfc_verification_module.NfcMainActivity");
            pm.setComponentEnabledSetting(componentName, option,
                    PackageManager.DONT_KILL_APP);

        } else if (item.equals("pass")) {
            user.passwordModule = value;
            MainActivity.user.passwordModule = value;
        }

        getActivity().getIntent().putExtra("User", user);
        SharedPreferences.Editor editor = pref.edit();

        String json = new Gson().toJson(user);
        editor.putString("User", json);
        editor.commit();
    }

    /*
    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, AndroidLocalize.class);
        startActivity(refresh);
    }
    */

    private User retrieveLoginSession() {
        Gson gson = new Gson();
        String json = pref.getString("User", "");
        user = gson.fromJson(json, User.class);
        return user;
    }


}

