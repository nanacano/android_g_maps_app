package com.example.as2.myfirstapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by as2 on 12.11.2015.
 */
public class RetrieveCitiesTask extends AsyncTask<String, Void, ArrayList<City>> {

    private static final String TAG = "==+==retrieve==+==";

    public RetrieveCitiesTask(SendingCitiesListResult response) {
        this.response = response;
    }

    SendingCitiesListResult response;

    public Exception exception;

    protected ArrayList<City> doInBackground(String... urls) {
        ArrayList<City> cities = new ArrayList<City>();
        if (urls.length > 0){
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                inputStream = connection.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder result = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    result.append(line);
                }

                JSONObject jsonObject = new JSONObject(result.toString());
                JSONArray array = new JSONArray(jsonObject.getString("cities"));

                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = new JSONObject(array.getString(i));
                    City city = new City();
                    city.setTitle(obj.getString("city_name"));
                    city.setLatitude(Double.parseDouble(obj.getString("city_latitude")));
                    city.setLongitude(Double.parseDouble(obj.getString("city_longitude")));
                    Log.d(TAG, "title city=" + city.getTitle());
                    cities.add(city);
                }

            } catch (Exception e) {
                Log.d(TAG, e.toString());
                Log.d(TAG, e.getCause().toString());
                Log.d(TAG, e.getMessage());
                e.printStackTrace();

            }finally {
                if (connection != null){
                    connection.disconnect();
                }
            }
        }
        return cities;
    }

    protected void onPostExecute(ArrayList<City> list) {
        if (list.size()> 0){
            Log.d(TAG, "onPostExecute cities=" + list.toString());
            response.sendCitiesList(list);
        }else{
            response.sendCitiesList(new ArrayList<City>());
        }
    }


}
