package com.mparticle.messaging;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.mparticle.MPService;
import com.mparticle.internal.ConfigManager;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class AbstractCloudMessage implements Parcelable {
    public static final int FLAG_DIRECT_OPEN = 2;
    public static final int FLAG_DISPLAYED = 16;
    public static final int FLAG_INFLUENCE_OPEN = 8;
    public static final int FLAG_READ = 4;
    public static final int FLAG_RECEIVED = 1;
    private long mActualDeliveryTime = 0;
    private boolean mDisplayed;
    protected Bundle mExtras;

    public static class InvalidGcmMessageException extends Exception {
        public InvalidGcmMessageException(String detailMessage) {
            super(detailMessage);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Notification buildNotification(Context context);

    /* access modifiers changed from: protected */
    public abstract CloudAction getDefaultAction();

    public abstract int getId();

    public abstract String getPrimaryMessage(Context context);

    public abstract JSONObject getRedactedJsonPayload();

    public AbstractCloudMessage(Parcel pc) {
        boolean z = true;
        this.mExtras = pc.readBundle();
        this.mActualDeliveryTime = pc.readLong();
        if (pc.readInt() != 1) {
            z = false;
        }
        this.mDisplayed = z;
    }

    public AbstractCloudMessage(Bundle extras) {
        this.mExtras = extras;
    }

    public Notification buildNotification(Context context, long time) {
        setActualDeliveryTime(time);
        return buildNotification(context);
    }

    /* access modifiers changed from: protected */
    public Intent getDefaultOpenIntent(Context context, AbstractCloudMessage message) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.putExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, message);
        return launchIntentForPackage;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public static AbstractCloudMessage createMessage(Intent intent, JSONArray keys) throws InvalidGcmMessageException {
        if (MPCloudNotificationMessage.isValid(intent.getExtras())) {
            return new MPCloudNotificationMessage(intent.getExtras());
        }
        return new ProviderCloudMessage(intent.getExtras(), keys);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(this.mExtras);
        dest.writeLong(this.mActualDeliveryTime);
        dest.writeInt(this.mDisplayed ? 1 : 0);
    }

    protected static PendingIntent getLoopbackIntent(Context context, AbstractCloudMessage message, CloudAction action) {
        Intent intent = new Intent(MPService.INTERNAL_NOTIFICATION_TAP + action.getActionIdentifier());
        intent.setClass(context, MPService.class);
        intent.putExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, message);
        intent.putExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA, action);
        return PendingIntent.getService(context, action.getActionIdentifier().hashCode(), intent, 134217728);
    }

    public boolean shouldDisplay() {
        return true;
    }

    public long getActualDeliveryTime() {
        return this.mActualDeliveryTime;
    }

    public void setActualDeliveryTime(long time) {
        this.mActualDeliveryTime = time;
    }

    public void setDisplayed(boolean displayed) {
        this.mDisplayed = displayed;
    }

    public boolean getDisplayed() {
        return this.mDisplayed;
    }

    protected static String getFallbackTitle(Context context) {
        boolean z = false;
        int pushTitle = ConfigManager.getPushTitle(context);
        if (pushTitle > 0) {
            try {
                return context.getString(pushTitle);
            } catch (NotFoundException e) {
                return z;
            }
        } else {
            try {
                return context.getResources().getString(context.getApplicationInfo().labelRes);
            } catch (NotFoundException e2) {
                return z;
            }
        }
    }

    protected static int getFallbackIcon(Context context) {
        int pushIcon = ConfigManager.getPushIcon(context);
        try {
            context.getResources().getDrawable(pushIcon);
        } catch (NotFoundException e) {
            pushIcon = 0;
        }
        if (pushIcon != 0) {
            return pushIcon;
        }
        try {
            pushIcon = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).icon;
        } catch (NameNotFoundException e2) {
        }
        if (pushIcon == 0) {
            return context.getResources().getIdentifier("ic_dialog_alert", "drawable", "android");
        }
        return pushIcon;
    }
}
