package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.interfaces.GuestIdentity;
import java.lang.reflect.Type;

public class DeleteGuestIdentityInformationRequest extends BaseRequestV2<BaseResponse> {
    private final GuestIdentity identity;

    public DeleteGuestIdentityInformationRequest(GuestIdentity identity2) {
        this.identity = identity2;
    }

    public String getPath() {
        String path;
        switch (this.identity.getType()) {
            case ChineseNationalID:
                path = "china_resident_identity_cards";
                break;
            case Passport:
                path = "passports";
                break;
            default:
                throw new IllegalStateException("unknown identity type: " + this.identity.getType().name());
        }
        return path + "/" + this.identity.getId();
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }
}
