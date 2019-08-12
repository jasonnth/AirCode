package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingDatesTracking */
public final class SettingDatesTracking implements Struct {
    public static final Adapter<SettingDatesTracking, Object> ADAPTER = new SettingDatesTrackingAdapter();
    public final String current_checkin;
    public final String current_checkout;
    public final String previous_checkin;
    public final String previous_checkout;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingDatesTracking$SettingDatesTrackingAdapter */
    private static final class SettingDatesTrackingAdapter implements Adapter<SettingDatesTracking, Object> {
        private SettingDatesTrackingAdapter() {
        }

        public void write(Protocol protocol, SettingDatesTracking struct) throws IOException {
            protocol.writeStructBegin("SettingDatesTracking");
            if (struct.previous_checkin != null) {
                protocol.writeFieldBegin("previous_checkin", 1, PassportService.SF_DG11);
                protocol.writeString(struct.previous_checkin);
                protocol.writeFieldEnd();
            }
            if (struct.current_checkin != null) {
                protocol.writeFieldBegin("current_checkin", 2, PassportService.SF_DG11);
                protocol.writeString(struct.current_checkin);
                protocol.writeFieldEnd();
            }
            if (struct.previous_checkout != null) {
                protocol.writeFieldBegin("previous_checkout", 3, PassportService.SF_DG11);
                protocol.writeString(struct.previous_checkout);
                protocol.writeFieldEnd();
            }
            if (struct.current_checkout != null) {
                protocol.writeFieldBegin("current_checkout", 4, PassportService.SF_DG11);
                protocol.writeString(struct.current_checkout);
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
        if (!(other instanceof SettingDatesTracking)) {
            return false;
        }
        SettingDatesTracking that = (SettingDatesTracking) other;
        if ((this.previous_checkin == that.previous_checkin || (this.previous_checkin != null && this.previous_checkin.equals(that.previous_checkin))) && ((this.current_checkin == that.current_checkin || (this.current_checkin != null && this.current_checkin.equals(that.current_checkin))) && (this.previous_checkout == that.previous_checkout || (this.previous_checkout != null && this.previous_checkout.equals(that.previous_checkout))))) {
            if (this.current_checkout == that.current_checkout) {
                return true;
            }
            if (this.current_checkout != null && this.current_checkout.equals(that.current_checkout)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ (this.previous_checkin == null ? 0 : this.previous_checkin.hashCode())) * -2128831035) ^ (this.current_checkin == null ? 0 : this.current_checkin.hashCode())) * -2128831035) ^ (this.previous_checkout == null ? 0 : this.previous_checkout.hashCode())) * -2128831035;
        if (this.current_checkout != null) {
            i = this.current_checkout.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SettingDatesTracking{previous_checkin=" + this.previous_checkin + ", current_checkin=" + this.current_checkin + ", previous_checkout=" + this.previous_checkout + ", current_checkout=" + this.current_checkout + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
