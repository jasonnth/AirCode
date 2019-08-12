package com.airbnb.jitney.event.logging.ReviewFlow.p231v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SchedulableInfo.p244v1.C2716SchedulableInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ReviewFlow.v1.ReviewFlowClickNextTellUsMoreEvent */
public final class ReviewFlowClickNextTellUsMoreEvent implements Struct {
    public static final Adapter<ReviewFlowClickNextTellUsMoreEvent, Object> ADAPTER = new ReviewFlowClickNextTellUsMoreEventAdapter();
    public final Context context;
    public final String event_name;
    public final List<String> key_words;
    public final C2451Operation operation;
    public final String page;
    public final C2716SchedulableInfo schedulable_info;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ReviewFlow.v1.ReviewFlowClickNextTellUsMoreEvent$ReviewFlowClickNextTellUsMoreEventAdapter */
    private static final class ReviewFlowClickNextTellUsMoreEventAdapter implements Adapter<ReviewFlowClickNextTellUsMoreEvent, Object> {
        private ReviewFlowClickNextTellUsMoreEventAdapter() {
        }

        public void write(Protocol protocol, ReviewFlowClickNextTellUsMoreEvent struct) throws IOException {
            protocol.writeStructBegin("ReviewFlowClickNextTellUsMoreEvent");
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
            C2716SchedulableInfo.ADAPTER.write(protocol, struct.schedulable_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("key_words", 6, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.key_words.size());
            for (String item0 : struct.key_words) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
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
        if (!(other instanceof ReviewFlowClickNextTellUsMoreEvent)) {
            return false;
        }
        ReviewFlowClickNextTellUsMoreEvent that = (ReviewFlowClickNextTellUsMoreEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.schedulable_info == that.schedulable_info || this.schedulable_info.equals(that.schedulable_info)) && (this.key_words == that.key_words || this.key_words.equals(that.key_words)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.schedulable_info.hashCode()) * -2128831035) ^ this.key_words.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ReviewFlowClickNextTellUsMoreEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", schedulable_info=" + this.schedulable_info + ", key_words=" + this.key_words + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
