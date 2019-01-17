package com.qt.qwebview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.qt.qwebview.interfaces.ICustomView;
import com.qt.qwebview.interfaces.IFileChooser;
import com.qt.qwebview.interfaces.ITitleReceiver;

public class QWebView extends WebView {

    private IFileChooser fileChooser;
    private ICustomView customView;
    private ITitleReceiver titleReceiver;

    private QWebViewClient webViewClient;
    private QWebChromeClient webChromeClient;

    public QWebView(Context context) {
        super(context);
        init(context);
    }

    public QWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public QWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.webViewClient = new QWebViewClient(this);
        this.webChromeClient = new QWebChromeClient(this);
//        this.downloadListener = newDownloadListener(this.context);

        setWebViewClient(webViewClient);
        setWebChromeClient(webChromeClient);
        initWebSetting();
    }

    private void initWebSetting(){
        //设置水平滚动条
        setHorizontalScrollBarEnabled(true);
        //设置竖直滚动条
        setVerticalScrollBarEnabled(true);

        WebSettings settings = getSettings();
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//不缓存
        //缩放操作
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //支持javascript
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置可以访问磁盘相关
        settings.setLoadsImagesAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBlockNetworkImage(false);//阻挡加载图片
        //最大缓存设置 5M
        settings.setAppCacheEnabled(true);
        settings.setAppCacheMaxSize(5242880);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true);
        }
        //设置界面字体
//        settings.setTextSize(WebSettings.TextSize.NORMAL);//这个api已经弃用了注释掉先
        //地理位置信息
        settings.setGeolocationEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        requestFocus();
        //防止js注入安全措施
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            removeJavascriptInterface("searchBoxJavaBridge_");
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
    }

    @Override
    public synchronized void destroy() {
        stopLoading();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            onPause();
        }
        clearHistory();
        setVisibility(GONE);
        removeAllViews();
        destroyDrawingCache();
        super.destroy();
    }

    public void setFileChooser(IFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public IFileChooser getFileChooser() {
        return fileChooser;
    }

    public ICustomView getCustomView() {
        return customView;
    }

    public void setCustomView(ICustomView customView) {
        this.customView = customView;
    }

    public ITitleReceiver getTitleReceiver() {
        return titleReceiver;
    }

    public void setTitleReceiver(ITitleReceiver titleReceiver) {
        this.titleReceiver = titleReceiver;
    }
}
