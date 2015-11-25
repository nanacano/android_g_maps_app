package com.example.as2.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class FirstMainActivity extends AppCompatActivity implements SendingCitiesListResult {

    private static final String TAG = "==+==+==+==";

    private ArrayList<City> cities;
    private ArrayList<String> cityTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void secondButton(View v){

        Log.d(TAG, "second button");
        RetrieveCitiesTask retrieveCitiesTask = new RetrieveCitiesTask(this);
//        getUsers(new Availability());
        retrieveCitiesTask.execute();
    }

    public void thirdButton(View v){
        Intent intent = new Intent(this, GoogleMapsActivity.class);
        intent.putExtra("title", "moscow");
        startActivity(intent);
    }

    public void generateListButton(View v){
        Log.d(TAG, "start func generateListButton");

        Context context = this;
        ArrayList<String> tasks = new ArrayList<String>();



        ListAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,tasks);
//        ListAdapter adapter2 = new ArrayAdapter<String>( context, android.R.layout.simple_expandable_list_item_1, tasks);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        tasks.add("sdfadsfadsfadsf");
        tasks.add("dgfdg");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "position=" + position + " , id=" + id);
            }
        });
//        listView.invalidateViews();

//        adapter.notify();
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
//        linearLayout.notify();
//        linearLayout.updateViewLayout()


        Log.d(TAG, "end func generateListButton");

    }

    public void goToMapLayout(View v){
        Log.d(TAG, "one city=");
        RetrieveCitiesTask retrieveCitiesTask = new RetrieveCitiesTask(this);
//        getUsers(new Availability());
        retrieveCitiesTask.execute();

    }

//    public void updateData(String output){
//        TextView textView = (TextView)findViewById(R.id.textView);
//        textView.setText(output);
//    }

//    public static ArrayList<City> getUsers(Availability availability) {
//        ArrayList<City> cities = new ArrayList<City>();
//        try {
//            Log.d(TAG, "one city=");
//
//
////            String myurl = "http://localhost/MyWebService/getUsers";
//            String myurl = "http://beta.taxistock.ru/taxik/api/client/query_cities";
//
//            URL url = new URL(myurl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            boolean isConnectWorks = availability. isNetworkAvailable();
//            String tagNet = "===================";
//            Log.d(tagNet, "one is Network work=" + isConnectWorks);
//            Log.d(tagNet, "one is Network work=" + isConnectWorks);
//
//            connection.connect();
//            InputStream inputStream = connection.getInputStream();
//
//            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
//
//            String result = ""; //InputStreamOperations.InputStreamToString(inputStream);
//
////            StringBuilder total = new StringBuilder();
//            String line;
//            while ((line = r.readLine()) != null) {
//                result +=line;
//            }
//
//            JSONObject jsonObject = new JSONObject(result);
//            JSONArray array = new JSONArray(jsonObject.getString("UserDB"));
//
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject obj = new JSONObject(array.getString(i));
//                City city = new City();
//                Log.d(TAG, "one city=" + obj.getString("city_name"));
//                Log.d(TAG, "one city_latitude=" + obj.getString("city_latitude"));
//                Log.d(TAG, "one city_longitude=" + obj.getString("city_longitude"));
////                users.add(user);
//            }
//        } catch (Exception e) {
//            Log.d(TAG, e.toString() );
//            Log.d(TAG, e.getCause().toString() );
//            Log.d(TAG, e.getMessage() );
//            Log.d(TAG, "==================" );
//            e.printStackTrace();
//
//        }finally {
//            Log.d(TAG, "one city=" );
//        }
//        return cities;
//    }

    @Override
    public void sendCitiesList(String output) {
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(output);
    }

    @Override
    public void sendCitiesList(final ArrayList<City> cities) {
        if (cities != null){
            if (cities.size() > 0){
                final Context context = this;
                this.cities = cities;
                for (int i = 0; i < this.cities.size(); i++){
                    this.cityTitles.add(this.cities.get(i).getTitle());
                }
                Log.d(TAG, "cities=" + this.cityTitles);

                ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,this.cityTitles);
//        ListAdapter adapter2 = new ArrayAdapter<String>( context, android.R.layout.simple_expandable_list_item_1, tasks);

                ListView listView = (ListView)findViewById(R.id.listView);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG, "position=" + position + " , id=" + id);
                        Intent intent = new Intent(context, GoogleMapsActivity.class);
                        City city = cities.get(position);
                        intent.putExtra("title", city.getTitle());
                        intent.putExtra("latitude", city.getLatitude());
                        intent.putExtra("longitude", city.getLongitude());
                        startActivity(intent);
                    }
                });

            }
        }

    }

//    private class Availability{
//        public boolean isNetworkAvailable() {
//            ConnectivityManager connectivityManager
//                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//        }
//    }



}
