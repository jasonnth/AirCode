package com.bugsnag.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class Client extends Observable implements Observer {
    private final Context appContext;
    protected final AppData appData;
    final Breadcrumbs breadcrumbs;
    protected final Configuration config;
    protected final DeviceData deviceData;
    protected final ErrorStore errorStore;
    protected final User user;

    public Client(Context androidContext) {
        this(androidContext, null, true);
    }

    public Client(Context androidContext, String apiKey, boolean enableExceptionHandler) {
        this(androidContext, createNewConfiguration(androidContext, apiKey, enableExceptionHandler));
    }

    public Client(Context androidContext, Configuration configuration) {
        this.user = new User();
        this.appContext = androidContext.getApplicationContext();
        this.config = configuration;
        String buildUUID = null;
        try {
            buildUUID = this.appContext.getPackageManager().getApplicationInfo(this.appContext.getPackageName(), 128).metaData.getString("com.bugsnag.android.BUILD_UUID");
        } catch (Exception e) {
        }
        if (buildUUID != null) {
            this.config.setBuildUUID(buildUUID);
        }
        this.appData = new AppData(this.appContext, this.config);
        this.deviceData = new DeviceData(this.appContext);
        AppState.init();
        this.breadcrumbs = new Breadcrumbs();
        setProjectPackages(this.appContext.getPackageName());
        if (this.config.getPersistUserBetweenSessions()) {
            SharedPreferences sharedPref = this.appContext.getSharedPreferences("com.bugsnag.android", 0);
            this.user.setId(sharedPref.getString("user.id", this.deviceData.getUserId()));
            this.user.setName(sharedPref.getString("user.name", null));
            this.user.setEmail(sharedPref.getString("user.email", null));
        } else {
            this.user.setId(this.deviceData.getUserId());
        }
        this.errorStore = new ErrorStore(this.config, this.appContext);
        if (this.config.getEnableExceptionHandler()) {
            enableExceptionHandler();
        }
        this.config.addObserver(this);
        this.errorStore.flush();
    }

    public void notifyBugsnagObservers(NotifyType type) {
        setChanged();
        super.notifyObservers(type.getValue());
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            NotifyType type = NotifyType.fromInt((Integer) arg);
            if (type != null) {
                notifyBugsnagObservers(type);
            }
        }
    }

    private static Configuration createNewConfiguration(Context androidContext, String apiKey, boolean enableExceptionHandler) {
        Context appContext2 = androidContext.getApplicationContext();
        if (TextUtils.isEmpty(apiKey)) {
            try {
                apiKey = appContext2.getPackageManager().getApplicationInfo(appContext2.getPackageName(), 128).metaData.getString("com.bugsnag.android.API_KEY");
            } catch (Exception e) {
            }
        }
        if (apiKey == null) {
            throw new NullPointerException("You must provide a Bugsnag API key");
        }
        Configuration newConfig = new Configuration(apiKey);
        newConfig.setEnableExceptionHandler(enableExceptionHandler);
        return newConfig;
    }

    public void setContext(String context) {
        this.config.setContext(context);
    }

    public void setFilters(String... filters) {
        this.config.setFilters(filters);
    }

    public void setIgnoreClasses(String... ignoreClasses) {
        this.config.setIgnoreClasses(ignoreClasses);
    }

    public void setProjectPackages(String... projectPackages) {
        this.config.setProjectPackages(projectPackages);
    }

    public void setReleaseStage(String releaseStage) {
        this.config.setReleaseStage(releaseStage);
    }

    public void beforeNotify(BeforeNotify beforeNotify) {
        this.config.beforeNotify(beforeNotify);
    }

    public void notify(Throwable exception) {
        notify(new Error(this.config, exception), false);
    }

    public void notify(Throwable exception, Callback callback) {
        notify(new Error(this.config, exception), DeliveryStyle.ASYNC, callback);
    }

    public void notify(String name, String message, StackTraceElement[] stacktrace, Callback callback) {
        notify(new Error(this.config, name, message, stacktrace), DeliveryStyle.ASYNC, callback);
    }

    public void notify(Throwable exception, Severity severity) {
        Error error = new Error(this.config, exception);
        error.setSeverity(severity);
        notify(error, false);
    }

    public void addToTab(String tab, String key, Object value) {
        this.config.getMetaData().addToTab(tab, key, value);
    }

    public void leaveBreadcrumb(String breadcrumb) {
        this.breadcrumbs.add(breadcrumb);
        notifyBugsnagObservers(NotifyType.BREADCRUMB);
    }

    public void setMaxBreadcrumbs(int numBreadcrumbs) {
        this.breadcrumbs.setSize(numBreadcrumbs);
    }

    public void enableExceptionHandler() {
        ExceptionHandler.enable(this);
    }

    private void notify(Error error, boolean blocking) {
        notify(error, blocking ? DeliveryStyle.SAME_THREAD : DeliveryStyle.ASYNC, null);
    }

    private void notify(Error error, DeliveryStyle style, Callback callback) {
        if (!error.shouldIgnoreClass() && this.config.shouldNotifyForReleaseStage(this.appData.getReleaseStage())) {
            error.setAppData(this.appData);
            error.setDeviceData(this.deviceData);
            error.setAppState(new AppState(this.appContext));
            error.setDeviceState(new DeviceState(this.appContext));
            error.setBreadcrumbs(this.breadcrumbs);
            error.setUser(this.user);
            if (!runBeforeNotifyTasks(error)) {
                Logger.info("Skipping notification - beforeNotify task returned false");
                return;
            }
            Report report = new Report(this.config.getApiKey(), error);
            if (callback != null) {
                callback.beforeNotify(report);
            }
            switch (style) {
                case SAME_THREAD:
                    deliver(report, error);
                    break;
                case ASYNC:
                    final Report finalReport = report;
                    final Error finalError = error;
                    Async.run(new Runnable() {
                        public void run() {
                            Client.this.deliver(finalReport, finalError);
                        }
                    });
                    break;
                case ASYNC_WITH_CACHE:
                    this.errorStore.write(error);
                    this.errorStore.flush();
                    break;
            }
            this.breadcrumbs.add(error.getExceptionName(), BreadcrumbType.ERROR, Collections.singletonMap("message", error.getExceptionMessage()));
        }
    }

    /* access modifiers changed from: 0000 */
    public void deliver(Report report, Error error) {
        try {
            HttpClient.post(this.config.getEndpoint(), report);
            Logger.info(String.format(Locale.US, "Sent 1 new error to Bugsnag", new Object[0]));
        } catch (NetworkException e) {
            Logger.info("Could not send error(s) to Bugsnag, saving to disk to send later");
            this.errorStore.write(error);
        } catch (BadResponseException e2) {
            Logger.info("Bad response when sending data to Bugsnag");
        } catch (Exception e3) {
            Logger.warn("Problem sending error to Bugsnag", e3);
        }
    }

    /* access modifiers changed from: 0000 */
    public void cacheAndNotify(Throwable exception, Severity severity) {
        Error error = new Error(this.config, exception);
        error.setSeverity(severity);
        notify(error, DeliveryStyle.ASYNC_WITH_CACHE, null);
    }

    private boolean runBeforeNotifyTasks(Error error) {
        for (BeforeNotify beforeNotify : this.config.getBeforeNotifyTasks()) {
            try {
                if (!beforeNotify.run(error)) {
                    return false;
                }
            } catch (Throwable ex) {
                Logger.warn("BeforeNotify threw an Exception", ex);
            }
        }
        return true;
    }
}
