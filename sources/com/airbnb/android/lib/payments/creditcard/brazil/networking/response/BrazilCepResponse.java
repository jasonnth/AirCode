package com.airbnb.android.lib.payments.creditcard.brazil.networking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrazilCepResponse {
    @JsonProperty("brazil_cep")
    public BrazilCep brazilCep;
}
