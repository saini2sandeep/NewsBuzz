package com.example.newsbuzz.network;

import com.example.newsbuzz.ui.model.NewsApiResponse;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by sandeepsaini on 29,June,2019
 */
public interface AppApiHelper {

    Single<Response<NewsApiResponse>> fetchNewsList();

}
