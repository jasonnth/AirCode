package com.airbnb.android.core.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.PushNotificationType;
import com.airbnb.android.core.analytics.PushAnalytics;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager.PushType;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.airbnb.android.core.requests.PushNotificationConversionRequest;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.TripsReservationsSyncServiceIntents;
import com.airbnb.android.core.utils.PushHelper;
import com.airbnb.android.core.utils.PushNotificationUtil;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.TextUtil;
import com.google.common.base.Strings;

public abstract class PushNotification {
    private static final String EXTRA_HANDLED = "extra_handled";
    private static final String EXTRA_IS_PUSH = "extra_is_push";
    private static final String EXTRA_PUSH_TYPE = "extra_push_type";
    protected final CharSequence content;
    protected final Context context;
    protected final Intent intent;
    private final BundleBuilder intentExtras = new BundleBuilder();
    private final String pushId;
    public final long serverObjectId;
    private final String type;
    protected final PushNotificationType typeEnum;

    /* access modifiers changed from: protected */
    public abstract void buildNotification(PushNotificationBuilder pushNotificationBuilder);

    public PushNotification(Context context2, Intent intent2) {
        this.context = context2;
        this.intent = intent2;
        this.pushId = intent2.getStringExtra(PushNotificationConversionRequest.PUSH_NOTIFICATION_ID_KEY);
        this.serverObjectId = getServerId();
        this.content = getContent();
        this.type = (this.serverObjectId > -1 ? 1 : (this.serverObjectId == -1 ? 0 : -1)) != 0 ? intent2.getStringExtra("air_type") : null;
        this.typeEnum = PushNotificationType.findType(this.type);
    }

    public final void complete() {
        trackPush("push_received");
        receivePushNotification();
        boolean isHiddenNotification = this.intent.getBooleanExtra(PushNotificationConstants.EXTRA_IS_HIDDEN, false);
        if (!isHiddenNotification) {
            setIntentExtras();
            buildAndShowNotification();
        }
        trackPush(isHiddenNotification ? "push_hidden" : "push_displayed");
        PushHelper.completeWakefulIntent(this.context, this.intent);
    }

    private void trackPush(String operation) {
        PushAnalytics.trackOperation(this.type, this.pushId, operation);
    }

    private long getServerId() {
        try {
            String stringId = this.intent.getStringExtra("air_id");
            if (stringId == null) {
                return -1;
            }
            return Long.decode(stringId).longValue();
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void setIntentExtras() {
        ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) this.intentExtras.putBoolean("extra_is_push", true)).putBoolean("extra_handled", isHandled())).putString("extra_push_type", this.type)).putString(PushNotificationConversionRequest.PUSH_TYPE, this.type)).putString("secret", this.intent.getStringExtra("secret"))).putString(PushNotificationConversionRequest.PUSH_NOTIFICATION_ID_KEY, this.pushId);
        addExtraForLocalPush();
    }

    private void addExtraForLocalPush() {
        PushType pushType = (PushType) this.intent.getSerializableExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE);
        if (pushType != null) {
            this.intentExtras.putSerializable(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE, pushType);
            this.intentExtras.putString(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_LISTING_NAME, this.intent.getStringExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_LISTING_NAME));
            this.intentExtras.putString(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_LOCALIZED_CITY, this.intent.getStringExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_LOCALIZED_CITY));
            this.intentExtras.putString(PushNotificationConstants.DEEP_LINK_KEY, this.intent.getStringExtra(PushNotificationConstants.DEEP_LINK_KEY));
        }
    }

    private CharSequence getContent() {
        CharSequence bodyText = TextUtil.fromHtmlSafe(this.intent.getStringExtra(PushNotificationConstants.EXTRA_BODY));
        if (!TextUtils.isEmpty(bodyText)) {
            return bodyText;
        }
        String legacyMessage = this.intent.getStringExtra("message");
        return TextUtils.isEmpty(legacyMessage) ? "" : TextUtil.fromHtmlSafe(legacyMessage);
    }

    private void buildAndShowNotification() {
        String photoUrl = this.intent.getStringExtra(PushNotificationConstants.EXTRA_ICON_URL);
        boolean hideNotificationProfile = FeatureToggles.hideGuestProfilePhoto(this.typeEnum);
        PushNotificationBuilder titleAndContent = new PushNotificationBuilder(this.context).setTitleAndContent(getTitle(), this.content);
        if (hideNotificationProfile) {
            photoUrl = null;
        }
        PushNotificationBuilder builder = titleAndContent.setIconFromUrl(photoUrl).setIntentExtras(this.intentExtras.toBundle()).setDismissIntent(this.type, this.pushId);
        buildNotification(builder);
        builder.buildAndNotify(PushNotificationUtil.getClientTag(this.typeEnum), PushNotificationUtil.getClientPushId(this.typeEnum, this.serverObjectId));
    }

    private CharSequence getTitle() {
        CharSequence title = TextUtil.fromHtmlSafe(this.intent.getStringExtra(PushNotificationConstants.EXTRA_TITLE));
        if (Strings.isNullOrEmpty(String.valueOf(title))) {
            return this.context.getString(C0716R.string.application_name);
        }
        return title;
    }

    /* access modifiers changed from: protected */
    public void receivePushNotification() {
    }

    private ReservationStatus getReservationStatus() {
        String status = this.intent.getStringExtra("status");
        if (status != null) {
            return ReservationStatus.findStatus(status);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isInquiry() {
        return getReservationStatus() != null && getReservationStatus() == ReservationStatus.Inquiry;
    }

    /* access modifiers changed from: protected */
    public boolean isPending() {
        return getReservationStatus() != null && getReservationStatus() == ReservationStatus.Pending;
    }

    /* access modifiers changed from: protected */
    public boolean isAccepted() {
        return getReservationStatus() != null && getReservationStatus() == ReservationStatus.Accepted;
    }

    /* access modifiers changed from: protected */
    public boolean isHandled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void syncGuestUpcomingTrips(boolean isGuest) {
        if (isGuest) {
            this.context.startService(TripsReservationsSyncServiceIntents.intentForTripsOnlySync(this.context));
        }
    }
}
