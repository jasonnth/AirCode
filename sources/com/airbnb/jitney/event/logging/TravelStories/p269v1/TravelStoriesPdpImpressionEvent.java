package com.airbnb.jitney.event.logging.TravelStories.p269v1;

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

/* renamed from: com.airbnb.jitney.event.logging.TravelStories.v1.TravelStoriesPdpImpressionEvent */
public final class TravelStoriesPdpImpressionEvent implements Struct {
    public static final Adapter<TravelStoriesPdpImpressionEvent, Object> ADAPTER = new TravelStoriesPdpImpressionEventAdapter();
    public final Context context;
    public final String event_name;
    public final String schema;
    public final C2768TravelStoriesEntryPoint travel_stories_entry_point;
    public final Long travel_stories_id;
    public final List<C2769TravelStoriesSlide> travel_stories_slides;

    /* renamed from: com.airbnb.jitney.event.logging.TravelStories.v1.TravelStoriesPdpImpressionEvent$TravelStoriesPdpImpressionEventAdapter */
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.travel_stories_entry_point == that.travel_stories_entry_point || this.travel_stories_entry_point.equals(that.travel_stories_entry_point)) && ((this.travel_stories_id == that.travel_stories_id || this.travel_stories_id.equals(that.travel_stories_id)) && (this.travel_stories_slides == that.travel_stories_slides || this.travel_stories_slides.equals(that.travel_stories_slides))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.travel_stories_entry_point.hashCode()) * -2128831035) ^ this.travel_stories_id.hashCode()) * -2128831035) ^ this.travel_stories_slides.hashCode()) * -2128831035;
    }

    public String toString() {
        return "TravelStoriesPdpImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", travel_stories_entry_point=" + this.travel_stories_entry_point + ", travel_stories_id=" + this.travel_stories_id + ", travel_stories_slides=" + this.travel_stories_slides + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
