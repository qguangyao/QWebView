package com.qt.qwebview;

import android.net.Uri;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class QWebChromeClient extends WebChromeClient {
    private final QWebView qWebView;

    public QWebChromeClient(QWebView qWebView) {
        super();
        this.qWebView = qWebView;
    }

    @Override
    public void onProgressChanged(WebView view, int progress) {
        super.onProgressChanged(view, progress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        qWebView.getTitleReceiver().onReceivedTitle(view,title);
    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        qWebView.getCustomView().onShowCustomView(view,callback);
    }

    @Override
    public void onHideCustomView() {
        qWebView.getCustomView().onHideCustomView();
        super.onHideCustomView();
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        if (qWebView !=null){
            qWebView.getFileChooser().showFileChooser(filePathCallback,fileChooserParams);
        }
        return true;
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
        super.onGeolocationPermissionsShowPrompt(origin, callback);
    }
}