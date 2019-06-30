package com.example.newsbuzz.di.builder;

import com.example.newsbuzz.ui.MainActivity;
import com.example.newsbuzz.ui.MainActivityModule;
import com.example.newsbuzz.ui.newsdetail.NewsDetailActivity;
import com.example.newsbuzz.ui.newsdetail.NewsDetailModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
    })
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = {
            NewsDetailModule.class,
    })
    abstract NewsDetailActivity newsDetailActivity();
}