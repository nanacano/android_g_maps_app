package com.example.as2.myfirstapplication;

import java.util.ArrayList;

/**
 * Created by as2 on 12.11.2015.
 */
public interface SendingCitiesListResult {
    void sendCitiesList(String output);
    void sendCitiesList(ArrayList<City> cities);
}
