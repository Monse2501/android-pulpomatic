package com.pulpomatic.pulpomatic.presenters;

import android.content.Context;
import android.content.Intent;

import com.pulpomatic.pulpomatic.templates.PulpomaticBasicPresenter;
import com.pulpomatic.pulpomatic.views.SplashView;

/**
 * Created by monse2501 on 10/6/16.
 */
public class SplashPresenter extends PulpomaticBasicPresenter<SplashView>{


    public SplashPresenter(Context context, SplashView view) {
        super(context, view);
    }

    /**
     * Valida si hay una sesión guardada en el dispositivo
     */
    private void validateUser(){

    }

    /**
     * Obtiene el token de Backend de la sesión guardada
     */
    private void getToken(){

    }

    /**
     * Obtiene los autos relacionados con el usuario de la sesión guardada en el dispositivo
     */
    private void getCars(){

    }

    /**
     * Obtiene las alertas relacionadas con el usuario de la sesión guardada en el dispositivo
     */
    private void getAlerts(){

    }

    /**
     * Obtiene la información del usuario de la sesión guardada en el dispositivo
     */
    private void getUserInfo(){

    }

    /**
     * Lanza un Intent para iniciar la siguiente actividad dada en el parámetro
     * @param nextScreenClass clase de la actividad a iniciar
     */
    private void goToScreen(Class nextScreenClass) {

    }
}
