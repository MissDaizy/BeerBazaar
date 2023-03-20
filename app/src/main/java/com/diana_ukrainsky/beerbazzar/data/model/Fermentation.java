package com.diana_ukrainsky.beerbazzar.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fermentation {

    @SerializedName("temp")
    private Temp__1 temp;

    public Temp__1 getTemp() {
        return temp;
    }

    public void setTemp(Temp__1 temp) {
        this.temp = temp;
    }

}