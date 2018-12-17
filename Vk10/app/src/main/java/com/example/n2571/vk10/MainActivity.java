package com.example.n2571.vk10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView web;
    EditText url;
    int counter = 0;
    int urlCounter = 0;

    String previousUrl = null;
    String currentUrl = null;
    String nextUrl = null;

    boolean isPrevious = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = findViewById(R.id.editText);
        web = findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);


    }

    public void fetchUrl(View v) {
        String fullUrl = "http://" + url.getText().toString();
        if ( fullUrl.equals("http://index.html") ) {
            web.loadUrl("file:///android_asset/index.html");
            counter = 0;

            update("file:///android_asset/index.html");
            urlCounter++;
            nextUrl = null;

        }
        else {
            web.loadUrl(fullUrl);

            update(fullUrl);
            urlCounter++;
            nextUrl = null;
        }
        isPrevious = false;


    }
    public void refresh(View v) {
        if ( web.getUrl() == null ) {
            return;
        }

        web.loadUrl(web.getUrl().toString());
    }

    public void update(String url) {
        if (urlCounter == 0) {
            currentUrl = url;
            System.out.println(currentUrl);

            return;
        }
        else {
            previousUrl = currentUrl;
            currentUrl = url;
            System.out.println(previousUrl);
        }
    }


    public void jsExec(View v) {
        if (counter % 2 == 0) {
            web.evaluateJavascript("javascript:shoutOut()", null);
            counter++;
        }
        else {
            web.evaluateJavascript("javascript:initialize()", null);
            counter++;
        }
    }

    public void previous(View v) {
        if ( isPrevious ) {
            return;
        }

        if ( previousUrl == null ) {
            return;
        }

        web.loadUrl(previousUrl);
        nextUrl = currentUrl;
        currentUrl = previousUrl;
        url.setText(currentUrl.substring(7));
        isPrevious = true;
    }
    public void next(View v) {
        if ( nextUrl == null ) {
            return;
        }
        web.loadUrl(nextUrl);
        System.out.println(nextUrl);
        currentUrl = nextUrl;
        url.setText(currentUrl.substring(7));
        isPrevious = false;

    }

}
