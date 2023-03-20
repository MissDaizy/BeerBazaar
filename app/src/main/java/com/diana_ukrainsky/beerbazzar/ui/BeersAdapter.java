package com.diana_ukrainsky.beerbazzar.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.diana_ukrainsky.beerbazzar.common.util.ImageUtil;
import com.diana_ukrainsky.beerbazzar.data.model.Beer;
import com.diana_ukrainsky.beerbazzar.databinding.BeerItemBinding;
import com.diana_ukrainsky.beerbazzar.ui.callback.BeersDiffUtil;
import com.diana_ukrainsky.beerbazzar.ui.callback.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class BeersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int WIDTH = 1000;
    private static final int HEIGHT =1000 ;
    private BeerItemBinding beerItemBinding;
    private List<Beer> beerList;
    private CustomItemClickListener customItemClickListener;
    private Context context;

    public BeersAdapter(Context context) {
        this.context = context;
        this.customItemClickListener=(CustomItemClickListener) context;
        this.beerList=new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        beerItemBinding=BeerItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(beerItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Beer beer=beerList.get(position);
        MyViewHolder myViewHolder=(MyViewHolder) holder;
        setListeners(myViewHolder,beer);
        setDetailedAttrsVisibility(myViewHolder);
        myViewHolder.beerItemBinding.beerItemTXTId.setText(""+beer.getId());
        myViewHolder.beerItemBinding.beerItemTXTName.setText(beer.getName());
        myViewHolder.beerItemBinding.beerItemTXTTagline.setText(beer.getTagline());
        setImageUI(myViewHolder.beerItemBinding.beerItemIMGImage,beer.getImageUrl());
        myViewHolder.beerItemBinding.beerItemTXTDescription.setText(beer.getDescription());

    }

    private void setDetailedAttrsVisibility(MyViewHolder myViewHolder) {
        myViewHolder.beerItemBinding.beerItemTXTAttenuationLevelPreview.setVisibility(View.GONE);
        myViewHolder.beerItemBinding.beerItemTXTAttenuationLevel.setVisibility(View.GONE);
        myViewHolder.beerItemBinding.beerItemTXTBoilVolumePreview.setVisibility(View.GONE);
        myViewHolder.beerItemBinding.beerItemTXTBoilVolume.setVisibility(View.GONE);
    }

    private void setImageUI(ImageView beerItemIMGImage, String pictureUrl) {
        ImageUtil.setImage(context,pictureUrl,beerItemIMGImage,WIDTH,HEIGHT);
    }

    private void setListeners(MyViewHolder myViewHolder, Beer beer) {
        myViewHolder.beerItemBinding.beerItemCVBeerItemCard.setOnClickListener(v -> {
            customItemClickListener.onClick(beer);
        });

    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public void updateBeerListItems(List<Beer> beerList) {
        final BeersDiffUtil diffCallback = new BeersDiffUtil(this.beerList, beerList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.beerList.clear();
        this.beerList.addAll(beerList);
        this.notifyDataSetChanged();
        diffResult.dispatchUpdatesTo(this);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final BeerItemBinding beerItemBinding;

        public MyViewHolder( BeerItemBinding beerItemBinding) {
            super(beerItemBinding.getRoot());
            this.beerItemBinding = beerItemBinding;
        }
    }

}
