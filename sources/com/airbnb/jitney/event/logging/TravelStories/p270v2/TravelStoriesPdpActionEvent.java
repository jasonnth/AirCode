package com.airbnb.jitney.event.logging.TravelStories.p270v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SharedItemType.p257v2.C2741SharedItemType;
import com.airbnb.jitney.event.logging.TravelStoriesEntryPoint.p272v1.C2768TravelStoriesEntryPoint;
import com.airbnb.jitney.event.logging.TravelStoriesSlide.p273v1.C2769TravelStoriesSlide;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.TravelStories.v2.TravelStoriesPdpActionEvent */
public final class TravelStoriesPdpActionEvent implements Struct {
    public static final Adapter<TravelStoriesPdpActionEvent, Object> ADAPTER = new TravelStoriesPdpActionEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final String target;
    public final Long target_item_id;
    public final C2741SharedItemType target_item_type;
    public final C2768TravelStoriesEntryPoint travel_stories_entry_point;
    public final Long travel_stories_id;
    public final C2769TravelStoriesSlide travel_stories_slide;

    /* renamed from: com.airbnb.jitney.event.logging.TravelStories.v2.TravelStoriesPdpActionEvent$TravelStoriesPdpActionEventAdapter */
    private static final class TravelStoriesPdpActionEventAdapter implements Adapter<TravelStoriesPdpActionEvent, Object> {
        private TravelStoriesPdpActionEventAdapter() {
        }

        public void write(Protocol protocol, TravelStoriesPdpActionEvent struct) throws IOException {
            protocol.writeStructBegin("TravelStoriesPdpActionEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 7, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            if (struct.target_item_type != null) {
                protocol.writeFieldBegin("target_item_type", 8, 8);
                protocol.writeI32(struct.target_item_type.value);
                protocol.writeFieldEnd();
            }
            if (struct.target_item_id != null) {
                protocol.writeFieldBegin("target_item_id", 9, 10);
                protocol.writeI64(struct.target_item_id.longValue());
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
        if (!(other instanceof TravelStoriesPdpActionEvent)) {
            return false;
        }
        TravelStoriesPdpActionEvent that = (TravelStoriesPdpActionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.travel_stories_entry_point == that.travel_stories_entry_point || this.travel_stories_entry_point.equals(that.travel_stories_entry_point)) && ((this.travel_stories_id == that.travel_stories_id || this.travel_stories_id.equals(that.travel_stories_id)) && ((this.travel_stories_slide == that.travel_stories_slide || this.travel_stories_slide.equals(that.travel_stories_slide)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && (this.target_item_type == that.target_item_type || (this.target_item_type != null && this.target_item_type.equals(that.target_item_type))))))))))) {
            if (this.target_item_id == that.target_item_id) {
                return true;
            }
            if (this.target_item_id != null && this.target_item_id.equals(that.target_item_id)) {
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
        int code = (((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.travel_stories_entry_point.hashCode()) * -2128831035) ^ this.travel_stories_id.hashCode()) * -2128831035) ^ this.travel_stories_slide.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ (this.target_item_type == null ? 0 : this.target_item_type.hashCode())) * -2128831035;
        if (this.target_item_id != null) {
            i = this.target_item_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "TravelStoriesPdpActionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", travel_stories_entry_point=" + this.travel_stories_entry_point + ", travel_stories_id=" + this.travel_stories_id + ", travel_stories_slide=" + this.travel_stories_slide + ", operation=" + this.operation + ", target=" + this.target + ", target_item_type=" + this.target_item_type + ", target_item_id=" + this.target_item_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
