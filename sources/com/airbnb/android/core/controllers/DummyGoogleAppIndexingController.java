package com.airbnb.android.core.controllers;

import android.net.Uri;

public class DummyGoogleAppIndexingController implements GoogleAppIndexingController {
    public GoogleAppIndexingController setAppUri(Uri appUri) {
        return this;
    }

    public GoogleAppIndexingController setTitle(String title) {
        return this;
    }

    public void connect() {
    }

    public void disconnect() {
    }

    public void reportViewStart() {
    }

    public void reportViewEnd() {
    }
}
