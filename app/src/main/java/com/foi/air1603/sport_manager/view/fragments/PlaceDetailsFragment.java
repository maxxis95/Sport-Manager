package com.foi.air1603.sport_manager.view.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.view.PlaceDetailsView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


/**
 * Created by Korisnik on 06-Dec-16.
 */

public class PlaceDetailsFragment extends Fragment implements PlaceDetailsView {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 26;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 28;
    private TextView txtViewName;
    private TextView txtViewRadnoVrijeme;
    private TextView txtViewKontakt;
    private TextView txtViewAdresa;
    protected MapView mMapView;
    private Button reservation_btn;
    private int id_place;

    public GoogleMap map;
    public String placeName;
    public String placeAddress;
    private Boolean minimalLocationPermission = false;

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
            id_place = bundle.getInt("place_id");
            String place_name = bundle.getString("place_name", null);
            String place_address = bundle.getString("place_address", null);
            String place_contact = bundle.getString("place_contact", null);
            String place_imgUrl = bundle.getString("place_imgUrl", null);
            String place_workingHoursFrom = bundle.getString("place_workingHoursFrom", null);
            String place_workingHoursTo = bundle.getString("place_workingHoursTo", null);
            String place_lat = bundle.getString("place_lat", null);
            String place_lon = bundle.getString("place_lon", null);
            showPlace(place_name, place_address, place_contact, place_imgUrl, place_workingHoursFrom, place_workingHoursTo, place_lat, place_lon);
        }
        reservation_btn = (Button) getActivity().findViewById(R.id.buttonPlaceBook);

        reservation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ReservationFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("place_id", id_place);
                newFragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, newFragment);
                ft.addToBackStack(null);
                ft.commit();
                MainActivity.consoleLog(new Object(){}.getClass().getEnclosingMethod(), "----------------->RegisterFragment:onClickListener");
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
                MainActivity.consoleLog(new Object(){}.getClass().getEnclosingMethod(), "Error while attempting MapView.onDestroy(), ignoring exception:" + e);
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


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void showPlace(String place_name, String place_address, String place_contact, String place_imgUrl, String place_workingHoursFrom, String place_workingHoursTo, String place_lat, String place_lon) {
        txtViewAdresa = (TextView) getView().findViewById(R.id.tvPlaceAddress);
        txtViewName = (TextView) getView().findViewById(R.id.tvPlaceName);
        txtViewRadnoVrijeme = (TextView) getView().findViewById(R.id.tvPlaceWorkingHours);
        txtViewKontakt = (TextView) getView().findViewById(R.id.tvPlacePhone);
        txtViewAdresa.setText(place_address);
        txtViewName.setText(place_name);
        txtViewRadnoVrijeme.setText(place_workingHoursFrom.substring(0, 5) + " - " + place_workingHoursTo.substring(0, 5));
        txtViewKontakt.setText(place_contact);

        placeAddress = place_address;
        placeName = place_name;


        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

            return;
        } else {
            configureMap();
        }


    }

    @SuppressWarnings({"MissingPermission"})
    private void configureMap() throws SecurityException {

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

        Geocoder coder = new Geocoder(getActivity());
        List<Address> address = null;
        LatLng p1 = null;
        try {
            address = coder.getFromLocationName(placeAddress, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address location = address.get(0);
        location.getLatitude();
        location.getLongitude();

        p1 = new LatLng(location.getLatitude(), location.getLongitude());

        MapsInitializer.initialize(getActivity());

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(p1, 15));
        map.addMarker(new MarkerOptions()
                .position(p1)
                .title(placeName)
                .draggable(false).visible(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    minimalLocationPermission = true;
                    return;
                } else {
                    MainActivity.consoleLog(new Object(){}.getClass().getEnclosingMethod(), "Couldn't get fine location permission!");
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configureMap();

                } else {
                    if(minimalLocationPermission){
                        configureMap();
                    } else {
                        MainActivity.consoleLog(new Object(){}.getClass().getEnclosingMethod(), "Couldn't get COARSE location permission!");
                    }
                }
                return;
            }
        }
    }

}
