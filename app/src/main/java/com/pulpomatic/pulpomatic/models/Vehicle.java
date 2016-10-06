package com.pulpomatic.pulpomatic.models;

import com.google.gson.annotations.SerializedName;

/**
 * Modelo que contiene la información del vehículo asociado al conductor
 * Created by monse on 10/1/16.
 */
public class Vehicle {

    @SerializedName("id")
    private Integer mId;

    private String mPicture;

    @SerializedName("name")
    private String mName;

    @SerializedName("license_plate")
    private String mRegistration;

    @SerializedName("body_type")
    private String mType;

    @SerializedName("year")
    private String mYear;

    @SerializedName("model")
    private String mModel;

    @SerializedName("make")
    private String mMaker;

    @SerializedName("fuel_type_id")
    private String mFuelType;

    @SerializedName("fuel_tank_1_capacity")
    private String mFuelCapacity;

    public Integer getId() {
        return mId;
    }

    public String getPicture() {
        return mPicture;
    }

    public String getName() {
        return mName;
    }

    public String getRegistration() {
        return mRegistration;
    }

    public String getType() {
        return mType;
    }

    public String getYear() {
        return mYear;
    }

    public String getModel() {
        return mModel;
    }

    public String getMaker() {
        return mMaker;
    }

    public String getFuelType() {
        return mFuelType;
    }

    public String getFuelCapacity() {
        return mFuelCapacity;
    }
}
