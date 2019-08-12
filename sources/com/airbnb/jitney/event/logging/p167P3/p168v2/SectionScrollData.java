package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.SectionScrollData */
public final class SectionScrollData implements Struct {
    public static final Adapter<SectionScrollData, Object> ADAPTER = new SectionScrollDataAdapter();
    public final ScrollDirection scroll_direction;
    public final ScrollDirection section_enter_scroll_direction;
    public final Long section_enter_timestamp;
    public final String section_name;
    public final SectionScrollAction section_scroll_action;
    public final UIEventType triggering_window_focus_event_type;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.SectionScrollData$SectionScrollDataAdapter */
    private static final class SectionScrollDataAdapter implements Adapter<SectionScrollData, Object> {
        private SectionScrollDataAdapter() {
        }

        public void write(Protocol protocol, SectionScrollData struct) throws IOException {
            protocol.writeStructBegin("SectionScrollData");
            if (struct.section_name != null) {
                protocol.writeFieldBegin("section_name", 1, PassportService.SF_DG11);
                protocol.writeString(struct.section_name);
                protocol.writeFieldEnd();
            }
            if (struct.scroll_direction != null) {
                protocol.writeFieldBegin("scroll_direction", 2, 8);
                protocol.writeI32(struct.scroll_direction.value);
                protocol.writeFieldEnd();
            }
            if (struct.section_scroll_action != null) {
                protocol.writeFieldBegin("section_scroll_action", 3, 8);
                protocol.writeI32(struct.section_scroll_action.value);
                protocol.writeFieldEnd();
            }
            if (struct.section_enter_timestamp != null) {
                protocol.writeFieldBegin("section_enter_timestamp", 4, 10);
                protocol.writeI64(struct.section_enter_timestamp.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.section_enter_scroll_direction != null) {
                protocol.writeFieldBegin("section_enter_scroll_direction", 5, 8);
                protocol.writeI32(struct.section_enter_scroll_direction.value);
                protocol.writeFieldEnd();
            }
            if (struct.triggering_window_focus_event_type != null) {
                protocol.writeFieldBegin("triggering_window_focus_event_type", 6, 8);
                protocol.writeI32(struct.triggering_window_focus_event_type.value);
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
        if (!(other instanceof SectionScrollData)) {
            return false;
        }
        SectionScrollData that = (SectionScrollData) other;
        if ((this.section_name == that.section_name || (this.section_name != null && this.section_name.equals(that.section_name))) && ((this.scroll_direction == that.scroll_direction || (this.scroll_direction != null && this.scroll_direction.equals(that.scroll_direction))) && ((this.section_scroll_action == that.section_scroll_action || (this.section_scroll_action != null && this.section_scroll_action.equals(that.section_scroll_action))) && ((this.section_enter_timestamp == that.section_enter_timestamp || (this.section_enter_timestamp != null && this.section_enter_timestamp.equals(that.section_enter_timestamp))) && (this.section_enter_scroll_direction == that.section_enter_scroll_direction || (this.section_enter_scroll_direction != null && this.section_enter_scroll_direction.equals(that.section_enter_scroll_direction))))))) {
            if (this.triggering_window_focus_event_type == that.triggering_window_focus_event_type) {
                return true;
            }
            if (this.triggering_window_focus_event_type != null && this.triggering_window_focus_event_type.equals(that.triggering_window_focus_event_type)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.section_name == null ? 0 : this.section_name.hashCode())) * -2128831035) ^ (this.scroll_direction == null ? 0 : this.scroll_direction.hashCode())) * -2128831035) ^ (this.section_scroll_action == null ? 0 : this.section_scroll_action.hashCode())) * -2128831035) ^ (this.section_enter_timestamp == null ? 0 : this.section_enter_timestamp.hashCode())) * -2128831035) ^ (this.section_enter_scroll_direction == null ? 0 : this.section_enter_scroll_direction.hashCode())) * -2128831035;
        if (this.triggering_window_focus_event_type != null) {
            i = this.triggering_window_focus_event_type.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SectionScrollData{section_name=" + this.section_name + ", scroll_direction=" + this.scroll_direction + ", section_scroll_action=" + this.section_scroll_action + ", section_enter_timestamp=" + this.section_enter_timestamp + ", section_enter_scroll_direction=" + this.section_enter_scroll_direction + ", triggering_window_focus_event_type=" + this.triggering_window_focus_event_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
