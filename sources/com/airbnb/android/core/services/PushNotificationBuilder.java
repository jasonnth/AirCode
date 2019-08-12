package com.airbnb.android.core.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.p000v4.app.NotificationCompat;
import android.support.p000v4.app.NotificationCompat.BigTextStyle;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.analytics.PushAnalytics;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.Random;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class PushNotificationBuilder extends Builder {
    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
    public static final String EXTRA_VOICE_REPLY_IS_HOST_MODE = "extra_voice_reply_is_host_mode";
    public static final int PENDING_INTENT_DEFAULT = 0;
    private Bundle intentExtras = new Bundle();
    private final Intent mainIntent;
    AirbnbPreferences preferences;
    private final Random random = new Random();

    public PushNotificationBuilder(Context context) {
        super(context);
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
        applyDefaultStyle();
        applySoundPreferences();
        this.mainIntent = HomeActivityIntents.intentForDefaultTab(context);
        setPriority(2);
    }

    private void applyDefaultStyle() {
        setColor(this.mContext.getResources().getColor(C0716R.color.c_rausch));
        setAutoCancel(true);
        setCategory(NotificationCompat.CATEGORY_MESSAGE);
        setSmallIcon(C0716R.C0717drawable.ic_stat_notify);
    }

    private void applySoundPreferences() {
        setSound(MiscUtils.getRawResourceUri(C0716R.C0721raw.push_notification), 5);
        setDefaults(6);
    }

    public PushNotificationBuilder setTitleAndContent(CharSequence title, CharSequence content) {
        setContentTitle(title);
        setContentText(content);
        BigTextStyle bigText = new BigTextStyle();
        bigText.setBigContentTitle(title);
        bigText.bigText(content);
        setStyle(bigText);
        return this;
    }

    public PushNotificationBuilder setIconFromUrl(String photoUrl) {
        if (!TextUtils.isEmpty(photoUrl)) {
            Bitmap photo = AirImageView.getImageBlocking(this.mContext, photoUrl, null);
            if (photo != null) {
                setLargeIcon(Bitmap.createScaledBitmap(photo, (int) this.mContext.getResources().getDimension(17104901), (int) this.mContext.getResources().getDimension(17104902), true));
            }
        }
        return this;
    }

    public PushNotificationBuilder hasSeparateNotificationForWearable(boolean separate) {
        if (separate) {
            setLocalOnly(true);
        }
        return this;
    }

    public PushNotificationBuilder setDismissIntent(String type, String pushId) {
        setDeleteIntent(PendingIntent.getService(this.mContext, 0, NotificationDeleteIntentService.intentForNotification(this.mContext, type, pushId), 0));
        return this;
    }

    public PushNotificationBuilder setIntentExtras(Bundle intentExtras2) {
        this.intentExtras = intentExtras2;
        return this;
    }

    public PushNotificationBuilder setLaunchIntent(Intent intent) {
        return setContentIntent(getPendingIntent(intent), intent);
    }

    public PushNotificationBuilder setLaunchIntentWithMain(Intent intent) {
        return setContentIntent(getPendingIntentsWithMain(intent), intent);
    }

    public PushNotificationBuilder setLaunchIntentsWithMain(Intent... intents) {
        return setContentIntent(getPendingIntentsWithMain(intents), intents[intents.length - 1]);
    }

    public PendingIntent getPendingIntentsWithMain(Intent... intents) {
        Intent[] intentsWithMain = new Intent[(intents.length + 1)];
        System.arraycopy(intents, 0, intentsWithMain, 1, intents.length);
        intentsWithMain[0] = this.mainIntent;
        return getPendingIntent(intentsWithMain);
    }

    public void addMessageAction(boolean isHost, Intent actionIntent) {
    }

    public void addAcceptAction(Intent actionIntent, boolean isInquiry) {
        int actionStrRes;
        PendingIntent intent = getPendingIntentsWithMain(actionIntent);
        if (isInquiry) {
            actionStrRes = C0716R.string.ro_response_pre_approve;
        } else {
            actionStrRes = C0716R.string.ro_response_accept;
        }
        addAction(C0716R.C0717drawable.icon_white_check, this.mContext.getString(actionStrRes), intent);
    }

    public void addItineraryAction(Intent actionIntent) {
        addAction(C0716R.C0717drawable.icon_ro_itinerary, this.mContext.getString(C0716R.string.view_itinerary), getPendingIntentsWithMain(actionIntent));
    }

    public void buildAndNotify(String tag, int id) {
        ((NotificationManager) this.mContext.getSystemService("notification")).notify(tag, id, build());
    }

    private PendingIntent getPendingIntent(Intent[] intents) {
        int lastIntentIndex = intents.length - 1;
        Intent lastIntent = intents[lastIntentIndex];
        lastIntent.putExtras(this.intentExtras);
        intents[lastIntentIndex] = EntryActivityIntents.newIntent(this.mContext, lastIntent);
        return PendingIntent.getActivities(this.mContext, this.random.nextInt(Integer.MAX_VALUE), intents, 268435456);
    }

    private PendingIntent getPendingIntent(Intent intent) {
        intent.putExtras(this.intentExtras);
        return PendingIntent.getActivity(this.mContext, this.random.nextInt(Integer.MAX_VALUE), EntryActivityIntents.newIntent(this.mContext, intent), 268435456);
    }

    private PushNotificationBuilder setContentIntent(PendingIntent pendingIntent, Intent intent) {
        if (PendingIntent.getActivity(this.mContext, 0, intent, PKIFailureInfo.duplicateCertReq) != null) {
            PushAnalytics.trackOverridden();
        }
        setContentIntent(pendingIntent);
        return this;
    }
}
