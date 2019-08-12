package com.appboy.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Process;
import com.appboy.Appboy;
import com.appboy.support.AppboyLogger;

public class AppboyDataSyncService extends IntentService {

    /* renamed from: a */
    private static final String f2902a = AppboyLogger.getAppboyLogTag(AppboyDataSyncService.class);

    public AppboyDataSyncService() {
        super(AppboyDataSyncService.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        Process.setThreadPriority(10);
        if (intent == null) {
            AppboyLogger.m1739w(f2902a, "AppboyDataSyncService received null intent, doing nothing.");
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            AppboyLogger.m1739w(f2902a, "AppboyDataSyncService received intent with null action, doing nothing.");
        } else if (action.contains(getApplicationContext().getPackageName() + ".REQUEST_DATA_SYNC")) {
            AppboyLogger.m1733d(f2902a, "AppboyDataSyncService requesting Appboy DataSync.");
            Appboy.getInstance(getApplicationContext()).requestImmediateDataFlush();
        } else {
            AppboyLogger.m1739w(f2902a, "AppboyDataSyncService received unknown intent, doing nothing.");
        }
    }
}
