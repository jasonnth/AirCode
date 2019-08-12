package com.airbnb.android.lib;

import android.app.Application;
import com.airbnb.android.core.ApplicationFacade;
import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.apprater.GraphBindings;

public final class AirbnbApplicationFacade implements ApplicationFacade {
    private final AirbnbApplication application;

    public AirbnbApplicationFacade(AirbnbApplication application2) {
        this.application = application2;
    }

    public Application application() {
        return this.application.application;
    }

    public boolean isTestApplication() {
        return this.application.isTestApplication();
    }

    public <T extends BaseGraph> T component() {
        return this.application.component();
    }

    public <T extends GraphBindings> T componentProvider() {
        return this.application.component();
    }
}
