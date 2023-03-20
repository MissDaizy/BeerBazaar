package com.diana_ukrainsky.beerbazzar.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Method {

    @SerializedName("twist")
    @Expose
    private String twist;
    @SerializedName("mash_temp")
    @Expose
    private List<MashTemp> mashTemp;
    @SerializedName("fermentation")
    @Expose
    private Fermentation fermentation;

    public String getTwist() {
        return twist;
    }

    public void setTwist(String twist) {
        this.twist = twist;
    }

    public List<MashTemp> getMashTemp() {
        return mashTemp;
    }

    public void setMashTemp(List<MashTemp> mashTemp) {
        this.mashTemp = mashTemp;
    }

    public Fermentation getFermentation() {
        return fermentation;
    }

    public void setFermentation(Fermentation fermentation) {
        this.fermentation = fermentation;
    }

}