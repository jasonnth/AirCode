package com.jumio.sdk.models;

import com.jumio.core.enums.JumioDataCenter;
import com.jumio.core.mvp.model.StaticModel;

public class CredentialsModel implements StaticModel {
    private String mApiSecret;
    private String mApiToken;
    private JumioDataCenter mDataCenter;

    public String getApiToken() {
        return this.mApiToken;
    }

    public void setApiToken(String apiToken) {
        this.mApiToken = apiToken;
    }

    public String getApiSecret() {
        return this.mApiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.mApiSecret = apiSecret;
    }

    public JumioDataCenter getDataCenter() {
        return this.mDataCenter;
    }

    public void setDataCenter(JumioDataCenter dataCenter) {
        this.mDataCenter = dataCenter;
    }
}
