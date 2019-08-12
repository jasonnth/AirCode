package com.airbnb.jitney.event.logging.ReservationObject.p227v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ReservationObject.v2.ReservationObjectMessageEvent */
public final class ReservationObjectMessageEvent implements Struct {
    public static final Adapter<ReservationObjectMessageEvent, Builder> ADAPTER = new ReservationObjectMessageEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final Long message_thread_id;
    public final String operation;
    public final String page;
    public final Long reservation_id;
    public final String schema;
    public final String target;
    public final String type;

    /* renamed from: com.airbnb.jitney.event.logging.ReservationObject.v2.ReservationObjectMessageEvent$Builder */
    public static final class Builder implements StructBuilder<ReservationObjectMessageEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public Long message_thread_id;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Long reservation_id;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;
        /* access modifiers changed from: private */
        public String type;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.ReservationObject:ReservationObjectMessageEvent:2.0.0";
            this.event_name = "reservation_object_message";
            this.page = "reservation_object";
            this.target = "message";
            this.operation = "click";
        }

        public Builder(Context context2, Long listing_id2, String type2) {
            this.schema = "com.airbnb.jitney.event.logging.ReservationObject:ReservationObjectMessageEvent:2.0.0";
            this.event_name = "reservation_object_message";
            this.context = context2;
            this.page = "reservation_object";
            this.target = "message";
            this.operation = "click";
            this.listing_id = listing_id2;
            this.type = type2;
        }

        public Builder message_thread_id(Long message_thread_id2) {
            this.message_thread_id = message_thread_id2;
            return this;
        }

        public Builder reservation_id(Long reservation_id2) {
            this.reservation_id = reservation_id2;
            return this;
        }

        public ReservationObjectMessageEvent build() {
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
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.type != null) {
                return new ReservationObjectMessageEvent(this);
            } else {
                throw new IllegalStateException("Required field 'type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.ReservationObject.v2.ReservationObjectMessageEvent$ReservationObjectMessageEventAdapter */
    private static final class ReservationObjectMessageEventAdapter implements Adapter<ReservationObjectMessageEvent, Builder> {
        private ReservationObjectMessageEventAdapter() {
        }

        public void write(Protocol protocol, ReservationObjectMessageEvent struct) throws IOException {
            protocol.writeStructBegin("ReservationObjectMessageEvent");
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
            protocol.writeFieldBegin("listing_id", 6, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            if (struct.message_thread_id != null) {
                protocol.writeFieldBegin("message_thread_id", 7, 10);
                protocol.writeI64(struct.message_thread_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.reservation_id != null) {
                protocol.writeFieldBegin("reservation_id", 8, 10);
                protocol.writeI64(struct.reservation_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("type", 9, PassportService.SF_DG11);
            protocol.writeString(struct.type);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ReservationObjectMessageEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.message_thread_id = builder.message_thread_id;
        this.reservation_id = builder.reservation_id;
        this.type = builder.type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ReservationObjectMessageEvent)) {
            return false;
        }
        ReservationObjectMessageEvent that = (ReservationObjectMessageEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.message_thread_id == that.message_thread_id || (this.message_thread_id != null && this.message_thread_id.equals(that.message_thread_id))) && ((this.reservation_id == that.reservation_id || (this.reservation_id != null && this.reservation_id.equals(that.reservation_id))) && (this.type == that.type || this.type.equals(that.type))))))))))) {
            return true;
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ (this.message_thread_id == null ? 0 : this.message_thread_id.hashCode())) * -2128831035;
        if (this.reservation_id != null) {
            i = this.reservation_id.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ReservationObjectMessageEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", message_thread_id=" + this.message_thread_id + ", reservation_id=" + this.reservation_id + ", type=" + this.type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
