package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CheckInStepResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class CreateCheckInStepRequest extends BaseRequestV2<CheckInStepResponse> {
    private final CheckInStepBody body;

    private static final class CheckInStepBody {
        @JsonProperty("check_in_guide_id")
        final long guideId;
        @JsonProperty("note")
        final String note;

        CheckInStepBody(long guideId2, String note2) {
            this.guideId = guideId2;
            if (note2 == null) {
                note2 = "";
            }
            this.note = note2;
        }
    }

    public static CreateCheckInStepRequest forNote(long guideId, String note) {
        return new CreateCheckInStepRequest(guideId, note);
    }

    public static CreateCheckInStepRequest forGuideId(long guideId) {
        return new CreateCheckInStepRequest(guideId, null);
    }

    private CreateCheckInStepRequest(long guideId, String note) {
        this.body = new CheckInStepBody(guideId, note);
    }

    public String getPath() {
        return "check_in_guide_steps/";
    }

    public Object getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return CheckInStepResponse.class;
    }
}
