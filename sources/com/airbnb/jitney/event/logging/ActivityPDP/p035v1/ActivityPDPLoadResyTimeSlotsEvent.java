package com.airbnb.jitney.event.logging.ActivityPDP.p035v1;

import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.jitney.event.logging.MtProduct.p158v1.C2444MtProduct;
import com.airbnb.jitney.event.logging.TimeSlot.p266v1.C2755TimeSlot;
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

/* renamed from: com.airbnb.jitney.event.logging.ActivityPDP.v1.ActivityPDPLoadResyTimeSlotsEvent */
public final class ActivityPDPLoadResyTimeSlotsEvent implements Struct {
    public static final Adapter<ActivityPDPLoadResyTimeSlotsEvent, Builder> ADAPTER = new ActivityPDPLoadResyTimeSlotsEventAdapter();
    public final List<C2755TimeSlot> all_available_time_slots;
    public final Context context;
    public final String date;
    public final String event_name;
    public final Long guests;
    public final Long mt_product_id;
    public final C2444MtProduct mt_product_type;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ActivityPDP.v1.ActivityPDPLoadResyTimeSlotsEvent$ActivityPDPLoadResyTimeSlotsEventAdapter */
    private static final class ActivityPDPLoadResyTimeSlotsEventAdapter implements Adapter<ActivityPDPLoadResyTimeSlotsEvent, Builder> {
        private ActivityPDPLoadResyTimeSlotsEventAdapter() {
        }

        public void write(Protocol protocol, ActivityPDPLoadResyTimeSlotsEvent struct) throws IOException {
            protocol.writeStructBegin("ActivityPDPLoadResyTimeSlotsEvent");
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
            protocol.writeFieldBegin("mt_product_type", 4, 8);
            protocol.writeI32(struct.mt_product_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_product_id", 5, 10);
            protocol.writeI64(struct.mt_product_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("all_available_time_slots", 6, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.all_available_time_slots.size());
            for (C2755TimeSlot item0 : struct.all_available_time_slots) {
                C2755TimeSlot.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("date", 7, PassportService.SF_DG11);
            protocol.writeString(struct.date);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 8, 10);
            protocol.writeI64(struct.guests.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.ActivityPDP.v1.ActivityPDPLoadResyTimeSlotsEvent$Builder */
    public static final class Builder implements StructBuilder<ActivityPDPLoadResyTimeSlotsEvent> {
        /* access modifiers changed from: private */
        public List<C2755TimeSlot> all_available_time_slots;
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
        public String page;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.ActivityPDP:ActivityPDPLoadResyTimeSlotsEvent:1.0.0";
            this.event_name = "activitypdp_load_resy_time_slots";
            this.page = "activity_pdp";
            this.mt_product_type = C2444MtProduct.Activity;
        }

        public Builder(Context context2, Long mt_product_id2, List<C2755TimeSlot> all_available_time_slots2, String date2, Long guests2) {
            this.schema = "com.airbnb.jitney.event.logging.ActivityPDP:ActivityPDPLoadResyTimeSlotsEvent:1.0.0";
            this.event_name = "activitypdp_load_resy_time_slots";
            this.context = context2;
            this.page = "activity_pdp";
            this.mt_product_type = C2444MtProduct.Activity;
            this.mt_product_id = mt_product_id2;
            this.all_available_time_slots = all_available_time_slots2;
            this.date = date2;
            this.guests = guests2;
        }

        public ActivityPDPLoadResyTimeSlotsEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.mt_product_type == null) {
                throw new IllegalStateException("Required field 'mt_product_type' is missing");
            } else if (this.mt_product_id == null) {
                throw new IllegalStateException("Required field 'mt_product_id' is missing");
            } else if (this.all_available_time_slots == null) {
                throw new IllegalStateException("Required field 'all_available_time_slots' is missing");
            } else if (this.date == null) {
                throw new IllegalStateException("Required field 'date' is missing");
            } else if (this.guests != null) {
                return new ActivityPDPLoadResyTimeSlotsEvent(this);
            } else {
                throw new IllegalStateException("Required field 'guests' is missing");
            }
        }
    }

    private ActivityPDPLoadResyTimeSlotsEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.mt_product_type = builder.mt_product_type;
        this.mt_product_id = builder.mt_product_id;
        this.all_available_time_slots = Collections.unmodifiableList(builder.all_available_time_slots);
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
        if (!(other instanceof ActivityPDPLoadResyTimeSlotsEvent)) {
            return false;
        }
        ActivityPDPLoadResyTimeSlotsEvent that = (ActivityPDPLoadResyTimeSlotsEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.mt_product_type == that.mt_product_type || this.mt_product_type.equals(that.mt_product_type)) && ((this.mt_product_id == that.mt_product_id || this.mt_product_id.equals(that.mt_product_id)) && ((this.all_available_time_slots == that.all_available_time_slots || this.all_available_time_slots.equals(that.all_available_time_slots)) && ((this.date == that.date || this.date.equals(that.date)) && (this.guests == that.guests || this.guests.equals(that.guests)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.mt_product_type.hashCode()) * -2128831035) ^ this.mt_product_id.hashCode()) * -2128831035) ^ this.all_available_time_slots.hashCode()) * -2128831035) ^ this.date.hashCode()) * -2128831035) ^ this.guests.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ActivityPDPLoadResyTimeSlotsEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", mt_product_type=" + this.mt_product_type + ", mt_product_id=" + this.mt_product_id + ", all_available_time_slots=" + this.all_available_time_slots + ", date=" + this.date + ", guests=" + this.guests + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}