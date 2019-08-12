package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.generated.GenDashboardAlert;
import com.airbnb.erf.Experiments;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DashboardAlert extends GenDashboardAlert implements Comparable<DashboardAlert> {
    public static final Creator<DashboardAlert> CREATOR = new Creator<DashboardAlert>() {
        public DashboardAlert[] newArray(int size) {
            return new DashboardAlert[size];
        }

        public DashboardAlert createFromParcel(Parcel source) {
            DashboardAlert object = new DashboardAlert();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final List<DashboardAlertType> GUEST_ALERTS = ImmutableList.m1287of(DashboardAlertType.GuestPendingPayment, DashboardAlertType.AlterationRequest, DashboardAlertType.PaidAmenityGuestPurchaseService);
    private static final List<DashboardAlertType> HOST_ALERTS = ImmutableList.m1293of(DashboardAlertType.Webview, DashboardAlertType.AddPayoutInfo, DashboardAlertType.TryInstantBook, DashboardAlertType.FriendlySuspended, DashboardAlertType.HostHardSuspended, DashboardAlertType.AlterationRequest, DashboardAlertType.CancelPolicyUpdatesFlexible, DashboardAlertType.CancelPolicyUpdatesOther, DashboardAlertType.CancelPolicyUpdatesMultiple, DashboardAlertType.CancelPolicyUpdatesLaunchFlexible, DashboardAlertType.CancelPolicyUpdatesLaunchOther, DashboardAlertType.CancelPolicyUpdatesLaunchMultiple, DashboardAlertType.PaidAmenityHostOfferService, DashboardAlertType.ListingInvitedToApplySelect, DashboardAlertType.NewListingExpectations, DashboardAlertType.NewLinkedListings);
    private DashboardAlertType typeEnum;

    public enum DashboardAlertType {
        Webview("webview"),
        AddPayoutInfo("add_payout_info"),
        LeaveReviews("leave_reviews"),
        TryInstantBook("instant_book_avail"),
        FriendlySuspended("host_suspended_friendly"),
        HostHardSuspended("host_hard_suspension_review"),
        AlterationRequest("reservation_alteration_request"),
        GuestPendingPayment("guest_pending_payment_reservation"),
        TripSupportHostIssueResolved("trip_support_issue_resolved"),
        TripSupportHostGeneralContact("trip_support_general_contact"),
        TripSupportHostFirstContact("trip_support_host_first_contact"),
        TripSupportHostFollowup15Min("trip_support_host_followup_15_min"),
        TripSupportHostResoCanceled("trip_support_reso_canceled"),
        AlertTypeTripSupportHostFirstContactNoWait("trip_support_host_first_contact_no_wait"),
        TripSupportGuestFollowup("trip_support_guest_followup"),
        CancelPolicyUpdatesFlexible("cancel_policy_updates_flexible"),
        CancelPolicyUpdatesOther("cancel_policy_updates_other"),
        CancelPolicyUpdatesMultiple("cancel_policy_updates_multiple"),
        CancelPolicyUpdatesLaunchFlexible("cancel_policy_updates_launch_flexible"),
        CancelPolicyUpdatesLaunchOther("cancel_policy_updates_launch_other"),
        CancelPolicyUpdatesLaunchMultiple("cancel_policy_updates_launch_multiple"),
        NewListingExpectations("new_listing_expectations"),
        PaidAmenityGuestPurchaseService("paid_amenity_shop_services"),
        PaidAmenityHostOfferService("paid_amenity_offer_services"),
        ListingInvitedToApplySelect("listing_invited_to_apply_to_select"),
        ActionNotfication("magical_trips_store_count"),
        CohostingInvitationReceived("cohosting_invitation_received"),
        CohostingInvitationAccepted("cohosting_invitation_accepted"),
        CohostingInvitationExpired("cohosting_invitation_expired"),
        CohostingNewHostReminder("cohosting_new_host_reminder"),
        NewLinkedListings("new_linked_listings");
        
        private static Map<String, DashboardAlertType> map;
        public final String alertTypeFormatted;

        private DashboardAlertType(String type) {
            this.alertTypeFormatted = type;
        }

        public static DashboardAlertType findType(String key) {
            if (map == null) {
                DashboardAlertType[] values = values();
                map = new ArrayMap(values.length);
                for (DashboardAlertType alertType : values) {
                    map.put(alertType.alertTypeFormatted, alertType);
                }
            }
            return (DashboardAlertType) map.get(key);
        }

        public boolean matchesAny(DashboardAlertType... haystack) {
            return Arrays.asList(haystack).contains(this);
        }
    }

    public int compareTo(DashboardAlert other) {
        return 0 - getCreatedAt().compareTo(other.getCreatedAt());
    }

    public DashboardAlertType getAlertTypeEnum() {
        if (this.typeEnum == null) {
            this.typeEnum = DashboardAlertType.findType(getAlertTypeFormatted());
        }
        return this.typeEnum;
    }

    public static List<DashboardAlertType> getSupportedGuestTypes() {
        List<DashboardAlertType> types = new ArrayList<>(GUEST_ALERTS);
        if (FeatureToggles.isTripAssistantEnabled()) {
            types.add(DashboardAlertType.TripSupportGuestFollowup);
        }
        if (FeatureToggles.areActionNotificationsEnabled()) {
            types.add(DashboardAlertType.ActionNotfication);
        }
        if (FeatureToggles.enableCohostInvitationAlertsAndPushNotifications()) {
            types.add(DashboardAlertType.CohostingInvitationReceived);
        }
        return types;
    }

    public static List<DashboardAlertType> getSupportedHostTypes(Context context) {
        List<DashboardAlertType> types = new ArrayList<>(HOST_ALERTS);
        if (FeatureToggles.isTripAssistantEnabled()) {
            types.add(DashboardAlertType.TripSupportHostIssueResolved);
            types.add(DashboardAlertType.TripSupportHostGeneralContact);
            types.add(DashboardAlertType.TripSupportHostFirstContact);
            types.add(DashboardAlertType.TripSupportHostFollowup15Min);
            types.add(DashboardAlertType.TripSupportHostResoCanceled);
            types.add(DashboardAlertType.AlertTypeTripSupportHostFirstContactNoWait);
        }
        if (FeatureToggles.enableCohostInvitationAlertsAndPushNotifications()) {
            types.add(DashboardAlertType.CohostingInvitationReceived);
            types.add(DashboardAlertType.CohostingInvitationAccepted);
            types.add(DashboardAlertType.CohostingInvitationExpired);
        }
        if (Experiments.showCohostingNewHostReminderAlert()) {
            types.add(DashboardAlertType.CohostingNewHostReminder);
        }
        return types;
    }
}
