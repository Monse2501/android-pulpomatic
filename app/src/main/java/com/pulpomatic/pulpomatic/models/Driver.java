package com.pulpomatic.pulpomatic.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Modelo que contiene la información personal del conductor así como información sobre su licencia de conducir
 * Created by monse on 10/1/16.
 */
public class Driver implements Serializable{

    private Integer mInt;

    private String mEmail;

    @SerializedName("name")
    private String mName;

    private String mSurname;

    @SerializedName("phone")
    private String mPhone;

    private String mPicture;

    private String mLicenseNumber;

    private String mLicensePicture;

    private String mLicenseType;

    private String mLicenseDueDate;

    public Integer getInt() {
        return mInt;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getName() {
        return mName;
    }

    public String getSurname() {
        return mSurname;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getPicture() {
        return mPicture;
    }

    public String getLicenseNumber() {
        return mLicenseNumber;
    }

    public String getLicensePicture() {
        return mLicensePicture;
    }

    public String getLicenseType() {
        return mLicenseType;
    }

    public String getLicenseDueDate() {
        return mLicenseDueDate;
    }

}
