package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.NewHostPromoResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class NewHostPromoRequest extends BaseRequestV2<NewHostPromoResponse> {
    private Boolean isEnabled;
    private final long listingId;
    private final RequestMethod requestMethod;

    private NewHostPromoRequest(long listingId2, RequestMethod requestMethod2) {
        this.listingId = listingId2;
        this.requestMethod = requestMethod2;
    }

    public NewHostPromoRequest(long listingId2, Boolean isEnabled2, RequestMethod requestMethod2) {
        this.listingId = listingId2;
        this.isEnabled = isEnabled2;
        this.requestMethod = requestMethod2;
    }

    public static NewHostPromoRequest forLYSFetch(long listingId2) {
        return new NewHostPromoRequest(listingId2, RequestMethod.GET);
    }

    public static NewHostPromoRequest forLYSUpdate(long listingId2, Boolean newValue, boolean existed) {
        return new NewHostPromoRequest(listingId2, newValue, existed ? RequestMethod.PUT : RequestMethod.POST);
    }

    public Object getBody() {
        Strap strap = new Strap();
        if (this.requestMethod != RequestMethod.GET) {
            strap.mo11638kv("listing_id", this.listingId);
            strap.mo11640kv("has_opted_in_new_hosting_promotion", this.isEnabled.booleanValue());
        }
        return strap;
    }

    public Type successResponseType() {
        return NewHostPromoResponse.class;
    }

    public String getPath() {
        return "new_hosting_promotions/" + (this.requestMethod == RequestMethod.POST ? "" : Long.valueOf(this.listingId));
    }

    public RequestMethod getMethod() {
        return this.requestMethod;
    }
}
