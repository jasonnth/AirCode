package com.airbnb.jitney.event.logging.MobileP4Flow.p152v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.jitney.event.logging.IdType.p117v1.C2217IdType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.MobileP4Flow.v1.MobileP4FlowChinaGuestSelectIdTypeEvent */
public final class MobileP4FlowChinaGuestSelectIdTypeEvent implements Struct {
    public static final Adapter<MobileP4FlowChinaGuestSelectIdTypeEvent, Builder> ADAPTER = new MobileP4FlowChinaGuestSelectIdTypeEventAdapter();
    public final Long adults;
    public final String checkin_date;
    public final String checkout_date;
    public final Long children;
    public final Context context;
    public final String event_name;
    public final Long guests;
    public final Long infants;
    public final Boolean instant_book;
    public final Long listing_id;
    public final C2217IdType national_id_type;
    public final C2451Operation operation;
    public final String page;
    public final Boolean pets;
    public final Long reservation_id;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.MobileP4Flow.v1.MobileP4FlowChinaGuestSelectIdTypeEvent$Builder */
    public static final class Builder implements StructBuilder<MobileP4FlowChinaGuestSelectIdTypeEvent> {
        /* access modifiers changed from: private */
        public Long adults;
        /* access modifiers changed from: private */
        public String checkin_date;
        /* access modifiers changed from: private */
        public String checkout_date;
        /* access modifiers changed from: private */
        public Long children;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public Long infants;
        /* access modifiers changed from: private */
        public Boolean instant_book;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2217IdType national_id_type;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Boolean pets;
        /* access modifiers changed from: private */
        public Long reservation_id;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public Long user_id;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.MobileP4Flow:MobileP4FlowChinaGuestSelectIdTypeEvent:1.0.0";
            this.event_name = "mobilep4flow_china_guest_select_id_type";
            this.page = "ChinaGuestIdType";
            this.operation = C2451Operation.Select;
        }

        public Builder(Context context2, Long user_id2, Long listing_id2, Long reservation_id2, Boolean instant_book2, C2217IdType national_id_type2) {
            this.schema = "com.airbnb.jitney.event.logging.MobileP4Flow:MobileP4FlowChinaGuestSelectIdTypeEvent:1.0.0";
            this.event_name = "mobilep4flow_china_guest_select_id_type";
            this.context = context2;
            this.user_id = user_id2;
            this.listing_id = listing_id2;
            this.reservation_id = reservation_id2;
            this.instant_book = instant_book2;
            this.page = "ChinaGuestIdType";
            this.operation = C2451Operation.Select;
            this.national_id_type = national_id_type2;
        }

        public MobileP4FlowChinaGuestSelectIdTypeEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.user_id == null) {
                throw new IllegalStateException("Required field 'user_id' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.reservation_id == null) {
                throw new IllegalStateException("Required field 'reservation_id' is missing");
            } else if (this.instant_book == null) {
                throw new IllegalStateException("Required field 'instant_book' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.national_id_type != null) {
                return new MobileP4FlowChinaGuestSelectIdTypeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'national_id_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.MobileP4Flow.v1.MobileP4FlowChinaGuestSelectIdTypeEvent$MobileP4FlowChinaGuestSelectIdTypeEventAdapter */
    private static final class MobileP4FlowChinaGuestSelectIdTypeEventAdapter implements Adapter<MobileP4FlowChinaGuestSelectIdTypeEvent, Builder> {
        private MobileP4FlowChinaGuestSelectIdTypeEventAdapter() {
        }

        public void write(Protocol protocol, MobileP4FlowChinaGuestSelectIdTypeEvent struct) throws IOException {
            protocol.writeStructBegin("MobileP4FlowChinaGuestSelectIdTypeEvent");
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
            protocol.writeFieldBegin("reservation_id", 5, 10);
            protocol.writeI64(struct.reservation_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingAnalytics.INSTANT_BOOK, 6, 2);
            protocol.writeBool(struct.instant_book.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 7, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 8, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.adults != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.ADULTS, 9, 10);
                protocol.writeI64(struct.adults.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.children != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.CHILDREN, 10, 10);
                protocol.writeI64(struct.children.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.infants != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.INFANTS, 11, 10);
                protocol.writeI64(struct.infants.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.pets != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.PETS, 12, 2);
                protocol.writeBool(struct.pets.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.checkin_date != null) {
                protocol.writeFieldBegin("checkin_date", 13, PassportService.SF_DG11);
                protocol.writeString(struct.checkin_date);
                protocol.writeFieldEnd();
            }
            if (struct.checkout_date != null) {
                protocol.writeFieldBegin("checkout_date", 14, PassportService.SF_DG11);
                protocol.writeString(struct.checkout_date);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 15, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("national_id_type", 16, 8);
            protocol.writeI32(struct.national_id_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private MobileP4FlowChinaGuestSelectIdTypeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.user_id = builder.user_id;
        this.listing_id = builder.listing_id;
        this.reservation_id = builder.reservation_id;
        this.instant_book = builder.instant_book;
        this.page = builder.page;
        this.guests = builder.guests;
        this.adults = builder.adults;
        this.children = builder.children;
        this.infants = builder.infants;
        this.pets = builder.pets;
        this.checkin_date = builder.checkin_date;
        this.checkout_date = builder.checkout_date;
        this.operation = builder.operation;
        this.national_id_type = builder.national_id_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof MobileP4FlowChinaGuestSelectIdTypeEvent)) {
            return false;
        }
        MobileP4FlowChinaGuestSelectIdTypeEvent that = (MobileP4FlowChinaGuestSelectIdTypeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.reservation_id == that.reservation_id || this.reservation_id.equals(that.reservation_id)) && ((this.instant_book == that.instant_book || this.instant_book.equals(that.instant_book)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.adults == that.adults || (this.adults != null && this.adults.equals(that.adults))) && ((this.children == that.children || (this.children != null && this.children.equals(that.children))) && ((this.infants == that.infants || (this.infants != null && this.infants.equals(that.infants))) && ((this.pets == that.pets || (this.pets != null && this.pets.equals(that.pets))) && ((this.checkin_date == that.checkin_date || (this.checkin_date != null && this.checkin_date.equals(that.checkin_date))) && ((this.checkout_date == that.checkout_date || (this.checkout_date != null && this.checkout_date.equals(that.checkout_date))) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.national_id_type == that.national_id_type || this.national_id_type.equals(that.national_id_type)))))))))))))))))) {
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
        int code = (((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.reservation_id.hashCode()) * -2128831035) ^ this.instant_book.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.adults == null ? 0 : this.adults.hashCode())) * -2128831035) ^ (this.children == null ? 0 : this.children.hashCode())) * -2128831035) ^ (this.infants == null ? 0 : this.infants.hashCode())) * -2128831035) ^ (this.pets == null ? 0 : this.pets.hashCode())) * -2128831035) ^ (this.checkin_date == null ? 0 : this.checkin_date.hashCode())) * -2128831035;
        if (this.checkout_date != null) {
            i = this.checkout_date.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.national_id_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "MobileP4FlowChinaGuestSelectIdTypeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", user_id=" + this.user_id + ", listing_id=" + this.listing_id + ", reservation_id=" + this.reservation_id + ", instant_book=" + this.instant_book + ", page=" + this.page + ", guests=" + this.guests + ", adults=" + this.adults + ", children=" + this.children + ", infants=" + this.infants + ", pets=" + this.pets + ", checkin_date=" + this.checkin_date + ", checkout_date=" + this.checkout_date + ", operation=" + this.operation + ", national_id_type=" + this.national_id_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}