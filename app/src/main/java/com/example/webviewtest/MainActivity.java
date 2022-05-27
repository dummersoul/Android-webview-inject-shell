package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    WebView webView;
    View a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        webView.addJavascriptInterface(MainActivity.this, "main");

//        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("file:///android_asset/js.html");


    }

    public void javaCallJs(View v){
        webView.loadUrl("javascript:javaCallJs(" + "'Message From Java'" + ")", null);
    }

    @JavascriptInterface
    public void jsCallJava(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }





}