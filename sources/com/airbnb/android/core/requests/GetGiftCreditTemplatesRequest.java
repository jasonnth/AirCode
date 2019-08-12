package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.GetGiftCreditTemplatesResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetGiftCreditTemplatesRequest extends BaseRequestV2<GetGiftCreditTemplatesResponse> {
    private static final String LIMIT = "_limit";
    private static final String PARAM_CAMPAIGN_NAME = "campaign_name";
    private static final String PATH_GIFT_CREDIT_TEMPLATES = "gift_credit_templates/";
    private final String campaignName;
    private int itemsPerPage = 20;

    public GetGiftCreditTemplatesRequest(String campaignName2) {
        this.campaignName = campaignName2;
    }

    public GetGiftCreditTemplatesRequest(String campaignName2, int itemsPerPage2) {
        this.campaignName = campaignName2;
        this.itemsPerPage = itemsPerPage2;
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public String getPath() {
        return PATH_GIFT_CREDIT_TEMPLATES;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(PARAM_CAMPAIGN_NAME, this.campaignName).mo7893kv("_limit", this.itemsPerPage);
    }

    public Type successResponseType() {
        return GetGiftCreditTemplatesResponse.class;
    }
}
