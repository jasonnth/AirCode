package com.facebook.accountkit.internal;

import android.util.Log;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.LoggingBehavior;

final class ConsoleLogger {
    private static final String LOG_TAG_BASE = "AccountKitSDK.";
    private static final String NEWLINE = "\n";
    private final LoggingBehavior behavior;
    private StringBuilder contents = new StringBuilder();
    private final String tag;

    public static void log(LoggingBehavior behavior2, String tag2, String string) {
        log(behavior2, 3, tag2, string);
    }

    public static void log(LoggingBehavior behavior2, String tag2, String format, Object... args) {
        log(behavior2, 3, tag2, String.format(format, args));
    }

    public static void log(LoggingBehavior behavior2, int priority, String tag2, String format, Object... args) {
        log(behavior2, priority, tag2, String.format(format, args));
    }

    private static void log(LoggingBehavior behavior2, int priority, String tag2, String string) {
        if (AccountKit.getLoggingBehaviors().isEnabled(behavior2)) {
            if (!tag2.startsWith(LOG_TAG_BASE)) {
                tag2 = LOG_TAG_BASE + tag2;
            }
            Log.println(priority, tag2, string);
            if (behavior2 == LoggingBehavior.DEVELOPER_ERRORS) {
                new Exception().printStackTrace();
            }
        }
    }

    ConsoleLogger(LoggingBehavior behavior2, String tag2) {
        this.behavior = behavior2;
        this.tag = LOG_TAG_BASE + tag2;
    }

    public void log() {
        log(this.behavior, 3, this.tag, this.contents.toString());
        this.contents = new StringBuilder();
    }

    /* access modifiers changed from: 0000 */
    public void appendLine(String string) {
        if (shouldLog()) {
            this.contents.append(string).append(NEWLINE);
        }
    }

    private void append(String toFormat, Object... args) {
        if (shouldLog()) {
            this.contents.append(String.format(toFormat, args));
        }
    }

    /* access modifiers changed from: 0000 */
    public void appendKeyValue(String key, Object value) {
        append("  %s:\t%s\n", key, value);
    }

    private boolean shouldLog() {
        return AccountKit.getLoggingBehaviors().isEnabled(this.behavior);
    }
}
