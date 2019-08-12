package com.airbnb.android.core.requests.cityregistration;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.ListingRegistrationOpenSubmission;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.responses.ListingRegistrationOpenSubmissionResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import retrofit2.Query;

public class UpdateListingRegistrationOpenSubmissionRequest extends BaseRequestV2<ListingRegistrationOpenSubmissionResponse> {
    private static final String FORMAT_WITH_ANSWERS = "with_answers";
    private static final String PARAM_FORMAT = "_format";
    private final String format;

    /* renamed from: id */
    private final long f8497id;
    private final ListingRegistrationOpenSubmission requestBody = new ListingRegistrationOpenSubmission();

    public UpdateListingRegistrationOpenSubmissionRequest(ListingRegistrationProcess listingRegistrationProcess, HashMap answers) {
        this.requestBody.setId(listingRegistrationProcess.getOpenSubmission().getId());
        this.requestBody.setListingId(listingRegistrationProcess.getListingId());
        this.requestBody.setRegulatoryBody(listingRegistrationProcess.getRegulatoryBody());
        this.requestBody.setAnswers(answers);
        this.f8497id = listingRegistrationProcess.getOpenSubmission().getId();
        this.format = FORMAT_WITH_ANSWERS;
    }

    public Type successResponseType() {
        return ListingRegistrationOpenSubmissionResponse.class;
    }

    public String getPath() {
        return "listing_registration_open_submissions/" + this.f8497id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("_format", this.format);
    }
}
