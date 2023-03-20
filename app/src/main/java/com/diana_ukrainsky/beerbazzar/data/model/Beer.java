package com.diana_ukrainsky.beerbazzar.data.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Beer {

    @SerializedName("id")
    private Integer id;
    @SerializedName("ph")
    private Double ph;
    @SerializedName("abv")
    private Double abv;
    @SerializedName("ebc")
    private Integer ebc;
    @SerializedName("ibu")
    private Number ibu;
    @SerializedName("srm")
    private Double srm;
    @SerializedName("name")
    private String name;
    @SerializedName("method")
    private Method method;
    @SerializedName("volume")
    private Volume volume;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("target_fg")
    private Integer targetFg;
    @SerializedName("target_og")
    private Integer targetOg;
    @SerializedName("boil_volume")
    private BoilVolume boilVolume;
    @SerializedName("description")
    private String description;
    @SerializedName("ingredients")
    private Ingredients ingredients;
    @SerializedName("brewers_tips")
    private String brewersTips;
    @SerializedName("first_brewed")
    private String firstBrewed;
    @SerializedName("food_pairing")
    private List<String> foodPairing;
    @SerializedName("contributed_by")
    private String contributedBy;
    @SerializedName("more_details_url")
    private String moreDetailsUrl;
    @SerializedName("attenuation_level")
    private Double attenuationLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public Integer getEbc() {
        return ebc;
    }

    public void setEbc(Integer ebc) {
        this.ebc = ebc;
    }

    public Number getIbu() {
        return ibu;
    }

    public void setIbu(Number ibu) {
        this.ibu = ibu;
    }

    public Double getSrm() {
        return srm;
    }

    public void setSrm(Double srm) {
        this.srm = srm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(Integer targetFg) {
        this.targetFg = targetFg;
    }

    public Integer getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(Integer targetOg) {
        this.targetOg = targetOg;
    }

    public BoilVolume getBoilVolume() {
        return boilVolume;
    }

    public void setBoilVolume(BoilVolume boilVolume) {
        this.boilVolume = boilVolume;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(List<String> foodPairing) {
        this.foodPairing = foodPairing;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

    public String getMoreDetailsUrl() {
        return moreDetailsUrl;
    }

    public void setMoreDetailsUrl(String moreDetailsUrl) {
        this.moreDetailsUrl = moreDetailsUrl;
    }

    public Double getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(Double attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id.equals(beer.id) && Objects.equals(ph, beer.ph) && Objects.equals(abv, beer.abv) && Objects.equals(ebc, beer.ebc) && Objects.equals(ibu, beer.ibu) && Objects.equals(srm, beer.srm) && Objects.equals(name, beer.name) && Objects.equals(method, beer.method) && Objects.equals(volume, beer.volume) && Objects.equals(tagline, beer.tagline) && Objects.equals(imageUrl, beer.imageUrl) && Objects.equals(targetFg, beer.targetFg) && Objects.equals(targetOg, beer.targetOg) && Objects.equals(boilVolume, beer.boilVolume) && Objects.equals(description, beer.description) && Objects.equals(ingredients, beer.ingredients) && Objects.equals(brewersTips, beer.brewersTips) && Objects.equals(firstBrewed, beer.firstBrewed) && Objects.equals(foodPairing, beer.foodPairing) && Objects.equals(contributedBy, beer.contributedBy) && Objects.equals(moreDetailsUrl, beer.moreDetailsUrl) && Objects.equals(attenuationLevel, beer.attenuationLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ph, abv, ebc, ibu, srm, name, method, volume, tagline, imageUrl, targetFg, targetOg, boilVolume, description, ingredients, brewersTips, firstBrewed, foodPairing, contributedBy, moreDetailsUrl, attenuationLevel);
    }

    public static class SortById implements Comparator<Beer> {

        @Override
        public int compare(Beer o1, Beer o2) {
            return o1.getId()-(o2.getId());
        }
    }

    public static class SortByName implements Comparator<Beer> {

        @Override
        public int compare(Beer o1, Beer o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    public static class SortByDescription implements Comparator<Beer> {

        @Override
        public int compare(Beer o1, Beer o2) {
            return o1.getDescription().compareTo(o2.getDescription());
        }
    }
}