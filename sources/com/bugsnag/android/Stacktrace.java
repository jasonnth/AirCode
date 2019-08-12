package com.bugsnag.android;

import com.bugsnag.android.JsonStream.Streamable;
import com.facebook.common.util.UriUtil;
import com.facebook.internal.AnalyticsEvents;
import java.io.IOException;

class Stacktrace implements Streamable {
    final Configuration config;
    final StackTraceElement[] stacktrace;

    Stacktrace(Configuration config2, StackTraceElement[] stacktrace2) {
        this.config = config2;
        this.stacktrace = stacktrace2;
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginArray();
        StackTraceElement[] stackTraceElementArr = this.stacktrace;
        int length = stackTraceElementArr.length;
        for (int i = 0; i < length; i++) {
            StackTraceElement el = stackTraceElementArr[i];
            try {
                writer.beginObject();
                writer.name("method").value(el.getClassName() + "." + el.getMethodName());
                writer.name(UriUtil.LOCAL_FILE_SCHEME).value(el.getFileName() == null ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : el.getFileName());
                writer.name("lineNumber").value((long) el.getLineNumber());
                if (this.config.inProject(el.getClassName())) {
                    writer.name("inProject").value(true);
                }
                writer.endObject();
            } catch (Exception lineEx) {
                lineEx.printStackTrace(System.err);
            }
        }
        writer.endArray();
    }
}
