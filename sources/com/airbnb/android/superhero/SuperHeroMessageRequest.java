package com.airbnb.android.superhero;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.superhero.SuperHeroMessage.Status;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class SuperHeroMessageRequest extends BaseRequestV2<SuperHeroMessageResponse> {

    /* renamed from: id */
    private final long f2445id;
    private final Strap params;
    private final RequestMethod requestMethod;

    public static SuperHeroMessageRequest forMessage(long id) {
        return new SuperHeroMessageRequest(id);
    }

    public static SuperHeroMessageRequest forStatusUpdate(long id, Status status) {
        return new SuperHeroMessageRequest(id, status.value);
    }

    private SuperHeroMessageRequest(long id) {
        this.f2445id = id;
        this.requestMethod = RequestMethod.GET;
        this.params = null;
    }

    private SuperHeroMessageRequest(long id, int status) {
        this.f2445id = id;
        this.requestMethod = RequestMethod.PUT;
        this.params = Strap.make().mo11637kv("status", status);
    }

    public Type successResponseType() {
        return SuperHeroMessageResponse.class;
    }

    public RequestMethod getMethod() {
        return this.requestMethod;
    }

    public String getPath() {
        return "hero_messages/" + this.f2445id;
    }

    public Object getBody() {
        return this.params;
    }
}
