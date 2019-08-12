package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.UserCommunityCommitmentResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class UserCommunityCommitmentRequest extends BaseRequestV2<UserCommunityCommitmentResponse> {
    private final boolean isAccepted;
    private final long userId;

    public UserCommunityCommitmentRequest(long userId2, boolean isAccepted2) {
        this.userId = userId2;
        this.isAccepted = isAccepted2;
    }

    public String getPath() {
        return "user_community_commitments/" + this.userId;
    }

    public Object getBody() {
        return Strap.make().mo11640kv("agreed_to_community_commitment", this.isAccepted);
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return UserCommunityCommitmentResponse.class;
    }
}
