package com.airbnb.jitney.event.logging.GuestCheckIn.p106v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestGetStartedEvent */
public final class GuestCheckInCheckinGuideGuestGetStartedEvent implements Struct {
    public static final Adapter<GuestCheckInCheckinGuideGuestGetStartedEvent, Object> ADAPTER = new GuestCheckInCheckinGuideGuestGetStartedEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestGetStartedEvent$GuestCheckInCheckinGuideGuestGetStartedEventAdapter */
    private static final class GuestCheckInCheckinGuideGuestGetStartedEventAdapter implements Adapter<GuestCheckInCheckinGuideGuestGetStartedEvent, Object> {
        private GuestCheckInCheckinGuideGuestGetStartedEventAdapter() {
        }

        public void write(Protocol protocol, GuestCheckInCheckinGuideGuestGetStartedEvent struct) throws IOException {
            protocol.writeStructBegin("GuestCheckInCheckinGuideGuestGetStartedEvent");
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
            protocol.writeFieldBegin("user_id", 6, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GuestCheckInCheckinGuideGuestGetStartedEvent)) {
            return false;
        }
        GuestCheckInCheckinGuideGuestGetStartedEvent that = (GuestCheckInCheckinGuideGuestGetStartedEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.user_id == that.user_id || this.user_id.equals(that.user_id)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "GuestCheckInCheckinGuideGuestGetStartedEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", user_id=" + this.user_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
