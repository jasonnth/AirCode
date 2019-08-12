package com.airbnb.android.core.controllers;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;

public final class GoogleAppIndexingControllerImpl implements GoogleAppIndexingController {
    private final GoogleApiClient appIndexingApiClient;
    private Action appIndexingViewAction;
    private Uri appUri;
    private boolean hasReportedViewStart;
    private String title;

    public GoogleAppIndexingControllerImpl(Context context) {
        this.appIndexingApiClient = new Builder(context).addApi(AppIndex.API).build();
    }

    public GoogleAppIndexingController setAppUri(Uri appUri2) {
        this.appUri = appUri2;
        if (!this.hasReportedViewStart) {
            reportViewStart();
        }
        return this;
    }

    public GoogleAppIndexingController setTitle(String title2) {
        this.title = title2;
        if (!this.hasReportedViewStart) {
            reportViewStart();
        }
        return this;
    }

    public void connect() {
        this.appIndexingApiClient.connect();
    }

    public void disconnect() {
        this.appIndexingApiClient.disconnect();
    }

    public void reportViewStart() {
        if (!this.hasReportedViewStart && this.appUri != null && !TextUtils.isEmpty(this.title)) {
            this.hasReportedViewStart = true;
            this.appIndexingViewAction = Action.newAction("http://schema.org/ViewAction", this.title, this.appUri);
            AppIndex.AppIndexApi.start(this.appIndexingApiClient, this.appIndexingViewAction);
        }
    }

    public void reportViewEnd() {
        if (this.hasReportedViewStart) {
            this.hasReportedViewStart = false;
            AppIndex.AppIndexApi.end(this.appIndexingApiClient, this.appIndexingViewAction);
        }
    }
}
