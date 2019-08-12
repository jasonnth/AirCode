package com.airbnb.jitney.event.logging.Wishlists.p298v1;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.IndexPageType */
public enum IndexPageType {
    HOME_INDEX_PAGE(1),
    AUTH_USER_INDEX_PAGE(2),
    POPULAR_INDEX_PAGE(3),
    AIRBNB_PICKS_INDEX_PAGE(4),
    PUBLIC_USER_INDEX_PAGE(5);
    
    public final int value;

    private IndexPageType(int value2) {
        this.value = value2;
    }
}
