package com.airbnb.jitney.event.logging.PaidGrowth.p180v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.RoomType.p239v1.C2680RoomType;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaidGrowth.v2.PaidGrowthHomeSearchPixelEvent */
public final class PaidGrowthHomeSearchPixelEvent implements Struct {
    public static final Adapter<PaidGrowthHomeSearchPixelEvent, Object> ADAPTER = new PaidGrowthHomeSearchPixelEventAdapter();
    public final Long adults;
    public final List<Long> amenities;
    public final Double bathrooms;
    public final Long bedrooms;
    public final Long beds;
    public final String checkin_date;
    public final String checkout_date;
    public final Long children;
    public final Context context;
    public final String device_id;
    public final String event_name;
    public final Long guests;
    public final Long infants;
    public final Boolean instant_book;
    public final C2451Operation operation;
    public final Boolean pets;
    public final Long price_max;
    public final Long price_min;
    public final List<Long> remarketing_ids;
    public final List<C2680RoomType> room_types;
    public final String schema;
    public final C2731SearchContext search_context;
    public final List<Long> search_results;
    public final C2139ExploreSubtab subtab;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.PaidGrowth.v2.PaidGrowthHomeSearchPixelEvent$PaidGrowthHomeSearchPixelEventAdapter */
    private static final class PaidGrowthHomeSearchPixelEventAdapter implements Adapter<PaidGrowthHomeSearchPixelEvent, Object> {
        private PaidGrowthHomeSearchPixelEventAdapter() {
        }

