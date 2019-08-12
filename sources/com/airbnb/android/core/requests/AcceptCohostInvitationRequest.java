package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.responses.AcceptCohostInvitationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import retrofit2.Query;

public class AcceptCohostInvitationRequest extends BaseRequestV2<AcceptCohostInvitationResponse> {
    private static final String CODE = "code";
    private String code = null;

    /* renamed from: id */
    private long f8483id = -1;

    private AcceptCohostInvitationRequest(String code2) {
        this.code = code2;
    }

    private AcceptCohostInvitationRequest(long invitationId) {
        this.f8483id = invitationId;
    }

    public static AcceptCohostInvitationRequest forInvitation(CohostInvitation invitation) {
        if (invitation.getInviteeIdentifierType().equals("code")) {
            return new AcceptCohostInvitationRequest(invitation.getInviteeIdentifier());
        }
        return new AcceptCohostInvitationRequest(invitation.getId());
    }

    public String getPath() {
        if (this.code != null) {
            return "cohost_invitations/" + this.code;
        }
        return "cohost_invitations/" + this.f8483id;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_accepted_invitation");
    }

    public Object getBody() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("action", Integer.valueOf(2));
        return payload;
    }

    public Type successResponseType() {
        return AcceptCohostInvitationResponse.class;
    }
}
