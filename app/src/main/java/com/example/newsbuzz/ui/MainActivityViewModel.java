package com.example.newsbuzz.ui;

import android.util.Log;

import com.example.newsbuzz.datamanager.AppDataManager;
import com.example.newsbuzz.ui.base.BaseViewModel;
import com.example.newsbuzz.database.entity.NewsEntity;
import com.example.newsbuzz.ui.model.NewsApiResponse;
import com.example.newsbuzz.utils.livedata.SingleLiveEvent;
import com.example.newsbuzz.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Response;

/**
 * Created by sandeepsaini on 29,June,2019
 */
public class MainActivityViewModel extends BaseViewModel {

    private SingleLiveEvent<List<NewsEntity>> newsListLiveEvent = new SingleLiveEvent<>();


    public MainActivityViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        super(appDataManager, schedulerProvider);
    }

    public void fetchMovieList() {

        Disposable disposable = getAppDataManager().fetchNewsList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Response<NewsApiResponse>>() {
                    @Override
                    public void accept(Response<NewsApiResponse> newsApiResponseResponse) throws Exception {

                        if (newsApiResponseResponse.body() != null) {
                            processNewsList(newsApiResponseResponse.body().getNewsEntityList());
                        } else {
                            showToastMessage(newsApiResponseResponse.message());
                            fetchDataFromDb();
                        }
                    }
                }, throwable -> {
                    fetchDataFromDb();
                    showToastMessage(throwable.getMessage());
                });

        getCompositeDisposable().add(disposable);
    }

    private void processNewsList(List<NewsEntity> newsEntityList) {

        if (!newsEntityList.isEmpty()) {
            saveDataToDB(newsEntityList);
            newsListLiveEvent.setValue(newsEntityList);
        } else {

        }
    }


    public SingleLiveEvent<List<NewsEntity>> getNewsListLiveEvent() {
        return newsListLiveEvent;
    }

    private void saveDataToDB(List<NewsEntity> newsEntityList) {
        Disposable disposable = getAppDataManager().insertNewsList(newsEntityList)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(successValue -> Log.d("DataBase : ", "saving success"),
                        throwable -> Log.d("DataBase : ", throwable.getMessage()));

        getCompositeDisposable().add(disposable);
    }

    private void fetchDataFromDb() {

        Disposable disposable = getAppDataManager().getAllNews()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<NewsEntity>>() {
                    @Override
                    public void accept(List<NewsEntity> newsEntityList) throws Exception {
                        newsListLiveEvent.setValue(newsEntityList);
                    }
                }, throwable -> {
                    Log.d("DataBase : ", throwable.getMessage());
                });
        getCompositeDisposable().add(disposable);

    }
}
