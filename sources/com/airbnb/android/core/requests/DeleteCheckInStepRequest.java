package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CheckInStepResponse;
import java.lang.reflect.Type;

public class DeleteCheckInStepRequest extends BaseRequestV2<CheckInStepResponse> {
    private final long stepId;

    public DeleteCheckInStepRequest(long stepId2) {
        this.stepId = stepId2;
    }

    public String getPath() {
        return "check_in_guide_steps/" + this.stepId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return CheckInStepResponse.class;
    }
}
