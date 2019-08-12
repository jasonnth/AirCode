package com.airbnb.jitney.event.logging.Guidebook.p109v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PlacePdpType.p201v2.C2560PlacePdpType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookClickPlacePdpMeetupJoinEvent */
public final class GuidebookClickPlacePdpMeetupJoinEvent implements Struct {
    public static final Adapter<GuidebookClickPlacePdpMeetupJoinEvent, Builder> ADAPTER = new GuidebookClickPlacePdpMeetupJoinEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Long place_id;
    public final C2560PlacePdpType place_pdp_type;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookClickPlacePdpMeetupJoinEvent$Builder */
    public static final class Builder implements StructBuilder<GuidebookClickPlacePdpMeetupJoinEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Long place_id;
        /* access modifiers changed from: private */
        public C2560PlacePdpType place_pdp_type;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Guidebook:GuidebookClickPlacePdpMeetupJoinEvent:1.0.0";
            this.event_name = "guidebook_click_place_pdp_meetup_join";
            this.page = "place_pdp";
            this.operation = C2451Operation.Click;
            this.target = "join_meetup";
        }

        public Builder(Context context2, Long place_id2, C2560PlacePdpType place_pdp_type2) {
            this.schema = "com.airbnb.jitney.event.logging.Guidebook:GuidebookClickPlacePdpMeetupJoinEvent:1.0.0";
            this.event_name = "guidebook_click_place_pdp_meetup_join";
            this.context = context2;
            this.page = "place_pdp";
            this.operation = C2451Operation.Click;
            this.target = "join_meetup";
            this.place_id = place_id2;
            this.place_pdp_type = place_pdp_type2;
        }

        public GuidebookClickPlacePdpMeetupJoinEvent build() {
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
            } else if (this.place_id == null) {
                throw new IllegalStateException("Required field 'place_id' is missing");
            } else if (this.place_pdp_type != null) {
                return new GuidebookClickPlacePdpMeetupJoinEvent(this);
            } else {
                throw new IllegalStateException("Required field 'place_pdp_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Guidebook.v1.GuidebookClickPlacePdpMeetupJoinEvent$GuidebookClickPlacePdpMeetupJoinEventAdapter */
    private static final class GuidebookClickPlacePdpMeetupJoinEventAdapter implements Adapter<GuidebookClickPlacePdpMeetupJoinEvent, Builder> {
        private GuidebookClickPlacePdpMeetupJoinEventAdapter() {
        }

        public void write(Protocol protocol, GuidebookClickPlacePdpMeetupJoinEvent struct) throws IOException {
            protocol.writeStructBegin("GuidebookClickPlacePdpMeetupJoinEvent");
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
            protocol.writeFieldBegin("place_id", 6, 10);
            protocol.writeI64(struct.place_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("place_pdp_type", 7, 8);
            protocol.writeI32(struct.place_pdp_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private GuidebookClickPlacePdpMeetupJoinEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.target = builder.target;
        this.place_id = builder.place_id;
        this.place_pdp_type = builder.place_pdp_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GuidebookClickPlacePdpMeetupJoinEvent)) {
            return false;
        }
        GuidebookClickPlacePdpMeetupJoinEvent that = (GuidebookClickPlacePdpMeetupJoinEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.place_id == that.place_id || this.place_id.equals(that.place_id)) && (this.place_pdp_type == that.place_pdp_type || this.place_pdp_type.equals(that.place_pdp_type))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.place_id.hashCode()) * -2128831035) ^ this.place_pdp_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "GuidebookClickPlacePdpMeetupJoinEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", target=" + this.target + ", place_id=" + this.place_id + ", place_pdp_type=" + this.place_pdp_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
