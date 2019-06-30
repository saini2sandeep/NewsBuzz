package com.example.newsbuzz.ui.newsdetail;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.newsbuzz.AppConstants;
import com.example.newsbuzz.R;
import com.example.newsbuzz.database.entity.NewsEntity;
import com.example.newsbuzz.databinding.ActivityNewsDetailBinding;
import com.example.newsbuzz.ui.base.BaseActivity;
import com.example.newsbuzz.utils.network.NetworkUtils;
import com.example.newsbuzz.utils.view.CustomWebViewClient;

import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class NewsDetailActivity extends BaseActivity<ActivityNewsDetailBinding, NewsDetailViewModel> {

    @Inject
    NewsDetailViewModel newsDetailViewModel;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected NewsDetailViewModel getViewModel() {
        return newsDetailViewModel;
    }

    @Override
    protected void initObservers() {

    }

    @Override
    protected void setUp() {


//        Toolbar toolbar = dataBinding.toolbar;
//        toolbar.setTitle(R.string.news_detail);
//        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
//
//        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            NewsEntity newsEntity = bundle.getParcelable(AppConstants.BUNDLE_NEWS_ITEM);
            checkNetworkAndLoadData(newsEntity);
        }
    }


    private void checkNetworkAndLoadData(NewsEntity newsEntity) {

        NetworkUtils networkUtils = new NetworkUtils(this);

        if (networkUtils.isNetworkConnected()) {

            dataBinding.setIsOffline(false);
            loadWebView(newsEntity.getUrl());
        } else {

            dataBinding.setIsOffline(true);
            dataBinding.offlineNewsDetailLayout.setNewsEntity(newsEntity);
        }
    }

    private void loadWebView(String webUrl) {

        WebView webView = dataBinding.newsWv;

        //  Setting the main content data
        if (!TextUtils.isEmpty(webUrl)) {

            webView.setBackgroundColor(Color.TRANSPARENT);

            webView.setWebViewClient(
                    new CustomWebViewClient(dataBinding.newsWv.getContext(),
                            () -> {
                                dataBinding.progressBar.setVisibility(GONE);
                                webView.setVisibility(VISIBLE);
                                webView.invalidate();
                            }));

            webView.loadUrl(webUrl);


        } else {
            webView.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
