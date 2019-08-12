package com.airbnb.android.lib.paidamenities.responses;

import com.airbnb.android.core.models.PaidAmenityOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PaidAmenityOrdersResponse {
    @JsonProperty("paid_amenity_orders")
    public List<PaidAmenityOrder> paidAmenityOrders;
}
