package com.airbnb.jitney.event.logging.Guidebook.p109v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookClickActivityPdpAddItineraryEvent */
public final class GuidebookClickActivityPdpAddItineraryEvent implements Struct {
    public static final Adapter<GuidebookClickActivityPdpAddItineraryEvent, Builder> ADAPTER = new GuidebookClickActivityPdpAddItineraryEventAdapter();
    public final Long activity_id;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookClickActivityPdpAddItineraryEvent$Builder */
    public static final class Builder implements StructBuilder<GuidebookClickActivityPdpAddItineraryEvent> {
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
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Guidebook:GuidebookClickActivityPdpAddItineraryEvent:1.0.0";
            this.event_name = "guidebook_click_activity_pdp_add_itinerary";
            this.page = "activity_pdp";
            this.operation = C2451Operation.Click;
            this.target = "add_itinerary_button";
        }

        public Builder(Context context2, Long activity_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Guidebook:GuidebookClickActivityPdpAddItineraryEvent:1.0.0";
            this.event_name = "guidebook_click_activity_pdp_add_itinerary";
            this.context = context2;
            this.page = "activity_pdp";
            this.operation = C2451Operation.Click;
            this.target = "add_itinerary_button";
            this.activity_id = activity_id2;
        }

        public GuidebookClickActivityPdpAddItineraryEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.activity_id != null) {
                return new GuidebookClickActivityPdpAddItineraryEvent(this);
            } else {
                throw new IllegalStateException("Required field 'activity_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookClickActivityPdpAddItineraryEvent$GuidebookClickActivityPdpAddItineraryEventAdapter */
    private static final class GuidebookClickActivityPdpAddItineraryEventAdapter implements Adapter<GuidebookClickActivityPdpAddItineraryEvent, Builder> {
        private GuidebookClickActivityPdpAddItineraryEventAdapter() {
        }

        public void write(Protocol protocol, GuidebookClickActivityPdpAddItineraryEvent struct) throws IOException {
            protocol.writeStructBegin("GuidebookClickActivityPdpAddItineraryEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("activity_id", 6, 10);
            protocol.writeI64(struct.activity_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private GuidebookClickActivityPdpAddItineraryEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.target = builder.target;
        this.activity_id = builder.activity_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GuidebookClickActivityPdpAddItineraryEvent)) {
            return false;
        }
        GuidebookClickActivityPdpAddItineraryEvent that = (GuidebookClickActivityPdpAddItineraryEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && (this.activity_id == that.activity_id || this.activity_id.equals(that.activity_id)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.activity_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "GuidebookClickActivityPdpAddItineraryEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", target=" + this.target + ", activity_id=" + this.activity_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
