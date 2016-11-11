package com.foi.air1603.sport_manager;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
                if(presenter.validateUserRegister()) {
                    //todo: poziv na login screen
                    System.out.println("Sve radi");
                }
            }

        });
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

    public String getEmailFromEditText() {
        EditText emailInput = (EditText) findViewById(R.id.etMail);
        return emailInput.getText().toString();
    }


    public String getUsernameFromEditText() {
        EditText usernameInput = (EditText) findViewById(R.id.etUsernameR);
        return usernameInput.getText().toString();
    }


    public String getPasswordFromEditText() {
        EditText passwordInput = (EditText) findViewById(R.id.etPasswordR1);
        return passwordInput.getText().toString();
    }
    public String getPassword1FromEditText() {
        EditText password1Input = (EditText) findViewById(R.id.etPasswordR2);
        return password1Input.getText().toString();
    }
    public String getNameFromEditText() {
        EditText nameInput = (EditText) findViewById(R.id.etName);
        return nameInput.getText().toString();
    }
    public String getLastNameFromEditText() {
        EditText lastNameInput = (EditText) findViewById(R.id.etLastName);
        return lastNameInput.getText().toString();
    }

    public String getAddressFromEditText() {
        EditText addressInput = (EditText) findViewById(R.id.etAddress);
        return addressInput.getText().toString();
    }
    public String getPhoneNumberFromEditText() {
        EditText phoneNumberInput = (EditText) findViewById(R.id.etPhoneNumber);
        return phoneNumberInput.getText().toString();
    }

    @Override
    public void displayError(String editTextName, String message) {

        switch (editTextName) {

            case "etMail": EditText etMail = (EditText) findViewById(R.id.etMail);
                etMail.setError(message);
                break;
            case "etUsernameR": EditText etUsernameR = (EditText) findViewById(R.id.etUsernameR);
                etUsernameR.setError(message);
                break;
            case "etPasswordR1": EditText etPasswordR1 = (EditText) findViewById(R.id.etPasswordR1);
                etPasswordR1.setError(message);
                break;
            case "etPasswordR2": EditText etPasswordR2 = (EditText) findViewById(R.id.etPasswordR2);
                etPasswordR2.setError(message);
                break;
            case "etName": EditText etName = (EditText) findViewById(R.id.etName);
                etName.setError(message);
                break;
            case "etLastName": EditText etLastName = (EditText) findViewById(R.id.etLastName);
                etLastName.setError(message);
                break;
            case "etAddress": EditText etAddress = (EditText) findViewById(R.id.etAddress);
                etAddress.setError(message);
                break;
            case "etPhoneNumber": EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
                etPhoneNumber.setError(message);
                break;

        }


    }

}

