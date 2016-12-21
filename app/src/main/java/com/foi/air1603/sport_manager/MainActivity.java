package com.foi.air1603.sport_manager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.helper.enums.Rights;
import com.foi.air1603.sport_manager.view.fragments.AllPlacesFragment;
import com.foi.air1603.sport_manager.view.fragments.ProfileFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by Karlo on 3.12.2016..
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public User user;
    private  AllPlacesFragment login;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    private Rights rights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        user = getIntent().getExtras().getParcelable("User");
        rights = rights.getRightFormInt(user.type);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setNavigationView();
    }

    //region Activity methods
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

        if (id == R.id.nav_my_profile) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new ProfileFragment());
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_places_list) {
            if(login != null){
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, login)
                        .addToBackStack(null)
                        .commit();
            }
        } else if (id == R.id.nav_my_reserved) {

        } else if (id == R.id.nav_my_reservations) {

        } else if (id == R.id.nav_add_new_reservation) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //endregion


    private void setNavigationView() {
        switch (rights) {
            case User:
                setUserView();
            case Admin:
                //todo: set admin nav and start screen
            case Owner:
                //todo: set owner nav and start screen
        }
    }

    private void setUserView() {
        setAllUsersDataToHeaderView();
        initAllPlacesFragment();
    }

    private void setAllUsersDataToHeaderView() {
        View header = navigationView.getHeaderView(0);
        TextView firstLastName = (TextView) header.findViewById(R.id.textViewFirstLastName);
        TextView email = (TextView) header.findViewById(R.id.textViewUserEmail);
        ImageView userImg = (ImageView) header.findViewById(R.id.imageViewUserPicture);

        firstLastName.setText(user.first_name + ' ' + user.last_name + ' ');
        email.setText(user.email);
        Picasso.with(this).load(user.img).into(userImg);
    }

    private void initAllPlacesFragment() {
        login = new AllPlacesFragment();
        fragmentTransaction.add(R.id.fragment_container, login, "HELLO");
        fragmentTransaction.commit();
    }
}