package com.example.newsbuzz.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.newsbuzz.AppConstants;
import com.example.newsbuzz.database.dao.NewsDao;
import com.example.newsbuzz.database.entity.NewsEntity;

/**
 * Created by sandeepsaini on 29,June,2019
 */

@Database(entities = {NewsEntity.class},version = AppConstants.APP_DB_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NewsDao getNewsDao();
}
