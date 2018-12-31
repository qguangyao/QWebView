# QWebView <br>
a basic webView <br>
## usage <br>
### in your Activity <br>
    
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

### in your layout file
    
    <?xml version="1.0" encoding="utf-8"?>
    <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    
    <com.qt.qwebview.QWebView
        android:id="@+id/mian_web_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    </android.support.constraint.ConstraintLayout>
    
### Add property to your activity that contains QWebView <br>
<AndroidManifest.xml> <br>

    android:configChanges="mcc|mnc|orientation|keyboardHidden|screenSize"
    android:windowSoftInputMode="adjustResize|stateAlwaysHidden"
  
### permissions <br>
    
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    
## License:

_[Apache License, Version 2.0](https://github.com/qguangyao/QWebView/blob/master/LICENSE)_
