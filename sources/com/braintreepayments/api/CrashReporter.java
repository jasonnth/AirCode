package com.braintreepayments.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

class CrashReporter implements UncaughtExceptionHandler {
    private BraintreeFragment mBraintreeFragment;
    private UncaughtExceptionHandler mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    static CrashReporter setup(BraintreeFragment fragment) {
        return new CrashReporter(fragment);
    }

    private CrashReporter(BraintreeFragment fragment) {
        this.mBraintreeFragment = fragment;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* access modifiers changed from: 0000 */
    public void tearDown() {
        Thread.setDefaultUncaughtExceptionHandler(this.mDefaultExceptionHandler);
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        if (stringWriter.toString().contains("com.braintreepayments") || stringWriter.toString().contains("com.paypal")) {
            this.mBraintreeFragment.sendAnalyticsEvent("crash");
        }
        if (this.mDefaultExceptionHandler != null) {
            this.mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    }
}
