package com.airbnb.jitney.event.logging.Resy.p228v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.jitney.event.logging.MtProduct.p158v1.C2444MtProduct;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.TimeSlotInfo.p267v1.C2757TimeSlotInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Resy.v1.ResyClickTimeSlotEvent */
public final class ResyClickTimeSlotEvent implements Struct {
    public static final Adapter<ResyClickTimeSlotEvent, Builder> ADAPTER = new ResyClickTimeSlotEventAdapter();
    public final Context context;
    public final String date;
    public final String event_name;
    public final Long guests;
    public final Long mt_product_id;
    public final C2444MtProduct mt_product_type;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String target;
    public final C2757TimeSlotInfo time_slot_info;

    /* renamed from: com.airbnb.jitney.event.logging.Resy.v1.ResyClickTimeSlotEvent$Builder */
    public static final class Builder implements StructBuilder<ResyClickTimeSlotEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String date;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public Long mt_product_id;
        /* access modifiers changed from: private */
        public C2444MtProduct mt_product_type;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;
        /* access modifiers changed from: private */
        public C2757TimeSlotInfo time_slot_info;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Resy:ResyClickTimeSlotEvent:1.0.0";
            this.event_name = "resy_click_time_slot";
            this.page = "resy_page";
            this.target = "time_slot";
            this.operation = C2451Operation.Click;
            this.mt_product_type = C2444MtProduct.Activity;
        }

        public Builder(Context context2, Long mt_product_id2, C2757TimeSlotInfo time_slot_info2, String date2, Long guests2) {
            this.schema = "com.airbnb.jitney.event.logging.Resy:ResyClickTimeSlotEvent:1.0.0";
            this.event_name = "resy_click_time_slot";
            this.context = context2;
            this.page = "resy_page";
            this.target = "time_slot";
            this.operation = C2451Operation.Click;
            this.mt_product_type = C2444MtProduct.Activity;
            this.mt_product_id = mt_product_id2;
            this.time_slot_info = time_slot_info2;
            this.date = date2;
            this.guests = guests2;
        }

        public ResyClickTimeSlotEvent build() {
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
            } else if (this.mt_product_type == null) {
                throw new IllegalStateException("Required field 'mt_product_type' is missing");
            } else if (this.mt_product_id == null) {
                throw new IllegalStateException("Required field 'mt_product_id' is missing");
            } else if (this.time_slot_info == null) {
                throw new IllegalStateException("Required field 'time_slot_info' is missing");
            } else if (this.date == null) {
                throw new IllegalStateException("Required field 'date' is missing");
            } else if (this.guests != null) {
                return new ResyClickTimeSlotEvent(this);
            } else {
                throw new IllegalStateException("Required field 'guests' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Resy.v1.ResyClickTimeSlotEvent$ResyClickTimeSlotEventAdapter */
    private static final class ResyClickTimeSlotEventAdapter implements Adapter<ResyClickTimeSlotEvent, Builder> {
        private ResyClickTimeSlotEventAdapter() {
        }

        public void write(Protocol protocol, ResyClickTimeSlotEvent struct) throws IOException {
            protocol.writeStructBegin("ResyClickTimeSlotEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_product_type", 6, 8);
            protocol.writeI32(struct.mt_product_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_product_id", 7, 10);
            protocol.writeI64(struct.mt_product_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("time_slot_info", 8, PassportService.SF_DG12);
            C2757TimeSlotInfo.ADAPTER.write(protocol, struct.time_slot_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("date", 9, PassportService.SF_DG11);
            protocol.writeString(struct.date);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 10, 10);
            protocol.writeI64(struct.guests.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ResyClickTimeSlotEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.mt_product_type = builder.mt_product_type;
        this.mt_product_id = builder.mt_product_id;
        this.time_slot_info = builder.time_slot_info;
        this.date = builder.date;
        this.guests = builder.guests;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ResyClickTimeSlotEvent)) {
            return false;
        }
        ResyClickTimeSlotEvent that = (ResyClickTimeSlotEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.mt_product_type == that.mt_product_type || this.mt_product_type.equals(that.mt_product_type)) && ((this.mt_product_id == that.mt_product_id || this.mt_product_id.equals(that.mt_product_id)) && ((this.time_slot_info == that.time_slot_info || this.time_slot_info.equals(that.time_slot_info)) && ((this.date == that.date || this.date.equals(that.date)) && (this.guests == that.guests || this.guests.equals(that.guests)))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.mt_product_type.hashCode()) * -2128831035) ^ this.mt_product_id.hashCode()) * -2128831035) ^ this.time_slot_info.hashCode()) * -2128831035) ^ this.date.hashCode()) * -2128831035) ^ this.guests.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ResyClickTimeSlotEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", mt_product_type=" + this.mt_product_type + ", mt_product_id=" + this.mt_product_id + ", time_slot_info=" + this.time_slot_info + ", date=" + this.date + ", guests=" + this.guests + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
