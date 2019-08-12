package com.airbnb.android.core.responses.listing;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.WhatsMyPlaceWorth;
import com.airbnb.android.core.utils.Check;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;

public class WhatsMyPlaceWorthResponse extends BaseResponse {
    @JsonProperty("earnings_estimates")
    public WhatsMyPlaceWorth[] data;

    public WhatsMyPlaceWorth forType(C6120RoomType roomType) {
        return (WhatsMyPlaceWorth) Check.notNull((WhatsMyPlaceWorth) FluentIterable.from((E[]) this.data).firstMatch(WhatsMyPlaceWorthResponse$$Lambda$1.lambdaFactory$(roomType)).orNull());
    }
}
