package com.airbnb.android.core.utils;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.models.AirNotificationDevice;
import com.appboy.Appboy;
import com.appboy.IAppboyEndpointProvider;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;

public class GCMHelper extends PushHelper {
    /* access modifiers changed from: private */
    public static final String TAG = GCMHelper.class.getSimpleName();
    AirbnbApi mAirbnbApi;

    public GCMHelper(Context context) {
        super(context);
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
    }

    /* access modifiers changed from: protected */
    public void registerBackground() {
        new AsyncTask<Void, Void, String>() {
            /* access modifiers changed from: protected */
            public String doInBackground(Void... params) {
                Context context = GCMHelper.this.getContext();
                String regId = null;
                try {
                    GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
                    regId = gcm.register(context.getString(C0716R.string.gcm_key));
                    gcm.close();
                    GCMHelper.this.initAppboy(context, regId);
                    GCMHelper.this.setRegistrationId(context, regId);
                    return regId;
                } catch (IOException ex) {
                    Log.d(GCMHelper.TAG, "Error :" + ex.getMessage());
                    return regId;
                } catch (SecurityException e) {
                    return regId;
                }
            }

            public void onPostExecute(String regId) {
                if (!TextUtils.isEmpty(regId)) {
                    GCMHelper.this.mAirbnbApi.enablePushNotifications();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void initAppboy(Context context, String regId) {
        Appboy.getInstance(context).registerAppboyPushMessages(regId);
        Appboy.setAppboyEndpointProvider(new IAppboyEndpointProvider() {
            public Uri getApiEndpoint(Uri appboyEndpoint) {
                return appboyEndpoint.buildUpon().authority("fornax.iad.appboy.com").build();
            }

            public Uri getResourceEndpoint(Uri appboyEndpoint) {
                return appboyEndpoint;
            }
        });
    }

    public String getDeviceType() {
        return AirNotificationDevice.DEVICE_TYPE_ANDROID_GCM;
    }
}