        public void write(Protocol protocol, PaidGrowthHomeSearchPixelEvent struct) throws IOException {
            protocol.writeStructBegin("PaidGrowthHomeSearchPixelEvent");
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
            protocol.writeFieldBegin("search_results", 4, 15);
            protocol.writeListBegin(10, struct.search_results.size());
            for (Long item0 : struct.search_results) {
                protocol.writeI64(item0.longValue());
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("remarketing_ids", 5, 15);
            protocol.writeListBegin(10, struct.remarketing_ids.size());
            for (Long item02 : struct.remarketing_ids) {
                protocol.writeI64(item02.longValue());
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab", 6, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("device_id", 7, PassportService.SF_DG11);
            protocol.writeString(struct.device_id);
            protocol.writeFieldEnd();
            if (struct.search_context != null) {
                protocol.writeFieldBegin("search_context", 8, PassportService.SF_DG12);
                C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
                protocol.writeFieldEnd();
            }
            if (struct.user_id != null) {
                protocol.writeFieldBegin("user_id", 9, 10);
                protocol.writeI64(struct.user_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.checkin_date != null) {
                protocol.writeFieldBegin("checkin_date", 10, PassportService.SF_DG11);
                protocol.writeString(struct.checkin_date);
                protocol.writeFieldEnd();
            }
            if (struct.checkout_date != null) {
                protocol.writeFieldBegin("checkout_date", 11, PassportService.SF_DG11);
                protocol.writeString(struct.checkout_date);
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 12, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.adults != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.ADULTS, 13, 10);
                protocol.writeI64(struct.adults.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.children != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.CHILDREN, 14, 10);
                protocol.writeI64(struct.children.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.infants != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.INFANTS, 15, 10);
                protocol.writeI64(struct.infants.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.pets != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.PETS, 16, 2);
                protocol.writeBool(struct.pets.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.instant_book != null) {
                protocol.writeFieldBegin(ManageListingAnalytics.INSTANT_BOOK, 17, 2);
                protocol.writeBool(struct.instant_book.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.price_min != null) {
                protocol.writeFieldBegin("price_min", 18, 10);
                protocol.writeI64(struct.price_min.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.price_max != null) {
                protocol.writeFieldBegin("price_max", 19, 10);
                protocol.writeI64(struct.price_max.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.room_types != null) {
                protocol.writeFieldBegin("room_types", 20, 15);
                protocol.writeListBegin(16, struct.room_types.size());
                for (C2680RoomType item03 : struct.room_types) {
                    protocol.writeI32(item03.value);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.beds != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BEDS_KEY, 21, 10);
                protocol.writeI64(struct.beds.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.bedrooms != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BEDROOMS_KEY, 22, 10);
                protocol.writeI64(struct.bedrooms.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.bathrooms != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BATHROOMS_KEY, 23, 4);
                protocol.writeDouble(struct.bathrooms.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.amenities != null) {
                protocol.writeFieldBegin("amenities", 24, 15);
                protocol.writeListBegin(10, struct.amenities.size());
                for (Long item04 : struct.amenities) {
                    protocol.writeI64(item04.longValue());
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof PaidGrowthHomeSearchPixelEvent)) {
            return false;
        }
        PaidGrowthHomeSearchPixelEvent that = (PaidGrowthHomeSearchPixelEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.search_results == that.search_results || this.search_results.equals(that.search_results)) && ((this.remarketing_ids == that.remarketing_ids || this.remarketing_ids.equals(that.remarketing_ids)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.device_id == that.device_id || this.device_id.equals(that.device_id)) && ((this.search_context == that.search_context || (this.search_context != null && this.search_context.equals(that.search_context))) && ((this.user_id == that.user_id || (this.user_id != null && this.user_id.equals(that.user_id))) && ((this.checkin_date == that.checkin_date || (this.checkin_date != null && this.checkin_date.equals(that.checkin_date))) && ((this.checkout_date == that.checkout_date || (this.checkout_date != null && this.checkout_date.equals(that.checkout_date))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.adults == that.adults || (this.adults != null && this.adults.equals(that.adults))) && ((this.children == that.children || (this.children != null && this.children.equals(that.children))) && ((this.infants == that.infants || (this.infants != null && this.infants.equals(that.infants))) && ((this.pets == that.pets || (this.pets != null && this.pets.equals(that.pets))) && ((this.instant_book == that.instant_book || (this.instant_book != null && this.instant_book.equals(that.instant_book))) && ((this.price_min == that.price_min || (this.price_min != null && this.price_min.equals(that.price_min))) && ((this.price_max == that.price_max || (this.price_max != null && this.price_max.equals(that.price_max))) && ((this.room_types == that.room_types || (this.room_types != null && this.room_types.equals(that.room_types))) && ((this.beds == that.beds || (this.beds != null && this.beds.equals(that.beds))) && ((this.bedrooms == that.bedrooms || (this.bedrooms != null && this.bedrooms.equals(that.bedrooms))) && (this.bathrooms == that.bathrooms || (this.bathrooms != null && this.bathrooms.equals(that.bathrooms)))))))))))))))))))))))))) {
            if (this.amenities == that.amenities) {
                return true;
            }
            if (this.amenities != null && this.amenities.equals(that.amenities)) {
                return true;
            }
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
        int code = (((((((((((((((((((((((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.search_results.hashCode()) * -2128831035) ^ this.remarketing_ids.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.device_id.hashCode()) * -2128831035) ^ (this.search_context == null ? 0 : this.search_context.hashCode())) * -2128831035) ^ (this.user_id == null ? 0 : this.user_id.hashCode())) * -2128831035) ^ (this.checkin_date == null ? 0 : this.checkin_date.hashCode())) * -2128831035) ^ (this.checkout_date == null ? 0 : this.checkout_date.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.adults == null ? 0 : this.adults.hashCode())) * -2128831035) ^ (this.children == null ? 0 : this.children.hashCode())) * -2128831035) ^ (this.infants == null ? 0 : this.infants.hashCode())) * -2128831035) ^ (this.pets == null ? 0 : this.pets.hashCode())) * -2128831035) ^ (this.instant_book == null ? 0 : this.instant_book.hashCode())) * -2128831035) ^ (this.price_min == null ? 0 : this.price_min.hashCode())) * -2128831035) ^ (this.price_max == null ? 0 : this.price_max.hashCode())) * -2128831035) ^ (this.room_types == null ? 0 : this.room_types.hashCode())) * -2128831035) ^ (this.beds == null ? 0 : this.beds.hashCode())) * -2128831035) ^ (this.bedrooms == null ? 0 : this.bedrooms.hashCode())) * -2128831035) ^ (this.bathrooms == null ? 0 : this.bathrooms.hashCode())) * -2128831035;
        if (this.amenities != null) {
            i = this.amenities.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PaidGrowthHomeSearchPixelEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", search_results=" + this.search_results + ", remarketing_ids=" + this.remarketing_ids + ", subtab=" + this.subtab + ", device_id=" + this.device_id + ", search_context=" + this.search_context + ", user_id=" + this.user_id + ", checkin_date=" + this.checkin_date + ", checkout_date=" + this.checkout_date + ", guests=" + this.guests + ", adults=" + this.adults + ", children=" + this.children + ", infants=" + this.infants + ", pets=" + this.pets + ", instant_book=" + this.instant_book + ", price_min=" + this.price_min + ", price_max=" + this.price_max + ", room_types=" + this.room_types + ", beds=" + this.beds + ", bedrooms=" + this.bedrooms + ", bathrooms=" + this.bathrooms + ", amenities=" + this.amenities + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
