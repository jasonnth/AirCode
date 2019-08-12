package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class HostListingsSimilarListingsResponse extends BaseResponse {
    @JsonProperty("listings")
    List<SimilarListingWrapper> listings;

    public static class SimilarListingWrapper {
        @JsonProperty("id")

        /* renamed from: id */
        long f1242id;
        @JsonProperty("name")
        String name;
        @JsonProperty("similar_listings")
        List<SimilarListing> similarListings;

        /* access modifiers changed from: 0000 */
        public Listing getListingObject() {
            Listing listing = new Listing();
            listing.setId(this.f1242id);
            listing.setName(this.name);
            return listing;
        }

        /* access modifiers changed from: 0000 */
        public List<SimilarListing> getSimilarListings() {
            return this.similarListings;
        }
    }

    public HashMap<Listing, List<SimilarListing>> getSimilarListings() {
        HashMap<Listing, List<SimilarListing>> listingsMap = new HashMap<>();
        if (!ListUtils.isEmpty((Collection<?>) this.listings)) {
            for (SimilarListingWrapper wrapper : this.listings) {
                listingsMap.put(wrapper.getListingObject(), wrapper.getSimilarListings());
            }
        }
        return listingsMap;
    }
}
