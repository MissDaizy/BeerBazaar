package com.diana_ukrainsky.beerbazzar.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MashTemp {

    @SerializedName("temp")
    private Temp temp;
    @SerializedName("duration")
    private Integer duration;

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}