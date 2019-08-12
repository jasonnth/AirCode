package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
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
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreSwipeCarouselEvent */
public final class ExploreSwipeCarouselEvent implements Struct {
    public static final Adapter<ExploreSwipeCarouselEvent, Builder> ADAPTER = new ExploreSwipeCarouselEventAdapter();
    public final String carousel_title;
    public final Context context;
    public final List<String> dates;
    public final C0940Direction direction;
    public final String event_name;
    public final Long guests;
    public final String location;
    public final Long max_scroll_item_index;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String section;
    public final Long section_offset;
    public final C2139ExploreSubtab subtab;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreSwipeCarouselEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreSwipeCarouselEvent> {
        /* access modifiers changed from: private */
        public String carousel_title;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates;
        /* access modifiers changed from: private */
        public C0940Direction direction;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public String location;
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
        public Long section_offset;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreSwipeCarouselEvent:1.0.0";
            this.event_name = "explore_swipe_carousel";
            this.page = P3Arguments.FROM_EXPLORE;
            this.section = "list";
            this.target = "carousel";
            this.operation = C2451Operation.Swipe;
        }

        public Builder(Context context2, String carousel_title2, Long section_offset2, Long max_scroll_item_index2, C0940Direction direction2, C2139ExploreSubtab subtab2, C2731SearchContext search_context2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreSwipeCarouselEvent:1.0.0";
            this.event_name = "explore_swipe_carousel";
            this.context = context2;
            this.page = P3Arguments.FROM_EXPLORE;
            this.section = "list";
            this.target = "carousel";
            this.carousel_title = carousel_title2;
            this.section_offset = section_offset2;
            this.operation = C2451Operation.Swipe;
            this.max_scroll_item_index = max_scroll_item_index2;
            this.direction = direction2;
            this.subtab = subtab2;
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

        public ExploreSwipeCarouselEvent build() {
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
            } else if (this.carousel_title == null) {
                throw new IllegalStateException("Required field 'carousel_title' is missing");
            } else if (this.section_offset == null) {
                throw new IllegalStateException("Required field 'section_offset' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.max_scroll_item_index == null) {
                throw new IllegalStateException("Required field 'max_scroll_item_index' is missing");
            } else if (this.direction == null) {
                throw new IllegalStateException("Required field 'direction' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.search_context != null) {
                return new ExploreSwipeCarouselEvent(this);
            } else {
                throw new IllegalStateException("Required field 'search_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreSwipeCarouselEvent$ExploreSwipeCarouselEventAdapter */
    private static final class ExploreSwipeCarouselEventAdapter implements Adapter<ExploreSwipeCarouselEvent, Builder> {
        private ExploreSwipeCarouselEventAdapter() {
        }

        public void write(Protocol protocol, ExploreSwipeCarouselEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreSwipeCarouselEvent");
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
            protocol.writeFieldBegin("carousel_title", 6, PassportService.SF_DG11);
            protocol.writeString(struct.carousel_title);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("section_offset", 7, 10);
            protocol.writeI64(struct.section_offset.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 8, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("max_scroll_item_index", 9, 10);
            protocol.writeI64(struct.max_scroll_item_index.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("direction", 10, 8);
            protocol.writeI32(struct.direction.value);
            protocol.writeFieldEnd();
            if (struct.location != null) {
                protocol.writeFieldBegin("location", 11, PassportService.SF_DG11);
                protocol.writeString(struct.location);
                protocol.writeFieldEnd();
            }
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 12, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 13, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("subtab", 14, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 15, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreSwipeCarouselEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.target = builder.target;
        this.carousel_title = builder.carousel_title;
        this.section_offset = builder.section_offset;
        this.operation = builder.operation;
        this.max_scroll_item_index = builder.max_scroll_item_index;
        this.direction = builder.direction;
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
        if (!(other instanceof ExploreSwipeCarouselEvent)) {
            return false;
        }
        ExploreSwipeCarouselEvent that = (ExploreSwipeCarouselEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.carousel_title == that.carousel_title || this.carousel_title.equals(that.carousel_title)) && ((this.section_offset == that.section_offset || this.section_offset.equals(that.section_offset)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.max_scroll_item_index == that.max_scroll_item_index || this.max_scroll_item_index.equals(that.max_scroll_item_index)) && ((this.direction == that.direction || this.direction.equals(that.direction)) && ((this.location == that.location || (this.location != null && this.location.equals(that.location))) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context))))))))))))))))) {
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
        int code = (((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.carousel_title.hashCode()) * -2128831035) ^ this.section_offset.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.max_scroll_item_index.hashCode()) * -2128831035) ^ this.direction.hashCode()) * -2128831035) ^ (this.location == null ? 0 : this.location.hashCode())) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035;
        if (this.guests != null) {
            i = this.guests.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExploreSwipeCarouselEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", target=" + this.target + ", carousel_title=" + this.carousel_title + ", section_offset=" + this.section_offset + ", operation=" + this.operation + ", max_scroll_item_index=" + this.max_scroll_item_index + ", direction=" + this.direction + ", location=" + this.location + ", dates=" + this.dates + ", guests=" + this.guests + ", subtab=" + this.subtab + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
