package com.airbnb.android.core.controllers;

import android.net.Uri;

public interface GoogleAppIndexingController {
    void connect();

    void disconnect();

    void reportViewEnd();

    void reportViewStart();

    GoogleAppIndexingController setAppUri(Uri uri);

    GoogleAppIndexingController setTitle(String str);
}
