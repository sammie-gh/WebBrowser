package com.learnoset.webbrowser;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BrowserView extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_view2);

        final WebView webView = findViewById(R.id.webView);
        final EditText urlET = findViewById(R.id.urlET);
        final ImageView homeBtn = findViewById(R.id.homeIcon);

        url = getIntent().getStringExtra("url");

        final String urlData = url.substring(0, 4);

        if (!urlData.contains("www.")) {
            url = "www.google.com/search?q=" + url;
        }

        urlET.setText(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                urlET.setText(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        urlET.setOnEditorActionListener((v, actionId, event) -> {

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                final String urlTxt = urlET.getText().toString();

                if (!urlTxt.isEmpty()) {

                    final String urlData1 = urlTxt.substring(0, 4);

                    if (!urlData1.contains("www.")) {
                        url = "www.google.com/search?q=" + url;
                    } else {
                        url = urlTxt;
                    }

                }
            }
            return false;
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}