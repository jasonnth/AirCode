package com.airbnb.jitney.event.logging.Itinerary.p019v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SchedulableInfo.p023v2.C0973SchedulableInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickItineraryRecommendationsCarouselEvent */
public final class ItineraryClickItineraryRecommendationsCarouselEvent implements Struct {
    public static final Adapter<ItineraryClickItineraryRecommendationsCarouselEvent, Builder> ADAPTER = new ItineraryClickItineraryRecommendationsCarouselEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C0973SchedulableInfo parent_schedulable_info;
    public final C0973SchedulableInfo schedulable_info;
    public final String schema;
    public final String section;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickItineraryRecommendationsCarouselEvent$Builder */
    public static final class Builder implements StructBuilder<ItineraryClickItineraryRecommendationsCarouselEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public C0973SchedulableInfo parent_schedulable_info;
        /* access modifiers changed from: private */
        public C0973SchedulableInfo schedulable_info;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String section;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Itinerary:ItineraryClickItineraryRecommendationsCarouselEvent:2.0.0";
            this.event_name = "itinerary_click_itinerary_recommendations_carousel";
            this.page = "t1";
            this.section = "list";
            this.target = "suggestions_carousel";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C0973SchedulableInfo schedulable_info2, C0973SchedulableInfo parent_schedulable_info2) {
            this.schema = "com.airbnb.jitney.event.logging.Itinerary:ItineraryClickItineraryRecommendationsCarouselEvent:2.0.0";
            this.event_name = "itinerary_click_itinerary_recommendations_carousel";
            this.context = context2;
            this.page = "t1";
            this.section = "list";
            this.target = "suggestions_carousel";
            this.operation = C2451Operation.Click;
            this.schedulable_info = schedulable_info2;
            this.parent_schedulable_info = parent_schedulable_info2;
        }

        public ItineraryClickItineraryRecommendationsCarouselEvent build() {
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
            } else if (this.schedulable_info == null) {
                throw new IllegalStateException("Required field 'schedulable_info' is missing");
            } else if (this.parent_schedulable_info != null) {
                return new ItineraryClickItineraryRecommendationsCarouselEvent(this);
            } else {
                throw new IllegalStateException("Required field 'parent_schedulable_info' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickItineraryRecommendationsCarouselEvent$ItineraryClickItineraryRecommendationsCarouselEventAdapter */
    private static final class ItineraryClickItineraryRecommendationsCarouselEventAdapter implements Adapter<ItineraryClickItineraryRecommendationsCarouselEvent, Builder> {
        private ItineraryClickItineraryRecommendationsCarouselEventAdapter() {
        }

        public void write(Protocol protocol, ItineraryClickItineraryRecommendationsCarouselEvent struct) throws IOException {
            protocol.writeStructBegin("ItineraryClickItineraryRecommendationsCarouselEvent");
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
            protocol.writeFieldBegin("schedulable_info", 7, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.schedulable_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("parent_schedulable_info", 8, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.parent_schedulable_info);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ItineraryClickItineraryRecommendationsCarouselEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.target = builder.target;
        this.operation = builder.operation;
        this.schedulable_info = builder.schedulable_info;
        this.parent_schedulable_info = builder.parent_schedulable_info;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ItineraryClickItineraryRecommendationsCarouselEvent)) {
            return false;
        }
        ItineraryClickItineraryRecommendationsCarouselEvent that = (ItineraryClickItineraryRecommendationsCarouselEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.schedulable_info == that.schedulable_info || this.schedulable_info.equals(that.schedulable_info)) && (this.parent_schedulable_info == that.parent_schedulable_info || this.parent_schedulable_info.equals(that.parent_schedulable_info)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.schedulable_info.hashCode()) * -2128831035) ^ this.parent_schedulable_info.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ItineraryClickItineraryRecommendationsCarouselEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", target=" + this.target + ", operation=" + this.operation + ", schedulable_info=" + this.schedulable_info + ", parent_schedulable_info=" + this.parent_schedulable_info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
