package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.facebook.share.internal.ShareConstants;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingPanelTracking */
public final class SettingPanelTracking implements Struct {
    public static final Adapter<SettingPanelTracking, Object> ADAPTER = new SettingPanelTrackingAdapter();
    public final SettingPanelAction action;
    public final SettingDatesTracking dates;
    public final SettingGuestsTracking guests;
    public final SettingMembershipTracking membership;
    public final SettingNameTracking name;
    public final SettingPrivacyTracking privacy;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingPanelTracking$SettingPanelTrackingAdapter */
    private static final class SettingPanelTrackingAdapter implements Adapter<SettingPanelTracking, Object> {
        private SettingPanelTrackingAdapter() {
        }

        public void write(Protocol protocol, SettingPanelTracking struct) throws IOException {
            protocol.writeStructBegin("SettingPanelTracking");
            if (struct.action != null) {
                protocol.writeFieldBegin("action", 1, 8);
                protocol.writeI32(struct.action.value);
                protocol.writeFieldEnd();
            }
            if (struct.name != null) {
                protocol.writeFieldBegin("name", 2, PassportService.SF_DG12);
                SettingNameTracking.ADAPTER.write(protocol, struct.name);
                protocol.writeFieldEnd();
            }
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 3, PassportService.SF_DG12);
                SettingDatesTracking.ADAPTER.write(protocol, struct.dates);
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 4, PassportService.SF_DG12);
                SettingGuestsTracking.ADAPTER.write(protocol, struct.guests);
                protocol.writeFieldEnd();
            }
            if (struct.privacy != null) {
                protocol.writeFieldBegin(ShareConstants.WEB_DIALOG_PARAM_PRIVACY, 5, PassportService.SF_DG12);
                SettingPrivacyTracking.ADAPTER.write(protocol, struct.privacy);
                protocol.writeFieldEnd();
            }
            if (struct.membership != null) {
                protocol.writeFieldBegin("membership", 6, PassportService.SF_DG12);
                SettingMembershipTracking.ADAPTER.write(protocol, struct.membership);
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
        if (!(other instanceof SettingPanelTracking)) {
            return false;
        }
        SettingPanelTracking that = (SettingPanelTracking) other;
        if ((this.action == that.action || (this.action != null && this.action.equals(that.action))) && ((this.name == that.name || (this.name != null && this.name.equals(that.name))) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && (this.privacy == that.privacy || (this.privacy != null && this.privacy.equals(that.privacy))))))) {
            if (this.membership == that.membership) {
                return true;
            }
            if (this.membership != null && this.membership.equals(that.membership)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.action == null ? 0 : this.action.hashCode())) * -2128831035) ^ (this.name == null ? 0 : this.name.hashCode())) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.privacy == null ? 0 : this.privacy.hashCode())) * -2128831035;
        if (this.membership != null) {
            i = this.membership.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SettingPanelTracking{action=" + this.action + ", name=" + this.name + ", dates=" + this.dates + ", guests=" + this.guests + ", privacy=" + this.privacy + ", membership=" + this.membership + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
