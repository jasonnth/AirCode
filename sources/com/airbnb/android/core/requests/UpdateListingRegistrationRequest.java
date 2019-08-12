package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.ListingRegistrationExemptionFields.Field;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.responses.ListingRegistrationResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Query;

public class UpdateListingRegistrationRequest extends BaseRequestV2<ListingRegistrationResponse> {
    private static final String FORMAT_UPDATE_REGISTRATION = "with_answers_and_exemption";
    private static final String PARAM_EXEMPTION_DATA = "exemption_data";
    private static final String PARAM_FORMAT = "_format";
    private static final String PARAM_LISTING_IDS = "listing_ids";
    private static final String PARAM_REGULATORY_BODY = "regulatory_body";
    private static final String PARAM_STATUS = "status";
    private static final String STATUS_EXEMPTED = "exempted";
    private final String format;

    /* renamed from: id */
    private final long f8495id;
    private final JSONObject requestBody;

    private UpdateListingRegistrationRequest(JSONObject requestBody2, String format2, long id) {
        this.requestBody = requestBody2;
        this.format = format2;
        this.f8495id = id;
    }

    private UpdateListingRegistrationRequest(JSONObject requestBody2, long id) {
        this(requestBody2, FORMAT_UPDATE_REGISTRATION, id);
    }

    public static UpdateListingRegistrationRequest forExemption(ListingRegistrationProcess process, String permitNumber, String expiryDate, String zipCode) {
        JSONArray listingIdsJsonArray = new JSONArray();
        listingIdsJsonArray.put(process.getListingId());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(PARAM_LISTING_IDS, listingIdsJsonArray).put(PARAM_REGULATORY_BODY, process.getRegulatoryBody()).put("status", STATUS_EXEMPTED);
            JSONObject exemptionDataObject = new JSONObject();
            exemptionDataObject.put(Field.expiration_date.name(), expiryDate);
            exemptionDataObject.put(Field.zipcode.name(), zipCode);
            exemptionDataObject.put(Field.permit_number.name(), permitNumber);
            jsonObject.put(PARAM_EXEMPTION_DATA, exemptionDataObject);
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
        }
        return new UpdateListingRegistrationRequest(jsonObject, process.getListingRegistration().getId());
    }

    public Type successResponseType() {
        return ListingRegistrationResponse.class;
    }

    public String getPath() {
        return "listing_registrations/" + this.f8495id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public String getBody() {
        return this.requestBody.toString();
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("_format", this.format);
    }
}
