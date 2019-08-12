package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class ListingRegistrationProcessesResponse extends BaseResponse {
    @JsonProperty("listing_registration_processes")
    public ArrayList<ListingRegistrationProcess> listingRegistrationProcesses;

    public ListingRegistrationProcess getFirstListingRegistrationProcess() {
        if (this.listingRegistrationProcesses == null || this.listingRegistrationProcesses.isEmpty()) {
            return null;
        }
        return (ListingRegistrationProcess) this.listingRegistrationProcesses.get(0);
    }
}
