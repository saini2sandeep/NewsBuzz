package com.example.newsbuzz.network;

import com.example.newsbuzz.ui.model.NewsApiResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Singleton
public class AppApiHelperImpl implements AppApiHelper {

    private NewsApiService newsApiService;

    @Inject
    AppApiHelperImpl(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @Override
    public Single<Response<NewsApiResponse>> fetchNewsList() {
        return newsApiService.fetchNewsList();
    }

//    @Override
//    public Single<Response<MoviesListResponse>> fetchMovieList(String type, long page) {
//        return movieApiService.fetchMoviesByType(type, page);
//    }
}