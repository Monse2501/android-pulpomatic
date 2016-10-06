package com.pulpomatic.pulpomatic.templates;

import android.content.Context;

import com.pulpomatic.pulpomatic.views.PulpomaticView;

/**
 * Created by monse2501 on 10/6/16.
 */
public class PulpomaticBasicPresenter<T extends PulpomaticView> {

    private T mView;

    public PulpomaticBasicPresenter(Context context, T view) {
        mView = view;
    }

    protected T getView() {
        return mView;
    }


}
