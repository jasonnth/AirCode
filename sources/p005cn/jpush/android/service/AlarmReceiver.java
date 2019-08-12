package p005cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants.PushService;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.AlarmReceiver */
public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmReceiver";

    public void onReceive(Context context, Intent intent) {
        Logger.m1418dd(TAG, "onReceive \n\n");
        if (JPush.init(context.getApplicationContext())) {
            if (ServiceInterface.isServiceStoped(context)) {
                ServiceInterface.setPushReceiverEnable(context, false);
                return;
            }
            try {
                Intent intent1 = new Intent(context, PushService.class);
                intent1.setAction(PushService.ACTION_RTC);
                intent1.putExtra(PushService.DELAY_TIME, 0);
                context.startService(intent1);
            } catch (SecurityException e) {
                e.printStackTrace();
                Logger.m1416d(TAG, "AlarmReceiver start PushService with action ACTION_RTC with SecurityException errno");
            } catch (Exception e2) {
                e2.printStackTrace();
                Logger.m1416d(TAG, "AlarmReceiver start PushService with action ACTION_RTC with errno");
            }
        }
    }
}
