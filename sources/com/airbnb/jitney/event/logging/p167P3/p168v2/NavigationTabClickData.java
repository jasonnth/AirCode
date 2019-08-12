package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.NavigationTabClickData */
public final class NavigationTabClickData implements Struct {
    public static final Adapter<NavigationTabClickData, Object> ADAPTER = new NavigationTabClickDataAdapter();
    public final String from_section_id;
    public final String section_id;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.NavigationTabClickData$NavigationTabClickDataAdapter */
    private static final class NavigationTabClickDataAdapter implements Adapter<NavigationTabClickData, Object> {
        private NavigationTabClickDataAdapter() {
        }

        public void write(Protocol protocol, NavigationTabClickData struct) throws IOException {
            protocol.writeStructBegin("NavigationTabClickData");
            if (struct.section_id != null) {
                protocol.writeFieldBegin("section_id", 1, PassportService.SF_DG11);
                protocol.writeString(struct.section_id);
                protocol.writeFieldEnd();
            }
            if (struct.from_section_id != null) {
                protocol.writeFieldBegin("from_section_id", 2, PassportService.SF_DG11);
                protocol.writeString(struct.from_section_id);
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
        if (!(other instanceof NavigationTabClickData)) {
            return false;
        }
        NavigationTabClickData that = (NavigationTabClickData) other;
        if (this.section_id == that.section_id || (this.section_id != null && this.section_id.equals(that.section_id))) {
            if (this.from_section_id == that.from_section_id) {
                return true;
            }
            if (this.from_section_id != null && this.from_section_id.equals(that.from_section_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (16777619 ^ (this.section_id == null ? 0 : this.section_id.hashCode())) * -2128831035;
        if (this.from_section_id != null) {
            i = this.from_section_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "NavigationTabClickData{section_id=" + this.section_id + ", from_section_id=" + this.from_section_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
