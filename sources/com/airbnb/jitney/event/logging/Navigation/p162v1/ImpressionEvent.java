package com.airbnb.jitney.event.logging.Navigation.p162v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Navigation.v1.ImpressionEvent */
public final class ImpressionEvent implements Struct {
    public static final Adapter<ImpressionEvent, Builder> ADAPTER = new ImpressionEventAdapter();
    public final Context context;
    public final String event_name;
    public final Map<String, String> info;
    public final String page;
    public final String referrer;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Navigation.v1.ImpressionEvent$Builder */
    public static final class Builder implements StructBuilder<ImpressionEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "impression";
        /* access modifiers changed from: private */
        public Map<String, String> info;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String referrer;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Navigation:ImpressionEvent:1.0.0";

        private Builder() {
        }

        public Builder(Context context2, String page2, String referrer2) {
            this.context = context2;
            this.page = page2;
            this.referrer = referrer2;
        }

        public Builder info(Map<String, String> info2) {
            this.info = info2;
            return this;
        }

        public ImpressionEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.referrer != null) {
                return new ImpressionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'referrer' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Navigation.v1.ImpressionEvent$ImpressionEventAdapter */
    private static final class ImpressionEventAdapter implements Adapter<ImpressionEvent, Builder> {
        private ImpressionEventAdapter() {
        }

        public void write(Protocol protocol, ImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("ImpressionEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("event_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 2, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 3, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("referrer", 4, PassportService.SF_DG11);
            protocol.writeString(struct.referrer);
            protocol.writeFieldEnd();
            if (struct.info != null) {
                protocol.writeFieldBegin("info", 5, 13);
                protocol.writeMapBegin(PassportService.SF_DG11, PassportService.SF_DG11, struct.info.size());
                for (Entry<String, String> entry0 : struct.info.entrySet()) {
                    String value0 = (String) entry0.getValue();
                    protocol.writeString((String) entry0.getKey());
                    protocol.writeString(value0);
                }
                protocol.writeMapEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ImpressionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.referrer = builder.referrer;
        this.info = builder.info == null ? null : Collections.unmodifiableMap(builder.info);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ImpressionEvent)) {
            return false;
        }
        ImpressionEvent that = (ImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && (this.referrer == that.referrer || this.referrer.equals(that.referrer)))))) {
            if (this.info == that.info) {
                return true;
            }
            if (this.info != null && this.info.equals(that.info)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.referrer.hashCode()) * -2128831035;
        if (this.info != null) {
            i = this.info.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", referrer=" + this.referrer + ", info=" + this.info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
