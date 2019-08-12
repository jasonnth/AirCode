package com.airbnb.android.superhero;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuperHeroMessageResponse extends BaseResponse {
    @JsonProperty("hero_message")
    private SuperHeroMessage heroMessage;

    public SuperHeroMessage getHeroMessage() {
        return this.heroMessage;
    }
}
