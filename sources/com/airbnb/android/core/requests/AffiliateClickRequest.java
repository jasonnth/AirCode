package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.AffiliateClickResponse;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class AffiliateClickRequest extends BaseRequestV2<AffiliateClickResponse> {
    private final String affiliateCampaign;
    private final String affiliateId;
    private final String clickInfo;

    public static AffiliateClickRequest logAffiliateRequest(String affiliateId2, String affiliateCampaign2, String clickInfo2) {
        return new AffiliateClickRequest(affiliateId2, affiliateCampaign2, clickInfo2);
    }

    private AffiliateClickRequest(String affiliateId2, String affiliateCampaign2, String clickInfo2) {
        this.affiliateId = affiliateId2;
        this.affiliateCampaign = affiliateCampaign2;
        this.clickInfo = clickInfo2;
    }

    public String getPath() {
        return "affiliate_clicks";
    }

    public Object getBody() {
        return Strap.make().mo11639kv(AirbnbConstants.PREFS_AFFILIATE_ID, this.affiliateId).mo11639kv(AirbnbConstants.PREFS_AFFILIATE_CAMPAIGN, this.affiliateCampaign).mo11639kv("click_info", this.clickInfo);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return AffiliateClickResponse.class;
    }
}
