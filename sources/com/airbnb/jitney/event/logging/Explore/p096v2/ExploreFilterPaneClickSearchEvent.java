package com.airbnb.jitney.event.logging.Explore.p096v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
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

/* renamed from: com.airbnb.jitney.event.logging.Explore.v2.ExploreFilterPaneClickSearchEvent */
public final class ExploreFilterPaneClickSearchEvent implements Struct {
    public static final Adapter<ExploreFilterPaneClickSearchEvent, Object> ADAPTER = new ExploreFilterPaneClickSearchEventAdapter();
    public final Long adults;
    public final List<Long> amenities;
    public final Double bathrooms;
    public final Long bedrooms;
    public final Long beds;
    public final Long children;
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final Long infants;
    public final Boolean instant_book;
    public final Long n_results;
    public final C2451Operation operation;
    public final String page;
    public final Boolean pets;
    public final List<Long> price_range;
    public final List<C2680RoomType> room_types;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String section;
    public final C2139ExploreSubtab subtab;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v2.ExploreFilterPaneClickSearchEvent$ExploreFilterPaneClickSearchEventAdapter */
    private static final class ExploreFilterPaneClickSearchEventAdapter implements Adapter<ExploreFilterPaneClickSearchEvent, Object> {
        private ExploreFilterPaneClickSearchEventAdapter() {
        }

        public void write(Protocol protocol, ExploreFilterPaneClickSearchEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreFilterPaneClickSearchEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.SECTION, 4, PassportService.SF_DG11);
            protocol.writeString(struct.section);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab", 7, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 8, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 9, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 10, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.adults != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.ADULTS, 11, 10);
                protocol.writeI64(struct.adults.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.children != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.CHILDREN, 12, 10);
                protocol.writeI64(struct.children.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.infants != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.INFANTS, 13, 10);
                protocol.writeI64(struct.infants.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.pets != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.PETS, 14, 2);
                protocol.writeBool(struct.pets.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.instant_book != null) {
                protocol.writeFieldBegin(ManageListingAnalytics.INSTANT_BOOK, 15, 2);
                protocol.writeBool(struct.instant_book.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.price_range != null) {
                protocol.writeFieldBegin(PlaceFields.PRICE_RANGE, 16, 15);
                protocol.writeListBegin(10, struct.price_range.size());
                for (Long item02 : struct.price_range) {
                    protocol.writeI64(item02.longValue());
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.room_types != null) {
                protocol.writeFieldBegin("room_types", 17, 15);
                protocol.writeListBegin(16, struct.room_types.size());
                for (C2680RoomType item03 : struct.room_types) {
                    protocol.writeI32(item03.value);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.beds != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BEDS_KEY, 18, 10);
                protocol.writeI64(struct.beds.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.bedrooms != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BEDROOMS_KEY, 19, 10);
                protocol.writeI64(struct.bedrooms.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.bathrooms != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BATHROOMS_KEY, 20, 4);
                protocol.writeDouble(struct.bathrooms.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.amenities != null) {
                protocol.writeFieldBegin("amenities", 21, 15);
                protocol.writeListBegin(10, struct.amenities.size());
                for (Long item04 : struct.amenities) {
                    protocol.writeI64(item04.longValue());
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.n_results != null) {
                protocol.writeFieldBegin("n_results", 22, 10);
                protocol.writeI64(struct.n_results.longValue());
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
        if (!(other instanceof ExploreFilterPaneClickSearchEvent)) {
            return false;
        }
        ExploreFilterPaneClickSearchEvent that = (ExploreFilterPaneClickSearchEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.search_context == that.search_context || this.search_context.equals(that.search_context)) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.adults == that.adults || (this.adults != null && this.adults.equals(that.adults))) && ((this.children == that.children || (this.children != null && this.children.equals(that.children))) && ((this.infants == that.infants || (this.infants != null && this.infants.equals(that.infants))) && ((this.pets == that.pets || (this.pets != null && this.pets.equals(that.pets))) && ((this.instant_book == that.instant_book || (this.instant_book != null && this.instant_book.equals(that.instant_book))) && ((this.price_range == that.price_range || (this.price_range != null && this.price_range.equals(that.price_range))) && ((this.room_types == that.room_types || (this.room_types != null && this.room_types.equals(that.room_types))) && ((this.beds == that.beds || (this.beds != null && this.beds.equals(that.beds))) && ((this.bedrooms == that.bedrooms || (this.bedrooms != null && this.bedrooms.equals(that.bedrooms))) && ((this.bathrooms == that.bathrooms || (this.bathrooms != null && this.bathrooms.equals(that.bathrooms))) && (this.amenities == that.amenities || (this.amenities != null && this.amenities.equals(that.amenities)))))))))))))))))))))))) {
            if (this.n_results == that.n_results) {
                return true;
            }
            if (this.n_results != null && this.n_results.equals(that.n_results)) {
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
        int code = (((((((((((((((((((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.adults == null ? 0 : this.adults.hashCode())) * -2128831035) ^ (this.children == null ? 0 : this.children.hashCode())) * -2128831035) ^ (this.infants == null ? 0 : this.infants.hashCode())) * -2128831035) ^ (this.pets == null ? 0 : this.pets.hashCode())) * -2128831035) ^ (this.instant_book == null ? 0 : this.instant_book.hashCode())) * -2128831035) ^ (this.price_range == null ? 0 : this.price_range.hashCode())) * -2128831035) ^ (this.room_types == null ? 0 : this.room_types.hashCode())) * -2128831035) ^ (this.beds == null ? 0 : this.beds.hashCode())) * -2128831035) ^ (this.bedrooms == null ? 0 : this.bedrooms.hashCode())) * -2128831035) ^ (this.bathrooms == null ? 0 : this.bathrooms.hashCode())) * -2128831035) ^ (this.amenities == null ? 0 : this.amenities.hashCode())) * -2128831035;
        if (this.n_results != null) {
            i = this.n_results.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExploreFilterPaneClickSearchEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", target=" + this.target + ", operation=" + this.operation + ", subtab=" + this.subtab + ", search_context=" + this.search_context + ", dates=" + this.dates + ", guests=" + this.guests + ", adults=" + this.adults + ", children=" + this.children + ", infants=" + this.infants + ", pets=" + this.pets + ", instant_book=" + this.instant_book + ", price_range=" + this.price_range + ", room_types=" + this.room_types + ", beds=" + this.beds + ", bedrooms=" + this.bedrooms + ", bathrooms=" + this.bathrooms + ", amenities=" + this.amenities + ", n_results=" + this.n_results + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
