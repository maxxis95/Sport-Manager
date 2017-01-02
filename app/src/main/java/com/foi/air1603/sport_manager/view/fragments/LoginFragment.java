package com.foi.air1603.sport_manager.view.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.helper.enums.LoginViewEnums;
import com.foi.air1603.sport_manager.presenter.LoginPresenter;
import com.foi.air1603.sport_manager.presenter.LoginPresenterImpl;
import com.foi.air1603.sport_manager.view.LoginView;
import com.google.gson.Gson;

public class LoginFragment extends android.app.Fragment implements LoginView {

    private LoginPresenter presenter;
    private Button btnLogin;
    private TextView txtViewRegistration;
    private EditText usernameInput;
    private EditText passwordInput;
    private User user;

    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Login");
        View v = inflater.inflate(R.layout.fragment_login, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pref = this.getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        if (pref.contains("User")) {
            Intent intent = new Intent(this.getActivity(), MainActivity.class).putExtra("User", retrieveLoginSession());

            startActivity(intent);
        } else {
            presenter = new LoginPresenterImpl(this);
            setValuesToTextViews();

            txtViewRegistration = (TextView) getView().findViewById(R.id.twRegistracija);   //tw is short for TextView

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.checkInputData();
                }
            });

            txtViewRegistration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, new RegisterFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
        }
    }

    private void createLoginSession(User user) {
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("User", json);
        editor.commit();
    }

    private User retrieveLoginSession() {
        Gson gson = new Gson();
        String json = pref.getString("User", "");
        user = gson.fromJson(json, User.class);
        return user;
    }


    @Override
    public String getUsernameFromEditText() {
        return usernameInput.getText().toString();
    }

    @Override
    public String getPasswordFromEditText() {
        return passwordInput.getText().toString();
    }

    @Override
    public void displayError(LoginViewEnums textView, String message) {
        final TextInputLayout usernameWrapper = (TextInputLayout) getView().findViewById(R.id.txiUsernameL);
        final TextInputLayout passwordWrapper = (TextInputLayout) getView().findViewById(R.id.txiPasswordL);

        if (textView == LoginViewEnums.Username) {
            usernameWrapper.setError(message);
        } else if (textView == LoginViewEnums.Password) {
            passwordWrapper.setError(message);
        }
    }

    @Override
    public void dataLoadingError(String message) {
        buildAlertDialogForWebServiceError(message);
    }

    @Override
    public void removeError(LoginViewEnums textView) {
        final TextInputLayout usernameWrapper = (TextInputLayout) getView().findViewById(R.id.txiUsernameL);
        final TextInputLayout passwordWrapper = (TextInputLayout) getView().findViewById(R.id.txiPasswordL);

        usernameWrapper.setErrorEnabled(false);
        passwordWrapper.setErrorEnabled(false);
    }

    @Override
    public void loginSuccessful(User userObject) {
        createLoginSession(userObject);
        Intent intent = new Intent(getActivity(), MainActivity.class).putExtra("User", userObject);
        startActivity(intent);
    }

    private void setValuesToTextViews() {
        btnLogin = (Button) getView().findViewById(R.id.bPrijava);
        usernameInput = (EditText) getView().findViewById(R.id.etUsername);
        passwordInput = (EditText) getView().findViewById(R.id.etPassword);
    }

    private void buildAlertDialogForWebServiceError(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Greška u dohvaćanju podataka!");
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
