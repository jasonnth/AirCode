package com.airbnb.jitney.event.logging.TravelStories.p269v1;

import com.airbnb.jitney.event.logging.TravelStoriesEntryPoint.p272v1.C2768TravelStoriesEntryPoint;
import com.airbnb.jitney.event.logging.TravelStory.p274v1.C2771TravelStory;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.TravelStories.v1.TravelStoriesRecommendedStoriesImpressionEvent */
public final class TravelStoriesRecommendedStoriesImpressionEvent implements Struct {
    public static final Adapter<TravelStoriesRecommendedStoriesImpressionEvent, Object> ADAPTER = new TravelStoriesRecommendedStoriesImpressionEventAdapter();
    public final Context context;
    public final String event_name;
    public final String schema;
    public final C2768TravelStoriesEntryPoint travel_stories_entry_point;
    public final Long travel_stories_id;
    public final List<C2771TravelStory> travel_stories_suggestions;

    /* renamed from: com.airbnb.jitney.event.logging.TravelStories.v1.TravelStoriesRecommendedStoriesImpressionEvent$TravelStoriesRecommendedStoriesImpressionEventAdapter */
    private static final class TravelStoriesRecommendedStoriesImpressionEventAdapter implements Adapter<TravelStoriesRecommendedStoriesImpressionEvent, Object> {
        private TravelStoriesRecommendedStoriesImpressionEventAdapter() {
        }

        public void write(Protocol protocol, TravelStoriesRecommendedStoriesImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("TravelStoriesRecommendedStoriesImpressionEvent");
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
            if (struct.travel_stories_id != null) {
                protocol.writeFieldBegin("travel_stories_id", 4, 10);
                protocol.writeI64(struct.travel_stories_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("travel_stories_suggestions", 5, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.travel_stories_suggestions.size());
            for (C2771TravelStory item0 : struct.travel_stories_suggestions) {
                C2771TravelStory.ADAPTER.write(protocol, item0);
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
        if (!(other instanceof TravelStoriesRecommendedStoriesImpressionEvent)) {
            return false;
        }
        TravelStoriesRecommendedStoriesImpressionEvent that = (TravelStoriesRecommendedStoriesImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.travel_stories_entry_point == that.travel_stories_entry_point || this.travel_stories_entry_point.equals(that.travel_stories_entry_point)) && ((this.travel_stories_id == that.travel_stories_id || (this.travel_stories_id != null && this.travel_stories_id.equals(that.travel_stories_id))) && (this.travel_stories_suggestions == that.travel_stories_suggestions || this.travel_stories_suggestions.equals(that.travel_stories_suggestions))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.travel_stories_entry_point.hashCode()) * -2128831035;
        if (this.travel_stories_id != null) {
            i = this.travel_stories_id.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.travel_stories_suggestions.hashCode()) * -2128831035;
    }

    public String toString() {
        return "TravelStoriesRecommendedStoriesImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", travel_stories_entry_point=" + this.travel_stories_entry_point + ", travel_stories_id=" + this.travel_stories_id + ", travel_stories_suggestions=" + this.travel_stories_suggestions + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
