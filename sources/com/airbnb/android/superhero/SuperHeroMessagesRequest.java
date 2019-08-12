package com.airbnb.android.superhero;

import com.airbnb.airrequest.BaseRequestV2;
import java.lang.reflect.Type;

public class SuperHeroMessagesRequest extends BaseRequestV2<SuperHeroMessagesResponse> {
    public Type successResponseType() {
        return SuperHeroMessagesResponse.class;
    }

    public String getPath() {
        return "hero_messages";
    }
}
