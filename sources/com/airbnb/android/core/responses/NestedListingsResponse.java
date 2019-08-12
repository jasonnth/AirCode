package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.NestedListing;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class NestedListingsResponse extends BaseResponse {
    @JsonProperty("nested_listings")
    private ArrayList<NestedListing> nested_listings;

    public ArrayList<NestedListing> getNestedListings() {
        return this.nested_listings;
    }

    public HashMap<Long, NestedListing> getNestedListingsById() {
        HashMap<Long, NestedListing> nestedListingsById = new HashMap<>();
        Iterator it = this.nested_listings.iterator();
        while (it.hasNext()) {
            NestedListing nestedListing = (NestedListing) it.next();
            nestedListingsById.put(Long.valueOf(nestedListing.getId()), nestedListing);
        }
        return nestedListingsById;
    }
}
