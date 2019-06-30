package com.example.newsbuzz.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.newsbuzz.AppConstants;
import com.example.newsbuzz.R;
import com.example.newsbuzz.database.entity.NewsEntity;
import com.example.newsbuzz.databinding.ActivityMainBinding;
import com.example.newsbuzz.ui.base.BaseActivity;
import com.example.newsbuzz.ui.newsdetail.NewsDetailActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> {

    @Inject
    MainActivityViewModel mainActivityViewModel;

    @Inject
    LinearLayoutManager linearLayoutManager;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected MainActivityViewModel getViewModel() {
        return mainActivityViewModel;
    }

    @Override
    protected void initObservers() {

        mainActivityViewModel.getNewsListLiveEvent().observe(this, this::setUpNewsRv);
    }

    @Override
    protected void setUp() {

        showLoadingBar(true);
        mainActivityViewModel.fetchMovieList();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void setUpNewsRv(List<NewsEntity> newsEntityList) {

        showLoadingBar(false);

        RecyclerView newsRv = dataBinding.contentMainLayout.newsRv;
        newsRv.setLayoutManager(linearLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(newsEntityList, this::navigateToNewsDetailPage);
        newsRv.setHasFixedSize(true);
        newsRv.setAdapter(newsAdapter);
    }


    public void navigateToNewsDetailPage(NewsEntity newsEntity) {

        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(AppConstants.BUNDLE_NEWS_ITEM, newsEntity);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showLoadingBar(boolean shouldShow) {
        dataBinding.contentMainLayout.setProgressBarVisibility(shouldShow);
    }
}
