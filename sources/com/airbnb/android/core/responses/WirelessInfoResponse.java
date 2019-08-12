package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WirelessInfoResponse extends BaseResponse {
    @JsonProperty("listing_wireless_info")
    public ListingWirelessInfo wirelessInfo;
}
