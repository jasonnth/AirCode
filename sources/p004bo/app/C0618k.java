package p004bo.app;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.k */
public final class C0618k implements C0626o {

    /* renamed from: a */
    private static final String f841a = AppboyLogger.getAppboyLogTag(C0618k.class);

    /* renamed from: b */
    private C0633v f842b = C0633v.UNKNOWN;

    /* renamed from: c */
    private boolean f843c = false;

    /* renamed from: d */
    private boolean f844d = false;

    /* renamed from: a */
    public C0633v mo7308a() {
        return this.f842b;
    }

    /* renamed from: a */
    public void mo7309a(Intent intent, ConnectivityManager connectivityManager) {
        String action = intent.getAction();
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                if (activeNetworkInfo == null || booleanExtra) {
                    this.f842b = C0633v.NONE;
                    this.f844d = false;
                    this.f843c = false;
                    return;
                }
                this.f844d = activeNetworkInfo.isConnectedOrConnecting();
                this.f843c = activeNetworkInfo.isRoaming();
                switch (activeNetworkInfo.getType()) {
                    case 0:
                        switch (activeNetworkInfo.getSubtype()) {
                            case 3:
                                this.f842b = C0633v.THREE_G;
                                return;
                            case 13:
                                this.f842b = C0633v.FOUR_G;
                                return;
                            default:
                                this.f842b = C0633v.TWO_G;
                                return;
                        }
                    case 1:
                        this.f842b = C0633v.WIFI;
                        return;
                    case 2:
                        this.f842b = C0633v.UNKNOWN;
                        return;
                    case 3:
                        this.f842b = C0633v.UNKNOWN;
                        return;
                    case 4:
                        this.f842b = C0633v.UNKNOWN;
                        return;
                    case 5:
                        this.f842b = C0633v.UNKNOWN;
                        return;
                    case 6:
                        this.f842b = C0633v.WIFI;
                        return;
                    case 7:
                        this.f842b = C0633v.UNKNOWN;
                        return;
                    case 8:
                        this.f842b = C0633v.UNKNOWN;
                        return;
                    case 9:
                        this.f842b = C0633v.UNKNOWN;
                        return;
                    default:
                        this.f842b = C0633v.UNKNOWN;
                        return;
                }
            } catch (SecurityException e) {
                AppboyLogger.m1736e(f841a, "Failed to get active network information. Ensure the permission android.permission.ACCESS_NETWORK_STATE is defined in your AndroidManifest.xml", e);
            }
        } else {
            AppboyLogger.m1739w(f841a, String.format("Unexpected system broadcast received [%s]", new Object[]{action}));
        }
    }
}
