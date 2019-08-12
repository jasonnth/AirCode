package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaQuestion;
import com.airbnb.android.core.responses.ListingPersonaResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Query;

public class ListingPersonaRequest extends BaseRequestV2<ListingPersonaResponse> {
    private static final String ANSWER_ID_FIELD = "answer_id";
    private static final String LISTING_ID_FIELD = "listing_id";
    private static final String PATH = "listing_persona_responses";
    private static final String QUESTION_ID_FIELD = "question_id";
    private final long listingId;
    private final String postBody;
    private final ListingPersonaQuestion questionType;
    private final RequestMethod requestMethod;

    public static ListingPersonaRequest updateRentHistory(long listingId2, ListingPersonaAnswer answer, boolean isFirstRequest) {
        return updatePersonaInput(listingId2, ListingPersonaQuestion.EXPERIENCE_QUESTION, answer, isFirstRequest);
    }

    public static ListingPersonaRequest updateOccupany(long listingId2, ListingPersonaAnswer answer, boolean isFirstRequest) {
        return updatePersonaInput(listingId2, ListingPersonaQuestion.OCCUPANCY_QUESTION, answer, isFirstRequest);
    }

    public static ListingPersonaRequest updatePersonaInput(long listingId2, ListingPersonaQuestion question, ListingPersonaAnswer answer, boolean isFirstRequest) {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put(QUESTION_ID_FIELD, question.getServerKey());
            requestBody.put(ANSWER_ID_FIELD, answer.getServerKey());
            requestBody.put("listing_id", listingId2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ListingPersonaRequest(listingId2, isFirstRequest ? RequestMethod.POST : RequestMethod.PUT, requestBody.toString(), question);
    }

    private ListingPersonaRequest(long listingId2, RequestMethod requestMethod2, String postBody2, ListingPersonaQuestion questionType2) {
        this.listingId = listingId2;
        this.requestMethod = requestMethod2;
        this.postBody = postBody2;
        this.questionType = questionType2;
    }

    public String getPath() {
        if (this.requestMethod == RequestMethod.PUT) {
            return "listing_persona_responses/" + this.listingId + "/" + this.questionType.getServerKey();
        }
        return PATH;
    }

    public RequestMethod getMethod() {
        return this.requestMethod;
    }

    public Object getBody() {
        return this.postBody != null ? this.postBody : super.getBody();
    }

    public Collection<Query> getQueryParams() {
        if (this.requestMethod == RequestMethod.GET) {
            return QueryStrap.make().mo7894kv("listing_id", this.listingId);
        }
        return super.getQueryParams();
    }

    public Type successResponseType() {
        return ListingPersonaResponse.class;
    }
}
