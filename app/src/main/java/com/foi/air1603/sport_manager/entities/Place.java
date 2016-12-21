package com.foi.air1603.sport_manager.entities;

/**
 * Created by Karlo on 3.12.2016..
 */

public class Place {
    int id;
    public String name;
    public String address;
    public Integer userId;
    public String imgUrl;
    public String contact;
    public String workingHoursFrom;
    public String workingHoursTo;
   // public Integer lat;
    //public Integer lon;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getContact() {
        return contact;
    }

    public String getWorkingHoursFrom() {
        return workingHoursFrom;
    }

    public String getWorkingHoursTo() {
        return workingHoursTo;
    }

  /*  public Integer getLat() {
        return lat;
    }*/

   /* public Integer getLon() {
        return lon;
    }*/



}
