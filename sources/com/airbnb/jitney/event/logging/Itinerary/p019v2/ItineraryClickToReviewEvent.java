package com.airbnb.jitney.event.logging.Itinerary.p019v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.fragments.NPSFragment;
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

/* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickToReviewEvent */
public final class ItineraryClickToReviewEvent implements Struct {
    public static final Adapter<ItineraryClickToReviewEvent, Builder> ADAPTER = new ItineraryClickToReviewEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C0973SchedulableInfo parent_schedulable_info;
    public final Long review_id;
    public final String schema;
    public final String section;

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickToReviewEvent$Builder */
    public static final class Builder implements StructBuilder<ItineraryClickToReviewEvent> {
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
        public Long review_id;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String section;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Itinerary:ItineraryClickToReviewEvent:2.0.0";
            this.event_name = "itinerary_click_to_review";
            this.operation = C2451Operation.Click;
            this.section = NPSFragment.ARG_REVIEW;
        }

        public Builder(Context context2, String page2, C0973SchedulableInfo parent_schedulable_info2, Long review_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Itinerary:ItineraryClickToReviewEvent:2.0.0";
            this.event_name = "itinerary_click_to_review";
            this.context = context2;
            this.page = page2;
            this.operation = C2451Operation.Click;
            this.section = NPSFragment.ARG_REVIEW;
            this.parent_schedulable_info = parent_schedulable_info2;
            this.review_id = review_id2;
        }

        public ItineraryClickToReviewEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.section == null) {
                throw new IllegalStateException("Required field 'section' is missing");
            } else if (this.parent_schedulable_info == null) {
                throw new IllegalStateException("Required field 'parent_schedulable_info' is missing");
            } else if (this.review_id != null) {
                return new ItineraryClickToReviewEvent(this);
            } else {
                throw new IllegalStateException("Required field 'review_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Itinerary.v2.ItineraryClickToReviewEvent$ItineraryClickToReviewEventAdapter */
    private static final class ItineraryClickToReviewEventAdapter implements Adapter<ItineraryClickToReviewEvent, Builder> {
        private ItineraryClickToReviewEventAdapter() {
        }

        public void write(Protocol protocol, ItineraryClickToReviewEvent struct) throws IOException {
            protocol.writeStructBegin("ItineraryClickToReviewEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.SECTION, 5, PassportService.SF_DG11);
            protocol.writeString(struct.section);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("parent_schedulable_info", 6, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.parent_schedulable_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("review_id", 7, 10);
            protocol.writeI64(struct.review_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ItineraryClickToReviewEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.section = builder.section;
        this.parent_schedulable_info = builder.parent_schedulable_info;
        this.review_id = builder.review_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ItineraryClickToReviewEvent)) {
            return false;
        }
        ItineraryClickToReviewEvent that = (ItineraryClickToReviewEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.parent_schedulable_info == that.parent_schedulable_info || this.parent_schedulable_info.equals(that.parent_schedulable_info)) && (this.review_id == that.review_id || this.review_id.equals(that.review_id))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.parent_schedulable_info.hashCode()) * -2128831035) ^ this.review_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ItineraryClickToReviewEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", section=" + this.section + ", parent_schedulable_info=" + this.parent_schedulable_info + ", review_id=" + this.review_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
