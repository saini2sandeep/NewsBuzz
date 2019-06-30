package com.example.newsbuzz.datamanager;

import com.example.newsbuzz.database.AppDbHelper;
import com.example.newsbuzz.database.entity.NewsEntity;
import com.example.newsbuzz.network.AppApiHelper;
import com.example.newsbuzz.ui.model.NewsApiResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Singleton
public class AppDataManagerImpl implements AppDataManager {

    private AppApiHelper appApiHelper;
    private AppDbHelper appDbHelper;


    @Inject
    AppDataManagerImpl(AppApiHelper appApiHelper, AppDbHelper appDbHelper) {
        this.appApiHelper = appApiHelper;
        this.appDbHelper = appDbHelper;
    }

    @Override
    public Single<Response<NewsApiResponse>> fetchNewsList() {
        return appApiHelper.fetchNewsList();
    }

    @Override
    public Single<List<Long>> insertNewsList(List<NewsEntity> newsEntityList) {
        return appDbHelper.insertNewsList(newsEntityList);
    }

    @Override
    public Single<List<NewsEntity>> getAllNews() {
        return appDbHelper.getAllNews();
    }
}
