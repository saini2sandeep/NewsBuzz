package com.example.newsbuzz.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.newsbuzz.di.ApplicationContext;

import javax.inject.Singleton;

/**
 * Created by sandeepsaini on 29,June,2019
 */
@Singleton
public class NetworkUtils {

    private Context context;

    public NetworkUtils(@ApplicationContext Context context) {
        this.context = context;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
