package com.example.newsbuzz.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.newsbuzz.database.AppDatabase;
import com.example.newsbuzz.datamanager.AppDataManager;
import com.example.newsbuzz.datamanager.AppDataManagerImpl;
import com.example.newsbuzz.di.ApplicationContext;
import com.example.newsbuzz.utils.rx.AppSchedulerProvider;
import com.example.newsbuzz.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Module
public class AppModule {

    @Provides
    @ApplicationContext
    Context providesContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    AppDatabase providesAppDataBase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "sampleMovieAppDB").fallbackToDestructiveMigration().build();
    }

//    @Provides
//    @Singleton
//    AppDbHelper providesAppDbHelper(AppDbHelperImpl appDbHelper) {
//        return appDbHelper;
//    }


//    @Provides
//    @Singleton
//    AppDatabase providesAppDataBase(@ApplicationContext Context context) {
//        return Room.databaseBuilder(context, AppDatabase.class, "FeedAgeApp").fallbackToDestructiveMigration().build();
//    }


//    @Provides
//    @Singleton
//    Gson provideGsonBuilder() {
//
//        return new GsonBuilder().create();
//    }
//
//    @Provides
//    @Singleton
//    AppDataManager provideAppDatabaseManager(AppDataManagerImpl appDataManager) {
//        return appDataManager;
//    }
//
//    @Provides
//    @Singleton
//    SchedulerProvider provideSchedulerProvider() {
//        return new AppSchedulerProvider();
//    }

}
