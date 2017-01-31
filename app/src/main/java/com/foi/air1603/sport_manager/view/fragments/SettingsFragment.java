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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.view.SettingsView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SettingsFragment extends android.app.Fragment implements SettingsView, SharedPreferences.OnSharedPreferenceChangeListener {
    private User user;
    private int passModul;
    private int nfcModul;
    private int notification;
    private Switch switchPass;
    private Switch switchNfc;
    private Switch switchNotifications;
    private SharedPreferences pref;
    private Locale myLocale;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.titleSettingsActivity));
        pref = this.getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        user = getActivity().getIntent().getExtras().getParcelable("User");
        this.passModul = user.passwordModule;
        this.nfcModul = user.nfcModule;
        this.notification = user.hide_notifications;

        View v = inflater.inflate(R.layout.fragment_settings, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.switchPass = (Switch) getActivity().findViewById(R.id.switchPassword);
        this.switchNfc = (Switch) getActivity().findViewById(R.id.switchNFC);
        this.switchNotifications = (Switch) getActivity().findViewById(R.id.switchNotification);
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
                setSettings();
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

        switchNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value;
                if (user.hide_notifications == 0) {
                    value = 1;
                } else {
                    value = 0;
                }
                updateSession("notification", value);
                setSettings();
            }
        });

        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();
                pref = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

                if (pref.contains("Language")) {
                    String currentLanguage = pref.getString("Language", null);
                    Toast.makeText(parent.getContext(), currentLanguage, Toast.LENGTH_LONG).show();

                    if(currentLanguage == "hr"){
                        //Toast.makeText(parent.getContext(), "Test HR", Toast.LENGTH_LONG).show();
                    } else if (currentLanguage == "en"){
                        //Toast.makeText(parent.getContext(), "Test EN", Toast.LENGTH_LONG).show();
                    }
                }

                if (position == 0) {
                    changeLanguage("en");
                } else if (position == 1) {
                    changeLanguage("hr");
                }

                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), "Selected: " + item + " Position: " + position, Toast.LENGTH_LONG).show();
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
        if (user.hide_notifications == 0) {
            switchNotifications.setChecked(false);
        } else {
            switchNotifications.setChecked(true);
        }
    }

    private void updateSession(String item, int value) {

        if (item.equals("nfc")) {
            user.nfcModule = value;
            MainActivity.user.nfcModule = value;

            int option = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
            if (value == 1) {
                option = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
            }

            PackageManager pm = getActivity().getApplicationContext().getPackageManager();
            ComponentName componentName = new ComponentName("com.foi.air1603.sport_manager",
                    "com.foi.air1603.nfc_verification_module.NfcMainActivity");
            pm.setComponentEnabledSetting(componentName, option,
                    PackageManager.DONT_KILL_APP);

        } else if (item.equals("pass")) {
            user.passwordModule = value;
            MainActivity.user.passwordModule = value;
        } else if (item.equals("notification")) {
            user.hide_notifications = value;
            MainActivity.user.hide_notifications = value;
        }

        getActivity().getIntent().putExtra("User", user);
        SharedPreferences.Editor editor = pref.edit();

        String json = new Gson().toJson(user);
        editor.putString("User", json);
        editor.commit();
    }

    private User retrieveLoginSession() {
        Gson gson = new Gson();
        String json = pref.getString("User", "");
        user = gson.fromJson(json, User.class);
        return user;
    }


    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLanguage(language);
    }

    public void changeLanguage(String language) {
        myLocale = new Locale(language);
        saveLocale(language);
        Locale.setDefault(myLocale);

        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(new Locale(language));
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.apply();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        getActivity().recreate();
    }
}

