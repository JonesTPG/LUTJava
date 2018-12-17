package com.example.n2571.vk8;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {


    SeekBar addMoney;
    TextView money;
    TextView infoText;
    TextView putMoney;
    BottleDispenser limukone = BottleDispenser.getInstance();
    Spinner spinner;
    Spinner spinner2;
    Button receipt;
    Context context = null;
    String name;
    String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        System.out.println(context.getFilesDir());


        ///data/user/0/com.example.n2571.vk8/files

        addMoney = (SeekBar)findViewById(R.id.seekBar);

        addMoney.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                putMoney.setText("Laita rahaa:" + (double) progress);
                receipt.setVisibility(View.INVISIBLE);


            }
        });

        money = (TextView)findViewById(R.id.textView5);
        infoText = (TextView)findViewById(R.id.textView6);
        putMoney = (TextView)findViewById(R.id.textView3);
        receipt = (Button)findViewById(R.id.button3);



        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bottles, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.sizes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);



    }

    public void addMoney(View v) {
            double addedMoney = (double) addMoney.getProgress();
            limukone.addMoney(addedMoney);
            infoText.setText("Rahaa laitettu " + addedMoney + "€");
            money.setText("Koneessa on rahaa " + round(limukone.money,2) + "€");
            receipt.setVisibility(View.INVISIBLE);
    }

    public void buyBottleById(View v) {
            name = spinner.getSelectedItem().toString();
            size = spinner2.getSelectedItem().toString();
            String bottleId = name+size;
            String status = limukone.buyBottleById(bottleId);
            infoText.setText(status);
            if (status.equals("Ostaminen onnistui.")) {
                receipt.setVisibility(View.VISIBLE);
            }

            else {
                receipt.setVisibility(View.INVISIBLE);
            }
            money.setText("Koneessa on rahaa " + round(limukone.money, 2) + "€");

    }

    public void receipt(View v) {
            receipt.setVisibility(View.INVISIBLE);
            writeFile(name, size);
            infoText.setText("Kuitti tallennettu tiedostoon kuitti.txt");
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void writeFile(String name, String size) {


        String file = "kuitti.txt";
        try {

            OutputStreamWriter ow = new OutputStreamWriter(context.openFileOutput(file,
                    Context.MODE_PRIVATE));

            ow.write("KUITTI\n" + name + " " + size + " ostettu.");
            ow.close();

        } catch(IOException e) {
            Log.e("IOException", "Virhe syötteessä!");

        } finally {
            System.out.println("KIRJOITETTU");
            return;
        }


    }



}



