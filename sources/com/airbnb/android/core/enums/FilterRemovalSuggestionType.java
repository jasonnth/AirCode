package com.airbnb.android.core.enums;

import com.airbnb.android.lib.analytics.ManageListingAnalytics;

public enum FilterRemovalSuggestionType {
    Amenity("amenity"),
    InstantBook(ManageListingAnalytics.INSTANT_BOOK);
    
    public final String type;

    private FilterRemovalSuggestionType(String type2) {
        this.type = type2;
    }

    public static FilterRemovalSuggestionType fromType(String type2) {
        FilterRemovalSuggestionType[] values;
        for (FilterRemovalSuggestionType t : values()) {
            if (t.type.equals(type2)) {
                return t;
            }
        }
        return null;
    }
}
