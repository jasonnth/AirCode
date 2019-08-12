package com.airbnb.android.superhero;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SuperHeroMessagesResponse extends BaseResponse {
    @JsonProperty("hero_messages")
    List<SuperHeroMessage> heroMessages;

    public List<SuperHeroMessage> heroMessages() {
        return this.heroMessages;
    }
}
