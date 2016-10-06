package com.pulpomatic.pulpomatic.views;

import android.view.View;

/**
 * Created by monse2501 on 10/6/16.
 */
public interface PulpomaticView<T> {

    void showMessage(String message, String closeText, View.OnClickListener listener);

    void showError(String error, String closeText, View.OnClickListener listener);

    void showLoading(String text);

    void hideLoading();

    T getScreen();
}
