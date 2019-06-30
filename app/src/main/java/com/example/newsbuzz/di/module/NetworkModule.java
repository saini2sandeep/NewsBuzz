package com.example.newsbuzz.di.module;

import android.app.Application;
import android.content.Context;

import com.example.newsbuzz.database.AppDbHelper;
import com.example.newsbuzz.database.AppDbHelperImpl;
import com.example.newsbuzz.datamanager.AppDataManager;
import com.example.newsbuzz.datamanager.AppDataManagerImpl;
import com.example.newsbuzz.di.ApplicationContext;
import com.example.newsbuzz.network.ApiKeyInterceptor;
import com.example.newsbuzz.network.AppApiHelper;
import com.example.newsbuzz.network.AppApiHelperImpl;
import com.example.newsbuzz.network.NetworkConstants;
import com.example.newsbuzz.network.NetworkInterceptor;
import com.example.newsbuzz.network.NewsApiService;
import com.example.newsbuzz.utils.rx.AppSchedulerProvider;
import com.example.newsbuzz.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.newsbuzz.network.NetworkConstants.BASE_URL;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Module
public class NetworkModule {


    @Provides
    @Singleton
    Gson provideGsonBuilder() {

        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    NetworkInterceptor provideNetworkInterceptor(Application application) {

        return new NetworkInterceptor(application.getApplicationContext());
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@ApplicationContext Context context, NetworkInterceptor networkInterceptor) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(networkInterceptor);
        okHttpClient.addInterceptor(new ChuckInterceptor(context));
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        okHttpClient.addInterceptor(new ApiKeyInterceptor());
        okHttpClient.connectTimeout(NetworkConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(NetworkConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(NetworkConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    NewsApiService provideNewsApiService(OkHttpClient okHttpClient, Gson gson, @ApplicationContext Context context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(NewsApiService.class);
    }

    @Provides
    @Singleton
    AppApiHelper provideAppApiHelper(AppApiHelperImpl appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    AppDataManager provideAppDatabaseManager(AppDataManagerImpl appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    AppDbHelper provideAppDbHelper(AppDbHelperImpl dbHelper) {
        return dbHelper;
    }
}
