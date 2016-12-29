package com.foi.air1603.sport_manager.view.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.adapters.MyPlaceRecycleAdapter;
import com.foi.air1603.sport_manager.adapters.PlaceRecycleAdapter;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.presenter.MyPlacePresenter;
import com.foi.air1603.sport_manager.presenter.MyPlacePresenterImpl;
import com.foi.air1603.sport_manager.view.MyPlacesView;

import java.util.List;

/**
 * Created by Korisnik on 28-Dec-16.
 */

public class MyPlacesFragment extends Fragment implements MyPlacesView {
    private RecyclerView recyclerView;
    MyPlacePresenter presenter;
    public User user;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_places, null);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MainActivity) getActivity();
        user = activity.getIntent().getExtras().getParcelable("User");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_Myplaces);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //presenter = new MyPlacePresenterImpl(this);
        int id = user.id;
        presenter = new MyPlacePresenterImpl(this);

        presenter.gettingMultipleMyPlaces(id);


    }

    @Override
    public void showMyPlaces(List<Integer> id, List<String> name, List<String> address, List<String> contact, List<String> img, List<String> workingHoursFrom, List<String> workingHoursTo, List<String> lat, List<String> lon) {
        recyclerView.setAdapter(new MyPlaceRecycleAdapter(id, name, address, contact,img, workingHoursFrom,workingHoursTo,lat,lon, this));
    }

    @Override
    public void changeFragmentToAddAppointmentFragment(Integer id) {
        Fragment newFragment = new AddAppointmentFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("place_id", id);
        newFragment.setArguments(bundle);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void changeFragmentToPlaceReservationFragment(Integer id) {

        Fragment newFragment = new PlaceReservationFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("place_id", id);
        newFragment.setArguments(bundle);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
