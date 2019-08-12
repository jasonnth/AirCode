package com.airbnb.jitney.event.logging.PaidGrowth.p180v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentsContext.p189v1.C2543PaymentsContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaidGrowth.v2.PaidGrowthBookingRequestPixelEvent */
public final class PaidGrowthBookingRequestPixelEvent implements Struct {
    public static final Adapter<PaidGrowthBookingRequestPixelEvent, Object> ADAPTER = new PaidGrowthBookingRequestPixelEventAdapter();
    public final Context context;
    public final String device_id;
    public final String event_name;
    public final Boolean is_first_time;
    public final Long listing_id;
    public final Long n_nights;
    public final C2451Operation operation;
    public final C2543PaymentsContext payments_context;
    public final Long request_id;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.PaidGrowth.v2.PaidGrowthBookingRequestPixelEvent$PaidGrowthBookingRequestPixelEventAdapter */
    private static final class PaidGrowthBookingRequestPixelEventAdapter implements Adapter<PaidGrowthBookingRequestPixelEvent, Object> {
        private PaidGrowthBookingRequestPixelEventAdapter() {
        }

        public void write(Protocol protocol, PaidGrowthBookingRequestPixelEvent struct) throws IOException {
            protocol.writeStructBegin("PaidGrowthBookingRequestPixelEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 3, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 4, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("device_id", 5, PassportService.SF_DG11);
            protocol.writeString(struct.device_id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_id", 6, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("n_nights", 7, 10);
            protocol.writeI64(struct.n_nights.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_first_time", 8, 2);
            protocol.writeBool(struct.is_first_time.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payments_context", 9, PassportService.SF_DG12);
            C2543PaymentsContext.ADAPTER.write(protocol, struct.payments_context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("request_id", 10, 10);
            protocol.writeI64(struct.request_id.longValue());
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
        if (!(other instanceof PaidGrowthBookingRequestPixelEvent)) {
            return false;
        }
        PaidGrowthBookingRequestPixelEvent that = (PaidGrowthBookingRequestPixelEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.device_id == that.device_id || this.device_id.equals(that.device_id)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.n_nights == that.n_nights || this.n_nights.equals(that.n_nights)) && ((this.is_first_time == that.is_first_time || this.is_first_time.equals(that.is_first_time)) && ((this.payments_context == that.payments_context || this.payments_context.equals(that.payments_context)) && (this.request_id == that.request_id || this.request_id.equals(that.request_id)))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.device_id.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.n_nights.hashCode()) * -2128831035) ^ this.is_first_time.hashCode()) * -2128831035) ^ this.payments_context.hashCode()) * -2128831035) ^ this.request_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaidGrowthBookingRequestPixelEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", device_id=" + this.device_id + ", user_id=" + this.user_id + ", n_nights=" + this.n_nights + ", is_first_time=" + this.is_first_time + ", payments_context=" + this.payments_context + ", request_id=" + this.request_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
