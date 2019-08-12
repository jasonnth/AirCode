package com.airbnb.jitney.event.logging.GuestCheckIn.p106v1;

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

/* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestTapBottomDotsEvent */
public final class GuestCheckInCheckinGuideGuestTapBottomDotsEvent implements Struct {
    public static final Adapter<GuestCheckInCheckinGuideGuestTapBottomDotsEvent, Builder> ADAPTER = new GuestCheckInCheckinGuideGuestTapBottomDotsEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final Long step_index;

    /* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestTapBottomDotsEvent$Builder */
    public static final class Builder implements StructBuilder<GuestCheckInCheckinGuideGuestTapBottomDotsEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public Long step_index;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.GuestCheckIn:GuestCheckInCheckinGuideGuestTapBottomDotsEvent:1.0.0";
            this.event_name = "guestcheckin_checkin_guide_guest_tap_bottom_dots";
            this.page = "checkin_guide_guest_view";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, Long listing_id2, Long step_index2) {
            this.schema = "com.airbnb.jitney.event.logging.GuestCheckIn:GuestCheckInCheckinGuideGuestTapBottomDotsEvent:1.0.0";
            this.event_name = "guestcheckin_checkin_guide_guest_tap_bottom_dots";
            this.context = context2;
            this.page = "checkin_guide_guest_view";
            this.operation = C2451Operation.Click;
            this.listing_id = listing_id2;
            this.step_index = step_index2;
        }

        public GuestCheckInCheckinGuideGuestTapBottomDotsEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.step_index != null) {
                return new GuestCheckInCheckinGuideGuestTapBottomDotsEvent(this);
            } else {
                throw new IllegalStateException("Required field 'step_index' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestTapBottomDotsEvent$GuestCheckInCheckinGuideGuestTapBottomDotsEventAdapter */
    private static final class GuestCheckInCheckinGuideGuestTapBottomDotsEventAdapter implements Adapter<GuestCheckInCheckinGuideGuestTapBottomDotsEvent, Builder> {
        private GuestCheckInCheckinGuideGuestTapBottomDotsEventAdapter() {
        }

        public void write(Protocol protocol, GuestCheckInCheckinGuideGuestTapBottomDotsEvent struct) throws IOException {
            protocol.writeStructBegin("GuestCheckInCheckinGuideGuestTapBottomDotsEvent");
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
            protocol.writeFieldBegin("listing_id", 5, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("step_index", 6, 10);
            protocol.writeI64(struct.step_index.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private GuestCheckInCheckinGuideGuestTapBottomDotsEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.step_index = builder.step_index;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GuestCheckInCheckinGuideGuestTapBottomDotsEvent)) {
            return false;
        }
        GuestCheckInCheckinGuideGuestTapBottomDotsEvent that = (GuestCheckInCheckinGuideGuestTapBottomDotsEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.step_index == that.step_index || this.step_index.equals(that.step_index)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.step_index.hashCode()) * -2128831035;
    }

    public String toString() {
        return "GuestCheckInCheckinGuideGuestTapBottomDotsEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", step_index=" + this.step_index + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
