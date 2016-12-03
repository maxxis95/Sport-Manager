package com.foi.air1603.sport_manager.fragments;

import android.app.FragmentTransaction;;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.helper.enums.LoginViewEnums;
import com.foi.air1603.sport_manager.presenter.LoginPresenter;
import com.foi.air1603.sport_manager.presenter.LoginPresenterImpl;
import com.foi.air1603.sport_manager.view.LoginView;

public class LoginFragment extends android.app.Fragment
        implements NavigationView.OnNavigationItemSelectedListener, LoginView{

    private LoginPresenter presenter;
    private Button btnLogin;
    private TextView txtViewRegistration;
    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.fragment_login, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //instance the presenter class
        presenter = new LoginPresenterImpl(this);
        setValuesToTextViews();

        txtViewRegistration = (TextView) getView().findViewById(R.id.twRegistracija);   //tw is short for TextView

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkIfInputDataIsEmpty();
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

    /**
     * Returns the entered string from the Username field
     */
    @Override
    public String getUsernameFromEditText() {
        return usernameInput.getText().toString();
    }

    /**
     * Returns the entered string from the Password field
     */
    @Override
    public String getPasswordFromEditText() {
        return passwordInput.getText().toString();
    }

    /**
     * Displays error message underneath the Username or Password fields which were caused
     * by a set of incorrectly entered characters or by not entering any of them
     * @param textView
     * @param message
     */
    @Override
    public void displayError(LoginViewEnums textView, String message) {
        final TextInputLayout usernameWrapper = (TextInputLayout) getView().findViewById(R.id.txiUsernameL);
        final TextInputLayout passwordWrapper = (TextInputLayout) getView().findViewById(R.id.txiPasswordL);

        if(textView == LoginViewEnums.Username){
            usernameWrapper.setError(message);
        }
        else if(textView == LoginViewEnums.Password){
            passwordWrapper.setError(message);
        }
    }

    /**
     * Call a pop-up window and notifies the User if there is data loading problem occured
     * on the web service
     */
    @Override
    public void dataLoadingError(String message) {
        buildAlertDialogForWebServiceError(message);
    }

    /**
     * Removes error messages underneath the Username or Password fields which were caused
     * by a set of incorrectly entered characters or by not entering any of them
     * @param textView
     */
    @Override
    public void removeError(LoginViewEnums textView) {
        final TextInputLayout usernameWrapper = (TextInputLayout) getView().findViewById(R.id.txiUsernameL);
        final TextInputLayout passwordWrapper = (TextInputLayout) getView().findViewById(R.id.txiPasswordL);

        usernameWrapper.setErrorEnabled(false);
        passwordWrapper.setErrorEnabled(false);
    }

    @Override
    public void loginSuccesful() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new AllPlacesFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * Sets the values to private fields of Text Views on XML
     */
    private void setValuesToTextViews(){
        btnLogin = (Button) getView().findViewById(R.id.bPrijava);
        usernameInput = (EditText) getView().findViewById(R.id.etUsername);
        passwordInput = (EditText) getView().findViewById(R.id.etPassword);
    }

    /**
     * alert dialog for webservice error handling
     * @param message
     */
    private void buildAlertDialogForWebServiceError(String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        //setting the title
        alertDialogBuilder.setTitle("Greška u dohvaćanju podataka!");
        //setting a dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) getView().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
