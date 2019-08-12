package com.airbnb.android.react;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.utils.BuildHelper;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.common.JavascriptException;

public class ReactModuleCallExceptionHandler implements NativeModuleCallExceptionHandler {
    public void handleException(Exception e) {
        if (!BuildHelper.isDevelopmentBuild()) {
            handleProdException(e);
        } else {
            rethrow(e);
        }
    }

    private void handleProdException(Exception e) {
        if (e instanceof JavascriptException) {
            throw ((JavascriptException) e);
        }
        BugsnagWrapper.notify((Throwable) e);
    }

    private void rethrow(Exception e) {
        if (e instanceof RuntimeException) {
            throw ((RuntimeException) e);
        }
        throw new RuntimeException(e);
    }
}
