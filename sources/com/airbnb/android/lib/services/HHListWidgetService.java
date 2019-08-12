package com.airbnb.android.lib.services;

import android.content.Intent;
import android.widget.RemoteViewsService;
import android.widget.RemoteViewsService.RemoteViewsFactory;

public class HHListWidgetService extends RemoteViewsService {
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new HHListRemoteViewsFactory(getApplicationContext());
    }
}
