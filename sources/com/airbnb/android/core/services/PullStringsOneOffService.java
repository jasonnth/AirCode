package com.airbnb.android.core.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.utils.PullStringsDownloader;

public class PullStringsOneOffService extends IntentService {
    private static final String TAG = PullStringsOneOffService.class.getSimpleName();

    public static Intent intent(Context context) {
        return new Intent(context, PullStringsOneOffService.class);
    }

    public PullStringsOneOffService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        new PullStringsDownloader(this).pullStrings();
    }
}
