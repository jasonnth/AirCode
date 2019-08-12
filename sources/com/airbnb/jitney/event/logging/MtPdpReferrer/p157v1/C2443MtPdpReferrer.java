package com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1;

/* renamed from: com.airbnb.jitney.event.logging.MtPdpReferrer.v1.MtPdpReferrer */
public enum C2443MtPdpReferrer {
    ExploreP2Card(1),
    Direct(2),
    ExternalReferrer(3),
    PaidMarketing(4),
    Itinerary(5),
    Wishlist(6),
    P5Upsell(7),
    TripsUpsell(8),
    SimilarExperiences(9),
    DashboardAlert(10),
    Unknown(11),
    Playlist(12),
    SimilarPlaylists(13),
    DeepLink(14),
    PushNotification(15),
    SimilarActivities(16);
    
    public final int value;

    private C2443MtPdpReferrer(int value2) {
        this.value = value2;
    }

    public static C2443MtPdpReferrer findByValue(int value2) {
        switch (value2) {
            case 1:
                return ExploreP2Card;
            case 2:
                return Direct;
            case 3:
                return ExternalReferrer;
            case 4:
                return PaidMarketing;
            case 5:
                return Itinerary;
            case 6:
                return Wishlist;
            case 7:
                return P5Upsell;
            case 8:
                return TripsUpsell;
            case 9:
                return SimilarExperiences;
            case 10:
                return DashboardAlert;
            case 11:
                return Unknown;
            case 12:
                return Playlist;
            case 13:
                return SimilarPlaylists;
            case 14:
                return DeepLink;
            case 15:
                return PushNotification;
            case 16:
                return SimilarActivities;
            default:
                return null;
        }
    }
}
