package com.airbnb.android.core;

import android.app.Application;
import com.airbnb.android.core.apprater.GraphBindings;

public class CoreApplicationFacade implements ApplicationFacade {
    private final ApplicationFacade application;

    public CoreApplicationFacade(ApplicationFacade application2) {
        this.application = application2;
    }

    public Application application() {
        return this.application.application();
    }

    public boolean isTestApplication() {
        return this.application.isTestApplication();
    }

    public <T extends BaseGraph> T component() {
        return this.application.component();
    }

    public <T extends GraphBindings> T componentProvider() {
        return this.application.componentProvider();
    }
}
