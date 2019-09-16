package com.example.barcodegender.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static String BASE_URL = "https://script.google.com/macros/s/AKfycbyURj7T8d8gz6WdgHWy3l6XtLOVAN0CCc-EFufnAw/";
    private static String USER_EDIT_BASE_URL = "";

    private static ApiService apiService;

    private static HttpManager instance;

    private HttpManager() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    public ApiService getService() {
        return apiService;
    }

    public void setBaseUrl(String url) {
        BASE_URL = url;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public String getUserEditBaseUrl() {
        return USER_EDIT_BASE_URL;
    }

    public void setUserEditBaseUrl(String url) throws IllegalArgumentException {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit;
        if (url.length() > 0) {
            if (url.contains("http://") || url.contains("https://")) {
                USER_EDIT_BASE_URL = url;
            } else {
                USER_EDIT_BASE_URL = "http://" + url;
            }

            if (!url.endsWith("/")) {
                USER_EDIT_BASE_URL += "/";
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(USER_EDIT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        } else {
            USER_EDIT_BASE_URL = "";
            
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        apiService = retrofit.create(ApiService.class);
    }
}