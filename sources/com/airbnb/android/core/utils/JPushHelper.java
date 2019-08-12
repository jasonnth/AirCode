package com.airbnb.android.core.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.JPushInitializer;
import com.airbnb.android.core.models.AirNotificationDevice;
import p005cn.jpush.android.api.JPushInterface;

public class JPushHelper extends PushHelper {
    /* access modifiers changed from: private */
    public static final String TAG = JPushHelper.class.getSimpleName();
    AirbnbApi airbnbApi;

    public JPushHelper(Context context) {
        super(context);
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
    }

    /* access modifiers changed from: protected */
    public void registerBackground() {
        new AsyncTask<Void, Void, String>() {
            /* access modifiers changed from: protected */
            public String doInBackground(Void... params) {
                String regId = null;
                try {
                    Context context = JPushHelper.this.getContext();
                    new JPushInitializer(context).initialize();
                    regId = JPushInterface.getRegistrationID(context);
                    JPushHelper.this.setRegistrationId(context, regId);
                    C0715L.m1191e(JPushHelper.TAG, "Register JPush service success " + regId);
                    return regId;
                } catch (SecurityException e) {
                    C0715L.m1191e(JPushHelper.TAG, "Register JPush service failed " + e.getMessage());
                    return regId;
                }
            }

            public void onPostExecute(String regId) {
                if (TextUtils.isEmpty(regId)) {
                    C0715L.m1191e(JPushHelper.TAG, "Empty JPush registration id");
                } else {
                    JPushHelper.this.airbnbApi.enablePushNotifications();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static boolean enableJPush(Context context) {
        if (MiscUtils.hasGooglePlayServices(context)) {
            return false;
        }
        if (BuildHelper.isDebugFeaturesEnabled()) {
            return true;
        }
        return Trebuchet.launch(TrebuchetKeys.ENABLE_JPUSH);
    }

    public String getDeviceType() {
        return AirNotificationDevice.DEVICE_TYPE_ANDROID_JPUSH;
    }
}
