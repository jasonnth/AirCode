package com.bugsnag.android;

import android.text.TextUtils;
import com.bugsnag.android.JsonStream.Streamable;
import com.facebook.places.model.PlaceFields;
import java.io.IOException;
import p005cn.jpush.android.JPushConstants.PushService;

public class Error implements Streamable {
    private AppData appData;
    private AppState appState;
    private Breadcrumbs breadcrumbs;
    final Configuration config;
    private String context;
    private DeviceData deviceData;
    private DeviceState deviceState;
    private Throwable exception;
    private String groupingHash;
    private MetaData metaData = new MetaData();
    private Severity severity = Severity.WARNING;
    private User user;

    Error(Configuration config2, Throwable exception2) {
        this.config = config2;
        this.exception = exception2;
    }

    Error(Configuration config2, String name, String message, StackTraceElement[] frames) {
        this.config = config2;
        this.exception = new BugsnagException(name, message, frames);
    }

    public void toStream(JsonStream writer) throws IOException {
        MetaData mergedMetaData = MetaData.merge(this.config.getMetaData(), this.metaData);
        writer.beginObject();
        writer.name("payloadVersion").value("3");
        writer.name(PlaceFields.CONTEXT).value(getContext());
        writer.name("severity").value((Streamable) this.severity);
        writer.name("metaData").value((Streamable) mergedMetaData);
        if (this.config.getProjectPackages() != null) {
            writer.name("projectPackages").beginArray();
            for (String projectPackage : this.config.getProjectPackages()) {
                writer.value(projectPackage);
            }
            writer.endArray();
        }
        writer.name("exceptions").value((Streamable) new Exceptions(this.config, this.exception));
        writer.name("user").value((Streamable) this.user);
        writer.name(PushService.PARAM_APP).value((Streamable) this.appData);
        writer.name("appState").value((Streamable) this.appState);
        writer.name("device").value((Streamable) this.deviceData);
        writer.name("deviceState").value((Streamable) this.deviceState);
        writer.name("breadcrumbs").value((Streamable) this.breadcrumbs);
        writer.name("groupingHash").value(this.groupingHash);
        if (this.config.getSendThreads()) {
            writer.name("threads").value((Streamable) new ThreadState(this.config));
        }
        writer.endObject();
    }

    public void setContext(String context2) {
        this.context = context2;
    }

    public String getContext() {
        if (this.context != null && !TextUtils.isEmpty(this.context)) {
            return this.context;
        }
        if (this.config.getContext() != null) {
            return this.config.getContext();
        }
        if (this.appState != null) {
            return AppState.getActiveScreenClass(this.context);
        }
        return null;
    }

    public void setGroupingHash(String groupingHash2) {
        this.groupingHash = groupingHash2;
    }

    public void setSeverity(Severity severity2) {
        if (severity2 != null) {
            this.severity = severity2;
        }
    }

    public Severity getSeverity() {
        return this.severity;
    }

    public MetaData getMetaData() {
        return this.metaData;
    }

    public void setMetaData(MetaData metaData2) {
        this.metaData = metaData2;
    }

    public String getExceptionName() {
        if (this.exception instanceof BugsnagException) {
            return ((BugsnagException) this.exception).getName();
        }
        return this.exception.getClass().getName();
    }

    public String getExceptionMessage() {
        return this.exception.getLocalizedMessage();
    }

    public Throwable getException() {
        return this.exception;
    }

    /* access modifiers changed from: 0000 */
    public void setAppData(AppData appData2) {
        this.appData = appData2;
    }

    /* access modifiers changed from: 0000 */
    public void setDeviceData(DeviceData deviceData2) {
        this.deviceData = deviceData2;
    }

    /* access modifiers changed from: 0000 */
    public void setAppState(AppState appState2) {
        this.appState = appState2;
    }

    /* access modifiers changed from: 0000 */
    public void setDeviceState(DeviceState deviceState2) {
        this.deviceState = deviceState2;
    }

    /* access modifiers changed from: 0000 */
    public void setUser(User user2) {
        this.user = user2;
    }

    /* access modifiers changed from: 0000 */
    public void setBreadcrumbs(Breadcrumbs breadcrumbs2) {
        this.breadcrumbs = breadcrumbs2;
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldIgnoreClass() {
        return this.config.shouldIgnoreClass(getExceptionName());
    }
}
