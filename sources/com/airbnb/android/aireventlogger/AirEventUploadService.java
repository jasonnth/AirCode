package com.airbnb.android.aireventlogger;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class AirEventUploadService extends Service {
    private static final String TAG = AirEventUploadService.class.getSimpleName();
    private static final long WAKELOCK_TIMEOUT_MILLIS = 120000;
    private WakeLock wakelock;

    static Intent createIntent(Context context) {
        return new Intent(context, AirEventUploadService.class);
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        this.wakelock = ((PowerManager) getSystemService("power")).newWakeLock(1, TAG);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.wakelock.isHeld()) {
            this.wakelock.acquire(WAKELOCK_TIMEOUT_MILLIS);
        }
        Log.d(TAG, "onStartCommand");
        LogAir.uploadAirEventsAsync();
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.wakelock.isHeld()) {
            this.wakelock.release();
        }
    }
}
