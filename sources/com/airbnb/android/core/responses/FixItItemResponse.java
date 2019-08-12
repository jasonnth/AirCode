package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.FixItItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FixItItemResponse extends BaseResponse {
    @JsonProperty("fixit_report_item")
    public FixItItem item;
}
