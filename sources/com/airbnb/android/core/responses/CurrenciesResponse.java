package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CurrenciesResponse extends BaseResponse {
    @JsonProperty("currencies")
    public List<Currency> currencies;
    public boolean requiresCurrencyChange;
}
