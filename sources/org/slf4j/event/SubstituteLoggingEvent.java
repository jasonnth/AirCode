package org.slf4j.event;

import org.slf4j.helpers.SubstituteLogger;

public class SubstituteLoggingEvent implements LoggingEvent {
    Object[] argArray;
    Level level;
    SubstituteLogger logger;
    String loggerName;
    String message;
    String threadName;
    Throwable throwable;
    long timeStamp;

    public void setLevel(Level level2) {
        this.level = level2;
    }

    public void setLoggerName(String loggerName2) {
        this.loggerName = loggerName2;
    }

    public SubstituteLogger getLogger() {
        return this.logger;
    }

    public void setLogger(SubstituteLogger logger2) {
        this.logger = logger2;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public void setArgumentArray(Object[] argArray2) {
        this.argArray = argArray2;
    }

    public void setTimeStamp(long timeStamp2) {
        this.timeStamp = timeStamp2;
    }

    public void setThreadName(String threadName2) {
        this.threadName = threadName2;
    }

    public void setThrowable(Throwable throwable2) {
        this.throwable = throwable2;
    }
}
