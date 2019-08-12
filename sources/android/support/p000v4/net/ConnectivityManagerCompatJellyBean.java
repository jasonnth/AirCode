package android.support.p000v4.net;

import android.annotation.TargetApi;
import android.net.ConnectivityManager;

@TargetApi(16)
/* renamed from: android.support.v4.net.ConnectivityManagerCompatJellyBean */
class ConnectivityManagerCompatJellyBean {
    public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
        return cm.isActiveNetworkMetered();
    }
}
