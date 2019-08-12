package com.airbnb.android.react;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.provider.Settings;
import android.support.p002v7.app.AlertDialog;
import android.widget.Toast;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.utils.AndroidUtils;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class ReactNativeUtils {
    private static final int APP_INITIALIZE_TOAST_DELAY = 3000;
    private static final String HAS_SHARED_ELEMENT_TRANSITION = "hasSharedElementTransition";
    private static final String INITIALIZE_REACT_NATIVE = "initialize_react_native";
    private static final String JS_BUNDLE_FILE_NAME = "index.js";

    private class FakeClassForBugFix {
        private FakeClassForBugFix() {
        }

        @ReactProp(name = "foo")
        public void setFoo(String foo) {
        }

        @ReactPropGroup(names = {"a", "b"})
        public void setGroup(String a, String b) {
        }
    }

    private ReactNativeUtils() {
    }

    public static boolean packagerIsAvailable() {
        boolean z = false;
        if (BuildHelper.isDevelopmentBuild()) {
            ThreadPolicy originalPolicy = StrictMode.getThreadPolicy();
            StrictMode.setThreadPolicy(new Builder().build());
            Response response = null;
            try {
                response = new OkHttpClient.Builder().connectTimeout(50, TimeUnit.MILLISECONDS).build().newCall(new Request.Builder().url("http://127.0.0.1:8081/status").build()).execute();
                z = response.isSuccessful();
                if (response != null) {
                    response.body().close();
                }
                StrictMode.setThreadPolicy(originalPolicy);
            } catch (IOException e) {
                if (response != null) {
                    response.body().close();
                }
                StrictMode.setThreadPolicy(originalPolicy);
            } catch (Throwable th) {
                if (response != null) {
                    response.body().close();
                }
                StrictMode.setThreadPolicy(originalPolicy);
                throw th;
            }
        }
        return z;
    }

    public static void maybeEmitEvent(ReactContext context, String name, Object data) {
        if (context == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException(String.format("reactContext is null (calling event: %s)", new Object[]{name})));
        } else if (context.hasActiveCatalystInstance()) {
            try {
                ((RCTDeviceEventEmitter) context.getJSModule(RCTDeviceEventEmitter.class)).emit(name, data);
            } catch (RuntimeException e) {
            }
        }
    }

    public static boolean isReactNativeIntent(Intent intent) {
        String className = intent.getComponent().getClassName();
        return Activities.reactNativeModal().getName().equals(className) || Activities.reactNative().getName().equals(className);
    }

    public static boolean hasSharedElementTransition(Intent intent) {
        return intent.getBooleanExtra(HAS_SHARED_ELEMENT_TRANSITION, false);
    }

    public static void setHasSharedElementTransition(Intent intent, boolean value) {
        intent.putExtra(HAS_SHARED_ELEMENT_TRANSITION, value);
    }

    public static void showAlertBecauseChecksFailed(Context context, OnDismissListener onDismissListener) {
        BugsnagWrapper.notify((Throwable) new IllegalStateException("Tried to create ReactNativeActivity/ReactNativeFragment, but ReactContext is null. Alerting the user."));
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(C7663R.string.error).setMessage(C7663R.string.rn_init_error).create();
        alertDialog.setOnDismissListener(onDismissListener);
        alertDialog.show();
    }

    @TargetApi(23)
    private static void handleOverlayPermissionsMissing(Application application) {
        new Handler(Looper.getMainLooper()).postDelayed(ReactNativeUtils$$Lambda$1.lambdaFactory$(application), 3000);
    }

    static /* synthetic */ void lambda$handleOverlayPermissionsMissing$0(Application application) {
        application.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION").addFlags(268435456));
        Toast.makeText(application, application.getString(C7663R.string.rn_draw_permissions_toast), 1).show();
    }

    private static void handleBundleMissing(Application application) {
        new Handler(Looper.getMainLooper()).postDelayed(ReactNativeUtils$$Lambda$2.lambdaFactory$(application), 3000);
    }

    private static boolean bundleCanBeUsedFromPackagerOrFilesystem(Context context) {
        return AndroidUtils.assetExists(context, JS_BUNDLE_FILE_NAME) || packagerIsAvailable();
    }

    public static void safeInitialize(Application application, AirReactInstanceManager reactInstanceManager, LoggingContextFactory loggingContextFactory, SharedPrefsHelper sharedPrefsHelper) {
        if (BuildHelper.isDevelopmentBuild() && !CoreApplication.instance().isTestApplication()) {
            if (VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(application)) {
                handleOverlayPermissionsMissing(application);
                return;
            } else if (!bundleCanBeUsedFromPackagerOrFilesystem(application)) {
                handleBundleMissing(application);
                return;
            }
        }
        try {
            PerformanceLogger performanceLogger = new PerformanceLogger(loggingContextFactory, sharedPrefsHelper);
            performanceLogger.markStart(INITIALIZE_REACT_NATIVE);
            reactInstanceManager.addReactInstanceEventListener(ReactNativeUtils$$Lambda$3.lambdaFactory$(performanceLogger));
            reactInstanceManager.createReactContextInBackground();
        } catch (UnsatisfiedLinkError e) {
            BugsnagWrapper.notify((Throwable) e);
        }
    }

    public static void initializeReactAnnotationsToPreventSamsungLollipopCrash() {
        for (Method method : FakeClassForBugFix.class.getDeclaredMethods()) {
            ReactProp reactProp = (ReactProp) method.getAnnotation(ReactProp.class);
            ReactPropGroup reactPropGroup = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
        }
    }
}
