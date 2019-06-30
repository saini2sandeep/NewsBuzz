package com.example.newsbuzz.utils.view;

/**
 * Created by sandeepsaini on 30,June,2019
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class CustomWebViewClient extends WebViewClient {

    private Context mContext;
    private WebViewLoadedListener webViewLoadedListener;

    public CustomWebViewClient(Context context, WebViewLoadedListener webViewLoadedListener) {
        this.mContext = context;
        this.webViewLoadedListener = webViewLoadedListener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return false;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {

        webViewLoadedListener.onWedViewLoaded();
        view.clearCache(true);
        view.clearHistory();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        //do nothing
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Toast.makeText(mContext, description, Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Toast.makeText(mContext, error.getDescription(), Toast.LENGTH_LONG).show();
    }

    public interface WebViewLoadedListener {
        void onWedViewLoaded();
    }
}
