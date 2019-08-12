package com.airbnb.android.lib.postbooking;

public enum PostBookingState {
    ConfirmAndUpsell(PostBookConfirmAndUpsellFragment.class.getCanonicalName()),
    Landing(PostBookingLandingFragment.class.getCanonicalName()),
    Referral(PostBookingReferralFragment.class.getCanonicalName()),
    MTPostHomeBookingList(MTPostHomeBookingListFragment.class.getCanonicalName()),
    MTPostHomeBookingSplash(MTPostHomeBookingSplashFragment.class.getCanonicalName()),
    BusinessTravelPromo(PostBookingBusinessTravelPromoFragment.class.getCanonicalName()),
    WaitForResponse("Waiting for response"),
    Done("");
    
    public final String fragmentClassName;

    private PostBookingState(String fragmentClassName2) {
        this.fragmentClassName = fragmentClassName2;
    }
}
