package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.UserFlagDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class GetUserFlagDetailsResponse extends BaseResponse {
    @JsonProperty("user_flag_details")
    public List<UserFlagDetail> userFlagDetails;
}
