package com.airbnb.android.core.net;

import com.airbnb.android.lib.StethoInitializer;

public interface StethoInitializerImpl extends StethoInitializer {
    public static final StethoInitializer INSTANCE = StethoInitializer.NO_OP;
}
