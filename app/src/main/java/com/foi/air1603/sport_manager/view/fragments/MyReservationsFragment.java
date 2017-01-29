package com.foi.air1603.sport_manager.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.adapters.MyReservationsExpandableItem;
import com.foi.air1603.sport_manager.adapters.MyReservationsRecycleAdapter;
import com.foi.air1603.sport_manager.entities.Reservation;
import com.foi.air1603.sport_manager.presenter.MyReservationsPresenter;
import com.foi.air1603.sport_manager.presenter.MyReservationsPresenterImpl;
import com.foi.air1603.sport_manager.verifications.VerificationListener;
import com.foi.air1603.sport_manager.verifications.VerificationLoader;
import com.foi.air1603.sport_manager.view.MyReservationsView;

import java.util.ArrayList;
import java.util.List;

public class MyReservationsFragment extends android.app.Fragment implements MyReservationsView, VerificationListener {

    private static final String TAG = "MyReservationsFragment";
    public Reservation reservation;
    protected RecyclerView mRecyclerView;
    MyReservationsPresenter myReservationsPresenter;
    MyReservationsRecycleAdapter adapter;
    VerificationLoader verificationLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.titleMyReservationsActivity));
        MainActivity.showProgressDialog(getResources().getString(R.string.progressDataLoading));

        View rootView = inflater.inflate(R.layout.fragment_my_reservations, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_recycler);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(MainActivity.user.type > 1){
            verificationLoader = new VerificationLoader();
            verificationLoader.initializeNfc(this);
        }

        myReservationsPresenter = MyReservationsPresenterImpl.getInstance().Init(this);
        myReservationsPresenter.getUserReservationsData();
    }

    @Override
    public void loadRecycleViewData(List<Reservation> reservations) {
        List<MyReservationsExpandableItem> reservationsItems = new ArrayList<>();
        System.out.println("MyReservationsFragment:LoadRecyclerViewData");

        if (reservations == null) {
            MainActivity.dismissProgressDialog();
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.toastNoReservations), Toast.LENGTH_LONG).show();
            return;
        }

        for (Reservation res : reservations) {
            if (res.appointment == null) {
                continue;
            }

            MyReservationsExpandableItem tmp = new MyReservationsExpandableItem(res);
            reservationsItems.add(tmp);
        }
        if (mRecyclerView != null) {
            adapter = new MyReservationsRecycleAdapter(getActivity(), reservationsItems, this);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        MainActivity.dismissProgressDialog();
    }

    @Override
    public void verifyAppointment() {
        CharSequence modules[] = VerificationLoader.getEnabledModules();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Verify by");
        builder.setItems(modules, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                runVerification(which);
            }
        });
        builder.show();
    }

    void runVerification(int verificationMethod) {
        verificationLoader.startVerification(this, getActivity(), reservation.appointment, verificationMethod);
    }

    @Override
    public void setObject(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void deleteReservation(int id) {
        myReservationsPresenter.deleteReservationById(id);
    }

    @Override
    public void successfulDeletedReservation() {
        Toast.makeText(getActivity(),
                getResources().getString(R.string.toastTermDeletionSuccessful), Toast.LENGTH_LONG).show();
        MyReservationsPresenterImpl.updateData = true;
        myReservationsPresenter.getUserReservationsData();
    }

    @Override
    public void backFragment() {
        getFragmentManager().popBackStack();
        Toast.makeText(getActivity(), getResources().getString(R.string.toastNoReservation), Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onVerificationResult(Boolean result) {
        System.out.println("MyReservationsFragment:onVerificationResult ---- result is -- " + result);

        if(result){
            Toast.makeText(getActivity(), getResources().getString(R.string.toastAppointmentConfirmation), Toast.LENGTH_LONG).show();
            myReservationsPresenter.updateReservation(reservation);
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.toastError), Toast.LENGTH_LONG).show();
        }
    }
}
