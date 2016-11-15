package com.foi.air1603.sport_manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foi.air1603.sport_manager.helper.enums.RegisterViewEnums;
import com.foi.air1603.sport_manager.presenter.RegisterPresenter;
import com.foi.air1603.sport_manager.presenter.RegisterPresenterImpl;
import com.foi.air1603.sport_manager.view.RegisterView;

public class RegisterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RegisterView {


    private RegisterPresenter presenter;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //instance the presenter class
        presenter = new RegisterPresenterImpl(this);
        btnRegister = (Button) findViewById(R.id.bRegistracija);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                System.out.println("----------------->1. RegisterActivity:onClickListener");
                presenter.validateUserRegister();
            }

        });
    }

    public void returnResponseCode (int statusCode, String message) {
        if(statusCode == 200) {
            Toast.makeText(RegisterActivity.this,
                    "Uspješno ste se registrirali!", Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(loginIntent);
        } else {
            Toast.makeText(RegisterActivity.this,
                    "Registracija nije uspjela:"+message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Returns the entered string from the Email field
     */
    public String getEmailFromEditText() {
        EditText emailInput = (EditText) findViewById(R.id.etMail);
        return emailInput.getText().toString();
    }

    /**
     * Returns the entered string from the Username field
     */
    public String getUsernameFromEditText() {
        EditText usernameInput = (EditText) findViewById(R.id.etUsernameR);
        return usernameInput.getText().toString();
    }

    /**
     * Returns the entered string from the Password field
     */
    public String getPasswordFromEditText() {
        EditText passwordInput = (EditText) findViewById(R.id.etPasswordR);
        return passwordInput.getText().toString();
    }

    /**
     * Returns the entered string from the Password matching field
     */
    public String getPassword1FromEditText() {
        EditText password1Input = (EditText) findViewById(R.id.etPasswordR1);
        return password1Input.getText().toString();
    }

    /**
     * Returns the entered string from the Name field
     */
    public String getNameFromEditText() {
        EditText nameInput = (EditText) findViewById(R.id.etName);
        return nameInput.getText().toString();
    }

    /**
     * Returns the entered string from the Last Name field
     */
    public String getLastNameFromEditText() {
        EditText lastNameInput = (EditText) findViewById(R.id.etLastName);
        return lastNameInput.getText().toString();
    }

    /**
     * Returns the entered string from the Address field
     */
    public String getAddressFromEditText() {
        EditText addressInput = (EditText) findViewById(R.id.etAddress);
        return addressInput.getText().toString();
    }

    /**
     * Returns the entered string from the Phone Number field
     */
    public String getPhoneNumberFromEditText() {
        EditText phoneNumberInput = (EditText) findViewById(R.id.etPhoneNumber);
        return phoneNumberInput.getText().toString();
    }

    /**
     * Displays error messages underneath the fields in the Registration window
     * which were caused by a set of incorrectly entered characters or by not
     * entering any of them
     * @param textView
     */
    @Override
    public void displayError(RegisterViewEnums textView, String message) {
        final TextInputLayout mailRWrapper = (TextInputLayout) findViewById(R.id.txiMailR);
        final TextInputLayout usernameRWrapper = (TextInputLayout) findViewById(R.id.txiUsernameR);
        final TextInputLayout passwordRWrapper = (TextInputLayout) findViewById(R.id.txiPasswordR);
        final TextInputLayout passwordR1Wrapper = (TextInputLayout) findViewById(R.id.txiPasswordR1);
        final TextInputLayout nameRWrapper = (TextInputLayout) findViewById(R.id.txiImeR);
        final TextInputLayout lastNameRWrapper = (TextInputLayout) findViewById(R.id.txiPrezimeR);
        final TextInputLayout addressRWrapper = (TextInputLayout) findViewById(R.id.txiAdresaR);
        final TextInputLayout phoneNumberRWrapper = (TextInputLayout) findViewById(R.id.txiBrojTelefonaR);

        if(textView == RegisterViewEnums.UsernameR){
            usernameRWrapper.setError(message);
        }
        else if(textView == RegisterViewEnums.PasswordR){
            passwordRWrapper.setError(message);
        }
        else if(textView == RegisterViewEnums.PasswordR1){
            passwordR1Wrapper.setError(message);
        }
        else if(textView == RegisterViewEnums.EmailR){
            mailRWrapper.setError(message);
        }
        else if(textView == RegisterViewEnums.NameR){
            nameRWrapper.setError(message);
        }
        else if(textView == RegisterViewEnums.LastNameR){
            lastNameRWrapper.setError(message);
        }
        else if(textView == RegisterViewEnums.AddressR){
            addressRWrapper.setError(message);
        }
        else if(textView == RegisterViewEnums.PhoneNumberR){
            phoneNumberRWrapper.setError(message);
        }

    }

    /**
     * Removes error messages underneath the fields in the Registration window
     * which were caused by a set of incorrectly entered characters or by not
     * entering any of them
     * @param textView
     */
    @Override
    public void removeError(RegisterViewEnums textView) {
        final TextInputLayout mailRWrapper = (TextInputLayout) findViewById(R.id.txiMailR);
        final TextInputLayout usernameRWrapper = (TextInputLayout) findViewById(R.id.txiUsernameR);
        final TextInputLayout passwordRWrapper = (TextInputLayout) findViewById(R.id.txiPasswordR);
        final TextInputLayout passwordR1Wrapper = (TextInputLayout) findViewById(R.id.txiPasswordR1);
        final TextInputLayout nameRWrapper = (TextInputLayout) findViewById(R.id.txiImeR);
        final TextInputLayout lastNameRWrapper = (TextInputLayout) findViewById(R.id.txiPrezimeR);
        final TextInputLayout addressRWrapper = (TextInputLayout) findViewById(R.id.txiAdresaR);
        final TextInputLayout phoneNumberRWrapper = (TextInputLayout) findViewById(R.id.txiBrojTelefonaR);

        mailRWrapper.setErrorEnabled(false);
        usernameRWrapper.setErrorEnabled(false);
        passwordRWrapper.setErrorEnabled(false);
        passwordR1Wrapper.setErrorEnabled(false);
        nameRWrapper.setErrorEnabled(false);
        lastNameRWrapper.setErrorEnabled(false);
        addressRWrapper.setErrorEnabled(false);
        phoneNumberRWrapper.setErrorEnabled(false);
    }
}

