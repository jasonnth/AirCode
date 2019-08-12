package com.airbnb.p027n2;

/* renamed from: com.airbnb.n2.TeamOwner */
public enum TeamOwner {
    DLS("airbnb/android-dls", "#android-dls"),
    CHINA("airbnb/china-product", "#china-product-team"),
    GROWTH("airbnb/mobile-growth", "#mobile-growth"),
    HOMES("airbnb/moho-android", "#android-411"),
    MARKETPLACE("airbnb/?", "#android-411"),
    PAYMENTS("airbnb/android", "#android-411"),
    PSX("airbnb/android", "#android-411"),
    TRIPS("airbnb/mt-explore", "#mt-explore"),
    TRUST("airbnb/android", "#android-411"),
    UNKNOWN("airbnb/android", "#android-411");

    private TeamOwner(String githubEnterpriseGroup, String slackChannel) {
    }
}
