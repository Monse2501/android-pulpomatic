package com.pulpomatic.pulpomatic.templates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by monse2501 on 10/6/16.
 */
public abstract class PulpomaticBasicFragment extends Fragment implements InitViews{

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        beforeInitViews();
        initViews();
    }

    public void beforeInitViews() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
