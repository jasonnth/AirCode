package com.airbnb.android.core.models.find;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ChangeType {
    public static final int RESET = 2;
    public static final int UNSPECIFIED = 1;
}
