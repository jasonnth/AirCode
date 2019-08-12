package com.airbnb.android.core.enums;

import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;

public enum InlineSearchFeedFilterItemType {
    Amenity("amenity"),
    Dates(CancellationAnalytics.VALUE_PAGE_DATES),
    Guests("guest_count"),
    InstantBook(ManageListingAnalytics.INSTANT_BOOK),
    InstantBookAutoOn("instant_book_auto_on"),
    Price(EditPriceFragment.RESULT_PRICE),
    BedroomCount("bedroom_count"),
    FamilyFriendlyRoomType("family_room_type"),
    LongTermStay("long_term_stay"),
    RoomType("room_type"),
    TripType("trip_type", false),
    Location("location");
    
    public final boolean enabled;
    public final String type;

    private InlineSearchFeedFilterItemType(String type2) {
        this(r2, r3, type2, true);
    }

    private InlineSearchFeedFilterItemType(String type2, boolean enabled2) {
        this.type = type2;
        this.enabled = enabled2;
    }

    public static InlineSearchFeedFilterItemType fromType(String type2) {
        InlineSearchFeedFilterItemType[] values;
        for (InlineSearchFeedFilterItemType t : values()) {
            if (t.type.equals(type2)) {
                return t;
            }
        }
        return null;
    }
}
