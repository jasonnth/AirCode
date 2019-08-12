package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingGuestsTracking */
public final class SettingGuestsTracking implements Struct {
    public static final Adapter<SettingGuestsTracking, Object> ADAPTER = new SettingGuestsTrackingAdapter();
    public final Integer current_number_of_adults;
    public final Integer current_number_of_children;
    public final Integer current_number_of_infants;
    public final Integer previous_number_of_adults;
    public final Integer previous_number_of_children;
    public final Integer previous_number_of_infants;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.SettingGuestsTracking$SettingGuestsTrackingAdapter */
    private static final class SettingGuestsTrackingAdapter implements Adapter<SettingGuestsTracking, Object> {
        private SettingGuestsTrackingAdapter() {
        }

        public void write(Protocol protocol, SettingGuestsTracking struct) throws IOException {
            protocol.writeStructBegin("SettingGuestsTracking");
            if (struct.previous_number_of_adults != null) {
                protocol.writeFieldBegin("previous_number_of_adults", 1, 8);
                protocol.writeI32(struct.previous_number_of_adults.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.current_number_of_adults != null) {
                protocol.writeFieldBegin("current_number_of_adults", 2, 8);
                protocol.writeI32(struct.current_number_of_adults.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.previous_number_of_children != null) {
                protocol.writeFieldBegin("previous_number_of_children", 3, 8);
                protocol.writeI32(struct.previous_number_of_children.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.current_number_of_children != null) {
                protocol.writeFieldBegin("current_number_of_children", 4, 8);
                protocol.writeI32(struct.current_number_of_children.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.previous_number_of_infants != null) {
                protocol.writeFieldBegin("previous_number_of_infants", 5, 8);
                protocol.writeI32(struct.previous_number_of_infants.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.current_number_of_infants != null) {
                protocol.writeFieldBegin("current_number_of_infants", 6, 8);
                protocol.writeI32(struct.current_number_of_infants.intValue());
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
        if (!(other instanceof SettingGuestsTracking)) {
            return false;
        }
        SettingGuestsTracking that = (SettingGuestsTracking) other;
        if ((this.previous_number_of_adults == that.previous_number_of_adults || (this.previous_number_of_adults != null && this.previous_number_of_adults.equals(that.previous_number_of_adults))) && ((this.current_number_of_adults == that.current_number_of_adults || (this.current_number_of_adults != null && this.current_number_of_adults.equals(that.current_number_of_adults))) && ((this.previous_number_of_children == that.previous_number_of_children || (this.previous_number_of_children != null && this.previous_number_of_children.equals(that.previous_number_of_children))) && ((this.current_number_of_children == that.current_number_of_children || (this.current_number_of_children != null && this.current_number_of_children.equals(that.current_number_of_children))) && (this.previous_number_of_infants == that.previous_number_of_infants || (this.previous_number_of_infants != null && this.previous_number_of_infants.equals(that.previous_number_of_infants))))))) {
            if (this.current_number_of_infants == that.current_number_of_infants) {
                return true;
            }
            if (this.current_number_of_infants != null && this.current_number_of_infants.equals(that.current_number_of_infants)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.previous_number_of_adults == null ? 0 : this.previous_number_of_adults.hashCode())) * -2128831035) ^ (this.current_number_of_adults == null ? 0 : this.current_number_of_adults.hashCode())) * -2128831035) ^ (this.previous_number_of_children == null ? 0 : this.previous_number_of_children.hashCode())) * -2128831035) ^ (this.current_number_of_children == null ? 0 : this.current_number_of_children.hashCode())) * -2128831035) ^ (this.previous_number_of_infants == null ? 0 : this.previous_number_of_infants.hashCode())) * -2128831035;
        if (this.current_number_of_infants != null) {
            i = this.current_number_of_infants.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SettingGuestsTracking{previous_number_of_adults=" + this.previous_number_of_adults + ", current_number_of_adults=" + this.current_number_of_adults + ", previous_number_of_children=" + this.previous_number_of_children + ", current_number_of_children=" + this.current_number_of_children + ", previous_number_of_infants=" + this.previous_number_of_infants + ", current_number_of_infants=" + this.current_number_of_infants + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
