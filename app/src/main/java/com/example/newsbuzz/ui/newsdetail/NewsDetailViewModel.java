package com.example.newsbuzz.ui.newsdetail;

import com.example.newsbuzz.datamanager.AppDataManager;
import com.example.newsbuzz.ui.base.BaseViewModel;
import com.example.newsbuzz.utils.rx.SchedulerProvider;


/**
 * Created by sandeepsaini on 29,June,2019
 */
public class NewsDetailViewModel extends BaseViewModel {

    public NewsDetailViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        super(appDataManager, schedulerProvider);
    }
}
