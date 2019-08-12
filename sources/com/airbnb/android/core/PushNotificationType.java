package com.airbnb.android.core;

import com.airbnb.android.core.utils.Trebuchet;
import java.util.Arrays;
import java.util.HashMap;

public enum PushNotificationType {
    MessageWithTextOnly("message"),
    MessageWithImageAttachment("message_image_attachment"),
    MessageNonBooking("message_non_booking"),
    ReservationGuestAccepted("reservation_guest_accepted"),
    ReservationHostAccepted("reservation_host_accepted"),
    ReservationGuestCancelled("reservation_guest_cancelled"),
    ReservationGuestDeclined("reservation_guest_declined"),
    ReservationGuestExpired("reservation_guest_expired"),
    ReservationGuestPreapproval("preapproval_guest_sent"),
    ReservationGuestSpecialOffer("special_offer_guest"),
    ReservationGuestPreapprovalWithdrawn("preapproval_guest_withdrawn"),
    ReservationGuestSpecialOfferWithdrawn("special_offer_guest_withdrawn"),
    ReservationHostCancelled("reservation_host_cancelled"),
    ReservationHostRequest("reservation_host_request"),
    Checkpoint(Trebuchet.CHECKPOINT),
    TripCharges("trip_invoice_purchase"),
    InstantBookEligible("instant_book_upsell"),
    HostSuspendedFriendly("suspension_notification_responsiveness_friendly"),
    HostSuspendedHard("suspension_notification_responsiveness_hard"),
    HostSuspendedAcceptance("suspension_notification_acceptance"),
    ShareYourTripPrompt("share_your_trip_prompt"),
    SuperHero("hero_push"),
    SuperHeroLocal("hero_push_local"),
    AppboyEngagement("appboy"),
    SupportMessage("support_message"),
    CohostingInvitationReceived("cohosting_invitation_received"),
    CohostingInvitationAccepted("cohosting_invitation_accepted"),
    CohostingInvitationExpired("cohosting_invitation_expired"),
    ReviewYourAccount("review_your_account"),
    ActionNotification("action_notification"),
    Unknown("");
    
    private static HashMap<String, PushNotificationType> serverKeyToInstanceMap;
    public final String type;

    private PushNotificationType(String type2) {
        addKey(type2, this);
        this.type = type2;
    }

    static void addKey(String serverKey, PushNotificationType type2) {
        if (serverKeyToInstanceMap == null) {
            serverKeyToInstanceMap = new HashMap<>();
        }
        serverKeyToInstanceMap.put(serverKey, type2);
    }

    public static PushNotificationType findType(String key) {
        if (!serverKeyToInstanceMap.containsKey(key)) {
            return Unknown;
        }
        return (PushNotificationType) serverKeyToInstanceMap.get(key);
    }

    public boolean matchesAny(PushNotificationType... haystack) {
        return Arrays.asList(haystack).contains(this);
    }
}
