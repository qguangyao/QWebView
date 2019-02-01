package com.qt.qbrowser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qt.qwebview.QWebView;
import com.qt.qwebview.QWebViewActivity;

import java.util.Map;

public class MainActivity extends QWebViewActivity {

    private QWebView webView;
    private EditText main_address;
    private Button main_go;
    private LinearLayout main_url_layout;
    private DrawerLayout main_drawer;
    private NavigationView navigationView;
    private String homeUtl = "https://www.baidu.com";
    private long clickTime;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeUtl = getHomeUtl();
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.mian_web_view);
        setqWebView(webView);
        webView.loadUrl(homeUtl);
//        webView.loadUrl("file:///android_asset/qecharts.html");

        main_address = findViewById(R.id.main_address);
        main_go = findViewById(R.id.main_go);
        main_url_layout = findViewById(R.id.main_url_layout);
        main_drawer = findViewById(R.id.main_drawer);
        navigationView = findViewById(R.id.main_navigation);

        main_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = main_address.getText().toString();
                if (!address.startsWith("https://") && !address.startsWith("http://")) {
                    address = "http://" + address;
                }
                webView.loadUrl(address);
                hideSoftInput();
            }
        });

        main_go.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示")
                        .setMessage("保存当前地址为主页？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setHomeUtl(webView.getUrl());
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

        main_address.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    String address = main_address.getText().toString();
                    if (!address.startsWith("https://") && !address.startsWith("http://")) {
                        address = "http://" + address;
                    }
                    webView.loadUrl(address);
                    hideSoftInput();
                    return true;
                }
                return false;
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.menu_add:
                        setBookMark(webView.getTitle(), webView.getUrl());
                        main_drawer.closeDrawers();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onHideCustomView() {
        if (main_url_layout != null) {
            main_url_layout.setVisibility(View.VISIBLE);
        }
        return super.onHideCustomView();
    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        if (main_url_layout != null) {
            main_url_layout.setVisibility(View.GONE);
        }
        super.onShowCustomView(view, callback);
    }

    public String getHomeUtl() {
        return getApplicationContext().getSharedPreferences("url", MODE_PRIVATE).getString("home", "https://www.baidu.com");
    }

    public void setHomeUtl(String url) {
        getApplicationContext().getSharedPreferences("url", MODE_PRIVATE).edit().putString("home", url).apply();
    }

    public void setBookMark(String name, String url) {
        getApplicationContext().getSharedPreferences("url", MODE_PRIVATE).edit().putString(name, url).apply();
    }

    public Map<String, ?> getBookMarks() {
        return getApplicationContext().getSharedPreferences("url", MODE_PRIVATE).getAll();
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        main_address.setText(view.getUrl());
    }

    protected void hideSoftInput() {
        InputMethodManager imManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onBackPressed() {
        if (!webView.canGoBack()) {
            long current = System.currentTimeMillis();
            if (current - clickTime > 2000) {
                QToast("再按一次推出程序");
                clickTime = current;
                return;
            }
        }
        super.onBackPressed();
    }
}
