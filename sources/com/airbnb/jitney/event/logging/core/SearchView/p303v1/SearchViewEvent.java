package com.airbnb.jitney.event.logging.core.SearchView.p303v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.jitney.event.logging.core.HelperMessage.p299v1.C2833HelperMessage;
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

/* renamed from: com.airbnb.jitney.event.logging.core.SearchView.v1.SearchViewEvent */
public final class SearchViewEvent implements Struct {
    public static final Adapter<SearchViewEvent, Builder> ADAPTER = new SearchViewEventAdapter();
    public final List<Long> amenities;
    public final Long bathrooms;
    public final Long bedrooms;
    public final Long beds;
    public final String checkin_date;
    public final String checkout_date;
    public final Context context;
    public final String event_name;
    public final Long guests;
    public final List<C2833HelperMessage> helper_messages;
    public final List<Long> languages;
    public final Long n_results;
    public final Double ne_lat;
    public final Double ne_lng;
    public final List<String> neighborhoods;
    public final String operation;
    public final String page;
    public final Long price_max;
    public final Long price_min;
    public final List<String> property_types;
    public final String raw_location;
    public final List<String> room_types;
    public final String schema;
    public final String search_id;
    public final Double sw_lat;
    public final Double sw_lng;

    /* renamed from: com.airbnb.jitney.event.logging.core.SearchView.v1.SearchViewEvent$Builder */
    public static final class Builder implements StructBuilder<SearchViewEvent> {
        /* access modifiers changed from: private */
        public List<Long> amenities;
        /* access modifiers changed from: private */
        public Long bathrooms;
        /* access modifiers changed from: private */
        public Long bedrooms;
        /* access modifiers changed from: private */
        public Long beds;
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
        public List<C2833HelperMessage> helper_messages;
        /* access modifiers changed from: private */
        public List<Long> languages;
        /* access modifiers changed from: private */
        public Long n_results;
        /* access modifiers changed from: private */
        public Double ne_lat;
        /* access modifiers changed from: private */
        public Double ne_lng;
        /* access modifiers changed from: private */
        public List<String> neighborhoods;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Long price_max;
        /* access modifiers changed from: private */
        public Long price_min;
        /* access modifiers changed from: private */
        public List<String> property_types;
        /* access modifiers changed from: private */
        public String raw_location;
        /* access modifiers changed from: private */
        public List<String> room_types;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String search_id;
        /* access modifiers changed from: private */
        public Double sw_lat;
        /* access modifiers changed from: private */
        public Double sw_lng;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.core.SearchView:SearchViewEvent:1.0.0";
            this.page = P3Arguments.FROM_P2;
            this.operation = "view";
            this.event_name = "search_view";
        }

        public Builder(String search_id2, Long guests2, Long n_results2, String raw_location2, Context context2) {
            this.schema = "com.airbnb.jitney.event.logging.core.SearchView:SearchViewEvent:1.0.0";
            this.search_id = search_id2;
            this.page = P3Arguments.FROM_P2;
            this.operation = "view";
            this.guests = guests2;
            this.n_results = n_results2;
            this.raw_location = raw_location2;
            this.context = context2;
            this.event_name = "search_view";
        }

        public Builder guests(Long guests2) {
            if (guests2 == null) {
                throw new NullPointerException("Required field 'guests' cannot be null");
            }
            this.guests = guests2;
            return this;
        }

        public Builder checkin_date(String checkin_date2) {
            this.checkin_date = checkin_date2;
            return this;
        }

        public Builder checkout_date(String checkout_date2) {
            this.checkout_date = checkout_date2;
            return this;
        }

        public Builder n_results(Long n_results2) {
            if (n_results2 == null) {
                throw new NullPointerException("Required field 'n_results' cannot be null");
            }
            this.n_results = n_results2;
            return this;
        }

        public Builder ne_lat(Double ne_lat2) {
            this.ne_lat = ne_lat2;
            return this;
        }

        public Builder ne_lng(Double ne_lng2) {
            this.ne_lng = ne_lng2;
            return this;
        }

        public Builder sw_lat(Double sw_lat2) {
            this.sw_lat = sw_lat2;
            return this;
        }

