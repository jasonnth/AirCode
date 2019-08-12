package com.airbnb.android.core.deserializers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface WrappedObject {
    String value();
}
