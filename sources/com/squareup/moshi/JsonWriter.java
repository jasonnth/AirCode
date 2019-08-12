package com.squareup.moshi;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import okio.BufferedSink;

public abstract class JsonWriter implements Closeable, Flushable {
    String indent;
    boolean lenient;
    final int[] pathIndices = new int[32];
    final String[] pathNames = new String[32];
    boolean promoteValueToName;
    final int[] scopes = new int[32];
    boolean serializeNulls;
    int stackSize = 0;

    public abstract JsonWriter beginArray() throws IOException;

    public abstract JsonWriter beginObject() throws IOException;

    public abstract JsonWriter endArray() throws IOException;

    public abstract JsonWriter endObject() throws IOException;

    public abstract JsonWriter name(String str) throws IOException;

    public abstract JsonWriter nullValue() throws IOException;

    public abstract JsonWriter value(double d) throws IOException;

    public abstract JsonWriter value(long j) throws IOException;

    public abstract JsonWriter value(Number number) throws IOException;

    public abstract JsonWriter value(String str) throws IOException;

    public abstract JsonWriter value(boolean z) throws IOException;

    /* renamed from: of */
    public static JsonWriter m2492of(BufferedSink sink) {
        return new JsonUtf8Writer(sink);
    }

    JsonWriter() {
    }

    /* access modifiers changed from: 0000 */
    public final int peekScope() {
        if (this.stackSize != 0) {
            return this.scopes[this.stackSize - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* access modifiers changed from: 0000 */
    public final void pushScope(int newTop) {
        if (this.stackSize == this.scopes.length) {
            throw new JsonDataException("Nesting too deep at " + getPath() + ": circular reference?");
        }
        int[] iArr = this.scopes;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = newTop;
    }

    /* access modifiers changed from: 0000 */
    public final void replaceTop(int topOfStack) {
        this.scopes[this.stackSize - 1] = topOfStack;
    }

    /* access modifiers changed from: 0000 */
    public final void promoteValueToName() throws IOException {
        int context = peekScope();
        if (context == 5 || context == 3) {
            this.promoteValueToName = true;
            return;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    public final String getPath() {
        return JsonScope.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }
}
