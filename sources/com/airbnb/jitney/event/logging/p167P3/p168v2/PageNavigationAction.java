package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.facebook.share.internal.ShareConstants;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.PageNavigationAction */
public final class PageNavigationAction implements Struct {
    public static final Adapter<PageNavigationAction, Builder> ADAPTER = new PageNavigationActionAdapter();
    public final PageNavigationActionType action_type;
    public final DurationCheckpointData duration_checkpoint_data;
    public final LeavePageData leave_page_data;
    public final NavigationTabClickData navigation_tab_click_data;
    public final SectionScrollData section_scroll_data;
    public final UserScrollData user_scroll_data;
    public final WaypointScrollData waypoint_scroll_data;
    public final UIEventType window_focus_event_type;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.PageNavigationAction$Builder */
    public static final class Builder implements StructBuilder<PageNavigationAction> {
        /* access modifiers changed from: private */
        public PageNavigationActionType action_type;
        /* access modifiers changed from: private */
        public DurationCheckpointData duration_checkpoint_data;
        /* access modifiers changed from: private */
        public LeavePageData leave_page_data;
        /* access modifiers changed from: private */
        public NavigationTabClickData navigation_tab_click_data;
        /* access modifiers changed from: private */
        public SectionScrollData section_scroll_data;
        /* access modifiers changed from: private */
        public UserScrollData user_scroll_data;
        /* access modifiers changed from: private */
        public WaypointScrollData waypoint_scroll_data;
        /* access modifiers changed from: private */
        public UIEventType window_focus_event_type;

        public Builder action_type(PageNavigationActionType action_type2) {
            this.action_type = action_type2;
            return this;
        }

        public Builder leave_page_data(LeavePageData leave_page_data2) {
            this.leave_page_data = leave_page_data2;
            return this;
        }

        public PageNavigationAction build() {
            return new PageNavigationAction(this);
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.PageNavigationAction$PageNavigationActionAdapter */
    private static final class PageNavigationActionAdapter implements Adapter<PageNavigationAction, Builder> {
        private PageNavigationActionAdapter() {
        }

        public void write(Protocol protocol, PageNavigationAction struct) throws IOException {
            protocol.writeStructBegin("PageNavigationAction");
            if (struct.action_type != null) {
                protocol.writeFieldBegin(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, 1, 8);
                protocol.writeI32(struct.action_type.value);
                protocol.writeFieldEnd();
            }
            if (struct.navigation_tab_click_data != null) {
                protocol.writeFieldBegin("navigation_tab_click_data", 2, PassportService.SF_DG12);
                NavigationTabClickData.ADAPTER.write(protocol, struct.navigation_tab_click_data);
                protocol.writeFieldEnd();
            }
            if (struct.waypoint_scroll_data != null) {
                protocol.writeFieldBegin("waypoint_scroll_data", 3, PassportService.SF_DG12);
                WaypointScrollData.ADAPTER.write(protocol, struct.waypoint_scroll_data);
                protocol.writeFieldEnd();
            }
            if (struct.section_scroll_data != null) {
                protocol.writeFieldBegin("section_scroll_data", 4, PassportService.SF_DG12);
                SectionScrollData.ADAPTER.write(protocol, struct.section_scroll_data);
                protocol.writeFieldEnd();
            }
            if (struct.window_focus_event_type != null) {
                protocol.writeFieldBegin("window_focus_event_type", 5, 8);
                protocol.writeI32(struct.window_focus_event_type.value);
                protocol.writeFieldEnd();
            }
            if (struct.leave_page_data != null) {
                protocol.writeFieldBegin("leave_page_data", 6, PassportService.SF_DG12);
                LeavePageData.ADAPTER.write(protocol, struct.leave_page_data);
                protocol.writeFieldEnd();
            }
            if (struct.duration_checkpoint_data != null) {
                protocol.writeFieldBegin("duration_checkpoint_data", 7, PassportService.SF_DG12);
                DurationCheckpointData.ADAPTER.write(protocol, struct.duration_checkpoint_data);
                protocol.writeFieldEnd();
            }
            if (struct.user_scroll_data != null) {
                protocol.writeFieldBegin("user_scroll_data", 8, PassportService.SF_DG12);
                UserScrollData.ADAPTER.write(protocol, struct.user_scroll_data);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PageNavigationAction(Builder builder) {
        this.action_type = builder.action_type;
        this.navigation_tab_click_data = builder.navigation_tab_click_data;
        this.waypoint_scroll_data = builder.waypoint_scroll_data;
        this.section_scroll_data = builder.section_scroll_data;
        this.window_focus_event_type = builder.window_focus_event_type;
        this.leave_page_data = builder.leave_page_data;
        this.duration_checkpoint_data = builder.duration_checkpoint_data;
        this.user_scroll_data = builder.user_scroll_data;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PageNavigationAction)) {
            return false;
        }
        PageNavigationAction that = (PageNavigationAction) other;
        if ((this.action_type == that.action_type || (this.action_type != null && this.action_type.equals(that.action_type))) && ((this.navigation_tab_click_data == that.navigation_tab_click_data || (this.navigation_tab_click_data != null && this.navigation_tab_click_data.equals(that.navigation_tab_click_data))) && ((this.waypoint_scroll_data == that.waypoint_scroll_data || (this.waypoint_scroll_data != null && this.waypoint_scroll_data.equals(that.waypoint_scroll_data))) && ((this.section_scroll_data == that.section_scroll_data || (this.section_scroll_data != null && this.section_scroll_data.equals(that.section_scroll_data))) && ((this.window_focus_event_type == that.window_focus_event_type || (this.window_focus_event_type != null && this.window_focus_event_type.equals(that.window_focus_event_type))) && ((this.leave_page_data == that.leave_page_data || (this.leave_page_data != null && this.leave_page_data.equals(that.leave_page_data))) && (this.duration_checkpoint_data == that.duration_checkpoint_data || (this.duration_checkpoint_data != null && this.duration_checkpoint_data.equals(that.duration_checkpoint_data))))))))) {
            if (this.user_scroll_data == that.user_scroll_data) {
                return true;
            }
            if (this.user_scroll_data != null && this.user_scroll_data.equals(that.user_scroll_data)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((16777619 ^ (this.action_type == null ? 0 : this.action_type.hashCode())) * -2128831035) ^ (this.navigation_tab_click_data == null ? 0 : this.navigation_tab_click_data.hashCode())) * -2128831035) ^ (this.waypoint_scroll_data == null ? 0 : this.waypoint_scroll_data.hashCode())) * -2128831035) ^ (this.section_scroll_data == null ? 0 : this.section_scroll_data.hashCode())) * -2128831035) ^ (this.window_focus_event_type == null ? 0 : this.window_focus_event_type.hashCode())) * -2128831035) ^ (this.leave_page_data == null ? 0 : this.leave_page_data.hashCode())) * -2128831035) ^ (this.duration_checkpoint_data == null ? 0 : this.duration_checkpoint_data.hashCode())) * -2128831035;
        if (this.user_scroll_data != null) {
            i = this.user_scroll_data.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PageNavigationAction{action_type=" + this.action_type + ", navigation_tab_click_data=" + this.navigation_tab_click_data + ", waypoint_scroll_data=" + this.waypoint_scroll_data + ", section_scroll_data=" + this.section_scroll_data + ", window_focus_event_type=" + this.window_focus_event_type + ", leave_page_data=" + this.leave_page_data + ", duration_checkpoint_data=" + this.duration_checkpoint_data + ", user_scroll_data=" + this.user_scroll_data + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
