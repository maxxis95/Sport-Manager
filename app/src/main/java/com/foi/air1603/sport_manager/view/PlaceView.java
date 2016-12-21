package com.foi.air1603.sport_manager.view;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by Karlo on 3.12.2016..
 */
public interface PlaceView {
    void showTestToast(List<String> name, List<String> address, List<String> contact, List<String> imgUrl , List<String> workingHoursFrom, List<String> workingHoursTo, List<String> lat, List<String> lon);
    void changeFragment();
}
