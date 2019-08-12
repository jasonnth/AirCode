package com.bugsnag.android;

import android.content.Context;

public final class Bugsnag {
    static Client client;

    private Bugsnag() {
    }

    public static Client init(Context androidContext) {
        client = new Client(androidContext);
        NativeInterface.configureClientObservers(client);
        return client;
    }

    public static void setContext(String context) {
        getClient().setContext(context);
    }

    public static void setFilters(String... filters) {
        getClient().setFilters(filters);
    }

    public static void setIgnoreClasses(String... ignoreClasses) {
        getClient().setIgnoreClasses(ignoreClasses);
    }

    public static void setProjectPackages(String... projectPackages) {
        getClient().setProjectPackages(projectPackages);
    }

    public static void setReleaseStage(String releaseStage) {
        getClient().setReleaseStage(releaseStage);
    }

    public static void beforeNotify(BeforeNotify beforeNotify) {
        getClient().beforeNotify(beforeNotify);
    }

    public static void notify(Throwable exception) {
        getClient().notify(exception);
    }

    public static void notify(Throwable exception, Callback callback) {
        getClient().notify(exception, callback);
    }

    public static void notify(Throwable exception, Severity severity) {
        getClient().notify(exception, severity);
    }

    public static void notify(Throwable exception, final MetaData metaData) {
        getClient().notify(exception, (Callback) new Callback() {
            public void beforeNotify(Report report) {
                report.getError().setMetaData(metaData);
            }
        });
    }

    @Deprecated
    public static void notify(String name, String message, StackTraceElement[] stacktrace, Severity severity, MetaData metaData) {
        final Severity finalSeverity = severity;
        final MetaData finalMetaData = metaData;
        getClient().notify(name, message, stacktrace, new Callback() {
            public void beforeNotify(Report report) {
                report.getError().setSeverity(finalSeverity);
                report.getError().setMetaData(finalMetaData);
            }
        });
    }

    @Deprecated
    public static void notify(String name, String message, String context, StackTraceElement[] stacktrace, Severity severity, MetaData metaData) {
        final String finalContext = context;
        final Severity finalSeverity = severity;
        final MetaData finalMetaData = metaData;
        getClient().notify(name, message, stacktrace, new Callback() {
            public void beforeNotify(Report report) {
                report.getError().setSeverity(finalSeverity);
                report.getError().setMetaData(finalMetaData);
                report.getError().setContext(finalContext);
            }
        });
    }

    public static void addToTab(String tab, String key, Object value) {
        getClient().addToTab(tab, key, value);
    }

    public static void leaveBreadcrumb(String message) {
        getClient().leaveBreadcrumb(message);
    }

    public static Client getClient() {
        if (client != null) {
            return client;
        }
        throw new IllegalStateException("You must call Bugsnag.init before any other Bugsnag methods");
    }
}
