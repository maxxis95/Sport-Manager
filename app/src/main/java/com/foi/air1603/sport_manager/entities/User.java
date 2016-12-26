package com.foi.air1603.sport_manager.entities;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Generalko on 10.11.2016..
 */

public class User implements Parcelable {
    public int id;
    public int type;
    public String img;
    public String first_name;
    public String last_name;
    public String email;
    public String phone;
    public String address;
    public String password;
    public String username;
    public Uri uploadedImage;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeString(this.img);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeString(this.password);
        dest.writeString(this.username);
    }

    public User() {
    }

    public Uri getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(Uri uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.type = in.readInt();
        this.img = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.password = in.readString();
        this.username = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
