package com.airbnb.android.lib.enums;

import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.lib.C0880R;
import com.google.common.collect.FluentIterable;

public enum VerificationFlowToLayoutId {
    Onboarding(C0880R.layout.fragment_verifications_welcome, C0880R.layout.fragment_verifications_complete),
    Booking(C0880R.layout.fragment_verifications_welcome_booking, C0880R.layout.fragment_verifications_complete_booking),
    ContactHost(-1, -1),
    NonBooking(C0880R.layout.fragment_verifications_welcome_booking, C0880R.layout.fragment_verifications_complete_booking),
    UserProfile(C0880R.layout.fragment_verifications_welcome_booking, C0880R.layout.fragment_verifications_complete_booking),
    FinalizeBooking(-1, -1),
    MagicalTripsBooking(C0880R.layout.fragment_verifications_welcome_booking, C0880R.layout.fragment_verifications_complete_booking),
    MagicalTripsGuest(C0880R.layout.fragment_verifications_welcome_booking, C0880R.layout.fragment_verifications_complete_booking),
    MobileHandOff(-1, C0880R.layout.fragment_verifications_complete_booking),
    MobileHandOffNonBooking(-1, C0880R.layout.fragment_verifications_complete_booking),
    VerifiedID(-1, C0880R.layout.fragment_verifications_complete_booking);
    
    private final int completeLayoutId;
    private final VerificationFlow verificationFlow;
    private final int welcomeLayoutId;

    private VerificationFlowToLayoutId(int welcomeLayoutId2, int completeLayoutId2) {
        this.welcomeLayoutId = welcomeLayoutId2;
        this.completeLayoutId = completeLayoutId2;
        this.verificationFlow = VerificationFlow.valueOf(name());
    }

    public int getWelcomeLayoutId() {
        return this.welcomeLayoutId;
    }

    public int getCompleteLayoutId() {
        return this.completeLayoutId;
    }

    public static VerificationFlowToLayoutId find(VerificationFlow verificationFlow2) {
        return (VerificationFlowToLayoutId) FluentIterable.m1283of(values()).firstMatch(VerificationFlowToLayoutId$$Lambda$1.lambdaFactory$(verificationFlow2)).get();
    }
}
