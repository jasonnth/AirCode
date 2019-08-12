package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetHostReferralInfoResponse extends BaseResponse {
    @JsonProperty("host_referral_referrer_infos")
    public List<HostReferralReferrerInfo> hostReferralInfos;
    public HostReferralReferrerInfo info;
}
