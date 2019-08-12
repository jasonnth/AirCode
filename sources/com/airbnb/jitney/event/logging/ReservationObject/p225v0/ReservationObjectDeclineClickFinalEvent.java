package com.airbnb.jitney.event.logging.ReservationObject.p225v0;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ReservationObject.v0.ReservationObjectDeclineClickFinalEvent */
public final class ReservationObjectDeclineClickFinalEvent implements Struct {
    public static final Adapter<ReservationObjectDeclineClickFinalEvent, Builder> ADAPTER = new ReservationObjectDeclineClickFinalEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final String operation;
    public final String page;
    public final String reason;
    public final Long reservation_id;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.ReservationObject.v0.ReservationObjectDeclineClickFinalEvent$Builder */
    public static final class Builder implements StructBuilder<ReservationObjectDeclineClickFinalEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String reason;
        /* access modifiers changed from: private */
        public Long reservation_id;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.ReservationObject:ReservationObjectDeclineClickFinalEvent:0.1.0";
            this.event_name = "reservation_object_decline_final";
            this.page = "reservation_object";
            this.target = "decline";
            this.operation = "click";
        }

        public Builder(Context context2, String reason2, Long listing_id2, Long reservation_id2) {
            this.schema = "com.airbnb.jitney.event.logging.ReservationObject:ReservationObjectDeclineClickFinalEvent:0.1.0";
            this.event_name = "reservation_object_decline_final";
            this.context = context2;
            this.page = "reservation_object";
            this.target = "decline";
            this.operation = "click";
            this.reason = reason2;
            this.listing_id = listing_id2;
            this.reservation_id = reservation_id2;
        }

        public ReservationObjectDeclineClickFinalEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.reason == null) {
                throw new IllegalStateException("Required field 'reason' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.reservation_id != null) {
                return new ReservationObjectDeclineClickFinalEvent(this);
            } else {
                throw new IllegalStateException("Required field 'reservation_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.ReservationObject.v0.ReservationObjectDeclineClickFinalEvent$ReservationObjectDeclineClickFinalEventAdapter */
    private static final class ReservationObjectDeclineClickFinalEventAdapter implements Adapter<ReservationObjectDeclineClickFinalEvent, Builder> {
        private ReservationObjectDeclineClickFinalEventAdapter() {
        }

        public void write(Protocol protocol, ReservationObjectDeclineClickFinalEvent struct) throws IOException {
            protocol.writeStructBegin("ReservationObjectDeclineClickFinalEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, PassportService.SF_DG11);
            protocol.writeString(struct.operation);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_REASON, 6, PassportService.SF_DG11);
            protocol.writeString(struct.reason);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 7, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("reservation_id", 8, 10);
            protocol.writeI64(struct.reservation_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ReservationObjectDeclineClickFinalEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.reason = builder.reason;
        this.listing_id = builder.listing_id;
        this.reservation_id = builder.reservation_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ReservationObjectDeclineClickFinalEvent)) {
            return false;
        }
        ReservationObjectDeclineClickFinalEvent that = (ReservationObjectDeclineClickFinalEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.reason == that.reason || this.reason.equals(that.reason)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.reservation_id == that.reservation_id || this.reservation_id.equals(that.reservation_id)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.reason.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.reservation_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ReservationObjectDeclineClickFinalEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", reason=" + this.reason + ", listing_id=" + this.listing_id + ", reservation_id=" + this.reservation_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}