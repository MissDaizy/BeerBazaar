package com.diana_ukrainsky.beerbazzar.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SearchView;

import com.diana_ukrainsky.beerbazzar.R;
import com.diana_ukrainsky.beerbazzar.common.Constants;
import com.diana_ukrainsky.beerbazzar.data.model.Beer;
import com.diana_ukrainsky.beerbazzar.databinding.ActivityBeerListBinding;
import com.diana_ukrainsky.beerbazzar.ui.callback.CustomItemClickListener;
import com.google.gson.Gson;

import java.util.List;

public class BeerListActivity extends AppCompatActivity
        implements CustomItemClickListener, LifecycleOwner {
     private MainViewModel mainViewModel;
        private RecyclerView recyclerView;
        private BeersAdapter beersAdapter;
        private ProgressBar progressBar;

        private ActivityBeerListBinding activityBeerListBinding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_beer_list);

            activityBeerListBinding = ActivityBeerListBinding.inflate(getLayoutInflater());
            View view = activityBeerListBinding.getRoot();
            setContentView(view);

            setViewModels();
            setViews();
            setObservers();

            setListeners();
            setRecyclerView();
            setAdapter();
            setUserListUI();
        }

        private void setUserListUI() {
            mainViewModel.getAllBeers();
        }

        private void setObservers() {
            mainViewModel.getBeerListLiveData().observe(this, new Observer<List<Beer>>() {
                @Override
                public void onChanged(List<Beer> beerList) {
                    beersAdapter.updateBeerListItems(beerList);
                }
            });

            mainViewModel.getBeerDetailsLiveData().observe(this, new Observer<Beer>() {
                @Override
                public void onChanged(Beer beer) {
                    if(beer!=null) {
                        Bundle bundle = new Bundle();
                        String userData = new Gson().toJson(beer);
                        Intent intent = new Intent(BeerListActivity.this, BeerDetailsActivity.class);
                        bundle.putString(Constants.ITEM_DETAILS, userData);
                        intent.putExtras(bundle);
                        startUserDetailsActivity(intent);
                    }
                }
            });
            mainViewModel.getLoading().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isLoading) {
                    if (isLoading)
                        progressBar.setVisibility(View.VISIBLE);
                    else
                        progressBar.setVisibility(View.INVISIBLE);
                }
            });
            mainViewModel.getCurrentSearchTextLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    activityBeerListBinding.activityBeerListSVSearchView.setQuery(s, false);
                }
            });
        }

        private void setListeners() {
            setRadioButtonsListener();
            setRadioButtonsSortParameterListener();
            setSearchViewListener();
        }

        private void setSearchViewListener() {
            activityBeerListBinding.activityBeerListSVSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    mainViewModel.onEventBeerList(BeerListEvent.SEARCH, newText);
                    return false;
                }
            });
        }

        private void setRadioButtonsSortParameterListener() {
            activityBeerListBinding.radiogroupSortParameter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radio_all:
                            mainViewModel.onEventBeerList(BeerListEvent.FILTER_LIST, FilterType.ALL);
                            break;
                        case R.id.radio_name:
                            mainViewModel.onEventBeerList(BeerListEvent.FILTER_LIST, FilterType.NAME);
                            break;
                        case R.id.radio_id:
                            mainViewModel.onEventBeerList(BeerListEvent.FILTER_LIST, FilterType.ID);
                            break;
                        case R.id.radio_description:
                            mainViewModel.onEventBeerList(BeerListEvent.FILTER_LIST, FilterType.DESCRIPTION);
                            break;

                    }
                }
            });

        }

        private void setRadioButtonsListener() {
            activityBeerListBinding.radiogroupSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radio_ascending:
                            mainViewModel.onEventBeerList(BeerListEvent.FILTER_LIST, SortType.ASC);
                            break;
                        case R.id.radio_descending:
                            mainViewModel.onEventBeerList(BeerListEvent.FILTER_LIST, SortType.DESC);
                            break;

                    }
                }
            });

        }

        private void setViews() {
            progressBar = activityBeerListBinding.activityBeerListPBProgressBar;
            activityBeerListBinding.radioAll.setChecked(true);
            activityBeerListBinding.radioAscending.setChecked(true);
        }

        private void setViewModels() {
            mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        }

        private void setRecyclerView() {
            recyclerView = activityBeerListBinding.activityBeerListRVRecyclerView;

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(linearLayoutManager);
        }

        private void setAdapter() {
            beersAdapter = new BeersAdapter(this);
            recyclerView.setAdapter(beersAdapter);
        }

        @Override
        public void onClick(Object object) {
            mainViewModel.onEventBeerItem(BeerDetailsEvent.GET_ITEM_DETAILS,object);
        }

        private void startUserDetailsActivity(Intent intent) {
            startActivity(intent);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mainViewModel.disposeComposite();
        }
    }

