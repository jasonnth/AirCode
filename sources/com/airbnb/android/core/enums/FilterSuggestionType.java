package com.airbnb.android.core.enums;

import com.airbnb.android.core.requests.constants.ListingRequestConstants;

public enum FilterSuggestionType {
    RoomTypes("room_types"),
    Bedrooms(ListingRequestConstants.JSON_BEDROOMS_KEY),
    Beds(ListingRequestConstants.JSON_BEDS_KEY);
    
    public final String type;

    private FilterSuggestionType(String type2) {
        this.type = type2;
    }

    public static FilterSuggestionType fromType(String type2) {
        FilterSuggestionType[] values;
        for (FilterSuggestionType t : values()) {
            if (t.type.equals(type2)) {
                return t;
            }
        }
        return null;
    }
}
