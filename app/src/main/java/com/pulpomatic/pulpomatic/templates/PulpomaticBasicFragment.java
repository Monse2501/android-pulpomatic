package com.pulpomatic.pulpomatic.templates;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by monse2501 on 10/6/16.
 */
public abstract class PulpomaticBasicFragment extends Fragment implements InitViews{

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * El fragmento se ha adjuntado al Contexto
     * @param context contexto de Android
     */
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    /**
     * El fragmento ha sido creado
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * El fragmento ha sido quitado de su Contexto y ya no está disponible
     */
    @Override
    public void onDetach(){
        super.onDetach();
    }



}
