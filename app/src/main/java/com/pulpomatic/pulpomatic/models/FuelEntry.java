package com.pulpomatic.pulpomatic.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Modelo que contiene la información necesaria para agregar una carga de combustible
 * Created by monse on 10/1/16.
 */
public class FuelEntry {

    @SerializedName("date")
    private Date mEntryDate;

    @SerializedName("volumne")
    private Double mVolume;

    @SerializedName("price_per_volume_unit")
    private Double mPrice;

    @SerializedName("meter")
    private Integer mOdometer;

    private String mOdometerPicture;

    @SerializedName("vendor_id")
    private String mVendor;

    @SerializedName("comment")
    private String mComment;

    public Date getEntryDate() {
        return mEntryDate;
    }

    public Double getVolume() {
        return mVolume;
    }

    public Double getPrice() {
        return mPrice;
    }

    public Integer getOdometer() {
        return mOdometer;
    }

    public String getOdometerPicture() {
        return mOdometerPicture;
    }

    public String getVendor() {
        return mVendor;
    }

    public String getComment() {
        return mComment;
    }

}