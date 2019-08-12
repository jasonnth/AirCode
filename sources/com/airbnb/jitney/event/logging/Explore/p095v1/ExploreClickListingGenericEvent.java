package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
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

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickListingGenericEvent */
public final class ExploreClickListingGenericEvent implements Struct {
    public static final Adapter<ExploreClickListingGenericEvent, Builder> ADAPTER = new ExploreClickListingGenericEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final Long item_id;
    public final String item_type;
    public final String location;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String section_id;
    public final C2139ExploreSubtab subtab;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickListingGenericEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreClickListingGenericEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public Long item_id;
        /* access modifiers changed from: private */
        public String item_type;
        /* access modifiers changed from: private */
        public String location;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public String section_id;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreClickListingGenericEvent:1.0.0";
            this.event_name = "explore_click_listing_generic";
            this.page = P3Arguments.FROM_EXPLORE;
            this.operation = C2451Operation.Click;
            this.subtab = C2139ExploreSubtab.All;
        }

        public Builder(Context context2, Long item_id2, String item_type2, String section_id2, C2731SearchContext search_context2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreClickListingGenericEvent:1.0.0";
            this.event_name = "explore_click_listing_generic";
            this.context = context2;
            this.page = P3Arguments.FROM_EXPLORE;
            this.operation = C2451Operation.Click;
            this.item_id = item_id2;
            this.item_type = item_type2;
            this.section_id = section_id2;
            this.subtab = C2139ExploreSubtab.All;
            this.search_context = search_context2;
        }

        public Builder location(String location2) {
            this.location = location2;
            return this;
        }

        public Builder dates(List<String> dates2) {
            this.dates = dates2;
            return this;
        }

        public Builder guests(Long guests2) {
            this.guests = guests2;
            return this;
        }

        public ExploreClickListingGenericEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.item_id == null) {
                throw new IllegalStateException("Required field 'item_id' is missing");
            } else if (this.item_type == null) {
                throw new IllegalStateException("Required field 'item_type' is missing");
            } else if (this.section_id == null) {
                throw new IllegalStateException("Required field 'section_id' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.search_context != null) {
                return new ExploreClickListingGenericEvent(this);
            } else {
                throw new IllegalStateException("Required field 'search_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickListingGenericEvent$ExploreClickListingGenericEventAdapter */
    private static final class ExploreClickListingGenericEventAdapter implements Adapter<ExploreClickListingGenericEvent, Builder> {
        private ExploreClickListingGenericEventAdapter() {
        }

        public void write(Protocol protocol, ExploreClickListingGenericEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreClickListingGenericEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("item_id", 5, 10);
            protocol.writeI64(struct.item_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("item_type", 6, PassportService.SF_DG11);
            protocol.writeString(struct.item_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("section_id", 7, PassportService.SF_DG11);
            protocol.writeString(struct.section_id);
            protocol.writeFieldEnd();
            if (struct.location != null) {
                protocol.writeFieldBegin("location", 8, PassportService.SF_DG11);
                protocol.writeString(struct.location);
                protocol.writeFieldEnd();
            }
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
            protocol.writeFieldBegin("subtab", 11, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 12, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreClickListingGenericEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.item_id = builder.item_id;
        this.item_type = builder.item_type;
        this.section_id = builder.section_id;
        this.location = builder.location;
        this.dates = builder.dates == null ? null : Collections.unmodifiableList(builder.dates);
        this.guests = builder.guests;
        this.subtab = builder.subtab;
        this.search_context = builder.search_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreClickListingGenericEvent)) {
            return false;
        }
        ExploreClickListingGenericEvent that = (ExploreClickListingGenericEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.item_id == that.item_id || this.item_id.equals(that.item_id)) && ((this.item_type == that.item_type || this.item_type.equals(that.item_type)) && ((this.section_id == that.section_id || this.section_id.equals(that.section_id)) && ((this.location == that.location || (this.location != null && this.location.equals(that.location))) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context)))))))))))))) {
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
        int code = (((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.item_id.hashCode()) * -2128831035) ^ this.item_type.hashCode()) * -2128831035) ^ this.section_id.hashCode()) * -2128831035) ^ (this.location == null ? 0 : this.location.hashCode())) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035;
        if (this.guests != null) {
            i = this.guests.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExploreClickListingGenericEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", item_id=" + this.item_id + ", item_type=" + this.item_type + ", section_id=" + this.section_id + ", location=" + this.location + ", dates=" + this.dates + ", guests=" + this.guests + ", subtab=" + this.subtab + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
