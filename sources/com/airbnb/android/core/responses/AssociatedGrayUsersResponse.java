package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.GrayUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class AssociatedGrayUsersResponse extends BaseResponse {
    @JsonProperty("associated_gray_users")
    public ArrayList<GrayUser> grayUsers;
}
