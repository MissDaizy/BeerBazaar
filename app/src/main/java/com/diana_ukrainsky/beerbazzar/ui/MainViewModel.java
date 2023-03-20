package com.diana_ukrainsky.beerbazzar.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diana_ukrainsky.beerbazzar.common.Constants;
import com.diana_ukrainsky.beerbazzar.data.model.Beer;
import com.diana_ukrainsky.beerbazzar.data.repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Beer>> beerListLiveData;
    private MutableLiveData<Beer> beerDetailsLiveData;
    private List<Beer> allBeersList;

    // Variable for hiding and showing the loading spinner
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<String> currentSearchTextLiveData;
    private CompositeDisposable disposables;
    private Repository repository;
    private FilterType selectedFilter;
    private SortType selectedSort;


    public MainViewModel() {
        this.repository = Repository.getInstance();

        init();
    }

    private void init() {
        beerListLiveData = new MutableLiveData<>();
        beerDetailsLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        currentSearchTextLiveData = new MutableLiveData<>("");
        allBeersList = new ArrayList<>();

        disposables = new CompositeDisposable();
        selectedFilter = FilterType.ALL;
        selectedSort=SortType.ASC;
    }

    public MutableLiveData<List<Beer>> getBeerListLiveData() {
        return beerListLiveData;
    }

    public List<Beer> getAllBeersList() {
        return allBeersList;
    }

    public MutableLiveData<Beer> getBeerDetailsLiveData() {
        return beerDetailsLiveData;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public MutableLiveData<String> getCurrentSearchTextLiveData() {
        return currentSearchTextLiveData;
    }

    public void getAllBeers() {
        repository.getAllBeers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Beer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        loading.setValue(true);
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Beer> beers) {
                        loading.setValue(false);
                        beerListLiveData.setValue(beers);
                        allBeersList=beers;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(Constants.LOG, "getBeers error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // Nothing to do here

                    }
                });

    }

    public void getBeerDetails(String beerId) {
        repository.getBeerDetails(beerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Beer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        loading.setValue(true);
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Beer beer) {
                        loading.setValue(false);
                        beerDetailsLiveData.setValue(beer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(Constants.LOG, "get Beer Details error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // Nothing to do here

                    }
                });
    }



    public void onEventBeerList(
            BeerListEvent event,
            Object object
    ) {
        switch (event) {
            //TODO: FILTER

            case FILTER_LIST: {
                if(object instanceof FilterType) {
                    selectedFilter = (FilterType) object;
                    if(selectedFilter==FilterType.ALL)
                        currentSearchTextLiveData.setValue("");
                }
                else if(object instanceof SortType)
                    selectedSort = (SortType)object;
                filterList();
                break;
            }
            case SEARCH: {
                String searchQuery=(String)object;
                currentSearchTextLiveData.setValue(searchQuery);
                // TODO Search
                searchBeerItem(searchQuery.toLowerCase());
                filterList();
                break;
            }
        }
    }
    public void onEventBeerItem(
            BeerDetailsEvent event,
            Object object
    ) {
        switch (event) {

            case GET_ITEM_DETAILS: {
                Beer beer = (Beer) object;
                getBeerDetails(beer.getMoreDetailsUrl().substring(beer.getMoreDetailsUrl().lastIndexOf('/') + 1).trim());
                break;
            }
        }
    }
    private void filterList() {
        List<Beer> filteredBeerItems;
        if (allBeersList.isEmpty())
            return;
        else if (beerListLiveData.getValue() == null)
            filteredBeerItems = beerListLiveData.getValue();
        else
            filteredBeerItems = beerListLiveData.getValue();

        filterCases(filteredBeerItems);
    }
    private void filterCases(List<Beer> filteredBeers) {
        switch (selectedFilter) {
            case ALL:
                filteredBeers =beerListLiveData.getValue();
                break;

            case NAME:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredBeers,  new Beer.SortByName());
                else
                    Collections.sort(filteredBeers, new Beer.SortByName().reversed());

                break;

            case ID:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredBeers, new Beer.SortById());
                else
                    Collections.sort(filteredBeers, new Beer.SortById().reversed());

                break;
            case DESCRIPTION:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredBeers, new Beer.SortByDescription());
                else
                    Collections.sort(filteredBeers, new Beer.SortByDescription().reversed());

                break;
        }
        beerListLiveData.setValue(filteredBeers);

    }
    public void disposeComposite() {
        disposables.dispose();
    }

    public void searchBeerItem(String searchQuery) {
        List<Beer> filteredBeerItems = new ArrayList<>();
        if (!allBeersList.isEmpty()) {
            for (Beer beer : allBeersList) {
                if (beer.getName().toLowerCase().contains(searchQuery)) {
                    filteredBeerItems.add(beer);
                }
            }
            beerListLiveData.setValue(filteredBeerItems);
        }
    }


}
