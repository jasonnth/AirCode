package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.DeleteCohostInvitationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class DeleteCohostInvitationRequest extends BaseRequestV2<DeleteCohostInvitationResponse> {
    private final long invitationId;

    public DeleteCohostInvitationRequest(long invitationId2) {
        this.invitationId = invitationId2;
    }

    public String getPath() {
        return "cohost_invitations/" + this.invitationId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_manage_listing");
    }

    public Type successResponseType() {
        return DeleteCohostInvitationResponse.class;
    }
}
