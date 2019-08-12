package com.squareup.moshi;

import java.io.IOException;
import okio.BufferedSink;

final class JsonUtf8Writer extends JsonWriter {
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private String deferredName;
    private String separator = ":";
    private final BufferedSink sink;

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        REPLACEMENT_CHARS[34] = "\\\"";
        REPLACEMENT_CHARS[92] = "\\\\";
        REPLACEMENT_CHARS[9] = "\\t";
        REPLACEMENT_CHARS[8] = "\\b";
        REPLACEMENT_CHARS[10] = "\\n";
        REPLACEMENT_CHARS[13] = "\\r";
        REPLACEMENT_CHARS[12] = "\\f";
    }

    JsonUtf8Writer(BufferedSink sink2) {
        if (sink2 == null) {
            throw new NullPointerException("sink == null");
        }
        this.sink = sink2;
        pushScope(6);
    }

    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        return open(1, "[");
    }

    public JsonWriter endArray() throws IOException {
        return close(1, 2, "]");
    }

    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        return open(3, "{");
    }

    public JsonWriter endObject() throws IOException {
        this.promoteValueToName = false;
        return close(3, 5, "}");
    }

    private JsonWriter open(int empty, String openBracket) throws IOException {
        beforeValue();
        pushScope(empty);
        this.pathIndices[this.stackSize - 1] = 0;
        this.sink.writeUtf8(openBracket);
        return this;
    }

    private JsonWriter close(int empty, int nonempty, String closeBracket) throws IOException {
        int context = peekScope();
        if (context != nonempty && context != empty) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        } else {
            this.stackSize--;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            if (context == nonempty) {
                newline();
            }
            this.sink.writeUtf8(closeBracket);
            return this;
        }
    }

    public JsonWriter name(String name) throws IOException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else if (this.deferredName != null) {
            throw new IllegalStateException("Nesting problem.");
        } else {
            this.deferredName = name;
            this.pathNames[this.stackSize - 1] = name;
            this.promoteValueToName = false;
            return this;
        }
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.sink, this.deferredName);
            this.deferredName = null;
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public JsonWriter value(String value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        if (this.promoteValueToName) {
            return name(value);
        }
        writeDeferredName();
        beforeValue();
        string(this.sink, value);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue();
        this.sink.writeUtf8("null");
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public JsonWriter value(boolean value) throws IOException {
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(value ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public JsonWriter value(double value) throws IOException {
        if (!this.lenient && (Double.isNaN(value) || Double.isInfinite(value))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        } else if (this.promoteValueToName) {
            return name(Double.toString(value));
        } else {
            writeDeferredName();
            beforeValue();
            this.sink.writeUtf8(Double.toString(value));
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public JsonWriter value(long value) throws IOException {
        if (this.promoteValueToName) {
            return name(Long.toString(value));
        }
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(Long.toString(value));
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    /* Debug info: failed to restart local var, previous not found, register: 4 */
    public JsonWriter value(Number value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        String string = value.toString();
        if (!this.lenient && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        } else if (this.promoteValueToName) {
            return name(string);
        } else {
            writeDeferredName();
            beforeValue();
            this.sink.writeUtf8(string);
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this;
        }
    }

    public void flush() throws IOException {
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.sink.flush();
    }

    public void close() throws IOException {
        this.sink.close();
        int size = this.stackSize;
        if (size > 1 || (size == 1 && this.scopes[size - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void string(okio.BufferedSink r8, java.lang.String r9) throws java.io.IOException {
        /*
            r7 = 34
            java.lang.String[] r5 = REPLACEMENT_CHARS
            r8.writeByte(r7)
            r2 = 0
            int r3 = r9.length()
            r1 = 0
        L_0x000d:
            if (r1 >= r3) goto L_0x0038
            char r0 = r9.charAt(r1)
            r6 = 128(0x80, float:1.794E-43)
            if (r0 >= r6) goto L_0x001e
            r4 = r5[r0]
            if (r4 != 0) goto L_0x0025
        L_0x001b:
            int r1 = r1 + 1
            goto L_0x000d
        L_0x001e:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r0 != r6) goto L_0x0030
            java.lang.String r4 = "\\u2028"
        L_0x0025:
            if (r2 >= r1) goto L_0x002a
            r8.writeUtf8(r9, r2, r1)
        L_0x002a:
            r8.writeUtf8(r4)
            int r2 = r1 + 1
            goto L_0x001b
        L_0x0030:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r0 != r6) goto L_0x001b
            java.lang.String r4 = "\\u2029"
            goto L_0x0025
        L_0x0038:
            if (r2 >= r3) goto L_0x003d
            r8.writeUtf8(r9, r2, r3)
        L_0x003d:
            r8.writeByte(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.JsonUtf8Writer.string(okio.BufferedSink, java.lang.String):void");
    }

    private void newline() throws IOException {
        if (this.indent != null) {
            this.sink.writeByte(10);
            int size = this.stackSize;
            for (int i = 1; i < size; i++) {
                this.sink.writeUtf8(this.indent);
            }
        }
    }

    private void beforeName() throws IOException {
        int context = peekScope();
        if (context == 5) {
            this.sink.writeByte(44);
        } else if (context != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    private void beforeValue() throws IOException {
        switch (peekScope()) {
            case 1:
                replaceTop(2);
                newline();
                return;
            case 2:
                this.sink.writeByte(44);
                newline();
                return;
            case 4:
                this.sink.writeUtf8(this.separator);
                replaceTop(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.lenient) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        replaceTop(7);
    }
}
