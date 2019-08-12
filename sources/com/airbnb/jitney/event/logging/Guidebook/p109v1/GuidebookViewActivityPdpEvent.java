package com.airbnb.jitney.event.logging.Guidebook.p109v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookViewActivityPdpEvent */
public final class GuidebookViewActivityPdpEvent implements Struct {
    public static final Adapter<GuidebookViewActivityPdpEvent, Builder> ADAPTER = new GuidebookViewActivityPdpEventAdapter();
    public final Long activity_id;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;

    /* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookViewActivityPdpEvent$Builder */
    public static final class Builder implements StructBuilder<GuidebookViewActivityPdpEvent> {
        /* access modifiers changed from: private */
        public Long activity_id;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Guidebook:GuidebookViewActivityPdpEvent:1.0.0";
            this.event_name = "guidebook_view_activity_pdp";
            this.page = "activity_pdp";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, Long activity_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Guidebook:GuidebookViewActivityPdpEvent:1.0.0";
            this.event_name = "guidebook_view_activity_pdp";
            this.context = context2;
            this.page = "activity_pdp";
            this.activity_id = activity_id2;
            this.operation = C2451Operation.Impression;
        }

        public GuidebookViewActivityPdpEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.activity_id == null) {
                throw new IllegalStateException("Required field 'activity_id' is missing");
            } else if (this.operation != null) {
                return new GuidebookViewActivityPdpEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookViewActivityPdpEvent$GuidebookViewActivityPdpEventAdapter */
    private static final class GuidebookViewActivityPdpEventAdapter implements Adapter<GuidebookViewActivityPdpEvent, Builder> {
        private GuidebookViewActivityPdpEventAdapter() {
        }

        public void write(Protocol protocol, GuidebookViewActivityPdpEvent struct) throws IOException {
            protocol.writeStructBegin("GuidebookViewActivityPdpEvent");
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
            protocol.writeFieldBegin("activity_id", 4, 10);
            protocol.writeI64(struct.activity_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            if (struct.search_context != null) {
                protocol.writeFieldBegin("search_context", 6, PassportService.SF_DG12);
                C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private GuidebookViewActivityPdpEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.activity_id = builder.activity_id;
        this.operation = builder.operation;
        this.search_context = builder.search_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GuidebookViewActivityPdpEvent)) {
            return false;
        }
        GuidebookViewActivityPdpEvent that = (GuidebookViewActivityPdpEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.activity_id == that.activity_id || this.activity_id.equals(that.activity_id)) && (this.operation == that.operation || this.operation.equals(that.operation))))))) {
            if (this.search_context == that.search_context) {
                return true;
            }
            if (this.search_context != null && this.search_context.equals(that.search_context)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.activity_id.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.search_context != null) {
            i = this.search_context.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "GuidebookViewActivityPdpEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", activity_id=" + this.activity_id + ", operation=" + this.operation + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
