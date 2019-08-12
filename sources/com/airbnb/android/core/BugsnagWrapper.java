package com.airbnb.android.core;

import android.annotation.SuppressLint;
import android.content.Context;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.p027n2.C0977N2;
import com.bugsnag.android.BeforeNotify;
import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.Callback;
import com.bugsnag.android.MetaData;
import com.bugsnag.android.Severity;
import com.google.common.collect.EvictingQueue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Queues;
import com.miteksystems.misnap.params.MiSnapAPI;
import java.util.Collection;
import java.util.Queue;

@SuppressLint({"Bugsnag"})
public final class BugsnagWrapper {
    private static final int BREADCRUMBS_CAPACITY = 50;
    private static final String DELIVERED_EXPERIMENTS_TAB_NAME = "delivered experiments";
    private static final String[] IGNORE_CLASSES = {"com.facebook.react.common.JavascriptException"};
    private static final String[] SENSITIVE_KEYS = {"password", "password_confirmation", "old_password", "oauth_token", "token", "access_token", "credit", MiSnapAPI.CREDIT_CARD_NUMBER, "cc_expire_month", "cc_expire_year", "cc_security_code", "payment_method_nonce", "existing_cc_confirm_pw", "accountNumber", "paypalEmail", "iban", "tax_id", "idNumber", "idnumber", "guest_lat", "guest_lng", "ach_account_number", "envoy_account_number", "envoy_iban", "paypal_email", "nationalId", "pin", "vat_number", "secret", "date_of_expiry", "date_of_expiry_day", "date_of_expiry_month", "date_of_expiry_year", "given_names", "id_number", OfficialIdActivity.ID_TYPE_EXTRA, "nationality", "surname", "user_id"};
    private static final String TAG = "BugsnagWrapper";
    private static final Queue<String> breadcrumbs = Queues.synchronizedQueue(EvictingQueue.create(50));
    private static boolean initialized = false;

    private BugsnagWrapper() {
    }

    public static void init(Context context, String releaseStage, BeforeNotify beforeNotify) {
        Bugsnag.init(context);
        Bugsnag.beforeNotify(beforeNotify);
        Bugsnag.getClient().setMaxBreadcrumbs(50);
        Bugsnag.setReleaseStage(releaseStage);
        Bugsnag.addToTab("App", "git_sha", BuildHelper.gitSha());
        Bugsnag.addToTab("App", "git_branch", BuildHelper.gitBranch());
        Bugsnag.addToTab("Device", "tablet", Boolean.valueOf(MiscUtils.isTabletScreen(context)));
        Bugsnag.setProjectPackages(context.getPackageName(), C0977N2.class.getPackage().getName());
        Bugsnag.setFilters(SENSITIVE_KEYS);
        Bugsnag.setIgnoreClasses(IGNORE_CLASSES);
        initialized = true;
    }

    public static void setLoggedIn(boolean loggedIn) {
        addToTab("user", "loggedIn", Boolean.toString(loggedIn));
    }

    public static void addToTab(String tabName, String name, String value) {
        if (initialized) {
            Bugsnag.addToTab(tabName, name, value);
        }
    }

    public static void notify(NetworkException e) {
        notify((Throwable) e);
    }

    public static void notify(Throwable throwable) {
        if (initialized) {
            Bugsnag.notify(throwable);
        }
    }

    public static void notify(Throwable throwable, MetaData metadata) {
        if (initialized) {
            Bugsnag.notify(throwable, metadata);
        }
    }

    public static void notify(Throwable throwable, Callback callback) {
        if (initialized) {
            Bugsnag.notify(throwable, callback);
        }
    }

    public static void notify(Throwable throwable, Severity severity) {
        if (initialized) {
            Bugsnag.notify(throwable, severity);
        }
    }

    public static void leaveBreadcrumb(String breadcrumb) {
        breadcrumbs.add(breadcrumb);
        if (initialized) {
            Bugsnag.leaveBreadcrumb(breadcrumb);
        }
    }

    public static ImmutableList<String> getBreadcrumbs() {
        return ImmutableList.copyOf((Collection<? extends E>) breadcrumbs);
    }

    public static void setProjectPackages(String packageName, String gcRoot) {
        if (initialized) {
            Bugsnag.setProjectPackages(packageName, gcRoot);
        }
    }

    public static void setContext(String s) {
        if (initialized) {
            Bugsnag.setContext(s);
        }
    }

    public static void notify(String leakName, String title, StackTraceElement[] stacktrace, Severity info, MetaData leakMetaData) {
        if (initialized) {
            Bugsnag.notify(leakName, title, stacktrace, info, leakMetaData);
        }
    }

    public static void notify(String name, String message, String context, StackTraceElement[] stackTrace, Severity severity, MetaData metaData) {
        if (initialized) {
            Bugsnag.notify(name, message, context, stackTrace, severity, metaData);
        }
    }

    public static void throwOrNotify(NetworkException e) {
        throwOrNotify((RuntimeException) e);
    }

    public static void throwOrNotify(RuntimeException ex) {
        if (!BuildHelper.isDebugFeaturesEnabled()) {
            notify((Throwable) ex, Severity.WARNING);
        } else if (ex instanceof NetworkException) {
            throw new RuntimeException(ex.getMessage());
        } else {
            throw ex;
        }
    }

    public static void addDeliveredExperimentMetadata(String experiment, String treatment) {
        addToTab(DELIVERED_EXPERIMENTS_TAB_NAME, experiment, treatment);
    }
}
