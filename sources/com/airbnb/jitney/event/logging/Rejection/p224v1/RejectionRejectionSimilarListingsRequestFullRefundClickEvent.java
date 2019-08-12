package com.airbnb.jitney.event.logging.Rejection.p224v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.RoomType.p239v1.C2680RoomType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Rejection.v1.RejectionRejectionSimilarListingsRequestFullRefundClickEvent */
public final class RejectionRejectionSimilarListingsRequestFullRefundClickEvent implements Struct {
    public static final Adapter<RejectionRejectionSimilarListingsRequestFullRefundClickEvent, Object> ADAPTER = new C2613x7a132da1();
    public final String checkin_date;
    public final String checkout_date;
    public final Context context;
    public final String event_name;
    public final Long guests;
    public final Long listing_id;
    public final Set<String> listing_ids;
    public final String location;
    public final C2451Operation operation;
    public final String page;
    public final Boolean pets;
    public final String reservation_code;
    public final List<C2680RoomType> room_types;
    public final String schema;
    public final String target;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Rejection.v1.RejectionRejectionSimilarListingsRequestFullRefundClickEvent$RejectionRejectionSimilarListingsRequestFullRefundClickEventAdapter */
    private static final class C2613x7a132da1 implements Adapter<RejectionRejectionSimilarListingsRequestFullRefundClickEvent, Object> {
        private C2613x7a132da1() {
        }

        public void write(Protocol protocol, RejectionRejectionSimilarListingsRequestFullRefundClickEvent struct) throws IOException {
            protocol.writeStructBegin("RejectionRejectionSimilarListingsRequestFullRefundClickEvent");
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
            if (struct.listing_ids != null) {
                protocol.writeFieldBegin("listing_ids", 12, 14);
                protocol.writeSetBegin(PassportService.SF_DG11, struct.listing_ids.size());
                for (String item02 : struct.listing_ids) {
                    protocol.writeString(item02);
                }
                protocol.writeSetEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("page", 13, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 14, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 15, 8);
            protocol.writeI32(struct.operation.value);
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
        if (!(other instanceof RejectionRejectionSimilarListingsRequestFullRefundClickEvent)) {
            return false;
        }
        RejectionRejectionSimilarListingsRequestFullRefundClickEvent that = (RejectionRejectionSimilarListingsRequestFullRefundClickEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.reservation_code == that.reservation_code || (this.reservation_code != null && this.reservation_code.equals(that.reservation_code))) && ((this.location == that.location || (this.location != null && this.location.equals(that.location))) && ((this.checkin_date == that.checkin_date || (this.checkin_date != null && this.checkin_date.equals(that.checkin_date))) && ((this.checkout_date == that.checkout_date || (this.checkout_date != null && this.checkout_date.equals(that.checkout_date))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.pets == that.pets || (this.pets != null && this.pets.equals(that.pets))) && ((this.room_types == that.room_types || (this.room_types != null && this.room_types.equals(that.room_types))) && ((this.listing_ids == that.listing_ids || (this.listing_ids != null && this.listing_ids.equals(that.listing_ids))) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && (this.operation == that.operation || this.operation.equals(that.operation))))))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ (this.reservation_code == null ? 0 : this.reservation_code.hashCode())) * -2128831035) ^ (this.location == null ? 0 : this.location.hashCode())) * -2128831035) ^ (this.checkin_date == null ? 0 : this.checkin_date.hashCode())) * -2128831035) ^ (this.checkout_date == null ? 0 : this.checkout_date.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.pets == null ? 0 : this.pets.hashCode())) * -2128831035) ^ (this.room_types == null ? 0 : this.room_types.hashCode())) * -2128831035;
        if (this.listing_ids != null) {
            i = this.listing_ids.hashCode();
        }
        return (((((((code ^ i) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RejectionRejectionSimilarListingsRequestFullRefundClickEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", user_id=" + this.user_id + ", listing_id=" + this.listing_id + ", reservation_code=" + this.reservation_code + ", location=" + this.location + ", checkin_date=" + this.checkin_date + ", checkout_date=" + this.checkout_date + ", guests=" + this.guests + ", pets=" + this.pets + ", room_types=" + this.room_types + ", listing_ids=" + this.listing_ids + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
