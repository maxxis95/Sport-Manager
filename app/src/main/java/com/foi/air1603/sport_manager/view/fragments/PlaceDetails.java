package com.foi.air1603.sport_manager.view.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.presenter.PlacePresenter;
import com.foi.air1603.sport_manager.view.PlaceDetailsView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


/**
 * Created by Korisnik on 06-Dec-16.
 */

public class PlaceDetails extends android.app.Fragment implements PlaceDetailsView {

    PlacePresenter presenter;
    private TextView txtViewName;
    private TextView txtViewRadnoVrijeme;
    private TextView txtViewKontakt;
    private TextView txtViewAdresa;
    protected MapView mMapView;
    private Button reservation_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_details_place, null);
        mMapView = (MapView) v.findViewById(R.id.mapViewPlace);
        mMapView.onCreate(savedInstanceState);

        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(VARAZDIN, 10));


        /*if (map != null) {
                    map.addMarker(new MarkerOptions()
                    .position(new LatLng(41.40338, 2.17403))
                    .title("prva gimnazija")
                    .draggable(false).visible(true));
        }*/
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String place_name = bundle.getString("place_name",null);
            String place_address = bundle.getString("place_address",null);
            String place_contact = bundle.getString("place_contact",null);
            String place_imgUrl = bundle.getString("place_imgUrl",null);
            String place_workingHoursFrom = bundle.getString("place_workingHoursFrom",null);
            String place_workingHoursTo = bundle.getString("place_workingHoursTo",null);
            String place_lat = bundle.getString("place_lat",null);
            String place_lon = bundle.getString("place_lon",null);
            showPlace(place_name, place_address, place_contact, place_imgUrl, place_workingHoursFrom, place_workingHoursTo, place_lat, place_lon);
        }
        reservation_btn = (Button) getActivity().findViewById(R.id.reservation_btn);

        reservation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new ReservationFragment());
                ft.addToBackStack(null);
                ft.commit();
                System.out.println("----------------->RegisterFragment:onClickListener");
            }

        });



    }
    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onDestroy() {
        if (mMapView != null) {
            try {
                mMapView.onDestroy();
            } catch (NullPointerException e) {
                System.out.println("Error while attempting MapView.onDestroy(), ignoring exception:" + e);

            }
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null) {
            mMapView.onLowMemory();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onPause() {
        if (mMapView != null) {
            mMapView.onPause();
        }
        super.onPause();
    }


    @Override
    public void showPlace(String place_name, String place_address, String place_contact, String place_imgUrl, String place_workingHoursFrom, String place_workingHoursTo, String place_lat, String place_lon) {
        txtViewAdresa = (TextView) getView().findViewById(R.id.textViewAdress);
        txtViewName = (TextView) getView().findViewById(R.id.textViewName);
        txtViewRadnoVrijeme = (TextView) getView().findViewById(R.id.textViewRadnoVrijeme);
        txtViewKontakt = (TextView) getView().findViewById(R.id.textViewKontakt);
        txtViewAdresa.setText("Adresa: " + place_address);
        txtViewName.setText(place_name);
        txtViewRadnoVrijeme.setText("Radno vrijeme: " + place_workingHoursFrom+" - " + place_workingHoursTo);
        txtViewKontakt.setText("Kontakt: " + place_contact);


        try {
            configureMap(place_address,place_name);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private void
    configureMap(String adresa, String name) throws IOException {
        GoogleMap map;
        map = mMapView.getMap();
        Geocoder coder = new Geocoder(getActivity());
        List<Address> address;
        LatLng p1 = null;
        address = coder.getFromLocationName(adresa, 5);

        Address location = address.get(0);
        location.getLatitude();
        location.getLongitude();

        p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        MapsInitializer.initialize(getActivity());

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(p1, 15));
        map.addMarker(new MarkerOptions()
                .position(p1)
                .title(name)
                .draggable(false).visible(true));
       // CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(p1,15);
        // map.animateCamera(camera);
    }


}
