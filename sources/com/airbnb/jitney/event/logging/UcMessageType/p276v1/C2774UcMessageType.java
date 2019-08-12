package com.airbnb.jitney.event.logging.UcMessageType.p276v1;

/* renamed from: com.airbnb.jitney.event.logging.UcMessageType.v1.UcMessageType */
public enum C2774UcMessageType {
    BookingProbability(1),
    CompetingViewsP2(2),
    CompetingViewsP3(3),
    LastBooked(4),
    LastMinuteTrip(5),
    LongTermPricingDiscount(6),
    GoodPrice(7),
    NumberAvailable(8),
    PercentageAvailable(9),
    PriceTrends(10),
    RareFind(11),
    RecentViews(12),
    SmartPromotion(13),
    ExperienceViews(14),
    FriendsOnExperiences(15),
    CancellationPolicy(16),
    CompetingViewsP4(17),
    LongTermStayFriendly(18),
    Unknown(19);
    
    public final int value;

    private C2774UcMessageType(int value2) {
        this.value = value2;
    }
}
