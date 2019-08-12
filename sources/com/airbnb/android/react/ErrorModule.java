package com.airbnb.android.react;

import android.util.Log;
import com.airbnb.android.core.BugsnagWrapper;
import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class ErrorModule extends VersionedReactModuleBase {
    private static final String TAG = ErrorModule.class.getSimpleName();
    private static final int VERSION = 2;
    private static final String bugsnagAndroidVersion = Bugsnag.class.getPackage().getSpecificationVersion();

    private class InvalidPayloadException extends IllegalArgumentException {
        public InvalidPayloadException(String message) {
            super(message);
        }
    }

    ErrorModule(ReactApplicationContext reactContext) {
        super(reactContext, 2);
    }

    public String getName() {
        return "ErrorBridge";
    }

    @ReactMethod
    public void addAttribute(String name, String value, String tabName) {
        BugsnagWrapper.addToTab(tabName, name, value);
    }

    @ReactMethod
    public void leaveBreadcrumb(String message) {
        BugsnagWrapper.leaveBreadcrumb(message);
    }

    @ReactMethod
    public void notify(ReadableMap payload) {
        if (payload == null) {
            notifyOnInvalidPayload("Payload is missing");
        } else if (!payload.hasKey("errorClass")) {
            notifyOnInvalidPayload("No errorClass in payload");
        } else if (!payload.hasKey("stacktrace")) {
            notifyOnInvalidPayload("No stacktrace in payload");
        } else {
            String errorClass = payload.getString("errorClass");
            String errorMessage = payload.getString("errorMessage");
            String rawStacktrace = payload.getString("stacktrace");
            Log.i(TAG, String.format("Sending exception: %s - %s\n", new Object[]{errorClass, errorMessage, rawStacktrace}));
            BugsnagWrapper.notify((Throwable) new JavaScriptException(errorClass, errorMessage, rawStacktrace), (Callback) new BugsnagDiagnosticsCallback(bugsnagAndroidVersion, payload));
        }
    }

    private void notifyOnInvalidPayload(String message) {
        String fullErrorMessage = String.format("Bugsnag could not notify: %s", new Object[]{message});
        BugsnagWrapper.notify((Throwable) new InvalidPayloadException(fullErrorMessage));
        Log.e(TAG, fullErrorMessage);
    }
}
