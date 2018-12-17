package com.example.n2571.vk11;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Display;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.n2571.vk11.R;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    TextView textView;
    EditText editori;
    boolean editAllowed;


    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);

        textView = (TextView) findViewById(R.id.TextView);
        editori = (EditText) findViewById(R.id.EditText2);
        //textView.setText("Testaillaan"); //set text for text view

        textView.setTypeface(null, Typeface.ITALIC);
        textView.setTextSize(15);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        if ( menuItem.getTitle().equals("tummennus") ) {
                            if ( textView.getTypeface().isBold() ) {
                                textView.setTextAppearance(R.style.normalText);
                            }
                            else {
                                textView.setTextAppearance(R.style.boldText);
                            }
                        }

                        if ( menuItem.getTitle().equals("fonttikoko") ) {
                            if (textView.getTextSize() == 52.5) {
                                textView.setTextSize(20);
                            }
                            else {
                                textView.setTextSize(15);
                            }
                        }

                        if ( menuItem.getTitle().equals("salli muokkaus") ) {
                            editori.setInputType(InputType.TYPE_CLASS_TEXT);
                            System.out.println("saa muokata");
                            menuItem.setTitle("estä muokkaus");
                            editAllowed = true;
                            return true;
                        }

                        if ( menuItem.getTitle().equals("estä muokkaus") ) {
                            editori.setInputType(InputType.TYPE_NULL);
                            editori.setKeyListener(null);
                            textView.setText(editori.getText());
                            System.out.println("ei saa muokata");
                            menuItem.setTitle("salli muokkaus");
                            editAllowed = false;

                        }

                        if ( menuItem.getTitle().equals("riviväli") ) {
                            if ( textView.getLineSpacingMultiplier() == 1 ) {
                                textView.setLineSpacing(0, 3);
                            }
                            else {
                                textView.setLineSpacing(0, 1);
                            }
                        }

                        if ( menuItem.getTitle().equals("caps lock") ) {
                            if ( textView.isAllCaps() ) {
                                textView.setAllCaps(false);
                            }
                            else {
                                textView.setAllCaps(true);
                            }
                        }


                        if ( menuItem.getTitle().equals("ruotsi") ) {

                        }

                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}