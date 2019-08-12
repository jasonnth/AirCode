package com.bugsnag.android;

import com.bugsnag.android.JsonStream.Streamable;
import java.io.IOException;

class Exceptions implements Streamable {
    private final Configuration config;
    private Throwable exception;

    Exceptions(Configuration config2, Throwable exception2) {
        this.config = config2;
        this.exception = exception2;
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginArray();
        for (Throwable currentEx = this.exception; currentEx != null; currentEx = currentEx.getCause()) {
            if (currentEx instanceof Streamable) {
                ((Streamable) currentEx).toStream(writer);
            } else {
                exceptionToStream(writer, getExceptionName(currentEx), currentEx.getLocalizedMessage(), currentEx.getStackTrace());
            }
        }
        writer.endArray();
    }

    private String getExceptionName(Throwable t) {
        if (t instanceof BugsnagException) {
            return ((BugsnagException) t).getName();
        }
        return t.getClass().getName();
    }

    private void exceptionToStream(JsonStream writer, String name, String message, StackTraceElement[] frames) throws IOException {
        Stacktrace stacktrace = new Stacktrace(this.config, frames);
        writer.beginObject();
        writer.name("errorClass").value(name);
        writer.name("message").value(message);
        writer.name("type").value(this.config.defaultExceptionType);
        writer.name("stacktrace").value((Streamable) stacktrace);
        writer.endObject();
    }
}
