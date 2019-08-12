package p005cn.jpush.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.DaemonService */
public class DaemonService extends Service {
    private static final String TAG = "JPushDaemonService";

    public void onCreate() {
        Logger.m1416d(TAG, "action onCreate");
        ServiceInterface.rtcWithDelayTime(getApplicationContext(), 30000);
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
