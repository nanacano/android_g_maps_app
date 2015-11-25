package com.example.as2.myfirstapplication;

/**
 * Created by as2 on 03.11.2015.
 */
public class City {

    private int id;

    private String title;

    private double latitude;
    private double spn_latitude;
    private double longitude;
    private double spn_longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getSpn_latitude() {
        return spn_latitude;
    }

    public void setSpn_latitude(double spn_latitude) {
        this.spn_latitude = spn_latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpn_longitude() {
        return spn_longitude;
    }

    public void setSpn_longitude(double spn_longitude) {
        this.spn_longitude = spn_longitude;
    }
}
