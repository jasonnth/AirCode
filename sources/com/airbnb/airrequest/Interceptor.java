package com.airbnb.airrequest;

import java.lang.reflect.Type;

public interface Interceptor {

    public interface Factory {
        Interceptor interceptorFor(Type type);
    }

    AirRequest intercept(AirRequest airRequest);
}
