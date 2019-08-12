package com.airbnb.android.core;

import android.app.Application;
import android.content.Context;
import com.airbnb.android.core.apprater.GraphBindings;

public class CoreApplication implements ApplicationFacade {
    private static CoreApplicationFacade singleton;

    private CoreApplication() {
    }

    public static Application appContext() {
        return singleton.application();
    }

    public static CoreApplicationFacade instance() {
        return singleton;
    }

    public static CoreApplicationFacade instance(Context context) {
        return singleton;
    }

    public Application application() {
        return singleton.application();
    }

    public boolean isTestApplication() {
        return singleton.isTestApplication();
    }

    public <T extends BaseGraph> T component() {
        return singleton.component();
    }

    public <T extends GraphBindings> T componentProvider() {
        return singleton.componentProvider();
    }

    public static CoreApplicationFacade init(ApplicationFacade facade) {
        singleton = new CoreApplicationFacade(facade);
        return singleton;
    }
}
