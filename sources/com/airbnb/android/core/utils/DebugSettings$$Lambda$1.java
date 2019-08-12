package com.airbnb.android.core.utils;

import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.events.ErfExperimentsUpdatedEvent;
import com.airbnb.android.core.utils.DebugSettings.SettingsAction;

final /* synthetic */ class DebugSettings$$Lambda$1 implements SettingsAction {
    private static final DebugSettings$$Lambda$1 instance = new DebugSettings$$Lambda$1();

    private DebugSettings$$Lambda$1() {
    }

    public static SettingsAction lambdaFactory$() {
        return instance;
    }

    public void call() {
        CoreApplication.instance().component().debugSettings().bus.post(new ErfExperimentsUpdatedEvent());
    }
}
