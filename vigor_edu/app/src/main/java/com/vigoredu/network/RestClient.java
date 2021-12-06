package com.vigoredu.network;

import com.vigoredu.BuildConfig;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RestClient {

    private static RestClient ourInstance;
    private Retrofit retrofit;

    static RestClient getInstance() {
        if (ourInstance == null) ourInstance = new RestClient();
        return ourInstance;
    }

    private RestClient() {
        createRetrofitInstance();
    }

    /**
     * Create Retrofit Instance
     */
    private void createRetrofitInstance() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(getBuilder().build())
                .build();

    }


    @NonNull
    private OkHttpClient.Builder getBuilder() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(BuildConfig.LOGGING ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // Setting timeout
        httpClient.readTimeout(NetworkConstants.RETROFIT_API_SERVICE_SOCKET_TIMEOUT, TimeUnit.SECONDS);
        httpClient.connectTimeout(NetworkConstants.RETROFIT_API_SERVICE_SOCKET_TIMEOUT, TimeUnit.SECONDS);
        httpClient.followRedirects(true);
        httpClient.followSslRedirects(true);

        httpClient.addNetworkInterceptor(new HttpInterceptor(httpClient.build()));

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        return httpClient;
    }

    private Retrofit getRetrofit() {
        return retrofit;
    }

    RestApiStore getRestAPIStore() {
        return getRetrofit().create(RestApiStore.class);
    }


}
