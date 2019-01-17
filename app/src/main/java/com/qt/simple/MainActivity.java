package com.qt.simple;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.qt.qwebview.QWebView;
import com.qt.qwebview.QWebViewActivity;

public class MainActivity extends QWebViewActivity {
    QWebView webView;
    EditText editText;
    Button button;
    String homeUtl = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeUtl = getApplicationContext().getSharedPreferences("url",MODE_PRIVATE).getString("home","https://www.baidu.com");
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.mian_web_view);
        setqWebView(webView);
        webView.loadUrl(homeUtl);
//        webView.loadUrl("file:///android_asset/qecharts.html");
        editText = findViewById(R.id.address);
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = editText.getText().toString();
                if (!address.startsWith("https://")){
                    address = "https://"+address;
                }
                webView.loadUrl(address);
                hideSoftInput();
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示")
                        .setMessage("保存当前地址为主页？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getApplicationContext().getSharedPreferences("url",MODE_PRIVATE).edit().putString("home",webView.getUrl()).apply();
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
                return true;
            }
        });

    }

    protected void hideSoftInput(){
        InputMethodManager imManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
