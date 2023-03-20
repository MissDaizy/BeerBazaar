package com.diana_ukrainsky.beerbazzar.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredients {

    @SerializedName("hops")
    @Expose
    private List<Hop> hops;
    @SerializedName("malt")
    @Expose
    private List<Malt> malt;
    @SerializedName("yeast")
    @Expose
    private String yeast;

    public List<Hop> getHops() {
        return hops;
    }

    public void setHops(List<Hop> hops) {
        this.hops = hops;
    }

    public List<Malt> getMalt() {
        return malt;
    }

    public void setMalt(List<Malt> malt) {
        this.malt = malt;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(String yeast) {
        this.yeast = yeast;
    }

}