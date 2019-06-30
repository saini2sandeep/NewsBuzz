package com.example.newsbuzz.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.newsbuzz.database.entity.NewsEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertNewsList(List<NewsEntity> newsList);

    @Query("DELETE FROM news")
    void flushTable();

    @Query("SELECT * FROM news")
    Single<List<NewsEntity>> getAllNews();
}
