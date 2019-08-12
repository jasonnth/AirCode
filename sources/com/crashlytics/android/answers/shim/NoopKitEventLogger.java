package com.crashlytics.android.answers.shim;

class NoopKitEventLogger implements KitEventLogger {
    NoopKitEventLogger() {
    }

    public static KitEventLogger create() {
        return new NoopKitEventLogger();
    }

    public void logKitEvent(KitEvent kitEvent) {
    }
}