        public Builder sw_lng(Double sw_lng2) {
            this.sw_lng = sw_lng2;
            return this;
        }

        public Builder price_min(Long price_min2) {
            this.price_min = price_min2;
            return this;
        }

        public Builder price_max(Long price_max2) {
            this.price_max = price_max2;
            return this;
        }

        public Builder room_types(List<String> room_types2) {
            this.room_types = room_types2;
            return this;
        }

        public Builder amenities(List<Long> amenities2) {
            this.amenities = amenities2;
            return this;
        }

        public Builder languages(List<Long> languages2) {
            this.languages = languages2;
            return this;
        }

        public Builder bedrooms(Long bedrooms2) {
            this.bedrooms = bedrooms2;
            return this;
        }

        public Builder bathrooms(Long bathrooms2) {
            this.bathrooms = bathrooms2;
            return this;
        }

        public Builder beds(Long beds2) {
            this.beds = beds2;
            return this;
        }

        public Builder helper_messages(List<C2833HelperMessage> helper_messages2) {
            this.helper_messages = helper_messages2;
            return this;
        }

        public SearchViewEvent build() {
            if (this.search_id == null) {
                throw new IllegalStateException("Required field 'search_id' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.guests == null) {
                throw new IllegalStateException("Required field 'guests' is missing");
            } else if (this.n_results == null) {
                throw new IllegalStateException("Required field 'n_results' is missing");
            } else if (this.raw_location == null) {
                throw new IllegalStateException("Required field 'raw_location' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.event_name != null) {
                return new SearchViewEvent(this);
            } else {
                throw new IllegalStateException("Required field 'event_name' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.core.SearchView.v1.SearchViewEvent$SearchViewEventAdapter */
    private static final class SearchViewEventAdapter implements Adapter<SearchViewEvent, Builder> {
        private SearchViewEventAdapter() {
        }

        public void write(Protocol protocol, SearchViewEvent struct) throws IOException {
            protocol.writeStructBegin("SearchViewEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(PlacesIntents.INTENT_EXTRA_SEARCH_ID, 1, PassportService.SF_DG11);
            protocol.writeString(struct.search_id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 2, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 3, PassportService.SF_DG11);
            protocol.writeString(struct.operation);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 4, 10);
            protocol.writeI64(struct.guests.longValue());
            protocol.writeFieldEnd();
            if (struct.checkin_date != null) {
                protocol.writeFieldBegin("checkin_date", 5, PassportService.SF_DG11);
                protocol.writeString(struct.checkin_date);
                protocol.writeFieldEnd();
            }
            if (struct.checkout_date != null) {
                protocol.writeFieldBegin("checkout_date", 6, PassportService.SF_DG11);
                protocol.writeString(struct.checkout_date);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("n_results", 7, 10);
            protocol.writeI64(struct.n_results.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("raw_location", 8, PassportService.SF_DG11);
            protocol.writeString(struct.raw_location);
            protocol.writeFieldEnd();
            if (struct.ne_lat != null) {
                protocol.writeFieldBegin("ne_lat", 10, 4);
                protocol.writeDouble(struct.ne_lat.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.ne_lng != null) {
                protocol.writeFieldBegin("ne_lng", 11, 4);
                protocol.writeDouble(struct.ne_lng.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.sw_lat != null) {
                protocol.writeFieldBegin("sw_lat", 12, 4);
                protocol.writeDouble(struct.sw_lat.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.sw_lng != null) {
                protocol.writeFieldBegin("sw_lng", 13, 4);
                protocol.writeDouble(struct.sw_lng.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.price_min != null) {
                protocol.writeFieldBegin("price_min", 14, 10);
                protocol.writeI64(struct.price_min.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.price_max != null) {
                protocol.writeFieldBegin("price_max", 15, 10);
                protocol.writeI64(struct.price_max.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.room_types != null) {
                protocol.writeFieldBegin("room_types", 16, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.room_types.size());
                for (String item0 : struct.room_types) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.property_types != null) {
                protocol.writeFieldBegin("property_types", 17, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.property_types.size());
                for (String item02 : struct.property_types) {
                    protocol.writeString(item02);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.amenities != null) {
                protocol.writeFieldBegin("amenities", 18, 15);
                protocol.writeListBegin(10, struct.amenities.size());
                for (Long item03 : struct.amenities) {
                    protocol.writeI64(item03.longValue());
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.neighborhoods != null) {
                protocol.writeFieldBegin("neighborhoods", 19, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.neighborhoods.size());
                for (String item04 : struct.neighborhoods) {
                    protocol.writeString(item04);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.languages != null) {
                protocol.writeFieldBegin("languages", 20, 15);
                protocol.writeListBegin(10, struct.languages.size());
                for (Long item05 : struct.languages) {
                    protocol.writeI64(item05.longValue());
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.bedrooms != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BEDROOMS_KEY, 21, 10);
                protocol.writeI64(struct.bedrooms.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.bathrooms != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BATHROOMS_KEY, 22, 10);
                protocol.writeI64(struct.bathrooms.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.beds != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_BEDS_KEY, 23, 10);
                protocol.writeI64(struct.beds.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.helper_messages != null) {
                protocol.writeFieldBegin("helper_messages", 24, 15);
                protocol.writeListBegin(PassportService.SF_DG12, struct.helper_messages.size());
                for (C2833HelperMessage item06 : struct.helper_messages) {
                    C2833HelperMessage.ADAPTER.write(protocol, item06);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 25, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("event_name", 26, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SearchViewEvent(Builder builder) {
        List<C2833HelperMessage> list = null;
        this.schema = builder.schema;
        this.search_id = builder.search_id;
        this.page = builder.page;
        this.operation = builder.operation;
        this.guests = builder.guests;
        this.checkin_date = builder.checkin_date;
        this.checkout_date = builder.checkout_date;
        this.n_results = builder.n_results;
        this.raw_location = builder.raw_location;
        this.ne_lat = builder.ne_lat;
        this.ne_lng = builder.ne_lng;
        this.sw_lat = builder.sw_lat;
        this.sw_lng = builder.sw_lng;
        this.price_min = builder.price_min;
        this.price_max = builder.price_max;
        this.room_types = builder.room_types == null ? null : Collections.unmodifiableList(builder.room_types);
        this.property_types = builder.property_types == null ? null : Collections.unmodifiableList(builder.property_types);
        this.amenities = builder.amenities == null ? null : Collections.unmodifiableList(builder.amenities);
        this.neighborhoods = builder.neighborhoods == null ? null : Collections.unmodifiableList(builder.neighborhoods);
        this.languages = builder.languages == null ? null : Collections.unmodifiableList(builder.languages);
        this.bedrooms = builder.bedrooms;
        this.bathrooms = builder.bathrooms;
        this.beds = builder.beds;
        if (builder.helper_messages != null) {
            list = Collections.unmodifiableList(builder.helper_messages);
        }
        this.helper_messages = list;
        this.context = builder.context;
        this.event_name = builder.event_name;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SearchViewEvent)) {
            return false;
        }
        SearchViewEvent that = (SearchViewEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.search_id == that.search_id || this.search_id.equals(that.search_id)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.guests == that.guests || this.guests.equals(that.guests)) && ((this.checkin_date == that.checkin_date || (this.checkin_date != null && this.checkin_date.equals(that.checkin_date))) && ((this.checkout_date == that.checkout_date || (this.checkout_date != null && this.checkout_date.equals(that.checkout_date))) && ((this.n_results == that.n_results || this.n_results.equals(that.n_results)) && ((this.raw_location == that.raw_location || this.raw_location.equals(that.raw_location)) && ((this.ne_lat == that.ne_lat || (this.ne_lat != null && this.ne_lat.equals(that.ne_lat))) && ((this.ne_lng == that.ne_lng || (this.ne_lng != null && this.ne_lng.equals(that.ne_lng))) && ((this.sw_lat == that.sw_lat || (this.sw_lat != null && this.sw_lat.equals(that.sw_lat))) && ((this.sw_lng == that.sw_lng || (this.sw_lng != null && this.sw_lng.equals(that.sw_lng))) && ((this.price_min == that.price_min || (this.price_min != null && this.price_min.equals(that.price_min))) && ((this.price_max == that.price_max || (this.price_max != null && this.price_max.equals(that.price_max))) && ((this.room_types == that.room_types || (this.room_types != null && this.room_types.equals(that.room_types))) && ((this.property_types == that.property_types || (this.property_types != null && this.property_types.equals(that.property_types))) && ((this.amenities == that.amenities || (this.amenities != null && this.amenities.equals(that.amenities))) && ((this.neighborhoods == that.neighborhoods || (this.neighborhoods != null && this.neighborhoods.equals(that.neighborhoods))) && ((this.languages == that.languages || (this.languages != null && this.languages.equals(that.languages))) && ((this.bedrooms == that.bedrooms || (this.bedrooms != null && this.bedrooms.equals(that.bedrooms))) && ((this.bathrooms == that.bathrooms || (this.bathrooms != null && this.bathrooms.equals(that.bathrooms))) && ((this.beds == that.beds || (this.beds != null && this.beds.equals(that.beds))) && ((this.helper_messages == that.helper_messages || (this.helper_messages != null && this.helper_messages.equals(that.helper_messages))) && ((this.context == that.context || this.context.equals(that.context)) && (this.event_name == that.event_name || this.event_name.equals(that.event_name))))))))))))))))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((((((((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.search_id.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.guests.hashCode()) * -2128831035) ^ (this.checkin_date == null ? 0 : this.checkin_date.hashCode())) * -2128831035) ^ (this.checkout_date == null ? 0 : this.checkout_date.hashCode())) * -2128831035) ^ this.n_results.hashCode()) * -2128831035) ^ this.raw_location.hashCode()) * -2128831035) ^ (this.ne_lat == null ? 0 : this.ne_lat.hashCode())) * -2128831035) ^ (this.ne_lng == null ? 0 : this.ne_lng.hashCode())) * -2128831035) ^ (this.sw_lat == null ? 0 : this.sw_lat.hashCode())) * -2128831035) ^ (this.sw_lng == null ? 0 : this.sw_lng.hashCode())) * -2128831035) ^ (this.price_min == null ? 0 : this.price_min.hashCode())) * -2128831035) ^ (this.price_max == null ? 0 : this.price_max.hashCode())) * -2128831035) ^ (this.room_types == null ? 0 : this.room_types.hashCode())) * -2128831035) ^ (this.property_types == null ? 0 : this.property_types.hashCode())) * -2128831035) ^ (this.amenities == null ? 0 : this.amenities.hashCode())) * -2128831035) ^ (this.neighborhoods == null ? 0 : this.neighborhoods.hashCode())) * -2128831035) ^ (this.languages == null ? 0 : this.languages.hashCode())) * -2128831035) ^ (this.bedrooms == null ? 0 : this.bedrooms.hashCode())) * -2128831035) ^ (this.bathrooms == null ? 0 : this.bathrooms.hashCode())) * -2128831035) ^ (this.beds == null ? 0 : this.beds.hashCode())) * -2128831035;
        if (this.helper_messages != null) {
            i = this.helper_messages.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.event_name.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SearchViewEvent{schema=" + this.schema + ", search_id=" + this.search_id + ", page=" + this.page + ", operation=" + this.operation + ", guests=" + this.guests + ", checkin_date=" + this.checkin_date + ", checkout_date=" + this.checkout_date + ", n_results=" + this.n_results + ", raw_location=" + this.raw_location + ", ne_lat=" + this.ne_lat + ", ne_lng=" + this.ne_lng + ", sw_lat=" + this.sw_lat + ", sw_lng=" + this.sw_lng + ", price_min=" + this.price_min + ", price_max=" + this.price_max + ", room_types=" + this.room_types + ", property_types=" + this.property_types + ", amenities=" + this.amenities + ", neighborhoods=" + this.neighborhoods + ", languages=" + this.languages + ", bedrooms=" + this.bedrooms + ", bathrooms=" + this.bathrooms + ", beds=" + this.beds + ", helper_messages=" + this.helper_messages + ", context=" + this.context + ", event_name=" + this.event_name + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
