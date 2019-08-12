package com.bugsnag.android;

import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.bugsnag.android.JsonStream.Streamable;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.spongycastle.crypto.tls.CipherSuite;

class Breadcrumbs implements Streamable {
    private int maxSize = 20;
    final Queue<Breadcrumb> store = new ConcurrentLinkedQueue();

    private static class Breadcrumb implements Streamable {
        private final String METADATA_KEY;
        private final String NAME_KEY;
        private final String TIMESTAMP_KEY;
        private final String TYPE_KEY;
        final Map<String, String> metadata;
        final String name;
        final String timestamp;
        final BreadcrumbType type;

        Breadcrumb(String message) {
            this.TIMESTAMP_KEY = ErfExperimentsModel.TIMESTAMP;
            this.NAME_KEY = "name";
            this.METADATA_KEY = "metaData";
            this.TYPE_KEY = "type";
            this.timestamp = DateUtils.toISO8601(new Date());
            this.type = BreadcrumbType.MANUAL;
            this.metadata = Collections.singletonMap("message", message.substring(0, Math.min(message.length(), CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA)));
            this.name = FindTweenAnalytics.SEARCH_TYPE_MANUAL;
        }

        Breadcrumb(String name2, BreadcrumbType type2, Map<String, String> metadata2) {
            this.TIMESTAMP_KEY = ErfExperimentsModel.TIMESTAMP;
            this.NAME_KEY = "name";
            this.METADATA_KEY = "metaData";
            this.TYPE_KEY = "type";
            this.timestamp = DateUtils.toISO8601(new Date());
            this.type = type2;
            this.metadata = metadata2;
            this.name = name2;
        }

        public void toStream(JsonStream writer) throws IOException {
            writer.beginObject();
            writer.name(ErfExperimentsModel.TIMESTAMP).value(this.timestamp);
            writer.name("name").value(this.name);
            writer.name("type").value(this.type.toString());
            writer.name("metaData");
            writer.beginObject();
            for (Entry<String, String> entry : this.metadata.entrySet()) {
                writer.name((String) entry.getKey()).value((String) entry.getValue());
            }
            writer.endObject();
            writer.endObject();
        }

        public int payloadSize() throws IOException {
            StringWriter writer = new StringWriter();
            toStream(new JsonStream(writer));
            return writer.toString().length();
        }
    }

    Breadcrumbs() {
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginArray();
        for (Breadcrumb breadcrumb : this.store) {
            breadcrumb.toStream(writer);
        }
        writer.endArray();
    }

    /* access modifiers changed from: 0000 */
    public void add(String message) {
        addToStore(new Breadcrumb(message));
    }

    /* access modifiers changed from: 0000 */
    public void add(String name, BreadcrumbType type, Map<String, String> metadata) {
        addToStore(new Breadcrumb(name, type, metadata));
    }

    /* access modifiers changed from: 0000 */
    public void setSize(int size) {
        if (size > this.store.size()) {
            this.maxSize = size;
            return;
        }
        while (this.store.size() > size) {
            this.store.poll();
        }
    }

    private void addToStore(Breadcrumb breadcrumb) {
        try {
            if (breadcrumb.payloadSize() > 4096) {
                Logger.warn("Dropping breadcrumb because payload exceeds 4KB limit");
                return;
            }
            if (this.store.size() >= this.maxSize) {
                this.store.poll();
            }
            this.store.add(breadcrumb);
        } catch (IOException ex) {
            Logger.warn("Dropping breadcrumb because it could not be serialized", ex);
        }
    }
}
