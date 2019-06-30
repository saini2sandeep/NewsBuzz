package com.example.newsbuzz.ui.newsdetail;

import com.example.newsbuzz.datamanager.AppDataManager;
import com.example.newsbuzz.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Module
public class NewsDetailModule {

    @Provides
    NewsDetailViewModel provideNewsDetailViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        return new NewsDetailViewModel(appDataManager, schedulerProvider);
    }

}
