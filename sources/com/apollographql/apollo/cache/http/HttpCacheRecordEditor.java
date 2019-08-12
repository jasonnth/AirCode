package com.apollographql.apollo.cache.http;

import java.io.IOException;
import okio.Sink;

public interface HttpCacheRecordEditor {
    void abort() throws IOException;

    Sink bodySink();

    void commit() throws IOException;

    Sink headerSink();
}
