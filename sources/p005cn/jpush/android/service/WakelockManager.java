package p005cn.jpush.android.service;

import android.os.PowerManager.WakeLock;

/* renamed from: cn.jpush.android.service.WakelockManager */
public class WakelockManager {
    private static WakelockManager mWakelockManager = null;
    private WakeLock mWakelock = null;

    private WakelockManager() {
    }

    public static WakelockManager getInstance() {
        if (mWakelockManager == null) {
            mWakelockManager = new WakelockManager();
        }
        return mWakelockManager;
    }

    public WakeLock getWakelock() {
        return this.mWakelock;
    }

    public void setWakelock(WakeLock wakelock) {
        this.mWakelock = wakelock;
    }
}
