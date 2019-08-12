package com.airbnb.jitney.event.logging.TravelStory.p274v1;

import com.airbnb.jitney.event.logging.TravelStoriesSlide.p273v1.C2769TravelStoriesSlide;
import com.facebook.react.uimanager.ViewProps;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.TravelStory.v1.TravelStory */
public final class C2771TravelStory implements Struct {
    public static final Adapter<C2771TravelStory, Object> ADAPTER = new TravelStoryAdapter();
    public final Boolean is_visible;
    public final Long position;
    public final Long travel_stories_id;
    public final C2769TravelStoriesSlide travel_stories_slide;

    /* renamed from: com.airbnb.jitney.event.logging.TravelStory.v1.TravelStory$TravelStoryAdapter */
    private static final class TravelStoryAdapter implements Adapter<C2771TravelStory, Object> {
        private TravelStoryAdapter() {
        }

        public void write(Protocol protocol, C2771TravelStory struct) throws IOException {
            protocol.writeStructBegin("TravelStory");
            protocol.writeFieldBegin("travel_stories_id", 1, 10);
            protocol.writeI64(struct.travel_stories_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ViewProps.POSITION, 2, 10);
            protocol.writeI64(struct.position.longValue());
            protocol.writeFieldEnd();
            if (struct.is_visible != null) {
                protocol.writeFieldBegin("is_visible", 3, 2);
                protocol.writeBool(struct.is_visible.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.travel_stories_slide != null) {
                protocol.writeFieldBegin("travel_stories_slide", 4, PassportService.SF_DG12);
                C2769TravelStoriesSlide.ADAPTER.write(protocol, struct.travel_stories_slide);
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
        if (!(other instanceof C2771TravelStory)) {
            return false;
        }
        C2771TravelStory that = (C2771TravelStory) other;
        if ((this.travel_stories_id == that.travel_stories_id || this.travel_stories_id.equals(that.travel_stories_id)) && ((this.position == that.position || this.position.equals(that.position)) && (this.is_visible == that.is_visible || (this.is_visible != null && this.is_visible.equals(that.is_visible))))) {
            if (this.travel_stories_slide == that.travel_stories_slide) {
                return true;
            }
            if (this.travel_stories_slide != null && this.travel_stories_slide.equals(that.travel_stories_slide)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ this.travel_stories_id.hashCode()) * -2128831035) ^ this.position.hashCode()) * -2128831035) ^ (this.is_visible == null ? 0 : this.is_visible.hashCode())) * -2128831035;
        if (this.travel_stories_slide != null) {
            i = this.travel_stories_slide.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "TravelStory{travel_stories_id=" + this.travel_stories_id + ", position=" + this.position + ", is_visible=" + this.is_visible + ", travel_stories_slide=" + this.travel_stories_slide + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
