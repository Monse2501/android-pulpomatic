package com.pulpomatic.pulpomatic.helpers;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;

/**
 * Clase Helper base para todos los Helpers que se quieran crear
 * La clase incluye gestión de
 *  +Recursos del sistema Android: Application y SharedPreferences
 *  +Configuración local
 * Created by Monse Chimal on 10/3/16
 * monse@parkiller.com
 */
public abstract class AbstractHelper {

    private Application mApplication;
    private JsonObject mJsonObject;
    private SharedPreferences mSharedPreferences;

    public AbstractHelper(
            Application application,
            JsonObject jsonObject,
            SharedPreferences sharedPreferences
    ) {
        mApplication = application;
        mJsonObject = jsonObject;
        mSharedPreferences = sharedPreferences;
    }

    /**
     * Devuelve el contexto Android que incluye funciones para obtener cualquier recurso del sistema
     * @return instancia de Application generada por android
     * @see Application
     */
    public Application getApplication() { return mApplication; }

    /**
     * Devuelve la configuración local la cual tiene parámetros necesarios para el funcionamiento de algunas instancias
     * @return instancia de JsonObject que contiene la configuración local
     */
    public JsonObject getConfiguration() { return mJsonObject; }

    /**
     * Devuelve el objeto SharedPreferences el cual ayuda a almacenar datos para usos futuros
     * @return instancia de SharedPreferences generada por Application
     * @see SharedPreferences
     */
    public SharedPreferences getSharedPreferences(){ return mSharedPreferences; }

}
