package com.pulpomatic.pulpomatic.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by monse on 10/3/16.
 */
public class AuthToken implements Serializable {

    @SerializedName("access_token")
    private String mAccessToken;

    @SerializedName("token_type")
    private String mTokenType;

    @SerializedName("expire_in")
    private int mExpireIn;

    @SerializedName("refresh_token")
    private String mRefreshToken;

    @SerializedName("error")
    private String error;

    public String getAccessToken() {
        return mAccessToken;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public int getExpireIn() {
        return mExpireIn;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public String getError(){ return error; }
}
