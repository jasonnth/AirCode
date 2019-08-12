package org.slf4j.impl;

import android.util.Log;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

class AndroidLoggerAdapter extends MarkerIgnoringBase {
    AndroidLoggerAdapter(String tag) {
        this.name = tag;
    }

    public void debug(String msg) {
        log(3, msg, null);
    }

    public void info(String msg) {
        log(4, msg, null);
    }

    public void warn(String msg) {
        log(5, msg, null);
    }

    public void warn(String format, Object arg) {
        formatAndLog(5, format, arg);
    }

    public void warn(String format, Object arg1, Object arg2) {
        formatAndLog(5, format, arg1, arg2);
    }

    public void error(String msg) {
        log(6, msg, null);
    }

    public void error(String msg, Throwable t) {
        log(6, msg, t);
    }

    private void formatAndLog(int priority, String format, Object... argArray) {
        if (isLoggable(priority)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logInternal(priority, ft.getMessage(), ft.getThrowable());
        }
    }

    private void log(int priority, String message, Throwable throwable) {
        if (isLoggable(priority)) {
            logInternal(priority, message, throwable);
        }
    }

    private boolean isLoggable(int priority) {
        return Log.isLoggable(this.name, priority);
    }

    private void logInternal(int priority, String message, Throwable throwable) {
        if (throwable != null) {
            message = message + 10 + Log.getStackTraceString(throwable);
        }
        Log.println(priority, this.name, message);
    }
}
