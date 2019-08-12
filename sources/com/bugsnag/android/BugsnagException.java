package com.bugsnag.android;

public class BugsnagException extends Throwable {
    private String name;

    public BugsnagException(String name2, String message, StackTraceElement[] frames) {
        super(message);
        super.setStackTrace(frames);
        this.name = name2;
    }

    public String getName() {
        return this.name;
    }
}
