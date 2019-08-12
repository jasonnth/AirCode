package org.slf4j.helpers;

public class NOPLogger extends MarkerIgnoringBase {
    public static final NOPLogger NOP_LOGGER = new NOPLogger();

    protected NOPLogger() {
    }

    public String getName() {
        return "NOP";
    }

    public final void debug(String msg) {
    }

    public final void info(String msg) {
    }

    public final void warn(String msg) {
    }

    public final void warn(String format, Object arg1) {
    }

    public final void warn(String format, Object arg1, Object arg2) {
    }

    public final void error(String msg) {
    }

    public final void error(String msg, Throwable t) {
    }
}
