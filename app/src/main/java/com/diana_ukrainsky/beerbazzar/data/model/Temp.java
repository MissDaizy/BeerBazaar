package com.diana_ukrainsky.beerbazzar.data.model;

import com.google.gson.annotations.SerializedName;

public class Temp {

    @SerializedName("unit")
    private String unit;
    @SerializedName("value")
    private Integer value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}