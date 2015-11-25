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

    private static final String TAG = "==+==+==+==";

    public RetrieveCitiesTask(SendingCitiesListResult response) {
        this.response = response;
    }

    SendingCitiesListResult response;

    public Exception exception;

    protected ArrayList<City> doInBackground(String... urls) {
        ArrayList<City> cities = new ArrayList<City>();
        try {
            Log.d(TAG, "starting of the");

            String myurl = "http://beta.taxistock.ru/taxik/api/client/query_cities";

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Log.d(TAG, "before connect");
            connection.connect();
            Log.d(TAG, "after connect");
            InputStream inputStream = connection.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

            String result = ""; //InputStreamOperations.InputStreamToString(inputStream);

//            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                result +=line;
            }

            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = new JSONArray(jsonObject.getString("cities"));

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = new JSONObject(array.getString(i));
                City city = new City();
                city.setTitle(obj.getString("city_name"));
                city.setLatitude(Double.parseDouble(obj.getString("city_latitude")));
                city.setLongitude(Double.parseDouble(obj.getString("city_longitude")));
                Log.d(TAG, "title city=" + obj.getString("city_name"));
                Log.d(TAG, " city_latitude=" + obj.getString("city_latitude"));
                Log.d(TAG, " city_longitude=" + obj.getString("city_longitude"));
                cities.add(city);
//                users.add(user);
            }

        } catch (Exception e) {
            Log.d(TAG, e.toString() );
            Log.d(TAG, e.getCause().toString() );
            Log.d(TAG, e.getMessage() );
            Log.d(TAG, "==================" );
            e.printStackTrace();

        }finally {
            Log.d(TAG, "one city=" );
        }
        return cities;
    }

    protected void onPostExecute(ArrayList<City> list) {
        if (list.size()> 0){
            Log.d(TAG, "onPostExecute cities=" + list.toString());
            response.sendCitiesList(list);
        }else{
            response.sendCitiesList("blalba");
        }
    }


}
