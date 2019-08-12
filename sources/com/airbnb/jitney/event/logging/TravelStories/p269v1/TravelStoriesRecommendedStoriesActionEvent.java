package com.airbnb.jitney.event.logging.TravelStories.p269v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.TravelStoriesEntryPoint.p272v1.C2768TravelStoriesEntryPoint;
import com.airbnb.jitney.event.logging.TravelStory.p274v1.C2771TravelStory;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.TravelStories.v1.TravelStoriesRecommendedStoriesActionEvent */
public final class TravelStoriesRecommendedStoriesActionEvent implements Struct {
    public static final Adapter<TravelStoriesRecommendedStoriesActionEvent, Object> ADAPTER = new TravelStoriesRecommendedStoriesActionEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final C2768TravelStoriesEntryPoint travel_stories_entry_point;
    public final Long travel_stories_id;
    public final C2771TravelStory travel_story;

    /* renamed from: com.airbnb.jitney.event.logging.TravelStories.v1.TravelStoriesRecommendedStoriesActionEvent$TravelStoriesRecommendedStoriesActionEventAdapter */
    private static final class TravelStoriesRecommendedStoriesActionEventAdapter implements Adapter<TravelStoriesRecommendedStoriesActionEvent, Object> {
        private TravelStoriesRecommendedStoriesActionEventAdapter() {
        }

        public void write(Protocol protocol, TravelStoriesRecommendedStoriesActionEvent struct) throws IOException {
            protocol.writeStructBegin("TravelStoriesRecommendedStoriesActionEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("travel_story", 6, PassportService.SF_DG12);
            C2771TravelStory.ADAPTER.write(protocol, struct.travel_story);
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
        if (!(other instanceof TravelStoriesRecommendedStoriesActionEvent)) {
            return false;
        }
        TravelStoriesRecommendedStoriesActionEvent that = (TravelStoriesRecommendedStoriesActionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.travel_stories_entry_point == that.travel_stories_entry_point || this.travel_stories_entry_point.equals(that.travel_stories_entry_point)) && ((this.travel_stories_id == that.travel_stories_id || (this.travel_stories_id != null && this.travel_stories_id.equals(that.travel_stories_id))) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.travel_story == that.travel_story || this.travel_story.equals(that.travel_story)))))))) {
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
        return (((((code ^ i) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.travel_story.hashCode()) * -2128831035;
    }

    public String toString() {
        return "TravelStoriesRecommendedStoriesActionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", travel_stories_entry_point=" + this.travel_stories_entry_point + ", travel_stories_id=" + this.travel_stories_id + ", operation=" + this.operation + ", travel_story=" + this.travel_story + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
