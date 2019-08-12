package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReferralStatusForMobileResponse extends BaseResponse {
    public ReferralStatusForMobile referralStatus;
    @JsonProperty("referral_statuses")
    public List<ReferralStatusForMobile> referralStatuses;
}
