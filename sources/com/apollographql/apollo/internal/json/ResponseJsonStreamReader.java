package com.apollographql.apollo.internal.json;

import com.apollographql.apollo.internal.json.JsonReader.Token;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResponseJsonStreamReader {
    private final JsonReader jsonReader;

    public interface ListReader<T> {
        T read(ResponseJsonStreamReader responseJsonStreamReader) throws IOException;
    }

    public interface ObjectReader<T> {
        T read(ResponseJsonStreamReader responseJsonStreamReader) throws IOException;
    }

    ResponseJsonStreamReader(JsonReader jsonReader2) {
        this.jsonReader = jsonReader2;
    }

    public boolean hasNext() throws IOException {
        return this.jsonReader.hasNext();
    }

    public String nextName() throws IOException {
        return this.jsonReader.nextName();
    }

    public void skipNext() throws IOException {
        this.jsonReader.skipValue();
    }

    public String nextString(boolean optional) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() != Token.NULL) {
            return this.jsonReader.nextString();
        }
        this.jsonReader.skipValue();
        return null;
    }

    public Boolean nextBoolean(boolean optional) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() != Token.NULL) {
            return Boolean.valueOf(this.jsonReader.nextBoolean());
        }
        this.jsonReader.skipValue();
        return null;
    }

    public <T> T nextObject(boolean optional, ObjectReader<T> objectReader) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() == Token.NULL) {
            this.jsonReader.skipValue();
            return null;
        }
        this.jsonReader.beginObject();
        T read = objectReader.read(this);
        this.jsonReader.endObject();
        return read;
    }

    public <T> List<T> nextList(boolean optional, ListReader<T> listReader) throws IOException {
        checkNextValue(optional);
        if (this.jsonReader.peek() == Token.NULL) {
            this.jsonReader.skipValue();
            return null;
        }
        List<T> result = new ArrayList<>();
        this.jsonReader.beginArray();
        while (this.jsonReader.hasNext()) {
            result.add(listReader.read(this));
        }
        this.jsonReader.endArray();
        return result;
    }

    public Object nextScalar(boolean optional) throws IOException {
        checkNextValue(optional);
        if (isNextNull()) {
            skipNext();
            return null;
        } else if (isNextBoolean()) {
            return nextBoolean(false);
        } else {
            if (isNextNumber()) {
                return new BigDecimal(nextString(false));
            }
            return nextString(false);
        }
    }

    public Map<String, Object> toMap() throws IOException {
        if (isNextObject()) {
            return readObject(this);
        }
        Map<String, Object> result = new LinkedHashMap<>();
        while (hasNext()) {
            String name = nextName();
            if (isNextNull()) {
                skipNext();
                result.put(name, null);
            } else if (isNextObject()) {
                result.put(name, readObject(this));
            } else if (isNextList()) {
                result.put(name, readScalarList(this));
            } else {
                result.put(name, nextScalar(true));
            }
        }
        return result;
    }

    /* access modifiers changed from: private */
    public boolean isNextObject() throws IOException {
        return this.jsonReader.peek() == Token.BEGIN_OBJECT;
    }

    private boolean isNextList() throws IOException {
        return this.jsonReader.peek() == Token.BEGIN_ARRAY;
    }

    private boolean isNextNull() throws IOException {
        return this.jsonReader.peek() == Token.NULL;
    }

    private boolean isNextBoolean() throws IOException {
        return this.jsonReader.peek() == Token.BOOLEAN;
    }

    private boolean isNextNumber() throws IOException {
        return this.jsonReader.peek() == Token.NUMBER;
    }

    private void checkNextValue(boolean optional) throws IOException {
        if (!optional && this.jsonReader.peek() == Token.NULL) {
            throw new NullPointerException("corrupted response reader, expected non null value");
        }
    }

    /* access modifiers changed from: private */
    public Map<String, Object> readObject(ResponseJsonStreamReader streamReader) throws IOException {
        return (Map) streamReader.nextObject(false, new ObjectReader<Map<String, Object>>() {
            public Map<String, Object> read(ResponseJsonStreamReader streamReader) throws IOException {
                return streamReader.toMap();
            }
        });
    }

    private List<?> readScalarList(final ResponseJsonStreamReader streamReader) throws IOException {
        return streamReader.nextList(false, new ListReader<Object>() {
            public Object read(ResponseJsonStreamReader reader) throws IOException {
                if (streamReader.isNextObject()) {
                    return ResponseJsonStreamReader.this.readObject(reader);
                }
                return reader.nextScalar(true);
            }
        });
    }
}
