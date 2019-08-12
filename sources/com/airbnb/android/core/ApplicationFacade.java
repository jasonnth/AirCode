package com.airbnb.android.core;

import android.app.Application;
import com.airbnb.android.core.apprater.GraphBindings;

public interface ApplicationFacade {
    Application application();

    @Deprecated
    <T extends BaseGraph> T component();

    <T extends GraphBindings> T componentProvider();

    boolean isTestApplication();
}
