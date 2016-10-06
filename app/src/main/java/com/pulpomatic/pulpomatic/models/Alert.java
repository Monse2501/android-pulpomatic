package com.pulpomatic.pulpomatic.models;

/**
 * Modelo que contiene información sobre las alertas para el conductor, ejemplo: aviso de vencimiento de licencia
 * Created by monse on 10/1/16.
 */
public class Alert {

    private Integer mId;

    private String mAlert;

    public Integer getId() {
        return mId;
    }

    public String getAlert() {
        return mAlert;
    }
}
