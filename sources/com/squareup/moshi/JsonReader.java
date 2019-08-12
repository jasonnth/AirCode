package com.squareup.moshi;

import java.io.Closeable;
import java.io.IOException;
import okio.Buffer;
import okio.ByteString;

public abstract class JsonReader implements Closeable {

    public static final class Options {
        final okio.Options doubleQuoteSuffix;
        final String[] strings;

        private Options(String[] strings2, okio.Options doubleQuoteSuffix2) {
            this.strings = strings2;
            this.doubleQuoteSuffix = doubleQuoteSuffix2;
        }

        /* renamed from: of */
        public static Options m2491of(String... strings2) {
            try {
                ByteString[] result = new ByteString[strings2.length];
                Buffer buffer = new Buffer();
                for (int i = 0; i < strings2.length; i++) {
                    JsonUtf8Writer.string(buffer, strings2[i]);
                    buffer.readByte();
                    result[i] = buffer.readByteString();
                }
                return new Options((String[]) strings2.clone(), okio.Options.m3950of(result));
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }
}
