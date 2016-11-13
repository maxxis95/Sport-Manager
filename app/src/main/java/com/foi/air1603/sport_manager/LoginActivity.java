package com.foi.air1603.sport_manager;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.foi.air1603.sport_manager.helper.enums.LoginViewEnums;
import com.foi.air1603.sport_manager.presenter.LoginPresenterImpl;
import com.foi.air1603.sport_manager.view.LoginView;

public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoginView{

    // private variables
    private LoginPresenterImpl presenter;
    private Button btnLogin;
    private TextView txtViewRegistration;
    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        presenter = new LoginPresenterImpl(this);
        setValuesToTextViews();

        txtViewRegistration = (TextView) findViewById(R.id.twRegistracija);   //tw is short for TextView

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 presenter.checkIfInputDataIsEmpty();
            }
        });
        txtViewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
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
        getMenuInflater().inflate(R.menu.login, menu);
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
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.txiUsernameL);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.txiPasswordL);

        /*
        switch (textView){
            case Username: usernameWrapper.setError(message);
            case Password: passwordWrapper.setError(message);
            default: usernameWrapper.setErrorEnabled(false);
                passwordWrapper.setErrorEnabled(false);
        }
        */

        if(textView == LoginViewEnums.Username){
            usernameWrapper.setError(message);
        }
        else if(textView == LoginViewEnums.Password){
            passwordWrapper.setError(message);
        }
        else{
            usernameWrapper.setErrorEnabled(false);
            passwordWrapper.setErrorEnabled(false);
        }
    }

    /**
     * Sets the values to private fields of Text Views on XML
     */
    private void setValuesToTextViews(){
        btnLogin = (Button) findViewById(R.id.bPrijava);
        usernameInput = (EditText) findViewById(R.id.etUsername);
        passwordInput = (EditText) findViewById(R.id.etPassword);
    }
}
