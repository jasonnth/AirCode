package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountResponse extends BaseResponse {
    @JsonProperty("account")
    public Account account;
}
