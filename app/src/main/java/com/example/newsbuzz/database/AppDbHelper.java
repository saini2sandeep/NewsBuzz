package com.example.newsbuzz.database;

import com.example.newsbuzz.database.entity.NewsEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by sandeepsaini on 29,June,2019
 */
public interface AppDbHelper {

    Single<List<Long>> insertNewsList(List<NewsEntity> newsEntityList);

    Single<List<NewsEntity>> getAllNews();
}
