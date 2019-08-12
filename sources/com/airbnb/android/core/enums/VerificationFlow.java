package com.airbnb.android.core.enums;

import android.content.Context;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.constants.ActivityConstants;
import com.airbnb.android.core.identity.IdentityReactNativeInfoType;
import com.airbnb.android.core.requests.VerificationsRequest.Filter;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.Strap;

public enum VerificationFlow {
    Onboarding(Filter.SignUp, "Registration", VerificationFlowText.Booking),
    Booking(Filter.Booking, "Book", VerificationFlowText.Booking),
    BookingV2(Filter.Booking, "BookingV2", VerificationFlowText.Booking),
    ContactHost(Filter.ContactHost, "ContactHost", VerificationFlowText.Booking),
    NonBooking(Filter.Booking, "NonBooking", VerificationFlowText.NonBooking),
    UserProfile(Filter.Booking, ActivityConstants.ACTIVITY_USER_PROFILE, VerificationFlowText.NonBooking),
    FinalizeBooking(Filter.Booking, "FinalizeBooking", VerificationFlowText.Booking),
    FinalizeBookingV2(Filter.Booking, "FinalizeBookingV2", VerificationFlowText.Booking),
    MagicalTripsBooking(Filter.Booking, "MagicalTripsBooking", VerificationFlowText.MagicalTripsBooking),
    MagicalTripsGuest(Filter.Booking, "MagicalTripsGuest", VerificationFlowText.MagicalTripsGuest),
    MobileHandOff(Filter.Booking, "MobileHandOff", VerificationFlowText.Booking),
    MobileHandOffV2(Filter.Booking, "MobileHandOffV2", VerificationFlowText.Booking),
    MobileHandOffNonBooking(Filter.Booking, "MobileHandOffNonBooking", VerificationFlowText.NonBooking),
    VerifiedID(Filter.Booking, "VerifiedID", VerificationFlowText.HostRequired),
    VerifiedIDBookingCheckpoint(Filter.Booking, "VerifiedIDForCheckpoint", VerificationFlowText.Booking),
    VerifiedIDNonBooking(Filter.Booking, "VerifiedIDNonBooking", VerificationFlowText.NonBooking),
    VerifiedIDForBookingV2(Filter.Booking, "VerifiedIDForBookingV2", VerificationFlowText.HostRequired),
    ListYourSpaceDLS(Filter.Booking, "ListYourSpaceDLS", VerificationFlowText.NonBooking),
    CohostInvitation(Filter.AcceptCohostInvitation, "CohostInvitation", VerificationFlowText.CohostInvitation),
    ListYourSpaceIdentity(Filter.Booking, "ListYourSpaceIdentity", VerificationFlowText.NonBooking),
    ProfileCompletion(Filter.Booking, "ProfileCompletion", VerificationFlowText.ProfileCompletion);
    
    private final String flowName;
    private final Filter requestFilter;
    private final VerificationFlowText text;

    private VerificationFlow(Filter requestFilter2, String flowName2, VerificationFlowText text2) {
        this.requestFilter = requestFilter2;
        this.flowName = flowName2;
        this.text = text2;
    }

    public Filter getRequestFilter() {
        return this.requestFilter;
    }

    public boolean isSkipEnabled() {
        return this == Onboarding;
    }

    public boolean requiresIdentityTreatment() {
        return this == Onboarding || this == Booking || this == VerifiedIDForBookingV2 || this == NonBooking || this == VerifiedID || this == UserProfile || this == ListYourSpaceDLS;
    }

    public boolean excludePhoneAndEmail() {
        return this == Booking || this == NonBooking || this == UserProfile || this == MagicalTripsBooking || this == MagicalTripsGuest || this == MobileHandOff || this == MobileHandOffNonBooking;
    }

    public boolean showFiveAxiomIntro() {
        return this.text == VerificationFlowText.Booking || this.text == VerificationFlowText.HostRequired || this.text == VerificationFlowText.CohostInvitation;
    }

    public boolean showFinishFragment() {
        return isIdentityRedesign() || this == MagicalTripsBooking || this == MagicalTripsGuest;
    }

    public String getFlowName() {
        return this.flowName;
    }

    public VerificationFlowText getText() {
        return this.text;
    }

    public static VerificationFlow getVerificationFlowFromString(String flowName2) {
        if (ContactHost.getFlowName().equals(flowName2)) {
            return ContactHost;
        }
        if (Booking.getFlowName().equals(flowName2)) {
            return Booking;
        }
        if (FinalizeBooking.getFlowName().equals(flowName2)) {
            return FinalizeBooking;
        }
        if (Onboarding.getFlowName().equals(flowName2)) {
            return Onboarding;
        }
        try {
            return valueOf(flowName2);
        } catch (IllegalArgumentException e) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown verification flow " + flowName2));
            return Booking;
        }
    }

    public boolean isHandOffFlow() {
        return this == MobileHandOff || this == MobileHandOffNonBooking || this == MobileHandOffV2;
    }

    public boolean isAddedToOtherFlow() {
        return this == VerifiedIDForBookingV2 || this == BookingV2 || this == MobileHandOffV2;
    }

    public boolean isWhiteBackground() {
        return isIdentityRedesign() || this == VerifiedIDForBookingV2 || this == BookingV2 || this == FinalizeBookingV2 || this == MobileHandOffV2;
    }

    public boolean isIdentityRedesign() {
        if (this == ListYourSpaceIdentity) {
            return true;
        }
        if (this.text == VerificationFlowText.Booking || this.text == VerificationFlowText.HostRequired) {
            return Trebuchet.launch(TrebuchetKeys.IDENTITY_REDESIGN_FOR_BOOKING);
        }
        return false;
    }

    public IdentityReactNativeInfoType getIntroIdentityReactNativeInfoType() {
        if (this == ListYourSpaceIdentity) {
            return IdentityReactNativeInfoType.LYS_INTRO;
        }
        if (this.text == VerificationFlowText.Booking || this.text == VerificationFlowText.HostRequired) {
            return IdentityReactNativeInfoType.BOOKING_INTRO;
        }
        return null;
    }

    public IdentityReactNativeInfoType getSelfieIdentityReactNativeInfoType() {
        if (this == ListYourSpaceIdentity) {
            return IdentityReactNativeInfoType.LYS_SELFIE_INTRO;
        }
        if (this.text == VerificationFlowText.Booking || this.text == VerificationFlowText.HostRequired) {
            return IdentityReactNativeInfoType.BOOKING_SELFIE_INTRO;
        }
        return null;
    }

    public IdentityReactNativeInfoType getFinishIdentityReactNativeInfoType() {
        if (this == ListYourSpaceIdentity) {
            return IdentityReactNativeInfoType.LYS_FINISH;
        }
        if (this.text == VerificationFlowText.Booking || this.text == VerificationFlowText.HostRequired) {
            return IdentityReactNativeInfoType.BOOKING_FINISH;
        }
        return null;
    }

    public boolean isFromNewP4IdentityStep() {
        return this == BookingV2 || this == VerifiedIDForBookingV2;
    }

    public Strap getNavigationTrackingParams(Context context) {
        if (this == VerifiedIDForBookingV2 || this == BookingV2 || this == FinalizeBookingV2 || this == MobileHandOffV2) {
            return BookingAnalytics.getP4NavigationTrackingParams(true);
        }
        return Strap.make();
    }
}
