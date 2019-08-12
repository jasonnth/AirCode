package com.airbnb.android.payout.requests;

import com.airbnb.android.core.models.AirAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UserAddressResponse {
    @JsonProperty("user")
    public UserAddress user;

    public static class UserAddress {
        @JsonProperty("addresses")
        public List<AirAddress> addresses;
    }
}
