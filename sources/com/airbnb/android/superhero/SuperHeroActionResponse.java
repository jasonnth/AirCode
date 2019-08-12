package com.airbnb.android.superhero;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuperHeroActionResponse extends BaseResponse {
    @JsonProperty("hero_action")
    private SuperHeroAction heroAction;

    public SuperHeroAction getHeroAction() {
        return this.heroAction;
    }
}
