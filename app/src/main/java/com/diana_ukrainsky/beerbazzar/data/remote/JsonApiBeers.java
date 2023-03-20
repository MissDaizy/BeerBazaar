package com.diana_ukrainsky.beerbazzar.data.remote;

import com.diana_ukrainsky.beerbazzar.common.Constants;
import com.diana_ukrainsky.beerbazzar.data.model.Beer;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonApiBeers {

        @GET(Constants.ALL_BEERS_ENDPOINT)
        Observable<List<Beer>> getAllBeers();

        @GET("{beer_endpoint}")
        Observable<Beer> getBeerDetails(
                @Path("beer_endpoint")String beerEndpoint
        );
}
