package com.qt.qwebview.interfaces;

import android.view.View;
import android.webkit.WebChromeClient;

public interface ICustomView {
    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);
    boolean onHideCustomView();
}
