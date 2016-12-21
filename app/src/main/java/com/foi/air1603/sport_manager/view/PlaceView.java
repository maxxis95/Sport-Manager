package com.foi.air1603.sport_manager.view;

import java.sql.Time;
import java.util.List;

/**
 * Created by Karlo on 3.12.2016..
 */
public interface PlaceView {
    void showTestToast(List<String> name, List<String> address,List<String> contact, List<String> imgUrl , List<Time> workingHoursFrom, List<Time> workingHoursTo, List<Integer> lat, List<Integer> lon);
    void changeFragment();
}
