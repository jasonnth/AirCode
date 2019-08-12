package com.bugsnag.android;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Configuration extends Observable implements Observer {
    private final String apiKey;
    private String appVersion;
    private final Collection<BeforeNotify> beforeNotifyTasks = new LinkedList();
    private String buildUUID;
    private String context;
    String defaultExceptionType = "android";
    private boolean enableExceptionHandler = true;
    private String endpoint = "https://notify.bugsnag.com";
    private String[] filters = {"password"};
    private String[] ignoreClasses;
    private MetaData metaData;
    private String[] notifyReleaseStages = null;
    private boolean persistUserBetweenSessions = false;
    private String[] projectPackages;
    private String releaseStage;
    private boolean sendThreads = true;

    public Configuration(String apiKey2) {
        this.apiKey = apiKey2;
        this.metaData = new MetaData();
        this.metaData.addObserver(this);
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context2) {
        this.context = context2;
        notifyBugsnagObservers(NotifyType.CONTEXT);
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public String getBuildUUID() {
        return this.buildUUID;
    }

    public void setBuildUUID(String buildUUID2) {
        this.buildUUID = buildUUID2;
        notifyBugsnagObservers(NotifyType.APP);
    }

    public void setFilters(String[] filters2) {
        this.filters = filters2;
        this.metaData.setFilters(filters2);
    }

    public void setIgnoreClasses(String[] ignoreClasses2) {
        this.ignoreClasses = ignoreClasses2;
    }

    public String[] getProjectPackages() {
        return this.projectPackages;
    }

    public void setProjectPackages(String[] projectPackages2) {
        this.projectPackages = projectPackages2;
    }

    public String getReleaseStage() {
        return this.releaseStage;
    }

    public void setReleaseStage(String releaseStage2) {
        this.releaseStage = releaseStage2;
        notifyBugsnagObservers(NotifyType.APP);
    }

    public boolean getSendThreads() {
        return this.sendThreads;
    }

    public boolean getEnableExceptionHandler() {
        return this.enableExceptionHandler;
    }

    public void setEnableExceptionHandler(boolean enableExceptionHandler2) {
        this.enableExceptionHandler = enableExceptionHandler2;
    }

    /* access modifiers changed from: protected */
    public MetaData getMetaData() {
        return this.metaData;
    }

    /* access modifiers changed from: protected */
    public Collection<BeforeNotify> getBeforeNotifyTasks() {
        return this.beforeNotifyTasks;
    }

    public boolean getPersistUserBetweenSessions() {
        return this.persistUserBetweenSessions;
    }

    /* access modifiers changed from: protected */
    public boolean shouldNotifyForReleaseStage(String releaseStage2) {
        if (this.notifyReleaseStages == null) {
            return true;
        }
        return Arrays.asList(this.notifyReleaseStages).contains(releaseStage2);
    }

    /* access modifiers changed from: protected */
    public boolean shouldIgnoreClass(String className) {
        if (this.ignoreClasses == null) {
            return false;
        }
        return Arrays.asList(this.ignoreClasses).contains(className);
    }

    /* access modifiers changed from: protected */
    public void beforeNotify(BeforeNotify beforeNotify) {
        this.beforeNotifyTasks.add(beforeNotify);
    }

    /* access modifiers changed from: protected */
    public boolean inProject(String className) {
        String[] strArr;
        if (this.projectPackages == null) {
            return false;
        }
        for (String packageName : this.projectPackages) {
            if (packageName != null && className.startsWith(packageName)) {
                return true;
            }
        }
        return false;
    }

    private void notifyBugsnagObservers(NotifyType type) {
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
}
