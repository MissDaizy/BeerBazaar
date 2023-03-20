package com.diana_ukrainsky.beerbazzar.data.remote;

import com.diana_ukrainsky.beerbazzar.common.Constants;
import com.google.gson.Gson;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService INSTANCE=null;
    private JsonApiBeers jsonApiBeers;

    public static ApiService getInstance() {
        if(INSTANCE==null)
            INSTANCE=new ApiService();
        return INSTANCE;
    }
    public ApiService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        jsonApiBeers= new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build()
                .create(JsonApiBeers.class);

    }

    public JsonApiBeers getJsonApiBeers() {
        return jsonApiBeers;
    }
}
