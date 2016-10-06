package com.pulpomatic.pulpomatic.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Módulo Dagger encargado de crear instancias básicas necesarias para la creación de
 * otras instancias que las requieren en diferentes módulos
 * Created by Monse Chimal on 10/3/16.
 * monse@parkiller.com
 */
@Module
public class PulpomaticModule {

    private static final String TAG = PulpomaticModule.class.getSimpleName();

    private Application mApplication;
    private String mConfigurationPath;

    public PulpomaticModule(Application application, String mConfigurationPath) {
        this.mApplication = application;
        this.mConfigurationPath = mConfigurationPath;
    }

    /**
     * Regresa el contexto Android necesario para la creación de los helpers y para acceder a recursos del sistema
     * @return contexto en objeto Application
     * @see Application
     */
    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }

    /**
     * Crea la instancia SharedPreferences por default de la aplicación, la cual nos sirve para
     * guardar datos en el dispositivo para utilizarlos en el futuro
     * @param application contexto android necesario para obtener recursos del sistema
     * @return instancia de SharedPreferences
     * @see SharedPreferences
     */
    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    /**
     * Crea la instancia de a clase Gson, la cual nos ayuda en el parseo de las respuestas enviadas por
     * backend en las llamadas que realicemos
     * @return instancia de Gson
     */
    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }

    /**
     * Obtiene la configuración local (config.json), la cual tiene parámetros necesarios para el funcionamiento
     * de instancias
     * @param gson que ayuda al parseo del archivo (config.json)
     * @return instancia de JsonObject la cual es la configuración local
     */
    @Provides
    @Singleton
    public JsonObject provideLocalConfiguration(
            Gson gson
    ) {
        AssetManager assetManager = mApplication.getAssets();
        JsonObject jsonObject = null;
        try {
            jsonObject = gson.fromJson(new InputStreamReader(assetManager.open(mConfigurationPath)), JsonObject.class);
        } catch (IOException e) {
            Log.e(TAG, "Error loading local configuration.", e);
        }
        return jsonObject;
    }

}
