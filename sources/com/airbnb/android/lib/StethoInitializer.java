package com.airbnb.android.lib;

import android.app.Application;
import p032rx.functions.Action1;

public interface StethoInitializer extends Action1<Application> {
    public static final StethoInitializer NO_OP = StethoInitializer$$Lambda$1.lambdaFactory$();
}
