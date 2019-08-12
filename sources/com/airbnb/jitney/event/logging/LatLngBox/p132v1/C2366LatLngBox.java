package com.airbnb.jitney.event.logging.LatLngBox.p132v1;

import com.airbnb.jitney.event.logging.LatLngPair.p133v1.C2368LatLngPair;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.LatLngBox.v1.LatLngBox */
public final class C2366LatLngBox implements Struct {
    public static final Adapter<C2366LatLngBox, Builder> ADAPTER = new LatLngBoxAdapter();
    public final C2368LatLngPair ne_lat_lng;
    public final C2368LatLngPair sw_lat_lng;

    /* renamed from: com.airbnb.jitney.event.logging.LatLngBox.v1.LatLngBox$Builder */
    public static final class Builder implements StructBuilder<C2366LatLngBox> {
        /* access modifiers changed from: private */
        public C2368LatLngPair ne_lat_lng;
        /* access modifiers changed from: private */
        public C2368LatLngPair sw_lat_lng;

        private Builder() {
        }

        public Builder(C2368LatLngPair ne_lat_lng2, C2368LatLngPair sw_lat_lng2) {
            this.ne_lat_lng = ne_lat_lng2;
            this.sw_lat_lng = sw_lat_lng2;
        }

        public C2366LatLngBox build() {
            if (this.ne_lat_lng == null) {
                throw new IllegalStateException("Required field 'ne_lat_lng' is missing");
            } else if (this.sw_lat_lng != null) {
                return new C2366LatLngBox(this);
            } else {
                throw new IllegalStateException("Required field 'sw_lat_lng' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.LatLngBox.v1.LatLngBox$LatLngBoxAdapter */
    private static final class LatLngBoxAdapter implements Adapter<C2366LatLngBox, Builder> {
        private LatLngBoxAdapter() {
        }

        public void write(Protocol protocol, C2366LatLngBox struct) throws IOException {
            protocol.writeStructBegin("LatLngBox");
            protocol.writeFieldBegin("ne_lat_lng", 1, PassportService.SF_DG12);
            C2368LatLngPair.ADAPTER.write(protocol, struct.ne_lat_lng);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("sw_lat_lng", 2, PassportService.SF_DG12);
            C2368LatLngPair.ADAPTER.write(protocol, struct.sw_lat_lng);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2366LatLngBox(Builder builder) {
        this.ne_lat_lng = builder.ne_lat_lng;
        this.sw_lat_lng = builder.sw_lat_lng;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2366LatLngBox)) {
            return false;
        }
        C2366LatLngBox that = (C2366LatLngBox) other;
        if ((this.ne_lat_lng == that.ne_lat_lng || this.ne_lat_lng.equals(that.ne_lat_lng)) && (this.sw_lat_lng == that.sw_lat_lng || this.sw_lat_lng.equals(that.sw_lat_lng))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((16777619 ^ this.ne_lat_lng.hashCode()) * -2128831035) ^ this.sw_lat_lng.hashCode()) * -2128831035;
    }

    public String toString() {
        return "LatLngBox{ne_lat_lng=" + this.ne_lat_lng + ", sw_lat_lng=" + this.sw_lat_lng + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
