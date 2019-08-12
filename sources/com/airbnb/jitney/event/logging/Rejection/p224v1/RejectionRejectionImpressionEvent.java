package com.airbnb.jitney.event.logging.Rejection.p224v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.RoomType.p239v1.C2680RoomType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Rejection.v1.RejectionRejectionImpressionEvent */
public final class RejectionRejectionImpressionEvent implements Struct {
    public static final Adapter<RejectionRejectionImpressionEvent, Builder> ADAPTER = new RejectionRejectionImpressionEventAdapter();
    public final String checkin_date;
    public final String checkout_date;
    public final Context context;
    public final String event_name;
    public final Long guests;
    public final Long listing_id;
    public final String location;
    public final C2451Operation operation;
    public final String page;
    public final Boolean pets;
    public final String reservation_code;
    public final List<C2680RoomType> room_types;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Rejection.v1.RejectionRejectionImpressionEvent$Builder */
    public static final class Builder implements StructBuilder<RejectionRejectionImpressionEvent> {
        /* access modifiers changed from: private */
        public String checkin_date;
        /* access modifiers changed from: private */
        public String checkout_date;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String location;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Boolean pets;
        /* access modifiers changed from: private */
        public String reservation_code;
        /* access modifiers changed from: private */
        public List<C2680RoomType> room_types;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public Long user_id;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Rejection:RejectionRejectionImpressionEvent:1.0.0";
            this.event_name = "rejection_rejection_impression";
            this.page = "Rejection";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, Long user_id2, Long listing_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Rejection:RejectionRejectionImpressionEvent:1.0.0";
            this.event_name = "rejection_rejection_impression";
            this.context = context2;
            this.user_id = user_id2;
            this.listing_id = listing_id2;
            this.page = "Rejection";
            this.operation = C2451Operation.Impression;
        }

        public Builder reservation_code(String reservation_code2) {
            this.reservation_code = reservation_code2;
            return this;
        }

        public RejectionRejectionImpressionEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.user_id == null) {
                throw new IllegalStateException("Required field 'user_id' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation != null) {
                return new RejectionRejectionImpressionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Rejection.v1.RejectionRejectionImpressionEvent$RejectionRejectionImpressionEventAdapter */
    private static final class RejectionRejectionImpressionEventAdapter implements Adapter<RejectionRejectionImpressionEvent, Builder> {
        private RejectionRejectionImpressionEventAdapter() {
        }

        public void write(Protocol protocol, RejectionRejectionImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("RejectionRejectionImpressionEvent");
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
            protocol.writeFieldBegin("user_id", 3, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 4, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            if (struct.reservation_code != null) {
                protocol.writeFieldBegin("reservation_code", 5, PassportService.SF_DG11);
                protocol.writeString(struct.reservation_code);
                protocol.writeFieldEnd();
            }
            if (struct.location != null) {
                protocol.writeFieldBegin("location", 6, PassportService.SF_DG11);
                protocol.writeString(struct.location);
                protocol.writeFieldEnd();
            }
            if (struct.checkin_date != null) {
                protocol.writeFieldBegin("checkin_date", 7, PassportService.SF_DG11);
                protocol.writeString(struct.checkin_date);
                protocol.writeFieldEnd();
            }
            if (struct.checkout_date != null) {
                protocol.writeFieldBegin("checkout_date", 8, PassportService.SF_DG11);
                protocol.writeString(struct.checkout_date);
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 9, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.pets != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.PETS, 10, 2);
                protocol.writeBool(struct.pets.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.room_types != null) {
                protocol.writeFieldBegin("room_types", 11, 15);
                protocol.writeListBegin(16, struct.room_types.size());
                for (C2680RoomType item0 : struct.room_types) {
                    protocol.writeI32(item0.value);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("page", 12, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 13, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private RejectionRejectionImpressionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.user_id = builder.user_id;
        this.listing_id = builder.listing_id;
        this.reservation_code = builder.reservation_code;
        this.location = builder.location;
        this.checkin_date = builder.checkin_date;
        this.checkout_date = builder.checkout_date;
        this.guests = builder.guests;
        this.pets = builder.pets;
        this.room_types = builder.room_types == null ? null : Collections.unmodifiableList(builder.room_types);
        this.page = builder.page;
        this.operation = builder.operation;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof RejectionRejectionImpressionEvent)) {
            return false;
        }
        RejectionRejectionImpressionEvent that = (RejectionRejectionImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.reservation_code == that.reservation_code || (this.reservation_code != null && this.reservation_code.equals(that.reservation_code))) && ((this.location == that.location || (this.location != null && this.location.equals(that.location))) && ((this.checkin_date == that.checkin_date || (this.checkin_date != null && this.checkin_date.equals(that.checkin_date))) && ((this.checkout_date == that.checkout_date || (this.checkout_date != null && this.checkout_date.equals(that.checkout_date))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.pets == that.pets || (this.pets != null && this.pets.equals(that.pets))) && ((this.room_types == that.room_types || (this.room_types != null && this.room_types.equals(that.room_types))) && ((this.page == that.page || this.page.equals(that.page)) && (this.operation == that.operation || this.operation.equals(that.operation))))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ (this.reservation_code == null ? 0 : this.reservation_code.hashCode())) * -2128831035) ^ (this.location == null ? 0 : this.location.hashCode())) * -2128831035) ^ (this.checkin_date == null ? 0 : this.checkin_date.hashCode())) * -2128831035) ^ (this.checkout_date == null ? 0 : this.checkout_date.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.pets == null ? 0 : this.pets.hashCode())) * -2128831035;
        if (this.room_types != null) {
            i = this.room_types.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RejectionRejectionImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", user_id=" + this.user_id + ", listing_id=" + this.listing_id + ", reservation_code=" + this.reservation_code + ", location=" + this.location + ", checkin_date=" + this.checkin_date + ", checkout_date=" + this.checkout_date + ", guests=" + this.guests + ", pets=" + this.pets + ", room_types=" + this.room_types + ", page=" + this.page + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
