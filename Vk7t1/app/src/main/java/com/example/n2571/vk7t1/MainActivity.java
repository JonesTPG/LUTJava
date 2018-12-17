package com.example.n2571.vk7t1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;

    TextView text;
    EditText textField;
    EditText fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        text = findViewById(R.id.textView);
        fileName = findViewById(R.id.editText3);
        textField = findViewById(R.id.editText2);
        textField.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                text.setText(textField.getText());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }



    public void readFile(View v) {

        String file = fileName.getText().toString();
        try {
            InputStream is = context.openFileInput(file);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s = "";

            while ( (s=br.readLine()) != null ) {
                text.setText(s);
            }
            is.close();

        } catch(IOException e) {
            Log.e("IOException", "Virhe syötteessä!");

        } finally {
            System.out.println("LUETTU");
        }
    }

    public void writeFile(View v) {
        String s = textField.getText().toString();

        String file = fileName.getText().toString();
        try {

            OutputStreamWriter ow = new OutputStreamWriter(context.openFileOutput(file,
                    Context.MODE_PRIVATE));

            ow.write(s);
            ow.close();

        } catch(IOException e) {
            Log.e("IOException", "Virhe syötteessä!");

        } finally {
            System.out.println("KIRJOITETTU");
        }
    }
}
