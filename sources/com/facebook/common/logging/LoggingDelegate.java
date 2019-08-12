package com.facebook.common.logging;

public interface LoggingDelegate {
    /* renamed from: d */
    void mo30383d(String str, String str2);

    /* renamed from: d */
    void mo30384d(String str, String str2, Throwable th);

    /* renamed from: e */
    void mo30385e(String str, String str2);

    /* renamed from: e */
    void mo30386e(String str, String str2, Throwable th);

    int getMinimumLoggingLevel();

    /* renamed from: i */
    void mo30388i(String str, String str2);

    /* renamed from: i */
    void mo30389i(String str, String str2, Throwable th);

    boolean isLoggable(int i);

    void log(int i, String str, String str2);

    void setMinimumLoggingLevel(int i);

    /* renamed from: v */
    void mo30394v(String str, String str2);

    /* renamed from: v */
    void mo30395v(String str, String str2, Throwable th);

    /* renamed from: w */
    void mo30396w(String str, String str2);

    /* renamed from: w */
    void mo30397w(String str, String str2, Throwable th);

    void wtf(String str, String str2);

    void wtf(String str, String str2, Throwable th);
}
