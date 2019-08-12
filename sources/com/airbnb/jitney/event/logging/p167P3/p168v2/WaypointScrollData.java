package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.WaypointScrollData */
public final class WaypointScrollData implements Struct {
    public static final Adapter<WaypointScrollData, Object> ADAPTER = new WaypointScrollDataAdapter();
    public final ScrollDirection scroll_direction;
    public final String waypoint_name;
    public final WaypointScrollAction waypoint_scroll_action;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.WaypointScrollData$WaypointScrollDataAdapter */
    private static final class WaypointScrollDataAdapter implements Adapter<WaypointScrollData, Object> {
        private WaypointScrollDataAdapter() {
        }

        public void write(Protocol protocol, WaypointScrollData struct) throws IOException {
            protocol.writeStructBegin("WaypointScrollData");
            if (struct.waypoint_name != null) {
                protocol.writeFieldBegin("waypoint_name", 1, PassportService.SF_DG11);
                protocol.writeString(struct.waypoint_name);
                protocol.writeFieldEnd();
            }
            if (struct.scroll_direction != null) {
                protocol.writeFieldBegin("scroll_direction", 2, 8);
                protocol.writeI32(struct.scroll_direction.value);
                protocol.writeFieldEnd();
            }
            if (struct.waypoint_scroll_action != null) {
                protocol.writeFieldBegin("waypoint_scroll_action", 3, 8);
                protocol.writeI32(struct.waypoint_scroll_action.value);
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
        if (!(other instanceof WaypointScrollData)) {
            return false;
        }
        WaypointScrollData that = (WaypointScrollData) other;
        if ((this.waypoint_name == that.waypoint_name || (this.waypoint_name != null && this.waypoint_name.equals(that.waypoint_name))) && (this.scroll_direction == that.scroll_direction || (this.scroll_direction != null && this.scroll_direction.equals(that.scroll_direction)))) {
            if (this.waypoint_scroll_action == that.waypoint_scroll_action) {
                return true;
            }
            if (this.waypoint_scroll_action != null && this.waypoint_scroll_action.equals(that.waypoint_scroll_action)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((16777619 ^ (this.waypoint_name == null ? 0 : this.waypoint_name.hashCode())) * -2128831035) ^ (this.scroll_direction == null ? 0 : this.scroll_direction.hashCode())) * -2128831035;
        if (this.waypoint_scroll_action != null) {
            i = this.waypoint_scroll_action.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "WaypointScrollData{waypoint_name=" + this.waypoint_name + ", scroll_direction=" + this.scroll_direction + ", waypoint_scroll_action=" + this.waypoint_scroll_action + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
