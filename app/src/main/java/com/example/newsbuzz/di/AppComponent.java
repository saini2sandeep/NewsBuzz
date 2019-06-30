package com.example.newsbuzz.di;

import android.app.Application;

import com.example.newsbuzz.NewsBuzzApp;
import com.example.newsbuzz.di.builder.ActivityBuilderModule;
import com.example.newsbuzz.di.module.AppModule;
import com.example.newsbuzz.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Singleton
@Component(modules = {
        NetworkModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
        AndroidInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(NewsBuzzApp newsBuzzApp);
}