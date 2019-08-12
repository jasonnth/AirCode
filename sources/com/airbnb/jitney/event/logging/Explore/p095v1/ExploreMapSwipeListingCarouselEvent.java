package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.jitney.event.logging.Direction.p012v1.C0940Direction;
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
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreMapSwipeListingCarouselEvent */
public final class ExploreMapSwipeListingCarouselEvent implements Struct {
    public static final Adapter<ExploreMapSwipeListingCarouselEvent, Builder> ADAPTER = new ExploreMapSwipeListingCarouselEventAdapter();
    public final Context context;
    public final C0940Direction direction;
    public final String event_name;
    public final Long listing_id;
    public final Long max_scroll_item_index;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String section;
    public final C2139ExploreSubtab subtab;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreMapSwipeListingCarouselEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreMapSwipeListingCarouselEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public C0940Direction direction;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public Long max_scroll_item_index;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public String section;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreMapSwipeListingCarouselEvent:1.0.0";
            this.event_name = "explore_map_swipe_listing_carousel";
            this.page = P3Arguments.FROM_EXPLORE;
            this.section = P3Arguments.FROM_MAP;
            this.target = "listing_carousel";
            this.operation = C2451Operation.Swipe;
        }

        public Builder(Context context2, C0940Direction direction2, C2139ExploreSubtab subtab2, C2731SearchContext search_context2, Long listing_id2, Long max_scroll_item_index2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreMapSwipeListingCarouselEvent:1.0.0";
            this.event_name = "explore_map_swipe_listing_carousel";
            this.context = context2;
            this.page = P3Arguments.FROM_EXPLORE;
            this.section = P3Arguments.FROM_MAP;
            this.target = "listing_carousel";
            this.operation = C2451Operation.Swipe;
            this.direction = direction2;
            this.subtab = subtab2;
            this.search_context = search_context2;
            this.listing_id = listing_id2;
            this.max_scroll_item_index = max_scroll_item_index2;
        }

        public ExploreMapSwipeListingCarouselEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.section == null) {
                throw new IllegalStateException("Required field 'section' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.direction == null) {
                throw new IllegalStateException("Required field 'direction' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.search_context == null) {
                throw new IllegalStateException("Required field 'search_context' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.max_scroll_item_index != null) {
                return new ExploreMapSwipeListingCarouselEvent(this);
            } else {
                throw new IllegalStateException("Required field 'max_scroll_item_index' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreMapSwipeListingCarouselEvent$ExploreMapSwipeListingCarouselEventAdapter */
    private static final class ExploreMapSwipeListingCarouselEventAdapter implements Adapter<ExploreMapSwipeListingCarouselEvent, Builder> {
        private ExploreMapSwipeListingCarouselEventAdapter() {
        }

        public void write(Protocol protocol, ExploreMapSwipeListingCarouselEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreMapSwipeListingCarouselEvent");
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
            protocol.writeFieldBegin("direction", 7, 8);
            protocol.writeI32(struct.direction.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab", 8, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 9, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 10, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("max_scroll_item_index", 11, 10);
            protocol.writeI64(struct.max_scroll_item_index.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreMapSwipeListingCarouselEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.target = builder.target;
        this.operation = builder.operation;
        this.direction = builder.direction;
        this.subtab = builder.subtab;
        this.search_context = builder.search_context;
        this.listing_id = builder.listing_id;
        this.max_scroll_item_index = builder.max_scroll_item_index;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreMapSwipeListingCarouselEvent)) {
            return false;
        }
        ExploreMapSwipeListingCarouselEvent that = (ExploreMapSwipeListingCarouselEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.direction == that.direction || this.direction.equals(that.direction)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.search_context == that.search_context || this.search_context.equals(that.search_context)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.max_scroll_item_index == that.max_scroll_item_index || this.max_scroll_item_index.equals(that.max_scroll_item_index))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.direction.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.max_scroll_item_index.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExploreMapSwipeListingCarouselEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", target=" + this.target + ", operation=" + this.operation + ", direction=" + this.direction + ", subtab=" + this.subtab + ", search_context=" + this.search_context + ", listing_id=" + this.listing_id + ", max_scroll_item_index=" + this.max_scroll_item_index + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
