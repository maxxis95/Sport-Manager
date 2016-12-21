package com.foi.air1603.sport_manager.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.User;
import com.squareup.picasso.Picasso;

/**
 * Created by marko on 21.12.2016..
 */

public class ProfileFragment extends Fragment {

    public User user;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();
        user = activity.getIntent().getExtras().getParcelable("User");

        View v = inflater.inflate(R.layout.fragment_profile, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getImageForImageView();
        getUserDataForTextView();
    }

    private void getImageForImageView() {
        ImageView imageView = (ImageView) getView().findViewById(R.id.profileImage);
        Picasso.with(activity).load(user.img).into(imageView);
    }

    private void getUserDataForTextView() {
        TextView nameProfile = (TextView) getView().findViewById(R.id.profileName);
        TextView lastNameProfile = (TextView) getView().findViewById(R.id.profileLastName);
        TextView emailProfile = (TextView) getView().findViewById(R.id.profileEmail);
        TextView addressProfile = (TextView) getView().findViewById(R.id.profileAddress);
        TextView phoneProfile = (TextView) getView().findViewById(R.id.profilePhone);

        nameProfile.setText(user.first_name);
        lastNameProfile.setText(user.last_name);
        emailProfile.setText(user.email);
        addressProfile.setText(user.address);
        phoneProfile.setText(user.phone);
    }
}
