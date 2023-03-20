package com.diana_ukrainsky.beerbazzar.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.diana_ukrainsky.beerbazzar.R;
import com.diana_ukrainsky.beerbazzar.common.Constants;
import com.diana_ukrainsky.beerbazzar.common.util.ImageUtil;
import com.diana_ukrainsky.beerbazzar.data.model.Beer;
import com.diana_ukrainsky.beerbazzar.databinding.BeerItemBinding;
import com.google.gson.Gson;

public class BeerDetailsActivity extends AppCompatActivity {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private BeerItemBinding beerItemBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);

        beerItemBinding = BeerItemBinding.inflate(getLayoutInflater());
        View view = beerItemBinding.getRoot();
        setContentView(view);

        setUI();

    }

    private void setUI() {
        Beer beer = getItemData();
        setBeerDetailsUI(beer);
    }

    private Beer getItemData() {
        Bundle bundle = getIntent().getExtras();
        String itemDetails = bundle.getString(Constants.ITEM_DETAILS);
        Beer beer = new Gson().fromJson(itemDetails, Beer.class);
        return beer;
    }

    private void setBeerDetailsUI(Beer beer) {
        setDetailedAttrsVisibility();
        beerItemBinding.beerItemTXTId.setText("" + beer.getId());
        beerItemBinding.beerItemTXTName.setText(beer.getName());
        beerItemBinding.beerItemTXTTagline.setText(beer.getTagline());
        setImageUI(beer.getImageUrl());
        beerItemBinding.beerItemTXTDescription.setText(beer.getDescription());
        beerItemBinding.beerItemTXTAttenuationLevel.setText(""+beer.getAttenuationLevel());
        beerItemBinding.beerItemTXTBoilVolume.setText(""+beer.getBoilVolume().getValue());
    }

    private void setDetailedAttrsVisibility() {
        beerItemBinding.beerItemTXTAttenuationLevelPreview.setVisibility(View.VISIBLE);
        beerItemBinding.beerItemTXTAttenuationLevel.setVisibility(View.VISIBLE);
        beerItemBinding.beerItemTXTBoilVolumePreview.setVisibility(View.VISIBLE);
        beerItemBinding.beerItemTXTBoilVolume.setVisibility(View.VISIBLE);
    }

    private void setImageUI(String pictureUrl) {
        ImageUtil.setImage(this, pictureUrl, beerItemBinding.beerItemIMGImage, WIDTH, HEIGHT);
    }
}