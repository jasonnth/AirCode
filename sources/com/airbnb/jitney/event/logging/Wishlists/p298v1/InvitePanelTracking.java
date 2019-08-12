package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Set;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.InvitePanelTracking */
public final class InvitePanelTracking implements Struct {
    public static final Adapter<InvitePanelTracking, Object> ADAPTER = new InvitePanelTrackingAdapter();
    public final InvitePanelAction action;
    public final Set<String> emails;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.InvitePanelTracking$InvitePanelTrackingAdapter */
    private static final class InvitePanelTrackingAdapter implements Adapter<InvitePanelTracking, Object> {
        private InvitePanelTrackingAdapter() {
        }

        public void write(Protocol protocol, InvitePanelTracking struct) throws IOException {
            protocol.writeStructBegin("InvitePanelTracking");
            if (struct.action != null) {
                protocol.writeFieldBegin("action", 1, 8);
                protocol.writeI32(struct.action.value);
                protocol.writeFieldEnd();
            }
            if (struct.emails != null) {
                protocol.writeFieldBegin("emails", 2, 14);
                protocol.writeSetBegin(PassportService.SF_DG11, struct.emails.size());
                for (String item0 : struct.emails) {
                    protocol.writeString(item0);
                }
                protocol.writeSetEnd();
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
        if (!(other instanceof InvitePanelTracking)) {
            return false;
        }
        InvitePanelTracking that = (InvitePanelTracking) other;
        if (this.action == that.action || (this.action != null && this.action.equals(that.action))) {
            if (this.emails == that.emails) {
                return true;
            }
            if (this.emails != null && this.emails.equals(that.emails)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (16777619 ^ (this.action == null ? 0 : this.action.hashCode())) * -2128831035;
        if (this.emails != null) {
            i = this.emails.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "InvitePanelTracking{action=" + this.action + ", emails=" + this.emails + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
