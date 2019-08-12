package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.DetailsPageTracking */
public final class DetailsPageTracking implements Struct {
    public static final Adapter<DetailsPageTracking, Object> ADAPTER = new DetailsPageTrackingAdapter();
    public final DetailsPageAction action;
    public final Long collection_id;
    public final FloatingButtonTracking floating_button;
    public final InvitePanelTracking invite_panel;
    public final ListDetailsMapTracking list_details_map;
    public final ListDetailsPanelTracking list_details_panel;
    public final Long owner;
    public final SettingPanelTracking setting_panel;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.DetailsPageTracking$DetailsPageTrackingAdapter */
    private static final class DetailsPageTrackingAdapter implements Adapter<DetailsPageTracking, Object> {
        private DetailsPageTrackingAdapter() {
        }

        public void write(Protocol protocol, DetailsPageTracking struct) throws IOException {
            protocol.writeStructBegin("DetailsPageTracking");
            protocol.writeFieldBegin("action", 1, 8);
            protocol.writeI32(struct.action.value);
            protocol.writeFieldEnd();
            if (struct.collection_id != null) {
                protocol.writeFieldBegin("collection_id", 2, 10);
                protocol.writeI64(struct.collection_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.owner != null) {
                protocol.writeFieldBegin("owner", 3, 10);
                protocol.writeI64(struct.owner.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.list_details_panel != null) {
                protocol.writeFieldBegin("list_details_panel", 4, PassportService.SF_DG12);
                ListDetailsPanelTracking.ADAPTER.write(protocol, struct.list_details_panel);
                protocol.writeFieldEnd();
            }
            if (struct.list_details_map != null) {
                protocol.writeFieldBegin("list_details_map", 5, PassportService.SF_DG12);
                ListDetailsMapTracking.ADAPTER.write(protocol, struct.list_details_map);
                protocol.writeFieldEnd();
            }
            if (struct.floating_button != null) {
                protocol.writeFieldBegin("floating_button", 6, PassportService.SF_DG12);
                FloatingButtonTracking.ADAPTER.write(protocol, struct.floating_button);
                protocol.writeFieldEnd();
            }
            if (struct.invite_panel != null) {
                protocol.writeFieldBegin("invite_panel", 7, PassportService.SF_DG12);
                InvitePanelTracking.ADAPTER.write(protocol, struct.invite_panel);
                protocol.writeFieldEnd();
            }
            if (struct.setting_panel != null) {
                protocol.writeFieldBegin("setting_panel", 8, PassportService.SF_DG12);
                SettingPanelTracking.ADAPTER.write(protocol, struct.setting_panel);
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
        if (!(other instanceof DetailsPageTracking)) {
            return false;
        }
        DetailsPageTracking that = (DetailsPageTracking) other;
        if ((this.action == that.action || this.action.equals(that.action)) && ((this.collection_id == that.collection_id || (this.collection_id != null && this.collection_id.equals(that.collection_id))) && ((this.owner == that.owner || (this.owner != null && this.owner.equals(that.owner))) && ((this.list_details_panel == that.list_details_panel || (this.list_details_panel != null && this.list_details_panel.equals(that.list_details_panel))) && ((this.list_details_map == that.list_details_map || (this.list_details_map != null && this.list_details_map.equals(that.list_details_map))) && ((this.floating_button == that.floating_button || (this.floating_button != null && this.floating_button.equals(that.floating_button))) && (this.invite_panel == that.invite_panel || (this.invite_panel != null && this.invite_panel.equals(that.invite_panel))))))))) {
            if (this.setting_panel == that.setting_panel) {
                return true;
            }
            if (this.setting_panel != null && this.setting_panel.equals(that.setting_panel)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((16777619 ^ this.action.hashCode()) * -2128831035) ^ (this.collection_id == null ? 0 : this.collection_id.hashCode())) * -2128831035) ^ (this.owner == null ? 0 : this.owner.hashCode())) * -2128831035) ^ (this.list_details_panel == null ? 0 : this.list_details_panel.hashCode())) * -2128831035) ^ (this.list_details_map == null ? 0 : this.list_details_map.hashCode())) * -2128831035) ^ (this.floating_button == null ? 0 : this.floating_button.hashCode())) * -2128831035) ^ (this.invite_panel == null ? 0 : this.invite_panel.hashCode())) * -2128831035;
        if (this.setting_panel != null) {
            i = this.setting_panel.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "DetailsPageTracking{action=" + this.action + ", collection_id=" + this.collection_id + ", owner=" + this.owner + ", list_details_panel=" + this.list_details_panel + ", list_details_map=" + this.list_details_map + ", floating_button=" + this.floating_button + ", invite_panel=" + this.invite_panel + ", setting_panel=" + this.setting_panel + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
