package com.foi.air1603.sport_manager.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.foi.air1603.password_verification_module.PasswordMainActivity;
import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.adapters.MyReservationsExpandableItem;
import com.foi.air1603.sport_manager.adapters.MyReservationsRecycleAdapter;
import com.foi.air1603.sport_manager.entities.Reservation;
import com.foi.air1603.sport_manager.loaders.PasswordVerification;
import com.foi.air1603.sport_manager.loaders.Verification;
import com.foi.air1603.sport_manager.loaders.VerificationListener;
import com.foi.air1603.sport_manager.presenter.MyReservationsPresenter;
import com.foi.air1603.sport_manager.presenter.MyReservationsPresenterImpl;
import com.foi.air1603.sport_manager.view.MyReservationsView;

import java.util.ArrayList;
import java.util.List;


public class MyReservationsFragment extends android.app.Fragment implements MyReservationsView,VerificationListener {

    private static final String TAG = "MyReservationsFragment";
    protected RecyclerView mRecyclerView;
    MyReservationsPresenter myReservationsPresenter;
    MyReservationsRecycleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Moje rezervacije");
        MainActivity.showProgressDialog("Dohvaćanje podataka");

        View rootView = inflater.inflate(R.layout.fragment_my_reservations, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_recycler);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myReservationsPresenter = MyReservationsPresenterImpl.getInstance().Init(this);
        myReservationsPresenter.getUserReservationsData();


    }
    @Override
    public void loadRecycleViewData(List<Reservation> reservations) {
        List<MyReservationsExpandableItem> reservationsItems = new ArrayList<>();
        System.out.println("MyReservationsFragment:LoadRecyclerViewData");

        if (reservations == null){
            MainActivity.dismissProgressDialog();
            Toast.makeText(getActivity(),
                    "Trenutno nemate svojih rezervacija!", Toast.LENGTH_LONG).show();
            return;
        }

        assert reservations != null;
        for (Reservation res : reservations) {
            if (res.appointment == null) {
                continue;
            }


            MyReservationsExpandableItem tmp = new MyReservationsExpandableItem(res);
            reservationsItems.add(tmp);
        }
        if (mRecyclerView != null) {
            adapter = new MyReservationsRecycleAdapter(getActivity(), reservationsItems,this);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        MainActivity.dismissProgressDialog();
    }

    @Override
    public void changeActivitiyToPasswordActivitiy(String pass) {
        /*Fragment newFragment = new AddAppointmentFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("place_id", 2);
        newFragment.setArguments(bundle);

        MainActivity.replaceFragment(newFragment);*/

        Verification passwordVerification = new PasswordVerification();
        Intent intent = new Intent();
        intent.putExtra("pass",pass);
        intent.setClass(getActivity(), PasswordMainActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void answerFromPassModule(Boolean flag) {
        if (flag) {


        } else {
            Toast.makeText(getActivity(),
                    "Lozinka nije tocna", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onVerificationResult(Boolean result) {
        if(result){
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeefdfdasfsa   radiii++");
        }

    }
}
