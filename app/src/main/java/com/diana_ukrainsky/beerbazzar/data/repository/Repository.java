package com.diana_ukrainsky.beerbazzar.data.repository;

import com.diana_ukrainsky.beerbazzar.data.model.Beer;
import com.diana_ukrainsky.beerbazzar.data.remote.ApiService;
import com.diana_ukrainsky.beerbazzar.data.remote.JsonApiBeers;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private static Repository INSTANCE = null;
    private final JsonApiBeers jsonApiBeers;

    public static Repository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Repository();

        return INSTANCE;
    }
    private Repository( ) {
        this.jsonApiBeers = ApiService.getInstance().getJsonApiBeers();
    }

    public Observable<List<Beer>> getAllBeers(){
        return jsonApiBeers.getAllBeers();
    }
    public  Observable<Beer> getBeerDetails(String contactId) {
        return jsonApiBeers.getBeerDetails(contactId);
    }
}

