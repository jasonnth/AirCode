package com.apollographql.apollo.internal.cache.http;

import java.io.IOException;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;

abstract class ResponseBodyCacheSink extends ForwardingSink {
    private boolean failed;

    /* access modifiers changed from: 0000 */
    public abstract void onException(Exception exc);

    ResponseBodyCacheSink(BufferedSink delegate) {
        super(delegate);
    }

    public void write(Buffer source, long byteCount) throws IOException {
        if (!this.failed) {
            try {
                super.write(source, byteCount);
            } catch (Exception e) {
                this.failed = true;
                onException(e);
            }
        }
    }

    public void flush() throws IOException {
        if (!this.failed) {
            try {
                super.flush();
            } catch (Exception e) {
                this.failed = true;
                onException(e);
            }
        }
    }

    public void close() throws IOException {
        if (!this.failed) {
            try {
                super.close();
            } catch (Exception e) {
                this.failed = true;
                onException(e);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void copyFrom(Buffer buffer, long offset, long bytesCount) {
        if (!this.failed) {
            try {
                BufferedSink outSink = (BufferedSink) delegate();
                buffer.copyTo(outSink.buffer(), offset, bytesCount);
                outSink.emitCompleteSegments();
            } catch (Exception e) {
                this.failed = true;
                onException(e);
            }
        }
    }
}
