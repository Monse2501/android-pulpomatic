package com.pulpomatic.pulpomatic.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Modelo que contiene información sobre el proveedor del servicio de combustible
 * Created by Monse Chimal on 10/6/16.
 * monse@parkiller.com
 */
public class Vendor implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
