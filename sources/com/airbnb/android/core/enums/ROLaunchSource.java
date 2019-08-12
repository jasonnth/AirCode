package com.airbnb.android.core.enums;

import com.airbnb.android.contentframework.ContentFrameworkUtil;
import com.airbnb.android.core.analytics.PushAnalytics;
import com.airbnb.android.core.intents.NestedListingsIntents;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.models.InboxType;

public enum ROLaunchSource {
    Account("account"),
    AlterationAlert("alteration_alert"),
    CalendarPopup("calendar_pop"),
    ConnectToWifiNotification("connect_to_wifi_notification"),
    GuestHome(ContentFrameworkUtil.GUEST_HOME),
    GuestInbox("guest_inbox"),
    ExperienceHostInbox("experience_host_inbox"),
    ExtraService("extra_service"),
    GuestReservations("guest_reservations"),
    HostCalendar("host_calendar"),
    HostHomeWidget("hh_widget"),
    HostInbox("host_inbox"),
    HostInquiry("host_inquiry"),
    HostReservations("host_reservations"),
    HostRO("host_ro"),
    HostStats("host_stats"),
    Itinerary("itinerary"),
    ListingReviews("listing_reviews"),
    MessageThread("message_thread"),
    NestedListing(NestedListingsIntents.INTENT_NESTED_LISTINGS),
    NewBooking("new_booking"),
    PendingPaymentSubmitted("pending_payment_submitted"),
    PushNotification(PushAnalytics.PUSH_NOTIFICATION_EVENT_NAME),
    PushNotificationActionAccept("push_notification_action_accept"),
    PushNotificationActionItinerary("push_notification_action_itinerary"),
    PushNotificationActionMessage("push_notification_action_message"),
    ReservationPicker("reservation_picker"),
    Trips("trips"),
    TripCharges("trip_charges"),
    UNKNOWN("unknown"),
    UserProfile("user_profile"),
    WearReply("wear_reply"),
    WebIntent(ReferralsIntents.ENTRY_POINT_WEB_INTENT);
    
    public final String key;

    public static ROLaunchSource fromInboxType(InboxType inboxType) {
        switch (inboxType) {
            case Host:
            case HostArchived:
                return HostInbox;
            case Guest:
            case GuestArchived:
                return GuestInbox;
            case ExperienceHost:
                return ExperienceHostInbox;
            default:
                throw new IllegalStateException("Unhandled: " + inboxType);
        }
    }

    private ROLaunchSource(String key2) {
        this.key = key2;
    }
}
