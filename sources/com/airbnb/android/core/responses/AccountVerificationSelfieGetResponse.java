package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.List;

public class AccountVerificationSelfieGetResponse extends BaseResponse {
    @JsonProperty("user_selfies")
    protected List<UserSelfie> userSelfies;

    static class UserSelfie {
        @JsonProperty("image_url")
        String url;

        UserSelfie() {
        }
    }

    public String getImageUrl() {
        if (ListUtils.isEmpty((Collection<?>) this.userSelfies)) {
            return null;
        }
        return ((UserSelfie) this.userSelfies.get(this.userSelfies.size() - 1)).url;
    }
}
