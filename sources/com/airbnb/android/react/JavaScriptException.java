package com.airbnb.android.react;

import android.util.Log;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.JsonStream.Streamable;
import com.facebook.common.util.UriUtil;
import java.io.IOException;

class JavaScriptException extends Exception implements Streamable {
    private static final String EXCEPTION_TYPE = "browserjs";
    private static final String TAG = JavaScriptException.class.getSimpleName();
    private final String name;
    private final String rawStacktrace;

    JavaScriptException(String name2, String message, String rawStacktrace2) {
        super(message);
        this.name = name2;
        this.rawStacktrace = rawStacktrace2;
    }

    public void toStream(JsonStream writer) throws IOException {
        String[] split;
        Log.i(TAG, "Serializing exception");
        writer.beginObject();
        writer.name("errorClass").value(this.name);
        writer.name("message").value(getLocalizedMessage());
        writer.name("type").value(EXCEPTION_TYPE);
        writer.name("stacktrace");
        writer.beginArray();
        for (String rawFrame : this.rawStacktrace.split("\\n")) {
            writer.beginObject();
            String[] methodComponents = rawFrame.split("@", 2);
            String fragment = methodComponents[0];
            if (methodComponents.length == 2) {
                writer.name("method").value(methodComponents[0]);
                fragment = methodComponents[1];
            }
            int columnIndex = fragment.lastIndexOf(":");
            if (columnIndex != -1) {
                String columnString = fragment.substring(columnIndex + 1, fragment.length());
                try {
                    writer.name("columnNumber").value((long) Integer.parseInt(columnString));
                } catch (NumberFormatException e) {
                    Log.e(TAG, String.format("Failed to parse column: '%s'", new Object[]{columnString}));
                }
                fragment = fragment.substring(0, columnIndex);
            }
            int lineNumberIndex = fragment.lastIndexOf(":");
            if (lineNumberIndex != -1) {
                String lineNumberString = fragment.substring(lineNumberIndex + 1, fragment.length());
                try {
                    writer.name("lineNumber").value((long) Integer.parseInt(lineNumberString));
                } catch (NumberFormatException e2) {
                    Log.e(TAG, String.format("Failed to parse lineNumber: '%s'", new Object[]{lineNumberString}));
                }
                fragment = fragment.substring(0, lineNumberIndex);
            }
            writer.name(UriUtil.LOCAL_FILE_SCHEME).value(fragment);
            writer.endObject();
        }
        writer.endArray();
        writer.endObject();
    }
}
