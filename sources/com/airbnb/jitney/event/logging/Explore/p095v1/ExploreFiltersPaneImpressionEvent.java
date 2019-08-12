package com.airbnb.jitney.event.logging.Explore.p095v1;

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
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneImpressionEvent */
public final class ExploreFiltersPaneImpressionEvent implements Struct {
    public static final Adapter<ExploreFiltersPaneImpressionEvent, Builder> ADAPTER = new ExploreFiltersPaneImpressionEventAdapter();
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
    public final C2451Operation operation;
    public final String page;
    public final Boolean pets;
    public final List<Long> price_range;
    public final List<C2680RoomType> room_types;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String section;
    public final C2139ExploreSubtab subtab;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneImpressionEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreFiltersPaneImpressionEvent> {
        /* access modifiers changed from: private */
        public Long adults;
        /* access modifiers changed from: private */
        public List<Long> amenities;
        /* access modifiers changed from: private */
        public Double bathrooms;
        /* access modifiers changed from: private */
        public Long bedrooms;
        /* access modifiers changed from: private */
        public Long beds;
        /* access modifiers changed from: private */
        public Long children;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public Long infants;
        /* access modifiers changed from: private */
        public Boolean instant_book;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Boolean pets;
        /* access modifiers changed from: private */
        public List<Long> price_range;
        /* access modifiers changed from: private */
        public List<C2680RoomType> room_types;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public String section;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreFiltersPaneImpressionEvent:1.0.1";
            this.event_name = "explore_filters_pane_impression";
            this.page = "inline_filters";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, String section2, C2139ExploreSubtab subtab2, C2731SearchContext search_context2, List<String> dates2, Long guests2, Boolean pets2, Boolean instant_book2, List<Long> price_range2, List<C2680RoomType> room_types2, Long beds2, Long bedrooms2, Double bathrooms2, List<Long> amenities2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreFiltersPaneImpressionEvent:1.0.1";
            this.event_name = "explore_filters_pane_impression";
            this.context = context2;
            this.page = "inline_filters";
            this.section = section2;
            this.operation = C2451Operation.Impression;
            this.subtab = subtab2;
            this.search_context = search_context2;
            this.dates = dates2;
            this.guests = guests2;
            this.pets = pets2;
            this.instant_book = instant_book2;
            this.price_range = price_range2;
            this.room_types = room_types2;
            this.beds = beds2;
            this.bedrooms = bedrooms2;
            this.bathrooms = bathrooms2;
            this.amenities = amenities2;
        }

        public ExploreFiltersPaneImpressionEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.section == null) {
                throw new IllegalStateException("Required field 'section' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.search_context == null) {
                throw new IllegalStateException("Required field 'search_context' is missing");
            } else if (this.dates == null) {
                throw new IllegalStateException("Required field 'dates' is missing");
            } else if (this.guests == null) {
                throw new IllegalStateException("Required field 'guests' is missing");
            } else if (this.pets == null) {
                throw new IllegalStateException("Required field 'pets' is missing");
            } else if (this.instant_book == null) {
                throw new IllegalStateException("Required field 'instant_book' is missing");
            } else if (this.price_range == null) {
                throw new IllegalStateException("Required field 'price_range' is missing");
            } else if (this.room_types == null) {
                throw new IllegalStateException("Required field 'room_types' is missing");
            } else if (this.beds == null) {
                throw new IllegalStateException("Required field 'beds' is missing");
            } else if (this.bedrooms == null) {
                throw new IllegalStateException("Required field 'bedrooms' is missing");
            } else if (this.bathrooms == null) {
                throw new IllegalStateException("Required field 'bathrooms' is missing");
            } else if (this.amenities != null) {
                return new ExploreFiltersPaneImpressionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'amenities' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneImpressionEvent$ExploreFiltersPaneImpressionEventAdapter */
    private static final class ExploreFiltersPaneImpressionEventAdapter implements Adapter<ExploreFiltersPaneImpressionEvent, Builder> {
        private ExploreFiltersPaneImpressionEventAdapter() {
        }

        public void write(Protocol protocol, ExploreFiltersPaneImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreFiltersPaneImpressionEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab", 6, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 7, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 8, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
            for (String item0 : struct.dates) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 9, 10);
            protocol.writeI64(struct.guests.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.PETS, 10, 2);
            protocol.writeBool(struct.pets.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingAnalytics.INSTANT_BOOK, 11, 2);
            protocol.writeBool(struct.instant_book.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.PRICE_RANGE, 12, 15);
            protocol.writeListBegin(10, struct.price_range.size());
            for (Long item02 : struct.price_range) {
                protocol.writeI64(item02.longValue());
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("room_types", 13, 15);
            protocol.writeListBegin(16, struct.room_types.size());
            for (C2680RoomType item03 : struct.room_types) {
                protocol.writeI32(item03.value);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ListingRequestConstants.JSON_BEDS_KEY, 14, 10);
            protocol.writeI64(struct.beds.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ListingRequestConstants.JSON_BEDROOMS_KEY, 15, 10);
            protocol.writeI64(struct.bedrooms.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ListingRequestConstants.JSON_BATHROOMS_KEY, 16, 4);
            protocol.writeDouble(struct.bathrooms.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("amenities", 17, 15);
            protocol.writeListBegin(10, struct.amenities.size());
            for (Long item04 : struct.amenities) {
                protocol.writeI64(item04.longValue());
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            if (struct.adults != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.ADULTS, 18, 10);
                protocol.writeI64(struct.adults.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.children != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.CHILDREN, 19, 10);
                protocol.writeI64(struct.children.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.infants != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.INFANTS, 20, 10);
                protocol.writeI64(struct.infants.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreFiltersPaneImpressionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.operation = builder.operation;
        this.subtab = builder.subtab;
        this.search_context = builder.search_context;
        this.dates = Collections.unmodifiableList(builder.dates);
        this.guests = builder.guests;
        this.pets = builder.pets;
        this.instant_book = builder.instant_book;
        this.price_range = Collections.unmodifiableList(builder.price_range);
        this.room_types = Collections.unmodifiableList(builder.room_types);
        this.beds = builder.beds;
        this.bedrooms = builder.bedrooms;
        this.bathrooms = builder.bathrooms;
        this.amenities = Collections.unmodifiableList(builder.amenities);
        this.adults = builder.adults;
        this.children = builder.children;
        this.infants = builder.infants;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreFiltersPaneImpressionEvent)) {
            return false;
        }
        ExploreFiltersPaneImpressionEvent that = (ExploreFiltersPaneImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.search_context == that.search_context || this.search_context.equals(that.search_context)) && ((this.dates == that.dates || this.dates.equals(that.dates)) && ((this.guests == that.guests || this.guests.equals(that.guests)) && ((this.pets == that.pets || this.pets.equals(that.pets)) && ((this.instant_book == that.instant_book || this.instant_book.equals(that.instant_book)) && ((this.price_range == that.price_range || this.price_range.equals(that.price_range)) && ((this.room_types == that.room_types || this.room_types.equals(that.room_types)) && ((this.beds == that.beds || this.beds.equals(that.beds)) && ((this.bedrooms == that.bedrooms || this.bedrooms.equals(that.bedrooms)) && ((this.bathrooms == that.bathrooms || this.bathrooms.equals(that.bathrooms)) && ((this.amenities == that.amenities || this.amenities.equals(that.amenities)) && ((this.adults == that.adults || (this.adults != null && this.adults.equals(that.adults))) && (this.children == that.children || (this.children != null && this.children.equals(that.children)))))))))))))))))))))) {
            if (this.infants == that.infants) {
                return true;
            }
            if (this.infants != null && this.infants.equals(that.infants)) {
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
        int code = (((((((((((((((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035) ^ this.guests.hashCode()) * -2128831035) ^ this.pets.hashCode()) * -2128831035) ^ this.instant_book.hashCode()) * -2128831035) ^ this.price_range.hashCode()) * -2128831035) ^ this.room_types.hashCode()) * -2128831035) ^ this.beds.hashCode()) * -2128831035) ^ this.bedrooms.hashCode()) * -2128831035) ^ this.bathrooms.hashCode()) * -2128831035) ^ this.amenities.hashCode()) * -2128831035) ^ (this.adults == null ? 0 : this.adults.hashCode())) * -2128831035) ^ (this.children == null ? 0 : this.children.hashCode())) * -2128831035;
        if (this.infants != null) {
            i = this.infants.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExploreFiltersPaneImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", operation=" + this.operation + ", subtab=" + this.subtab + ", search_context=" + this.search_context + ", dates=" + this.dates + ", guests=" + this.guests + ", pets=" + this.pets + ", instant_book=" + this.instant_book + ", price_range=" + this.price_range + ", room_types=" + this.room_types + ", beds=" + this.beds + ", bedrooms=" + this.bedrooms + ", bathrooms=" + this.bathrooms + ", amenities=" + this.amenities + ", adults=" + this.adults + ", children=" + this.children + ", infants=" + this.infants + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
