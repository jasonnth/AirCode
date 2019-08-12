package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View.OnClickListener;
import com.airbnb.android.collections.fragments.SelectInvitationListingPickerFragment;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.fragments.NavigationLoggingElement;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.intents.PayoutActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.requests.UpdateAlertRequest;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherResult;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.activities.DLSReservationObjectActivity;
import com.airbnb.android.lib.activities.PayForPendingReservationActivity;
import com.airbnb.android.lib.analytics.AlterationAnalytics;
import com.airbnb.android.lib.analytics.HostHomeAnalytics;
import com.airbnb.android.lib.host.HostReactivationIntents;
import com.airbnb.android.lib.host.HostReservationIntentFactory;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;
import com.airbnb.android.lib.utils.webintent.WebIntentMatcherUtil;
import com.airbnb.android.utils.Strap;

public class AlertClickListeners {
    public OnClickListener create(DashboardAlert alert, boolean isGuestAlert) {
        Check.notNull(alert.getAlertTypeEnum(), "alertType is null for alert with Id=" + alert.getId());
        return AlertClickListeners$$Lambda$1.lambdaFactory$(alert, isGuestAlert);
    }

    public static void handleAlert(Context context, DashboardAlert alert, boolean isGuestAlert) {
        logAlertNavigation(context, alert, isGuestAlert);
        if (!alert.isViewed()) {
            alert.setViewed(true);
            UpdateAlertRequest.markAsViewed(alert).execute(NetworkUtil.singleFireExecutor());
        }
        switch (alert.getAlertTypeEnum()) {
            case AddPayoutInfo:
                context.startActivity(PayoutActivityIntents.forManagePayoutMethods(context));
                HostHomeAnalytics.trackPriorityItem(HostHomeAnalytics.HOSPITALITY_ALERTS, HostHomeAnalytics.SET_PAYOUT);
                return;
            case NewListingExpectations:
                context.startActivity(ManageListingIntents.intentForHouseRules(context, alert.getObjectId()));
                return;
            case Webview:
                context.startActivity(WebViewIntentBuilder.newBuilder(context, alert.getUrl()).toIntent());
                return;
            case LeaveReviews:
                context.startActivity(WriteReviewActivity.newIntent(context, alert.getObjectId()));
                return;
            case TryInstantBook:
                context.startActivity(ManageListingIntents.intentForExistingListingSetting(context, alert.getObjectId(), SettingDeepLink.InstantBook));
                return;
            case FriendlySuspended:
                context.startActivity(HostReactivationIntents.intentForHostReactivation(context));
                HostHomeAnalytics.trackClickSuspensionAlert();
                return;
            case HostHardSuspended:
                context.startActivity(HelpCenterIntents.intentForHelpCenterArticle(context, HelpCenterArticle.HARD_SUSPENSION).toIntent());
                return;
            case AlterationRequest:
                alterationAlertClicked(context, alert, isGuestAlert);
                return;
            case GuestPendingPayment:
                pendingPaymentClicked(context, alert);
                return;
            case TripSupportHostIssueResolved:
            case TripSupportHostGeneralContact:
            case TripSupportHostFirstContact:
            case TripSupportHostFollowup15Min:
            case TripSupportHostResoCanceled:
            case TripSupportGuestFollowup:
            case CancelPolicyUpdatesFlexible:
            case CancelPolicyUpdatesMultiple:
            case CancelPolicyUpdatesOther:
            case CancelPolicyUpdatesLaunchFlexible:
            case CancelPolicyUpdatesLaunchMultiple:
            case CancelPolicyUpdatesLaunchOther:
            case CohostingInvitationReceived:
            case CohostingInvitationAccepted:
            case CohostingInvitationExpired:
            case CohostingNewHostReminder:
                launchAlertUrl(alert, context);
                return;
            case ListingInvitedToApplySelect:
                context.startActivity(SelectInvitationListingPickerFragment.intentForModal(context));
                return;
            case PaidAmenityHostOfferService:
                context.startActivity(PaidAmenityIntents.createAmenitiesIntent(context));
                return;
            case PaidAmenityGuestPurchaseService:
                Reservation reservation = alert.getReservation();
                context.startActivity(PaidAmenityIntents.purchaseAmenities(context, reservation.getConfirmationCode(), (long) reservation.getThreadId(), reservation.getListing().getId()));
                return;
            case ActionNotfication:
                DeepLinkUtils.startActivityForDeepLink(context, alert.getDeeplinkUrl());
                return;
            case NewLinkedListings:
                context.startActivity(ManageListingIntents.intentForExistingListingSettingWithFullscreenLoader(context, alert.getObjectId(), SettingDeepLink.NestedListings, true));
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Alert type: " + alert.getAlertTypeEnum() + " is not handled"));
                return;
        }
    }

    private static void logAlertNavigation(Context context, final DashboardAlert alert, final boolean isGuestAlert) {
        AirbnbApplication.instance(context).component().navigationAnalytics().trackImpression(new NavigationLoggingElement() {
            public NavigationTag getNavigationTrackingTag() {
                return NavigationTag.Alert;
            }

            public Strap getNavigationTrackingParams() {
                return new Strap().mo11639kv("alert_type", alert.getAlertTypeFormatted()).mo11638kv("alert_id", alert.getId()).mo11639kv("role", isGuestAlert ? TripRole.ROLE_KEY_GUEST : TripRole.ROLE_KEY_HOST);
            }
        });
    }

    private static void launchAlertUrl(DashboardAlert alert, Context context) {
        AirbnbAccountManager accountManager = AirbnbApplication.instance(context).component().accountManager();
        String url = alert.getUrl();
        WebIntentMatcherResult match = WebIntentMatcherUtil.getMatch(context, Uri.parse(url), accountManager.getCurrentUser());
        if (match.hasIntentMatch()) {
            context.startActivity(match.getIntent());
            return;
        }
        context.startActivity(WebViewIntentBuilder.newBuilder(context, url).authenticate().toIntent());
        BugsnagWrapper.notify((Throwable) new IllegalStateException("Unhandled dashboard alert url. Alert: " + alert));
    }

    private static void pendingPaymentClicked(Context context, DashboardAlert alert) {
        context.startActivity(PayForPendingReservationActivity.intentForPayment(context, alert.getPaymentId(), alert.getReservation()));
    }

    private static void alterationAlertClicked(Context context, DashboardAlert alert, boolean isGuestAlert) {
        Intent reservationIntent;
        Reservation reservation = alert.getReservation();
        if (!reservation.hasPendingAlterations()) {
            if (isGuestAlert) {
                reservationIntent = DLSReservationObjectActivity.intentForReservationId(context, reservation.getId());
            } else {
                reservationIntent = HostReservationIntentFactory.forReservationId(context, reservation.getId(), ROLaunchSource.AlterationAlert);
            }
            context.startActivity(reservationIntent);
            return;
        }
        AlterationAnalytics.trackViewPendingAlterationFromHostAlert(reservation, reservation.getFirstPendingAlteration());
        context.startActivity(ReactNativeIntents.intentForAlterations(context, reservation, !isGuestAlert));
    }
}
