package org.slf4j.helpers;

public class FormattingTuple {
    public static FormattingTuple NULL = new FormattingTuple(null);
    private Object[] argArray;
    private String message;
    private Throwable throwable;

    public FormattingTuple(String message2) {
        this(message2, null, null);
    }

    public FormattingTuple(String message2, Object[] argArray2, Throwable throwable2) {
        this.message = message2;
        this.throwable = throwable2;
        this.argArray = argArray2;
    }

    public String getMessage() {
        return this.message;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }
}
