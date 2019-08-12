package com.airbnb.jitney.event.logging.TravelStories.p270v2;

import com.airbnb.jitney.event.logging.TravelStoriesEntryPoint.p272v1.C2768TravelStoriesEntryPoint;
import com.airbnb.jitney.event.logging.TravelStoriesSlide.p273v1.C2769TravelStoriesSlide;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.TravelStories.v2.TravelStoriesPdpImpressionEvent */
public final class TravelStoriesPdpImpressionEvent implements Struct {
    public static final Adapter<TravelStoriesPdpImpressionEvent, Object> ADAPTER = new TravelStoriesPdpImpressionEventAdapter();
    public final Context context;
    public final String event_name;
    public final Boolean is_last_slide;
    public final Boolean is_trip_summary;
    public final String schema;
    public final C2768TravelStoriesEntryPoint travel_stories_entry_point;
    public final Long travel_stories_id;
    public final C2769TravelStoriesSlide travel_stories_slide;
    public final List<C2769TravelStoriesSlide> travel_stories_slides;

    /* renamed from: com.airbnb.jitney.event.logging.TravelStories.v2.TravelStoriesPdpImpressionEvent$TravelStoriesPdpImpressionEventAdapter */
    private static final class TravelStoriesPdpImpressionEventAdapter implements Adapter<TravelStoriesPdpImpressionEvent, Object> {
        private TravelStoriesPdpImpressionEventAdapter() {
        }

        public void write(Protocol protocol, TravelStoriesPdpImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("TravelStoriesPdpImpressionEvent");
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
            protocol.writeFieldBegin("travel_stories_slides", 5, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.travel_stories_slides.size());
            for (C2769TravelStoriesSlide item0 : struct.travel_stories_slides) {
                C2769TravelStoriesSlide.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            if (struct.is_trip_summary != null) {
                protocol.writeFieldBegin("is_trip_summary", 6, 2);
                protocol.writeBool(struct.is_trip_summary.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.travel_stories_slide != null) {
                protocol.writeFieldBegin("travel_stories_slide", 7, PassportService.SF_DG12);
                C2769TravelStoriesSlide.ADAPTER.write(protocol, struct.travel_stories_slide);
                protocol.writeFieldEnd();
            }
            if (struct.is_last_slide != null) {
                protocol.writeFieldBegin("is_last_slide", 8, 2);
                protocol.writeBool(struct.is_last_slide.booleanValue());
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
        if (!(other instanceof TravelStoriesPdpImpressionEvent)) {
            return false;
        }
        TravelStoriesPdpImpressionEvent that = (TravelStoriesPdpImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.travel_stories_entry_point == that.travel_stories_entry_point || this.travel_stories_entry_point.equals(that.travel_stories_entry_point)) && ((this.travel_stories_id == that.travel_stories_id || this.travel_stories_id.equals(that.travel_stories_id)) && ((this.travel_stories_slides == that.travel_stories_slides || this.travel_stories_slides.equals(that.travel_stories_slides)) && ((this.is_trip_summary == that.is_trip_summary || (this.is_trip_summary != null && this.is_trip_summary.equals(that.is_trip_summary))) && (this.travel_stories_slide == that.travel_stories_slide || (this.travel_stories_slide != null && this.travel_stories_slide.equals(that.travel_stories_slide)))))))))) {
            if (this.is_last_slide == that.is_last_slide) {
                return true;
            }
            if (this.is_last_slide != null && this.is_last_slide.equals(that.is_last_slide)) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.travel_stories_entry_point.hashCode()) * -2128831035) ^ this.travel_stories_id.hashCode()) * -2128831035) ^ this.travel_stories_slides.hashCode()) * -2128831035) ^ (this.is_trip_summary == null ? 0 : this.is_trip_summary.hashCode())) * -2128831035) ^ (this.travel_stories_slide == null ? 0 : this.travel_stories_slide.hashCode())) * -2128831035;
        if (this.is_last_slide != null) {
            i = this.is_last_slide.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "TravelStoriesPdpImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", travel_stories_entry_point=" + this.travel_stories_entry_point + ", travel_stories_id=" + this.travel_stories_id + ", travel_stories_slides=" + this.travel_stories_slides + ", is_trip_summary=" + this.is_trip_summary + ", travel_stories_slide=" + this.travel_stories_slide + ", is_last_slide=" + this.is_last_slide + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
