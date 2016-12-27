package com.foi.air1603.sport_manager.entities;

import android.app.Activity;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.foi.air1603.sport_manager.MainActivity;

/**
 * Created by Generalko on 10.11.2016..
 */

public class User implements Parcelable {
    public int id;
    public int type;
    public String img;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String address;
    public String password;
    public String username;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeString(this.img);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeString(this.password);
        dest.writeString(this.username);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.type = in.readInt();
        this.img = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
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
