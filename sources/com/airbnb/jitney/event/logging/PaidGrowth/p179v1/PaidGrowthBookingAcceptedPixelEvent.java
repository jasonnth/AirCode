package com.airbnb.jitney.event.logging.PaidGrowth.p179v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaidGrowth.v1.PaidGrowthBookingAcceptedPixelEvent */
public final class PaidGrowthBookingAcceptedPixelEvent implements Struct {
    public static final Adapter<PaidGrowthBookingAcceptedPixelEvent, Object> ADAPTER = new PaidGrowthBookingAcceptedPixelEventAdapter();
    public final Context context;
    public final String device_id;
    public final String event_name;
    public final Boolean is_first_time;
    public final Long listing_id;
    public final Long n_nights;
    public final C2451Operation operation;
    public final Long request_id;
    public final String schema;
    public final Double total_fees;

    /* renamed from: com.airbnb.jitney.event.logging.PaidGrowth.v1.PaidGrowthBookingAcceptedPixelEvent$PaidGrowthBookingAcceptedPixelEventAdapter */
    private static final class PaidGrowthBookingAcceptedPixelEventAdapter implements Adapter<PaidGrowthBookingAcceptedPixelEvent, Object> {
        private PaidGrowthBookingAcceptedPixelEventAdapter() {
        }

        public void write(Protocol protocol, PaidGrowthBookingAcceptedPixelEvent struct) throws IOException {
            protocol.writeStructBegin("PaidGrowthBookingAcceptedPixelEvent");
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
            protocol.writeFieldBegin("n_nights", 6, 10);
            protocol.writeI64(struct.n_nights.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_first_time", 7, 2);
            protocol.writeBool(struct.is_first_time.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("total_fees", 8, 4);
            protocol.writeDouble(struct.total_fees.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("request_id", 9, 10);
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
        if (!(other instanceof PaidGrowthBookingAcceptedPixelEvent)) {
            return false;
        }
        PaidGrowthBookingAcceptedPixelEvent that = (PaidGrowthBookingAcceptedPixelEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.device_id == that.device_id || this.device_id.equals(that.device_id)) && ((this.n_nights == that.n_nights || this.n_nights.equals(that.n_nights)) && ((this.is_first_time == that.is_first_time || this.is_first_time.equals(that.is_first_time)) && ((this.total_fees == that.total_fees || this.total_fees.equals(that.total_fees)) && (this.request_id == that.request_id || this.request_id.equals(that.request_id))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.device_id.hashCode()) * -2128831035) ^ this.n_nights.hashCode()) * -2128831035) ^ this.is_first_time.hashCode()) * -2128831035) ^ this.total_fees.hashCode()) * -2128831035) ^ this.request_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaidGrowthBookingAcceptedPixelEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", device_id=" + this.device_id + ", n_nights=" + this.n_nights + ", is_first_time=" + this.is_first_time + ", total_fees=" + this.total_fees + ", request_id=" + this.request_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
