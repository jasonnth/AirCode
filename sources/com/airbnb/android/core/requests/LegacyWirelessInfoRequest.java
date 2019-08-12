package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.responses.WirelessInfoResponse;
import com.airbnb.android.core.utils.BuildHelper;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;

public class LegacyWirelessInfoRequest extends BaseRequestV2<WirelessInfoResponse> {
    private static final String PATH = "listing_wireless_infos";
    private ListingWirelessInfo info;
    private final RequestMethod method;
    private final String path;

    public static LegacyWirelessInfoRequest forUpdate(ListingWirelessInfo info2, BaseRequestListener<WirelessInfoResponse> listener) {
        LegacyWirelessInfoRequest request = (LegacyWirelessInfoRequest) new LegacyWirelessInfoRequest(info2.getId(), RequestMethod.PUT, listener).skipCache();
        request.info = info2;
        return request;
    }

    public static LegacyWirelessInfoRequest forCreate(ListingWirelessInfo info2, BaseRequestListener<WirelessInfoResponse> listener) {
        LegacyWirelessInfoRequest request = (LegacyWirelessInfoRequest) new LegacyWirelessInfoRequest(RequestMethod.POST, listener).skipCache();
        request.info = info2;
        return request;
    }

    public static LegacyWirelessInfoRequest forDelete(ListingWirelessInfo info2, BaseRequestListener<WirelessInfoResponse> listener) {
        LegacyWirelessInfoRequest request = (LegacyWirelessInfoRequest) new LegacyWirelessInfoRequest(info2.getId(), RequestMethod.DELETE, listener).skipCache();
        request.info = info2;
        return request;
    }

    protected LegacyWirelessInfoRequest(long id, RequestMethod method2, BaseRequestListener<WirelessInfoResponse> listener) {
        this("listing_wireless_infos/" + id, method2, listener);
    }

    private LegacyWirelessInfoRequest(RequestMethod method2, BaseRequestListener<WirelessInfoResponse> listener) {
        this(PATH, method2, listener);
    }

    private LegacyWirelessInfoRequest(String path2, RequestMethod method2, BaseRequestListener<WirelessInfoResponse> listener) {
        withListener((Observer) listener);
        this.path = path2;
        this.method = method2;
    }

    public String getPath() {
        return this.path;
    }

    public String getBody() {
        if (this.method == RequestMethod.DELETE) {
            return null;
        }
        JSONObject json = new JSONObject();
        try {
            if (!this.info.hasValidId()) {
                json.put("listing_id", this.info.getListingId());
            }
            json.put(WirelessInfoRequest.JSON_SSID_KEY, this.info.getWirelessSsid());
            json.put(WirelessInfoRequest.JSON_PASSWORD_KEY, this.info.getWirelessPassword());
            json.put(WirelessInfoRequest.JSON_TYPE_KEY, this.info.getWirelessType());
        } catch (JSONException e) {
            if (BuildHelper.isDevelopmentBuild()) {
                throw new IllegalStateException("Error building wireless info json");
            }
        }
        return json.toString();
    }

    public RequestMethod getMethod() {
        return this.method;
    }

    public Type successResponseType() {
        return WirelessInfoResponse.class;
    }
}
