package com.kount.api;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

class SystemCollector extends CollectorTaskBase {
    private final WindowManager windowManager;

    SystemCollector(Object debugHandler, Context ctx) {
        super(debugHandler);
        this.windowManager = (WindowManager) ctx.getSystemService("window");
    }

    /* access modifiers changed from: 0000 */
    public final String getName() {
        return "System Collector";
    }

    /* access modifiers changed from: 0000 */
    public String getInternalName() {
        return internalName();
    }

    static String internalName() {
        return "LOCAL";
    }

    /* access modifiers changed from: 0000 */
    public void collect() {
        addDataPoint(PostKey.MOBILE_MODEL.toString(), Build.FINGERPRINT);
        addDataPoint(PostKey.OS_VERSION.toString(), VERSION.RELEASE);
        addDataPoint(PostKey.TOTAL_MEMORY.toString(), Long.toString(getTotalMemory() / 1048576));
        addDataPoint(PostKey.LANGUAGE.toString(), getLanguage());
        addDataPoint(PostKey.SCREEN_AVAILABLE.toString(), getScreenAvailable());
        addDataPoint(PostKey.TIMEZONE_AUGUST.toString(), Integer.toString(getTimeZone(7)));
        addDataPoint(PostKey.TIMEZONE_FEBRUARY.toString(), Integer.toString(getTimeZone(1)));
        callCompletionHandler(Boolean.valueOf(true), null);
    }

    /* access modifiers changed from: protected */
    public long getTotalMemory() {
        try {
            BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            long initial_memory = (long) (Integer.valueOf(localBufferedReader.readLine().split("\\s+")[1]).intValue() * 1024);
            localBufferedReader.close();
            return initial_memory;
        } catch (IOException e) {
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public String getLanguage() {
        return Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
    }

    /* access modifiers changed from: protected */
    public String getScreenAvailable() {
        Display display = this.windowManager.getDefaultDisplay();
        int realWidth = display.getWidth();
        int realHeight = display.getHeight();
        if (VERSION.SDK_INT >= 17) {
            DisplayMetrics realMetrics = new DisplayMetrics();
            display.getRealMetrics(realMetrics);
            realWidth = realMetrics.widthPixels;
            realHeight = realMetrics.heightPixels;
        } else if (VERSION.SDK_INT >= 14) {
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight", new Class[0]);
                realWidth = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(display, new Object[0])).intValue();
                realHeight = ((Integer) mGetRawH.invoke(display, new Object[0])).intValue();
            } catch (Exception e) {
            }
        }
        return realHeight + "x" + realWidth;
    }

    /* access modifiers changed from: protected */
    public int getTimeZone(int month) {
        TimeZone timeZone = TimeZone.getDefault();
        Calendar testDate = Calendar.getInstance(timeZone);
        testDate.set(1, 2007);
        testDate.set(2, month);
        testDate.set(5, 1);
        return ((timeZone.getOffset(testDate.getTimeInMillis()) * -1) / 1000) / 60;
    }
}
