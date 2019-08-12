package com.braintreepayments.browserswitch;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;

public class ChromeCustomTabs {
    public static boolean isAvailable(Context context) {
        if (VERSION.SDK_INT < 18) {
            return false;
        }
        Intent serviceIntent = new Intent("android.support.customtabs.action.CustomTabsService").setPackage("com.android.chrome");
        ServiceConnection connection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder service) {
            }

            public void onServiceDisconnected(ComponentName name) {
            }
        };
        boolean bindService = context.bindService(serviceIntent, connection, 33);
        context.unbindService(connection);
        return bindService;
    }

    public static Intent addChromeCustomTabsExtras(Context context, Intent intent) {
        if (VERSION.SDK_INT >= 18 && isAvailable(context)) {
            Bundle extras = new Bundle();
            extras.putBinder("android.support.customtabs.extra.SESSION", null);
            intent.putExtras(extras);
            intent.addFlags(1216380928);
        }
        return intent;
    }
}
