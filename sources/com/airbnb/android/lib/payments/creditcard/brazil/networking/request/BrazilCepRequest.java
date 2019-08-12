package com.airbnb.android.lib.payments.creditcard.brazil.networking.request;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCepResponse;
import java.lang.reflect.Type;

public class BrazilCepRequest extends BaseRequestV2<BrazilCepResponse> {
    private final String brazilCep;

    public BrazilCepRequest(String brazilCep2) {
        this.brazilCep = brazilCep2;
    }

    public Type successResponseType() {
        return BrazilCepResponse.class;
    }

    public String getPath() {
        return "brazil_ceps/" + this.brazilCep;
    }
}
