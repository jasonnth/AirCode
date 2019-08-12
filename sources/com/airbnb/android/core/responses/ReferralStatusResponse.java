package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ReferralStatus;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@Deprecated
public class ReferralStatusResponse extends BaseResponse {
    public double offerReferrerCreditGuest;
    public double offerReferrerCreditHost;
    @JsonProperty("referral_statuses")
    public List<ReferralStatus> referralStatuses;

    public String getReferralBonusGuest(CurrencyFormatter currencyFormatter) {
        return currencyFormatter.formatNativeCurrency(this.offerReferrerCreditGuest, true);
    }
}
