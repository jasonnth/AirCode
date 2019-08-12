package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SectionedListingDescription;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingDescriptionTranslateResponse extends BaseResponse {
    @JsonProperty("listing_description")
    private SectionedListingDescription translateSectionedListingDescription;

    public SectionedListingDescription getTranslateSectionedListingDescription() {
        return this.translateSectionedListingDescription;
    }
}
