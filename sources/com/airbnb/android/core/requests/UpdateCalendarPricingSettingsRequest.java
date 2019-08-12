package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.models.PricingRule;
import com.airbnb.android.core.responses.CalendarPricingSettingsResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Query;

public class UpdateCalendarPricingSettingsRequest extends BaseRequestV2<CalendarPricingSettingsResponse> {
    private static final String EARLY_BIRD_RULES_FIELD = "earlhy_bird";
    private static final String LAST_MINUTE_RULES_FIELD = "last_minute_rules";
    private static final String LENGTH_OF_STAY_RULES_FIELD = "length_of_stay_rules";
    private static final String PRICE_CHANGE_FIELD = "price_change";
    private static final String PRICE_CHANGE_TYPE_FIELD = "price_change_type";
    private static final String RULE_TYPE_FIELD = "rule_type";
    private static final String THRESHOLD_ONE_FIELD = "threshold_one";
    private static final String THRESHOLD_THREE_FIELD = "threshold_three";
    private static final String THRESHOLD_TWO_FIELD = "threshold_two";
    private final long listingId;
    private final String postBody;

    public static UpdateCalendarPricingSettingsRequest forLengthOfStayRules(long listingId2, List<PricingRule> lengthOfStayRules) {
        return new UpdateCalendarPricingSettingsRequest(listingId2, lengthOfStayRules, null, null);
    }

    public static UpdateCalendarPricingSettingsRequest forLastMinuteRules(long listingId2, List<PricingRule> lastMinuteRules) {
        return new UpdateCalendarPricingSettingsRequest(listingId2, null, lastMinuteRules, null);
    }

    public static UpdateCalendarPricingSettingsRequest forEarlyBirdRules(long listingId2, List<PricingRule> earlyBirdRules) {
        return new UpdateCalendarPricingSettingsRequest(listingId2, null, null, earlyBirdRules);
    }

    private UpdateCalendarPricingSettingsRequest(long listingId2, List<PricingRule> lengthOfStayRules, List<PricingRule> lastMinuteRules, List<PricingRule> earlyBirdRules) {
        this.listingId = listingId2;
        this.postBody = buildPostBody(lengthOfStayRules, lastMinuteRules, earlyBirdRules);
    }

    public String getPath() {
        return "calendar_pricing_settings/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "pricing_settings_for_vh"));
    }

    public Object getBody() {
        return this.postBody;
    }

    public Type successResponseType() {
        return CalendarPricingSettingsResponse.class;
    }

    private static String buildPostBody(List<PricingRule> lengthOfStayRules, List<PricingRule> lastMinuteRules, List<PricingRule> earlyBirdRules) {
        JSONObject requestBody = new JSONObject();
        if (lengthOfStayRules != null) {
            try {
                requestBody.put(LENGTH_OF_STAY_RULES_FIELD, rulesToJson(lengthOfStayRules));
            } catch (JSONException e) {
                if (BuildHelper.isDevelopmentBuild()) {
                    throw new RuntimeException(e);
                }
                C0715L.m1200w(UpdateCalendarPricingSettingsRequest.class.getSimpleName(), (Throwable) e);
            }
        }
        if (lastMinuteRules != null) {
            requestBody.put(LAST_MINUTE_RULES_FIELD, rulesToJson(lastMinuteRules));
        }
        if (earlyBirdRules != null) {
            requestBody.put(EARLY_BIRD_RULES_FIELD, rulesToJson(earlyBirdRules));
        }
        return requestBody.toString();
    }

    private static JSONArray rulesToJson(List<PricingRule> rules) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (PricingRule rule : rules) {
            jsonArray.put(ruleToJson(rule));
        }
        return jsonArray;
    }

    private static JSONObject ruleToJson(PricingRule pricingRule) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(RULE_TYPE_FIELD, pricingRule.getRuleType());
        jsonObject.put(PRICE_CHANGE_FIELD, pricingRule.getPriceChange());
        jsonObject.put(PRICE_CHANGE_TYPE_FIELD, pricingRule.getPriceChangeType());
        jsonObject.put(THRESHOLD_ONE_FIELD, pricingRule.getThresholdOne());
        jsonObject.put(THRESHOLD_TWO_FIELD, pricingRule.getThresholdTwo());
        jsonObject.put(THRESHOLD_THREE_FIELD, pricingRule.getThresholdThree());
        return jsonObject;
    }
}
