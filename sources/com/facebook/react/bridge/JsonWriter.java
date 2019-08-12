package com.facebook.react.bridge;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.Deque;

public class JsonWriter implements Closeable {
    private final Deque<Scope> mScopes = new ArrayDeque();
    private final Writer mWriter;

    private enum Scope {
        EMPTY_OBJECT,
        OBJECT,
        EMPTY_ARRAY,
        ARRAY
    }

    public JsonWriter(Writer writer) {
        this.mWriter = writer;
    }

    public JsonWriter beginArray() throws IOException {
        open(Scope.EMPTY_ARRAY, '[');
        return this;
    }

    public JsonWriter endArray() throws IOException {
        close(']');
        return this;
    }

    public JsonWriter beginObject() throws IOException {
        open(Scope.EMPTY_OBJECT, '{');
        return this;
    }

    public JsonWriter endObject() throws IOException {
        close('}');
        return this;
    }

    public JsonWriter name(String name) throws IOException {
        if (name == null) {
            throw new NullPointerException("name can not be null");
        }
        beforeName();
        string(name);
        this.mWriter.write(58);
        return this;
    }

    /* Debug info: failed to restart local var, previous not found, register: 0 */
    public JsonWriter value(String value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        beforeValue();
        string(value);
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        beforeValue();
        this.mWriter.write("null");
        return this;
    }

    public JsonWriter rawValue(String json) throws IOException {
        beforeValue();
        this.mWriter.write(json);
        return this;
    }

    public JsonWriter value(boolean value) throws IOException {
        beforeValue();
        this.mWriter.write(value ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
        return this;
    }

    public JsonWriter value(double value) throws IOException {
        beforeValue();
        this.mWriter.append(Double.toString(value));
        return this;
    }

    public JsonWriter value(long value) throws IOException {
        beforeValue();
        this.mWriter.write(Long.toString(value));
        return this;
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public JsonWriter value(Number value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        beforeValue();
        this.mWriter.append(value.toString());
        return this;
    }

    public void close() throws IOException {
        this.mWriter.close();
    }

    private void beforeValue() throws IOException {
        Scope scope = (Scope) this.mScopes.peek();
        switch (scope) {
            case EMPTY_ARRAY:
                replace(Scope.ARRAY);
                return;
            case EMPTY_OBJECT:
                throw new IllegalArgumentException(Scope.EMPTY_OBJECT.name());
            case ARRAY:
                this.mWriter.write(44);
                return;
            case OBJECT:
                return;
            default:
                throw new IllegalStateException("Unknown scope: " + scope);
        }
    }

    private void beforeName() throws IOException {
        Scope scope = (Scope) this.mScopes.peek();
        switch (scope) {
            case EMPTY_ARRAY:
            case ARRAY:
                throw new IllegalStateException("name not allowed in array");
            case EMPTY_OBJECT:
                replace(Scope.OBJECT);
                return;
            case OBJECT:
                this.mWriter.write(44);
                return;
            default:
                throw new IllegalStateException("Unknown scope: " + scope);
        }
    }

    private void open(Scope scope, char bracket) throws IOException {
        this.mScopes.push(scope);
        this.mWriter.write(bracket);
    }

    private void close(char bracket) throws IOException {
        this.mScopes.pop();
        this.mWriter.write(bracket);
    }

    private void string(String string) throws IOException {
        this.mWriter.write(34);
        int length = string.length();
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            switch (c) {
                case 8:
                    this.mWriter.write("\\b");
                    break;
                case 9:
                    this.mWriter.write("\\t");
                    break;
                case 10:
                    this.mWriter.write("\\n");
                    break;
                case 12:
                    this.mWriter.write("\\f");
                    break;
                case 13:
                    this.mWriter.write("\\r");
                    break;
                case '\"':
                case '\\':
                    this.mWriter.write(92);
                    this.mWriter.write(c);
                    break;
                case 8232:
                case 8233:
                    this.mWriter.write(String.format("\\u%04x", new Object[]{Integer.valueOf(c)}));
                    break;
                default:
                    if (c > 31) {
                        this.mWriter.write(c);
                        break;
                    } else {
                        this.mWriter.write(String.format("\\u%04x", new Object[]{Integer.valueOf(c)}));
                        break;
                    }
            }
        }
        this.mWriter.write(34);
    }

    private void replace(Scope scope) {
        this.mScopes.pop();
        this.mScopes.push(scope);
    }
}
