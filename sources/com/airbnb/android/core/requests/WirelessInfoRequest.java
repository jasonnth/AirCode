package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.WirelessInfoResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class WirelessInfoRequest extends BaseRequestV2<WirelessInfoResponse> {
    public static final String JSON_LISTING_ID_KEY = "listing_id";
    public static final String JSON_PASSWORD_KEY = "wireless_password";
    public static final String JSON_SSID_KEY = "wireless_ssid";
    public static final String JSON_TYPE_KEY = "wireless_type";
    private static final String PATH = "listing_wireless_infos";
    private final RequestMethod method;
    private final Object requestBody;
    private final Long wirelessInfoId;

    public static WirelessInfoRequest updateExisting(long wirelessInfoId2, Strap fieldsToUpdate) {
        return new WirelessInfoRequest(Long.valueOf(wirelessInfoId2), fieldsToUpdate, RequestMethod.PUT);
    }

    public static WirelessInfoRequest create(long listingId, Strap fieldsToUpdate) {
        fieldsToUpdate.mo11639kv("listing_id", String.valueOf(listingId));
        return new WirelessInfoRequest(null, fieldsToUpdate, RequestMethod.POST);
    }

    public static WirelessInfoRequest delete(long wirelessInfoId2) {
        return new WirelessInfoRequest(Long.valueOf(wirelessInfoId2), null, RequestMethod.DELETE);
    }

    private WirelessInfoRequest(Long wirelessInfoId2, Object requestBody2, RequestMethod method2) {
        this.wirelessInfoId = wirelessInfoId2;
        this.requestBody = requestBody2;
        this.method = method2;
    }

    public RequestMethod getMethod() {
        return this.method;
    }

    public Type successResponseType() {
        return WirelessInfoResponse.class;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public String getPath() {
        return PATH + (this.wirelessInfoId == null ? "" : "/" + this.wirelessInfoId);
    }
}
