package com.airbnb.jitney.event.logging.TravelStories.p269v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.OperationResult.p166v1.C2452OperationResult;
import com.airbnb.jitney.event.logging.TravelStoriesEntryPoint.p272v1.C2768TravelStoriesEntryPoint;
import com.airbnb.jitney.event.logging.TravelStoriesSlide.p273v1.C2769TravelStoriesSlide;
import com.airbnb.jitney.event.logging.VideoOperation.p282v1.C2787VideoOperation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.TravelStories.v1.TravelStoriesSlideActionEvent */
public final class TravelStoriesSlideActionEvent implements Struct {
    public static final Adapter<TravelStoriesSlideActionEvent, Object> ADAPTER = new TravelStoriesSlideActionEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long loop_number;
    public final C2451Operation operation;
    public final C2452OperationResult operation_result;
    public final String schema;
    public final C2768TravelStoriesEntryPoint travel_stories_entry_point;
    public final Long travel_stories_id;
    public final C2769TravelStoriesSlide travel_stories_slide;
    public final C2787VideoOperation video_operation;
    public final Long video_time_elapsed;

    /* renamed from: com.airbnb.jitney.event.logging.TravelStories.v1.TravelStoriesSlideActionEvent$TravelStoriesSlideActionEventAdapter */
    private static final class TravelStoriesSlideActionEventAdapter implements Adapter<TravelStoriesSlideActionEvent, Object> {
        private TravelStoriesSlideActionEventAdapter() {
        }

        public void write(Protocol protocol, TravelStoriesSlideActionEvent struct) throws IOException {
            protocol.writeStructBegin("TravelStoriesSlideActionEvent");
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
            protocol.writeFieldBegin("travel_stories_entry_point", 3, 8);
            protocol.writeI32(struct.travel_stories_entry_point.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("travel_stories_id", 4, 10);
            protocol.writeI64(struct.travel_stories_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("travel_stories_slide", 5, PassportService.SF_DG12);
            C2769TravelStoriesSlide.ADAPTER.write(protocol, struct.travel_stories_slide);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("operation_result", 7, 8);
            protocol.writeI32(struct.operation_result.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("video_operation", 8, 8);
            protocol.writeI32(struct.video_operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("video_time_elapsed", 9, 10);
            protocol.writeI64(struct.video_time_elapsed.longValue());
            protocol.writeFieldEnd();
            if (struct.loop_number != null) {
                protocol.writeFieldBegin("loop_number", 10, 10);
                protocol.writeI64(struct.loop_number.longValue());
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof TravelStoriesSlideActionEvent)) {
            return false;
        }
        TravelStoriesSlideActionEvent that = (TravelStoriesSlideActionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.travel_stories_entry_point == that.travel_stories_entry_point || this.travel_stories_entry_point.equals(that.travel_stories_entry_point)) && ((this.travel_stories_id == that.travel_stories_id || this.travel_stories_id.equals(that.travel_stories_id)) && ((this.travel_stories_slide == that.travel_stories_slide || this.travel_stories_slide.equals(that.travel_stories_slide)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.operation_result == that.operation_result || this.operation_result.equals(that.operation_result)) && ((this.video_operation == that.video_operation || this.video_operation.equals(that.video_operation)) && (this.video_time_elapsed == that.video_time_elapsed || this.video_time_elapsed.equals(that.video_time_elapsed))))))))))) {
            if (this.loop_number == that.loop_number) {
                return true;
            }
            if (this.loop_number != null && this.loop_number.equals(that.loop_number)) {
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
        int code = (((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.travel_stories_entry_point.hashCode()) * -2128831035) ^ this.travel_stories_id.hashCode()) * -2128831035) ^ this.travel_stories_slide.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.operation_result.hashCode()) * -2128831035) ^ this.video_operation.hashCode()) * -2128831035) ^ this.video_time_elapsed.hashCode()) * -2128831035;
        if (this.loop_number != null) {
            i = this.loop_number.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "TravelStoriesSlideActionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", travel_stories_entry_point=" + this.travel_stories_entry_point + ", travel_stories_id=" + this.travel_stories_id + ", travel_stories_slide=" + this.travel_stories_slide + ", operation=" + this.operation + ", operation_result=" + this.operation_result + ", video_operation=" + this.video_operation + ", video_time_elapsed=" + this.video_time_elapsed + ", loop_number=" + this.loop_number + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
