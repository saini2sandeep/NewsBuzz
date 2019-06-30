package com.example.newsbuzz.network;

import com.example.newsbuzz.ui.model.NewsApiResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by sandeepsaini on 29,June,2019
 */
public interface NewsApiService {

    @GET("v2/top-headlines?country=us")
    Single<Response<NewsApiResponse>> fetchNewsList();


}
