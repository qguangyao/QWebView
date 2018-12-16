package com.qt.qwebview.interfaces;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;

public interface IFileChooser {
    void showFileChooser(ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams);
}
