package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequency;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import com.airbnb.android.core.utils.BuildHelper;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class DemandBasedPricingRequest extends BaseRequestV2<DemandBasedPricingResponse> {
    private static final String ENABLED_FIELD = "is_enabled";
    private static final String FREQUENCY_FIELD = "desired_hosting_frequency";
    private static final String LISTING_ID_FIELD = "listing_id";
    private static final String MAX_PRICE_FIELD = "max_price";
    private static final String MIN_PRICE_FIELD = "min_price";
    private final long listingId;
    private final String postBody;
    private final RequestMethod requestMethod;

    public static DemandBasedPricingRequest forFetch(Listing listing) {
        return new DemandBasedPricingRequest(listing.getId(), null, RequestMethod.GET);
    }

    public static DemandBasedPricingRequest forFetch(long listingId2) {
        return new DemandBasedPricingRequest(listingId2, null, RequestMethod.GET);
    }

    public static DemandBasedPricingRequest setEnableSmartPricing(DynamicPricingControl control) {
        return new DemandBasedPricingRequest(control.getListingId(), createRequestBodyEnabled(control), RequestMethod.PUT);
    }

    public static DemandBasedPricingRequest updateMinPrice(int minPrice, long listingId2) {
        return new DemandBasedPricingRequest(listingId2, createRequestBodyUpdateField("min_price", minPrice, listingId2), RequestMethod.PUT);
    }

    public static DemandBasedPricingRequest updateMaxPrice(int maxPrice, long listingId2) {
        return new DemandBasedPricingRequest(listingId2, createRequestBodyUpdateField("max_price", maxPrice, listingId2), RequestMethod.PUT);
    }

    public static DemandBasedPricingRequest updateDesiredHostingFrequency(DesiredHostingFrequency frequency, long listingId2) {
        return new DemandBasedPricingRequest(listingId2, createRequestBodyUpdateField(FREQUENCY_FIELD, frequency.getServerKey(), listingId2), RequestMethod.PUT);
    }

    private DemandBasedPricingRequest(long listingId2, String postBody2, RequestMethod requestMethod2) {
        this.listingId = listingId2;
        this.requestMethod = requestMethod2;
        this.postBody = postBody2;
    }

    public String getPath() {
        return "dynamic_pricing_controls/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return this.requestMethod;
    }

    public Object getBody() {
        return this.postBody != null ? this.postBody : super.getBody();
    }

    public static String createRequestBodyEnabled(DynamicPricingControl control) {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put(ENABLED_FIELD, control.isEnabled());
            requestBody.put("listing_id", control.getListingId());
            if (control.isEnabled()) {
                requestBody.put("min_price", control.getMinPrice());
                requestBody.put("max_price", control.getMaxPrice());
                requestBody.put(FREQUENCY_FIELD, control.getDesiredHostingFrequencyKey());
            }
        } catch (JSONException e) {
            if (BuildHelper.isDevelopmentBuild()) {
                throw new RuntimeException(e);
            }
            C0715L.m1200w(DemandBasedPricingRequest.class.getSimpleName(), (Throwable) e);
        }
        return requestBody.toString();
    }

    public static String createRequestBodyUpdateField(String key, int value, long listingId2) {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("listing_id", listingId2);
            requestBody.put(key, value);
        } catch (JSONException e) {
            if (BuildHelper.isDevelopmentBuild()) {
                throw new RuntimeException(e);
            }
            C0715L.m1200w(DemandBasedPricingRequest.class.getSimpleName(), (Throwable) e);
        }
        return requestBody.toString();
    }

    public Type successResponseType() {
        return DemandBasedPricingResponse.class;
    }
}
