package com.foi.air1603.sport_manager.view.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.foi.air1603.sport_manager.MainActivity;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.model.UserInteractor;
import com.foi.air1603.sport_manager.model.UserInteractorImpl;
import com.foi.air1603.sport_manager.presenter.PresenterHandler;
import com.squareup.picasso.Picasso;

import java.io.File;


/**
 * Created by marko on 21.12.2016..
 */

public class ProfileFragment extends Fragment implements PresenterHandler {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    public User user;
    private MainActivity activity;
    private Button btnChangeProfilePicture;
    private String filePath = "";

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

        btnChangeProfilePicture = (Button) getView().findViewById(R.id.btnChangePicture);

        btnChangeProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfilePicture();
            }
        });

        getImageForImageView();
        getUserDataForTextView();
    }

    private void changeProfilePicture(){

        //TODO: Treba složiti callback funkciju koja omogoučuje change samo ako korisnik stisne yes
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Should we show an explanation?
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            }
        }

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null){
            System.out.println("data: "+data);
            System.out.println("resultcode: "+resultCode);

            //TODO: Ovaj kod za dohvačanje fajl name-a možda prebaciti u UserInteractor?
            Uri selectedImageUri = data.getData();

            // Will return "image:x*"
            String wholeID = DocumentsContract.getDocumentId(selectedImageUri);

            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];
            String[] column = { MediaStore.Images.Media.DATA };

            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";
            Cursor cursor = getActivity().getContentResolver().
                    query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            column, sel, new String[]{ id }, null);

            int columnIndex = cursor.getColumnIndex(column[0]);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();

            System.out.println("----------------->1/2. ProfileFragment:onActivityResult");

            UserInteractor interactor = new UserInteractorImpl(this);
            interactor.changeUserPicture(filePath);

        }
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


    @Override
    public void getResponseData(Object result) {

    }
}
