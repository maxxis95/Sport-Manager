package com.foi.air1603.sport_manager.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.adapters.MyReservationsExpandableItem;
import com.foi.air1603.sport_manager.adapters.MyReservationsRecycleAdapter;
import com.foi.air1603.sport_manager.entities.Reservation;
import com.foi.air1603.sport_manager.presenter.MyReservationsPresenterImpl;
import com.foi.air1603.sport_manager.view.MyReservationsView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyReservationsFragment extends android.app.Fragment implements MyReservationsView {

    private static final String TAG = "MyReservationsFragment";
    protected RecyclerView mRecyclerView;
    MyReservationsPresenterImpl myReservationsPresenter;
    MyReservationsRecycleAdapter adapter;


    public MyReservationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_reservations, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_recycler);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myReservationsPresenter = new MyReservationsPresenterImpl(this);
        //loadRecycleViewTestData();

    }


    public void loadRecycleViewTestData() {
        // test data

        Reservation res = new Reservation();
        //res.setSubmitted("DATUMMM");
        // res.setCreated("Gimnazija");
        //res.setConfirmed("SLIKICAAA");

        List<MyReservationsExpandableItem> reservationsItems = new ArrayList<MyReservationsExpandableItem>();
        reservationsItems.add(new MyReservationsExpandableItem(res));

        if (mRecyclerView != null) {
            adapter = new MyReservationsRecycleAdapter(getActivity(), reservationsItems);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void loadRecycleViewData(List<Reservation> reservations) {
        List<MyReservationsExpandableItem> reservationsItems = new ArrayList<MyReservationsExpandableItem>();
        System.out.println("MyReservationsFragment:LoadRecyclerViewData");

        for (Reservation res : reservations) {

            if (res.appointment == null) {
                continue;
            }

            MyReservationsExpandableItem tmp = new MyReservationsExpandableItem(res);
            reservationsItems.add(tmp);
        }
        if (mRecyclerView != null) {
            adapter = new MyReservationsRecycleAdapter(getActivity(), reservationsItems);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }
}
