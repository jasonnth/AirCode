package com.airbnb.jitney.event.logging.TravelStoriesSlide.p273v1;

import com.facebook.react.uimanager.ViewProps;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.TravelStoriesSlide.v1.TravelStoriesSlide */
public final class C2769TravelStoriesSlide implements Struct {
    public static final Adapter<C2769TravelStoriesSlide, Object> ADAPTER = new TravelStoriesSlideAdapter();
    public final Boolean is_visible;
    public final Long position;
    public final Long slide_id;

    /* renamed from: com.airbnb.jitney.event.logging.TravelStoriesSlide.v1.TravelStoriesSlide$TravelStoriesSlideAdapter */
    private static final class TravelStoriesSlideAdapter implements Adapter<C2769TravelStoriesSlide, Object> {
        private TravelStoriesSlideAdapter() {
        }

        public void write(Protocol protocol, C2769TravelStoriesSlide struct) throws IOException {
            protocol.writeStructBegin("TravelStoriesSlide");
            protocol.writeFieldBegin("slide_id", 1, 10);
            protocol.writeI64(struct.slide_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ViewProps.POSITION, 2, 10);
            protocol.writeI64(struct.position.longValue());
            protocol.writeFieldEnd();
            if (struct.is_visible != null) {
                protocol.writeFieldBegin("is_visible", 3, 2);
                protocol.writeBool(struct.is_visible.booleanValue());
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
        if (!(other instanceof C2769TravelStoriesSlide)) {
            return false;
        }
        C2769TravelStoriesSlide that = (C2769TravelStoriesSlide) other;
        if ((this.slide_id == that.slide_id || this.slide_id.equals(that.slide_id)) && (this.position == that.position || this.position.equals(that.position))) {
            if (this.is_visible == that.is_visible) {
                return true;
            }
            if (this.is_visible != null && this.is_visible.equals(that.is_visible)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ this.slide_id.hashCode()) * -2128831035) ^ this.position.hashCode()) * -2128831035) ^ (this.is_visible == null ? 0 : this.is_visible.hashCode())) * -2128831035;
    }

    public String toString() {
        return "TravelStoriesSlide{slide_id=" + this.slide_id + ", position=" + this.position + ", is_visible=" + this.is_visible + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
