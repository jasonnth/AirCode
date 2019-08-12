package com.airbnb.android.core.localpushnotifications;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.Services;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager.PushType;
import com.airbnb.android.core.notifications.PushNotificationConstants;

public class LocalPushNotificationReceiver extends WakefulBroadcastReceiver implements LocalPushDeliverListener {
    private static final String TAG = LocalPushNotificationReceiver.class.getSimpleName();
    private Context context;
    private Intent intent;
    private LocalPushNotificationManager localPushNotificationManager;

    public void onReceive(Context context2, Intent intent2) {
        C0715L.m1189d(TAG, "Localpush receiver on recieve");
        this.localPushNotificationManager = CoreApplication.instance(context2).component().localPushNotificationManager();
        this.context = context2;
        this.intent = intent2;
        if (constructPushCopy()) {
            this.localPushNotificationManager.withListener(this).onLocalPushReceived();
        }
    }

    public void deliverLocalPush() {
        C0715L.m1189d(TAG, "Localpush receiver on delivery");
        this.localPushNotificationManager.markLocalPushSeen();
        WakefulBroadcastReceiver.startWakefulService(this.context, new Intent(this.context, Services.push()).putExtras(this.intent.getExtras()));
    }

    private boolean constructPushCopy() {
        PushType pushType = (PushType) this.intent.getSerializableExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE);
        String listingName = this.intent.getStringExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_LISTING_NAME);
        String localizedCity = this.intent.getStringExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_LOCALIZED_CITY);
        if (PushType.P4.equals(pushType) && !TextUtils.isEmpty(listingName)) {
            p4LocalPushConstructCopy(listingName, localizedCity);
        } else if (PushType.P3.equals(pushType) && !TextUtils.isEmpty(listingName) && !TextUtils.isEmpty(localizedCity)) {
            p3LocalPushConstructCopy(listingName, localizedCity);
        } else if (!PushType.P2.equals(pushType) || TextUtils.isEmpty(localizedCity)) {
            LocalPushAnalytics.trackConstructingPushCopy(false, pushType);
            return false;
        } else {
            p2LocalPushConstructCopy(localizedCity);
        }
        LocalPushAnalytics.trackConstructingPushCopy(true, pushType);
        return true;
    }

    private void p4LocalPushConstructCopy(String listingName, String localizedCity) {
        String pushTitle;
        String pushBody;
        if (!TextUtils.isEmpty(localizedCity)) {
            pushTitle = String.format(this.context.getString(C0716R.string.p4_local_abandon_push_title_02), new Object[]{localizedCity});
            pushBody = String.format(this.context.getString(C0716R.string.p4_local_abandon_push_body_02), new Object[]{listingName});
        } else {
            pushTitle = this.context.getString(C0716R.string.p4_local_abandon_push_title_00);
            pushBody = String.format(this.context.getString(C0716R.string.p4_local_abandon_push_body_00), new Object[]{listingName});
        }
        this.intent.putExtra(PushNotificationConstants.EXTRA_TITLE, pushTitle).putExtra(PushNotificationConstants.EXTRA_BODY, pushBody);
    }

    private Intent p3LocalPushConstructCopy(String listingName, String localizedCity) {
        String pushTitle;
        String pushBody;
        if (!TextUtils.isEmpty(localizedCity)) {
            pushTitle = String.format(this.context.getString(C0716R.string.p3_local_abandon_push_title_02), new Object[]{localizedCity});
            pushBody = String.format(this.context.getString(C0716R.string.p3_local_abandon_push_body_02), new Object[]{listingName});
        } else {
            pushTitle = String.format(this.context.getString(C0716R.string.p3_local_abandon_push_title_00), new Object[]{listingName});
            pushBody = this.context.getString(C0716R.string.p3_local_abandon_push_body_00);
        }
        this.intent.putExtra(PushNotificationConstants.EXTRA_TITLE, pushTitle).putExtra(PushNotificationConstants.EXTRA_BODY, pushBody);
        return this.intent;
    }

    private Intent p2LocalPushConstructCopy(String localizedCity) {
        if (TextUtils.isEmpty(localizedCity)) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Local Push: Search location can't be empty"));
        }
        String pushTitle = String.format(this.context.getString(C0716R.string.p2_local_abandon_push_title_02), new Object[]{localizedCity});
        this.intent.putExtra(PushNotificationConstants.EXTRA_TITLE, pushTitle).putExtra(PushNotificationConstants.EXTRA_BODY, this.context.getString(C0716R.string.p2_local_abandon_push_body_02));
        return this.intent;
    }
}
