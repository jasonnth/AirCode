package com.airbnb.android.lib;

import android.content.Context;
import android.os.Build.VERSION;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.bugsnag.MetaDataWrapper;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.Systems.p263v1.SystemsMobileCrashEvent.Builder;
import com.airbnb.p027n2.utils.TextUtil;
import com.bugsnag.android.BeforeNotify;
import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.Error;
import com.bugsnag.android.MetaData;
import com.bugsnag.android.Severity;
import java.util.Map.Entry;

public final class BugsnagInitializer implements BeforeNotify {
    private final LoggingContextFactory loggingContextFactory;
    private final long onCreateTimeMillis;
    private final ViewBreadcrumbManager viewBreadcrumbManager;

    private BugsnagInitializer(AirbnbApplication application, Context context, long onCreateTimeMillis2, LoggingContextFactory loggingContextFactory2, ViewBreadcrumbManager viewBreadcrumbManager2) {
        this.onCreateTimeMillis = onCreateTimeMillis2;
        this.loggingContextFactory = loggingContextFactory2;
        this.viewBreadcrumbManager = viewBreadcrumbManager2;
        if (!application.isTestApplication()) {
            BugsnagWrapper.init(context, TextUtil.titleCase(BuildHelper.buildType()), this);
        }
    }

    public static void init(LoggingContextFactory loggingContextFactory2, AirbnbApplication application, Context context, long onCreateTimeMillis2, ViewBreadcrumbManager viewBreadcrumbManager2) {
        new BugsnagInitializer(application, context, onCreateTimeMillis2, loggingContextFactory2, viewBreadcrumbManager2);
    }

    public boolean run(Error error) {
        setGroupingHashIfAvailable(error);
        Bugsnag.addToTab("User", "monkey", Boolean.valueOf(MiscUtils.isUserAMonkey()));
        if (error.getSeverity() == Severity.ERROR) {
            for (Entry<String, String> e : this.viewBreadcrumbManager.getBreadcrumbMap().entrySet()) {
                Bugsnag.addToTab("view_breadcrumbs", (String) e.getKey(), e.getValue());
            }
            long crashTimeMillis = System.currentTimeMillis();
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv(BaseAnalytics.OPERATION, "crash").mo11639kv("exception_name", error.getExceptionName()).mo11638kv("session_length_seconds", (crashTimeMillis - this.onCreateTimeMillis) / 1000).mo11640kv("is_monkey", MiscUtils.isUserAMonkey()), true);
            publishJitneyCrashEvent(error, crashTimeMillis);
        }
        return true;
    }

    private void publishJitneyCrashEvent(Error error, long crashTimeMillis) {
        Builder builder = new Builder(this.loggingContextFactory.newInstance(), getCrashGroup(error), BuildHelper.versionName(), Long.valueOf((long) BuildHelper.versionCode()), Long.valueOf(this.onCreateTimeMillis), Long.valueOf(crashTimeMillis), String.valueOf(VERSION.SDK_INT));
        builder.git_sha(BuildHelper.gitSha()).exception_name(error.getExceptionName()).line_number(Long.valueOf((long) getLineNumber(error))).view_breadcrumbs(this.viewBreadcrumbManager.getBreadcrumbs());
        JitneyPublisher.publish(builder);
    }

    private void setGroupingHashIfAvailable(Error error) {
        MetaData metaData = error.getMetaData();
        if (metaData instanceof MetaDataWrapper) {
            MetaDataWrapper wrapper = (MetaDataWrapper) metaData;
            if (wrapper.hasGroupingHash()) {
                error.setGroupingHash(wrapper.getGroupingHash());
            }
        }
    }

    public String getCrashGroup(Error error) {
        StackTraceElement topStackTraceElement = getTopStackTraceElement(error);
        return topStackTraceElement == null ? "unknown" : topStackTraceElement.getFileName() + ":" + topStackTraceElement.getLineNumber();
    }

    private int getLineNumber(Error error) {
        StackTraceElement topStackTraceElement = getTopStackTraceElement(error);
        if (topStackTraceElement == null) {
            return 0;
        }
        return topStackTraceElement.getLineNumber();
    }

    private StackTraceElement getTopStackTraceElement(Error error) {
        StackTraceElement[] stackTrace = error.getException() == null ? null : error.getException().getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        }
        return stackTrace[0];
    }
}
