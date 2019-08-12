package com.bugsnag.android;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

public class JsonStream extends JsonWriter {
    private final Writer out;

    public interface Streamable {
        void toStream(JsonStream jsonStream) throws IOException;
    }

    JsonStream(Writer out2) {
        super(out2);
        setSerializeNulls(false);
        this.out = out2;
    }

    public JsonStream name(String name) throws IOException {
        super.name(name);
        return this;
    }

    public void value(Streamable streamable) throws IOException {
        if (streamable == null) {
            nullValue();
        } else {
            streamable.toStream(this);
        }
    }

    public void value(File file) throws IOException {
        super.flush();
        FileReader input = null;
        try {
            FileReader input2 = new FileReader(file);
            try {
                IOUtils.copy(input2, this.out);
                IOUtils.closeQuietly(input2);
                this.out.flush();
            } catch (Throwable th) {
                th = th;
                input = input2;
                IOUtils.closeQuietly(input);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly(input);
            throw th;
        }
    }
}
