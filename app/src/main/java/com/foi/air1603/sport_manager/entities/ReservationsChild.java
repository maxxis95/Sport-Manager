package com.foi.air1603.sport_manager.entities;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class ReservationsChild {
    String placeName;
    String placeAddress;
    String teamName;

    public ReservationsChild(String placeName, String placeAddress, String teamName) {
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.teamName = teamName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public String getTeamName() {
        return teamName;
    }
}
