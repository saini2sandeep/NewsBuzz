package com.example.newsbuzz.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.example.newsbuzz.datamanager.AppDataManager;
import com.example.newsbuzz.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel providesMainActivityViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        return new MainActivityViewModel(appDataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideGridLayoutManager(MainActivity mainActivity) {
        return new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
    }
}

