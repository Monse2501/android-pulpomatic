package com.pulpomatic.pulpomatic.templates;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Monse Chimal on 10/6/16.
 */
public abstract class PulpomaticBasicActivity extends AppCompatActivity implements InitViews{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int viewId) {
        super.setContentView(viewId);
        initViews();
        hideKeyboard();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void initViews() {

    }


    /**
     * Utilizado para cambiar el fragmento en una actividad
     * @param idLayout Id el layout con el contenido principal
     * @param fragment Fragmento a mostrar
     * @param title Título del nuevo fragmento que se va a mostrar
     */
    public void changeFragment(int idLayout, Fragment fragment, String title){

        getSupportFragmentManager().beginTransaction()
                .replace(idLayout, fragment)
                .commit();
        getSupportActionBar().setTitle(title);

    }

    /**
     * Oculta el teclado al iniciar una actividad
     */
    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
