package com.qt.simple;

import android.os.Bundle;

import com.qt.qwebview.QWebView;
import com.qt.qwebview.QWebViewActivity;

public class MainActivity extends QWebViewActivity {

    QWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.mian_web_view);
        setqWebView(webView);
        webView.loadUrl("https://www.baidu.com");
    }
}
