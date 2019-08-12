package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.AirRequestFactory.Provider;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListingResponse extends BaseResponse implements Provider<Listing> {
    @JsonProperty("is_paused")
    private boolean isPaused;
    @JsonProperty("listing")
    public Listing listing;
    @JsonProperty("listings")
    private List<Listing> listings;
    @JsonProperty("paused_body")
    private String pausedBody;
    @JsonProperty("paused_title")
    private String pausedTitle;

    public boolean isSoftSuspended() {
        return Boolean.TRUE.equals(Boolean.valueOf(this.isPaused));
    }

    public String getSoftSuspendedEducationTitle() {
        return this.pausedTitle;
    }

    public String getSoftSuspendedEducationBody() {
        return this.pausedBody;
    }

    public Collection<Listing> provide() {
        return new ArrayList(this.listings);
    }

    public List<Listing> getListings() {
        return (List) provide();
    }

    public ArrayList<Listing> getListingsArrayList() {
        return new ArrayList<>(this.listings);
    }
}
