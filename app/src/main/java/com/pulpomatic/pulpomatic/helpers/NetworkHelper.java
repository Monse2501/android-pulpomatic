package com.pulpomatic.pulpomatic.helpers;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.JsonObject;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Clase Helper dedicada a obtener información del estado de red en la que se encuentra
 * conectado el dispositivo y de recibir actualizaciones en caso de cambios en la red
 *
 * Created by Monse Chimal on 10/3/16
 * monse@parkiller.com
 */
@Singleton
public class NetworkHelper extends AbstractHelper {

    private NetworkInfo mNetworkInfo;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO add implementation
            networkChange();
        }
    };

    @Inject
    public NetworkHelper(
            Application application,
            JsonObject localConfiguration,
            SharedPreferences sharedPreferences

    ) {
        super(application, localConfiguration, sharedPreferences);
    }

    /**
     * Comienza a la escucha de los cambios de red
     */
    public void startListening() {
        //TODO add implementation
    }

    /**
     * Llamado por el BroadcastReceiver cuando la información de red ha sufrido algún cambio
     */
    private void networkChange() {
        //TODO add implementation
    }

    /**
     * Verifica el estado de la red
     * @return true si la red es válida y está conectada
     */
    public boolean networkAvailable() {
        //TODO add implementation
        return !isNetworkInfoNull() && mNetworkInfo.isConnected();
    }

    /**
     * Verifica el estado de la red
     * @return true si la red es válida y está conectada
     */
    public boolean isNetworkInfoNull() {
        //TODO add implementation
        return mNetworkInfo != null;
    }

    /**
     * Verifica si la red es de tipo Wi-fi
     * @return true si la información es válida y si es por Wi-fi
     */
    public boolean isWifi() {
        //TODO add implementation
        return !isNetworkInfoNull() && mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Verifica si la red actual es de datos móviles
     * @return true si la información es válida y si es por datos móviles
     */
    public boolean isMobile() {
        //TODO add implementation
        return !isNetworkInfoNull() && mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

}
