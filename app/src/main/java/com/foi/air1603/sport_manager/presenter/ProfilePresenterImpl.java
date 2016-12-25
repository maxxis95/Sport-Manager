package com.foi.air1603.sport_manager.presenter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.foi.air1603.sport_manager.model.UserInteractor;
import com.foi.air1603.sport_manager.model.UserInteractorImpl;
import com.foi.air1603.sport_manager.view.ProfileView;
/**
 * Created by Karlo on 25.12.2016..
 */

public class ProfilePresenterImpl implements ProfilePresenter, PresenterHandler {

    private ProfileView view;
    private String filePath = "";
    public ProfilePresenterImpl(ProfileView profileView) {
        this.view = profileView;
    }

    @Override
    public void getResponseData(Object result) {

    }

    @Override
    public void changeProfilePicture(Intent data, Activity activity) {
        Uri selectedImageUri = data.getData();

        // Will return "image:x*"
        String wholeID = DocumentsContract.getDocumentId(selectedImageUri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];
        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";
        Cursor cursor = activity.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();

        System.out.println("----------------->2. ProfilePresenterImpl:changeProfilePicture");
        UserInteractor interactor = new UserInteractorImpl(this);
        interactor.changeUserPicture(filePath);

    }


}
