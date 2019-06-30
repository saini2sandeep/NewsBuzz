package com.example.newsbuzz.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.newsbuzz.database.entity.NewsEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeepsaini on 29,June,2019
 */
public class NewsApiResponse  implements Parcelable {

    @SerializedName("status")
    String status;

    @SerializedName("totalResults")
    String totalResults;

    @SerializedName("articles")
    List<NewsEntity> newsEntityList;

    public NewsApiResponse() {
    }

    protected NewsApiResponse(Parcel in) {
        status = in.readString();
        totalResults = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(totalResults);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsApiResponse> CREATOR = new Creator<NewsApiResponse>() {
        @Override
        public NewsApiResponse createFromParcel(Parcel in) {
            return new NewsApiResponse(in);
        }

        @Override
        public NewsApiResponse[] newArray(int size) {
            return new NewsApiResponse[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsEntity> getNewsEntityList() {
        return newsEntityList;
    }

    public void setNewsEntityList(List<NewsEntity> newsEntityList) {
        this.newsEntityList = newsEntityList;
    }
}
