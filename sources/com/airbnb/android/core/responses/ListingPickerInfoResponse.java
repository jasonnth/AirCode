package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingPickerInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.List;

public class ListingPickerInfoResponse extends BaseResponse {
    @JsonProperty("listings")
    private ArrayList<ListingPickerInfo> listings;
    @JsonProperty("metadata")
    private ListingPickerInfoMetadata metadata;

    public class ListingPickerInfoMetadata {
        @JsonProperty("listing_count")
        public int listingCount;

        public ListingPickerInfoMetadata() {
        }
    }

    public ArrayList<ListingPickerInfo> getListings() {
        return this.listings;
    }

    public List<ListingPickerInfo> getHasEverBeenAvailableListings() {
        return FluentIterable.from((Iterable<E>) this.listings).filter(ListingPickerInfoResponse$$Lambda$1.lambdaFactory$()).toList();
    }
}
