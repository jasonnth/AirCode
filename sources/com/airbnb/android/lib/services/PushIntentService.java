package com.airbnb.android.lib.services;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.PushNotificationType;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.airbnb.android.core.requests.PushNotificationConversionRequest;
import com.airbnb.android.core.services.push_notifications.PushNotification;
import com.airbnb.android.core.services.push_notifications.ReviewYourAccountPushNotification;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.PushHelper;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.services.push_notifications.ActionNotificationPush;
import com.airbnb.android.lib.services.push_notifications.CheckpointPushNotification;
import com.airbnb.android.lib.services.push_notifications.DefaultPushNotification;
import com.airbnb.android.lib.services.push_notifications.GuestInquiryPushNotification;
import com.airbnb.android.lib.services.push_notifications.GuestReservationPushNotification;
import com.airbnb.android.lib.services.push_notifications.HostReservationPushNotification;
import com.airbnb.android.lib.services.push_notifications.HostSuspendedPushNotification;
import com.airbnb.android.lib.services.push_notifications.InstantBookEligiblePushNotification;
import com.airbnb.android.lib.services.push_notifications.MessagePushNotification;
import com.airbnb.android.lib.services.push_notifications.ShareYourTripPromptPushNotification;
import com.airbnb.android.superhero.SuperHeroPushNotification;
import com.airbnb.android.utils.Strap;
import com.squareup.otto.Bus;

public class PushIntentService extends IntentService {
    public static final String TAG = PushIntentService.class.getSimpleName();
    AirbnbAccountManager accountManager;
    AppForegroundDetector appForegroundDetector;
    AppLaunchAnalytics appLaunchAnalytics;
    Bus bus;
    CalendarStore calendarStore;
    protected MessagingRequestFactory messagingRequestFactory;

    public PushIntentService() {
        super(TAG);
    }

    private void logIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        String token = PushHelper.newInstance(this).getCachedRegistrationIdSafe();
        String token2 = token.substring(Math.max(token.length() - 6, 0), token.length());
        String pushId = extras.getString(PushNotificationConversionRequest.PUSH_NOTIFICATION_ID_KEY);
        String airType = extras.getString("air_type");
        String bodyText = extras.getString(PushNotificationConstants.EXTRA_BODY);
        if (!TextUtils.isEmpty(bodyText)) {
            String bodyText2 = bodyText.substring(0, Math.min(bodyText.length(), 6));
        }
        String message = String.format("received push for token: %s, push_id: %s, type: %s", new Object[]{token2, pushId, airType});
        BugsnagWrapper.leaveBreadcrumb(message);
        Log.d(TAG, message);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"WrongThread"})
    public void onHandleIntent(Intent intent) {
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        Intent intent2 = appboyPushAppendPushType(intent);
        logIntent(intent2);
        this.appLaunchAnalytics.trackColdLaunchCancelled("push_notification_received");
        if (!this.accountManager.isCurrentUserAuthorized()) {
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("push_suppressed", "logged_out"));
            PushHelper.completeWakefulIntent(this, intent2);
            return;
        }
        PushNotification notification = createPushNotification(PushNotificationType.findType(intent2.getStringExtra("air_type")), intent2);
        if (notification != null) {
            notification.complete();
            if (notification instanceof HostReservationPushNotification) {
                this.calendarStore.setCacheResetTime(AirDateTime.now());
                this.messagingRequestFactory.sync(InboxType.Host);
            }
            if ((notification instanceof GuestReservationPushNotification) || (notification instanceof GuestInquiryPushNotification)) {
                this.messagingRequestFactory.sync(InboxType.Guest);
            }
            if (notification instanceof MessagePushNotification) {
                MessagePushNotification messageNotification = (MessagePushNotification) notification;
                this.messagingRequestFactory.expireCacheForThread(messageNotification.serverObjectId, messageNotification.inboxType);
            }
        }
    }

    private Intent appboyPushAppendPushType(Intent intent) {
        if (Boolean.valueOf(intent.getStringExtra("_ab")).booleanValue()) {
            intent.putExtra("air_type", PushNotificationType.AppboyEngagement.type);
        }
        return intent;
    }

    private PushNotification createPushNotification(PushNotificationType type, Intent intent) {
        switch (type) {
            case MessageWithTextOnly:
            case MessageWithImageAttachment:
            case MessageNonBooking:
                return new MessagePushNotification(this, intent, this.bus, type);
            case ReservationGuestSpecialOffer:
            case ReservationGuestSpecialOfferWithdrawn:
            case ReservationGuestPreapproval:
            case ReservationGuestPreapprovalWithdrawn:
                return new GuestInquiryPushNotification(this, intent);
            case ReservationGuestAccepted:
            case ReservationGuestCancelled:
            case ReservationGuestDeclined:
            case ReservationGuestExpired:
                return new GuestReservationPushNotification(this, intent);
            case ReservationHostRequest:
            case ReservationHostCancelled:
            case ReservationHostAccepted:
                return new HostReservationPushNotification(this, intent);
            case SuperHero:
                return new SuperHeroPushNotification(this, intent);
            case Checkpoint:
                return new CheckpointPushNotification(this, intent);
            case InstantBookEligible:
                return new InstantBookEligiblePushNotification(this, intent);
            case HostSuspendedAcceptance:
            case HostSuspendedFriendly:
            case HostSuspendedHard:
                return new HostSuspendedPushNotification(this, intent);
            case ReviewYourAccount:
                return new ReviewYourAccountPushNotification(this, intent);
            case ShareYourTripPrompt:
                if (ChinaUtils.isWechatTripSharingEnabled(this)) {
                    return new ShareYourTripPromptPushNotification(this, intent);
                }
                return null;
            case AppboyEngagement:
                return null;
            case SupportMessage:
                if (!this.appForegroundDetector.isAppInForeground()) {
                    return new DefaultPushNotification(this, intent);
                }
                return null;
            case CohostingInvitationAccepted:
            case CohostingInvitationExpired:
            case CohostingInvitationReceived:
                if (FeatureToggles.enableCohostInvitationAlertsAndPushNotifications()) {
                    return new DefaultPushNotification(this, intent);
                }
                return null;
            case ActionNotification:
                return new ActionNotificationPush(this, intent);
            default:
                return new DefaultPushNotification(this, intent);
        }
    }
}
