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
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class FirstMainActivity extends AppCompatActivity implements SendingCitiesListResult {

    private static final String TAG = "==+==main==+==";

    private ArrayList<City> cities;
    private ArrayList<String> cityTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_main);
        refreshButton(null);
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

    public void refreshButton(View v){
        Log.d(TAG, "second button");
        RetrieveCitiesTask retrieveCitiesTask = new RetrieveCitiesTask(this);
        String url_get_json = getString(R.string.url_get_json_cities);
        retrieveCitiesTask.execute(url_get_json);
    }

    @Override
    public void sendCitiesList(final ArrayList<City> cities) {
        if (cities != null){
            if (cities.size() > 0){
                final Context context = this;
                if (this.cities != null){
                    this.cities.clear();
                    this.cityTitles.clear();
                }
                this.cities = cities;
                for (int i = 0; i < this.cities.size(); i++){
                    this.cityTitles.add(this.cities.get(i).getTitle());
                }
                Log.d(TAG, "cities=" + this.cityTitles);
                ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,this.cityTitles);
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


}
