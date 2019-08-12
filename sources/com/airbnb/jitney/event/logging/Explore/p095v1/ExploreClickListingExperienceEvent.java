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

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickListingExperienceEvent */
public final class ExploreClickListingExperienceEvent implements Struct {
    public static final Adapter<ExploreClickListingExperienceEvent, Builder> ADAPTER = new ExploreClickListingExperienceEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long experience_id;
    public final Long guests;
    public final String location;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final C2139ExploreSubtab subtab;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickListingExperienceEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreClickListingExperienceEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long experience_id;
        /* access modifiers changed from: private */
        public Long guests;
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
        public C2139ExploreSubtab subtab;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreClickListingExperienceEvent:1.0.0";
            this.event_name = "explore_click_listing_experience";
            this.page = P3Arguments.FROM_EXPLORE;
            this.target = "listing_experience";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, String location2, List<String> dates2, Long guests2, Long experience_id2, C2139ExploreSubtab subtab2, C2731SearchContext search_context2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreClickListingExperienceEvent:1.0.0";
            this.event_name = "explore_click_listing_experience";
            this.context = context2;
            this.page = P3Arguments.FROM_EXPLORE;
            this.target = "listing_experience";
            this.operation = C2451Operation.Click;
            this.location = location2;
            this.dates = dates2;
            this.guests = guests2;
            this.experience_id = experience_id2;
            this.subtab = subtab2;
            this.search_context = search_context2;
        }

        public ExploreClickListingExperienceEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.location == null) {
                throw new IllegalStateException("Required field 'location' is missing");
            } else if (this.dates == null) {
                throw new IllegalStateException("Required field 'dates' is missing");
            } else if (this.guests == null) {
                throw new IllegalStateException("Required field 'guests' is missing");
            } else if (this.experience_id == null) {
                throw new IllegalStateException("Required field 'experience_id' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.search_context != null) {
                return new ExploreClickListingExperienceEvent(this);
            } else {
                throw new IllegalStateException("Required field 'search_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickListingExperienceEvent$ExploreClickListingExperienceEventAdapter */
    private static final class ExploreClickListingExperienceEventAdapter implements Adapter<ExploreClickListingExperienceEvent, Builder> {
        private ExploreClickListingExperienceEventAdapter() {
        }

        public void write(Protocol protocol, ExploreClickListingExperienceEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreClickListingExperienceEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("location", 6, PassportService.SF_DG11);
            protocol.writeString(struct.location);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 7, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
            for (String item0 : struct.dates) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 8, 10);
            protocol.writeI64(struct.guests.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("experience_id", 9, 10);
            protocol.writeI64(struct.experience_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab", 10, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 11, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreClickListingExperienceEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.location = builder.location;
        this.dates = Collections.unmodifiableList(builder.dates);
        this.guests = builder.guests;
        this.experience_id = builder.experience_id;
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
        if (!(other instanceof ExploreClickListingExperienceEvent)) {
            return false;
        }
        ExploreClickListingExperienceEvent that = (ExploreClickListingExperienceEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.location == that.location || this.location.equals(that.location)) && ((this.dates == that.dates || this.dates.equals(that.dates)) && ((this.guests == that.guests || this.guests.equals(that.guests)) && ((this.experience_id == that.experience_id || this.experience_id.equals(that.experience_id)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.location.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035) ^ this.guests.hashCode()) * -2128831035) ^ this.experience_id.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExploreClickListingExperienceEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", location=" + this.location + ", dates=" + this.dates + ", guests=" + this.guests + ", experience_id=" + this.experience_id + ", subtab=" + this.subtab + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
