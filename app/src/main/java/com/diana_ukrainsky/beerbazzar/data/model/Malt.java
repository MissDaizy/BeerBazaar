package com.diana_ukrainsky.beerbazzar.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Malt {

    @SerializedName("name")
    private String name;
    @SerializedName("amount")
    private Amount__1 amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount__1 getAmount() {
        return amount;
    }

    public void setAmount(Amount__1 amount) {
        this.amount = amount;
    }

}