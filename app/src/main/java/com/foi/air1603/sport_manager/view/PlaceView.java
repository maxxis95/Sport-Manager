package com.foi.air1603.sport_manager.view;

import java.util.List;

/**
 * Created by Karlo on 3.12.2016..
 */
public interface PlaceView {
    void showTestToast(List<String> name, List<String> address, List<String> imgUrl, List<String> contact, List<String> workingHoursFrom, List<String> workingHoursTo/*, List<Integer> lat, List<Integer> lon*/);
    void changeFragment();
}
