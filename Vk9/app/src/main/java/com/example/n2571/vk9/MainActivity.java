package com.example.n2571.vk9;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText date;
    EditText timeStart;
    EditText timeEnd;
    RecyclerView rv;
    MyRecyclerViewAdapter adapter;

    ArrayList<String> movies = new ArrayList<String>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = (EditText) findViewById(R.id.editText5);
        timeStart = (EditText) findViewById(R.id.editText6);
        timeEnd = (EditText) findViewById(R.id.editText7);
        rv = (RecyclerView) findViewById(R.id.recycler);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, movies);
        rv.setAdapter(adapter);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Areas.readAreasXML();
        ArrayList<String> spinnerItems = Areas.getTheaterNames();

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        // Specify the layout to use when the list of choices appears
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(dataAdapter);
    }

    public void updateSearch(View v) {

        String theater = spinner.getSelectedItem().toString();
        String dateString = date.getText().toString();
        String startTime = timeStart.getText().toString();
        String endTime = timeEnd.getText().toString();
        String url;

        if ( dateString.equals("") && theater.equals("Valitse alue/teatteri") ) {
            System.out.println("molemmat tyhjiä");
            url = "http://www.finnkino.fi/xml/Schedule";
            movies = Areas.readScheduleXML(url);


        }
        else if ( dateString.equals("") && !theater.equals("Valitse alue/teatteri") ) {
            System.out.println("konsertti valittu");
            String id = "test";
            url = Areas.buildUrl(theater, null);
            movies = Areas.readScheduleXML(url);


        }
        else if ( !dateString.equals("") && theater.equals("Valitse alue/teatteri") ) {
            System.out.println("päivämäärä valittu");
            url = Areas.buildUrl(null, dateString);
            movies = Areas.readScheduleXML(url);


        }
        else {
            System.out.println("molemmat valittu");
            url = Areas.buildUrl(theater, dateString);
            movies = Areas.readScheduleXML(url);


        }

        if ( startTime.equals("") && endTime.equals("") ) {
            adapter.notifyDataSetChanged();
            adapter = new MyRecyclerViewAdapter(this, movies);
            rv.setAdapter(adapter);
        }

        else {
            movies = Areas.filterTimes(movies, url, startTime, endTime);
            adapter.notifyDataSetChanged();
            adapter = new MyRecyclerViewAdapter(this, movies);
            rv.setAdapter(adapter);
        }

    }
}
