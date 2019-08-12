package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.ListingRegistrationExemptionFields.Field;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.ListingRegistrationResponse;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import java.lang.reflect.Type;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Query;

public class CreateListingRegistrationRequest extends BaseRequestV2<ListingRegistrationResponse> {
    private static final String FORMAT_CREATE_REGISTRATION = "with_answers_and_exemption";
    private static final String PARAM_EXEMPTION_DATA = "exemption_data";
    private static final String PARAM_FORMAT = "_format";
    private static final String PARAM_LISTING_IDS = "listing_ids";
    private static final String PARAM_LISTING_REGISTRATION_SUBMISSIONS = "listing_registration_submissions";
    private static final String PARAM_REGULATORY_BODY = "regulatory_body";
    private static final String PARAM_STATUS = "status";
    private static final String STATUS_EXEMPTED = "exempted";
    private final String format;
    private final JSONObject requestBody;

    private CreateListingRegistrationRequest(JSONObject requestBody2, String format2) {
        this.requestBody = requestBody2;
        this.format = format2;
    }

    private CreateListingRegistrationRequest(JSONObject requestBody2) {
        this(requestBody2, FORMAT_CREATE_REGISTRATION);
    }

    public static CreateListingRegistrationRequest forUnregisteredListing(ListingRegistrationProcess process) {
        JSONArray listingIdsJsonArray = new JSONArray();
        listingIdsJsonArray.put(process.getListingId());
        JSONArray submissionsJsonArray = new JSONArray();
        JSONObject submissionObject = new JSONObject();
        JSONObject answersObject = new JSONObject();
        try {
            for (ListingRegistrationProcessInputGroup inputGroup : process.getInputGroups()) {
                for (ListingRegistrationQuestion question : inputGroup.getQuestions()) {
                    switch (question.getInputType()) {
                        case Address:
                            AirAddress address = CityRegistrationUtils.getAirAddressFromString(question.getInputAnswer());
                            if (address == null) {
                                break;
                            } else {
                                answersObject.put(question.getInputKey(), getAirAddressValue(address));
                                break;
                            }
                        default:
                            answersObject.put(question.getInputKey(), question.getInputAnswer());
                            break;
                    }
                }
            }
            submissionObject.put("answers", answersObject);
            submissionObject.put(PARAM_REGULATORY_BODY, process.getRegulatoryBody());
            submissionsJsonArray.put(submissionObject);
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(PARAM_LISTING_IDS, listingIdsJsonArray).put(PARAM_REGULATORY_BODY, process.getRegulatoryBody()).put(PARAM_LISTING_REGISTRATION_SUBMISSIONS, submissionsJsonArray);
        } catch (JSONException e2) {
            BugsnagWrapper.notify((Throwable) e2);
        }
        return new CreateListingRegistrationRequest(jsonObject, FORMAT_CREATE_REGISTRATION);
    }

    private static JSONObject getAirAddressValue(AirAddress address) throws JSONException {
        JSONObject addressObject = new JSONObject();
        addressObject.put("country", address.country());
        addressObject.put("country_code", address.countryCode());
        addressObject.put("street", address.streetAddressOne());
        addressObject.put(ListingRequestConstants.JSON_APT_KEY, address.streetAddressTwo());
        addressObject.put("city", address.city());
        addressObject.put("state", address.state());
        addressObject.put(ListingRequestConstants.JSON_ZIP_KEY, address.postalCode());
        return addressObject;
    }

    public static CreateListingRegistrationRequest forExistingPermitNumber(ListingRegistrationProcess process, String permitNumber) {
        return forExistingPermitNumber(process, permitNumber, null, null);
    }

    public static CreateListingRegistrationRequest forExistingPermitNumber(ListingRegistrationProcess process, String permitNumber, String expiryDate, String zipCode) {
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
        return new CreateListingRegistrationRequest(jsonObject);
    }

    public Type successResponseType() {
        return ListingRegistrationResponse.class;
    }

    public String getPath() {
        return "listing_registrations";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getBody() {
        return this.requestBody.toString();
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("_format", this.format);
    }
}
