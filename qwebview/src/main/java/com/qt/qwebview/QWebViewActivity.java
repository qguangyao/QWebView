package com.qt.qwebview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Toast;

import com.qt.qwebview.interfaces.IFileChooser;

public class QWebViewActivity extends Activity implements IFileChooser {

    public static final int REQUEST_FILE = 0x101;

    private QWebView qWebView;
    private ValueCallback<Uri[]> filePathCallback;

    public void setqWebView(QWebView qWebView) {
        this.qWebView = qWebView;
        if (this.qWebView != null) {
            this.qWebView.setFileChooser(this);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            filePathCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
        } else {
            QToast("系统版本过老，无法打开文件");
        }
    }

    @Override
    public void showFileChooser(ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        this.filePathCallback = filePathCallback;
        try {
            Intent intent = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                intent = fileChooserParams.createIntent();
                startActivityForResult(intent, REQUEST_FILE);
            } else {
                QToast("系统版本过老，无法打开文件");
            }

        } catch (Exception e) {
            QToast("打开文件失败");
        }
    }

    @Override
    public void onBackPressed() {
        if (qWebView != null && qWebView.canGoBack()) {
            qWebView.goBack();
            return;
        }
        super.onBackPressed();

    }

    public void QToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
