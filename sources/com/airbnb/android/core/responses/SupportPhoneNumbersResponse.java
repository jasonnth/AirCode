package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SupportPhoneNumber;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class SupportPhoneNumbersResponse extends BaseResponse {
    @JsonProperty("support_phone_numbers")
    public ArrayList<SupportPhoneNumber> numbers;
}
