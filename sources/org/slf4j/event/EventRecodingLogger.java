package org.slf4j.event;

import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;

public class EventRecodingLogger implements Logger {
    Queue<SubstituteLoggingEvent> eventQueue;
    SubstituteLogger logger;
    String name;

    public EventRecodingLogger(SubstituteLogger logger2, Queue<SubstituteLoggingEvent> eventQueue2) {
        this.logger = logger2;
        this.name = logger2.getName();
        this.eventQueue = eventQueue2;
    }

    private void recordEvent(Level level, String msg, Object[] args, Throwable throwable) {
        recordEvent(level, null, msg, args, throwable);
    }

    private void recordEvent(Level level, Marker marker, String msg, Object[] args, Throwable throwable) {
        SubstituteLoggingEvent loggingEvent = new SubstituteLoggingEvent();
        loggingEvent.setTimeStamp(System.currentTimeMillis());
        loggingEvent.setLevel(level);
        loggingEvent.setLogger(this.logger);
        loggingEvent.setLoggerName(this.name);
        loggingEvent.setMessage(msg);
        loggingEvent.setArgumentArray(args);
        loggingEvent.setThrowable(throwable);
        loggingEvent.setThreadName(Thread.currentThread().getName());
        this.eventQueue.add(loggingEvent);
    }

    public void debug(String msg) {
        recordEvent(Level.TRACE, msg, null, null);
    }

    public void info(String msg) {
        recordEvent(Level.INFO, msg, null, null);
    }

    public void warn(String msg) {
        recordEvent(Level.WARN, msg, null, null);
    }

    public void warn(String format, Object arg) {
        recordEvent(Level.WARN, format, new Object[]{arg}, null);
    }

    public void warn(String format, Object arg1, Object arg2) {
        recordEvent(Level.WARN, format, new Object[]{arg1, arg2}, null);
    }

    public void error(String msg) {
        recordEvent(Level.ERROR, msg, null, null);
    }

    public void error(String msg, Throwable t) {
        recordEvent(Level.ERROR, msg, null, t);
    }
}
