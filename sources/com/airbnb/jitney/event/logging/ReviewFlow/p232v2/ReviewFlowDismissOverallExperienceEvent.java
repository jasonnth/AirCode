package com.airbnb.jitney.event.logging.ReviewFlow.p232v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SchedulableInfo.p023v2.C0973SchedulableInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ReviewFlow.v2.ReviewFlowDismissOverallExperienceEvent */
public final class ReviewFlowDismissOverallExperienceEvent implements Struct {
    public static final Adapter<ReviewFlowDismissOverallExperienceEvent, Object> ADAPTER = new ReviewFlowDismissOverallExperienceEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Long review_rating;
    public final C0973SchedulableInfo schedulable_info;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ReviewFlow.v2.ReviewFlowDismissOverallExperienceEvent$ReviewFlowDismissOverallExperienceEventAdapter */
    private static final class ReviewFlowDismissOverallExperienceEventAdapter implements Adapter<ReviewFlowDismissOverallExperienceEvent, Object> {
        private ReviewFlowDismissOverallExperienceEventAdapter() {
        }

        public void write(Protocol protocol, ReviewFlowDismissOverallExperienceEvent struct) throws IOException {
            protocol.writeStructBegin("ReviewFlowDismissOverallExperienceEvent");
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
            protocol.writeFieldBegin("schedulable_info", 5, PassportService.SF_DG12);
            C0973SchedulableInfo.ADAPTER.write(protocol, struct.schedulable_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("review_rating", 6, 10);
            protocol.writeI64(struct.review_rating.longValue());
            protocol.writeFieldEnd();
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
        if (!(other instanceof ReviewFlowDismissOverallExperienceEvent)) {
            return false;
        }
        ReviewFlowDismissOverallExperienceEvent that = (ReviewFlowDismissOverallExperienceEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.schedulable_info == that.schedulable_info || this.schedulable_info.equals(that.schedulable_info)) && (this.review_rating == that.review_rating || this.review_rating.equals(that.review_rating)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.schedulable_info.hashCode()) * -2128831035) ^ this.review_rating.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ReviewFlowDismissOverallExperienceEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", schedulable_info=" + this.schedulable_info + ", review_rating=" + this.review_rating + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}