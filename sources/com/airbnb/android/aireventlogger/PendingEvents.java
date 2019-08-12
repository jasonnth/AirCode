package com.airbnb.android.aireventlogger;

import java.io.IOException;
import java.io.OutputStream;

public class PendingEvents {
    private final Data data;
    private final Metadata metadata;

    interface Data {
        int firstId();

        int lastId();

        int length();

        void writeTo(OutputStream outputStream) throws IOException;
    }

    static class Metadata {
        /* access modifiers changed from: private */
        public final CompressionType compressionType;
        /* access modifiers changed from: private */
        public final String contentType;
        /* access modifiers changed from: private */
        public final String endpoint;

        Metadata(String endpoint2, String contentType2, CompressionType compressionType2) {
            this.endpoint = endpoint2;
            this.contentType = contentType2;
            this.compressionType = compressionType2;
        }
    }

    public PendingEvents(Data data2, Metadata metadata2) {
        this.data = data2;
        this.metadata = metadata2;
    }

    /* access modifiers changed from: 0000 */
    public String endpoint() {
        return this.metadata.endpoint;
    }

    /* access modifiers changed from: 0000 */
    public String contentType() {
        return this.metadata.contentType;
    }

    /* access modifiers changed from: 0000 */
    public CompressionType compressionType() {
        return this.metadata.compressionType;
    }

    /* access modifiers changed from: 0000 */
    public int length() {
        return this.data.length();
    }

    public Data data() {
        return this.data;
    }

    /* access modifiers changed from: 0000 */
    public int firstId() {
        return this.data.firstId();
    }

    /* access modifiers changed from: 0000 */
    public int lastId() {
        return this.data.lastId();
    }
}
