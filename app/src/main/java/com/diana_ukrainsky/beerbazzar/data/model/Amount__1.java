package com.diana_ukrainsky.beerbazzar.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Amount__1 {

    @SerializedName("unit")
    private String unit;
    @SerializedName("value")
    private Double value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}