package com.facebook.react.modules.share;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import p005cn.jpush.android.JPushConstants.PushActivity;

@ReactModule(name = "ShareModule")
/* renamed from: com.facebook.react.modules.share.ShareModule */
public class C1066ShareModule extends ReactContextBaseJavaModule {
    static final String ACTION_SHARED = "sharedAction";
    static final String ERROR_INVALID_CONTENT = "E_INVALID_CONTENT";
    static final String ERROR_UNABLE_TO_OPEN_DIALOG = "E_UNABLE_TO_OPEN_DIALOG";

    public C1066ShareModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ShareModule";
    }

    @ReactMethod
    public void share(ReadableMap content, String dialogTitle, Promise promise) {
        if (content == null) {
            promise.reject(ERROR_INVALID_CONTENT, "Content cannot be null");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setTypeAndNormalize("text/plain");
            if (content.hasKey("title")) {
                intent.putExtra("android.intent.extra.SUBJECT", content.getString("title"));
            }
            if (content.hasKey("message")) {
                intent.putExtra("android.intent.extra.TEXT", content.getString("message"));
            }
            Intent chooser = Intent.createChooser(intent, dialogTitle);
            chooser.addCategory(PushActivity.CATEGORY_1);
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.startActivity(chooser);
            } else {
                getReactApplicationContext().startActivity(chooser);
            }
            WritableMap result = Arguments.createMap();
            result.putString("action", ACTION_SHARED);
            promise.resolve(result);
        } catch (Exception e) {
            promise.reject(ERROR_UNABLE_TO_OPEN_DIALOG, "Failed to open share dialog");
        }
    }
}
