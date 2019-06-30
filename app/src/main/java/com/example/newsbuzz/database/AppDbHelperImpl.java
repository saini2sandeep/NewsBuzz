package com.example.newsbuzz.database;

import com.example.newsbuzz.database.entity.NewsEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Singleton
public class AppDbHelperImpl implements AppDbHelper {

    final AppDatabase appDatabase;

    @Inject
    AppDbHelperImpl(AppDatabase appDataBase) {
        this.appDatabase = appDataBase;
    }

    @Override
    public Single<List<Long>> insertNewsList(List<NewsEntity> newsEntityList) {
        return Single.fromCallable(() -> appDatabase.getNewsDao().insertNewsList(newsEntityList));
    }

    @Override
    public Single<List<NewsEntity>> getAllNews() {
        return appDatabase.getNewsDao().getAllNews();
    }
}
