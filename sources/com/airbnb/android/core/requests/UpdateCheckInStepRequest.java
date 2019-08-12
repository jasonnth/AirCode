package com.airbnb.android.core.requests;

import com.airbnb.airrequest.MultipartRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CheckInStepResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Part;

public class UpdateCheckInStepRequest extends MultipartRequestV2<CheckInStepResponse> {
    private static final String KEY_JSON_ROOT_BODY = "json_root_body";
    private final List<Part> parts = new ArrayList();
    private final long stepId;

    private static final class NoteBody {
        @JsonProperty("note")
        String note;

        NoteBody(String note2) {
            this.note = note2;
        }
    }

    public static UpdateCheckInStepRequest forNote(long stepId2, String note) {
        return new UpdateCheckInStepRequest(stepId2, note);
    }

    private UpdateCheckInStepRequest(long stepId2, String note) {
        this.stepId = stepId2;
        if (note != null) {
            this.parts.add(new Part(KEY_JSON_ROOT_BODY, new NoteBody(note)));
        }
    }

    public String getPath() {
        return "check_in_guide_steps/" + this.stepId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public List<Part> getParts() {
        return this.parts;
    }

    public Type successResponseType() {
        return CheckInStepResponse.class;
    }
}
