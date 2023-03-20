package com.diana_ukrainsky.beerbazzar.ui.callback;

import androidx.recyclerview.widget.DiffUtil;

import com.diana_ukrainsky.beerbazzar.data.model.Beer;

import java.util.List;

public class BeersDiffUtil extends DiffUtil.Callback {

    private final List<Beer> mOldBeerList;
    private final List<Beer> mNewBeerList;

    public BeersDiffUtil(List<Beer> mOldBeerList, List<Beer> mNewBeerList) {
        this.mOldBeerList = mOldBeerList;
        this.mNewBeerList = mNewBeerList;
    }

    @Override
    public int getOldListSize() {
        return mOldBeerList==null?0:mOldBeerList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewBeerList==null?0:mNewBeerList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
