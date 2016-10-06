package com.pulpomatic.pulpomatic.helpers;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import retrofit2.Retrofit;

/**
 * Clase base ya configurada para cualquier Helper que necesite comunicarse por medio de REST
 *
 * La clase ya incluye gestión de
 *  +Consultas al estado actual de la red
 *  +Llamado a end points del backend de Pulpomatic
 * Created by Monse Chimal on 10/3/16.
 * monse@parkiller.com
 */
public abstract class RestHelper<T> extends AbstractHelper {

    private T mApiService;
    private NetworkHelper mNetworkHelper;

    public RestHelper(
            Application application,
            JsonObject configuration,
            SharedPreferences sharedPreferences,
            Retrofit retrofit,
            Class<T> genericClass,
            NetworkHelper networkHelper
    ) {
        super(application, configuration, sharedPreferences);
        mApiService = retrofit.create(genericClass);
        mNetworkHelper = networkHelper;
    }

    /**
     * Regresa la clase Api que se comunica con backend
     * @return Api de Pulpomatic
     */
    public T getApiService(){
        return mApiService;
    }

    /**
     * Regresa el Helper de red, el cual tiene las funcionalidades necesarias para verificar
     * si hay una red disponible y de qué tipo es la red (Móvil o Wi-Fi)
     * @return Helper de red
     */
    public NetworkHelper getNetworkHelper() {
        return mNetworkHelper;
    }

}
