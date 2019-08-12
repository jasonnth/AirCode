package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.CohostInvitationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class CohostInvitationRequest extends BaseRequestV2<CohostInvitationResponse> {
    private String code = "";

    /* renamed from: id */
    private long f8484id = -1;

    private CohostInvitationRequest(String code2) {
        this.code = code2;
    }

    private CohostInvitationRequest(long id) {
        this.f8484id = id;
    }

    public static CohostInvitationRequest forInvitationCode(String code2) {
        return new CohostInvitationRequest(code2);
    }

    public static CohostInvitationRequest forInvitationId(long id) {
        return new CohostInvitationRequest(id);
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_show_invitation");
    }

    public String getPath() {
        if (!this.code.isEmpty()) {
            return "cohost_invitations/" + this.code;
        }
        return "cohost_invitations/" + this.f8484id;
    }

    public Type successResponseType() {
        return CohostInvitationResponse.class;
    }
}
