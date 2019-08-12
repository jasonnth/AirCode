package com.airbnb.android.core.adtracking;

import android.content.Context;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.utils.ConcurrentUtil;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public final class GoogleAdvertisingIdProvider {
    public static final String GOOGLE_ADVERTISING_ID_TAG = "aaid";
    private static final String TAG = GoogleAdvertisingIdProvider.class.getSimpleName();
    private static String googleAdvertisingId = "";

    private GoogleAdvertisingIdProvider() {
    }

    public static void init(Context context) {
        ConcurrentUtil.deferParallel(GoogleAdvertisingIdProvider$$Lambda$1.lambdaFactory$(context));
    }

    static /* synthetic */ void lambda$init$0(Context context) {
        try {
            googleAdvertisingId = AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
        } catch (Exception exception) {
            C0715L.m1191e(TAG, "Failed to fetch advertising ID: " + exception);
        }
    }

    public static String getGoogleAdvertisingId() {
        return googleAdvertisingId;
    }
}
