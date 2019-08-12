package com.airbnb.jitney.event.logging.LatLngPair.p133v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.LatLngPair.v1.LatLngPair */
public final class C2368LatLngPair implements Struct {
    public static final Adapter<C2368LatLngPair, Builder> ADAPTER = new LatLngPairAdapter();
    public final Double lat;
    public final Double lng;

    /* renamed from: com.airbnb.jitney.event.logging.LatLngPair.v1.LatLngPair$Builder */
    public static final class Builder implements StructBuilder<C2368LatLngPair> {
        /* access modifiers changed from: private */
        public Double lat;
        /* access modifiers changed from: private */
        public Double lng;

        private Builder() {
        }

        public Builder(Double lat2, Double lng2) {
            this.lat = lat2;
            this.lng = lng2;
        }

        public C2368LatLngPair build() {
            if (this.lat == null) {
                throw new IllegalStateException("Required field 'lat' is missing");
            } else if (this.lng != null) {
                return new C2368LatLngPair(this);
            } else {
                throw new IllegalStateException("Required field 'lng' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.LatLngPair.v1.LatLngPair$LatLngPairAdapter */
    private static final class LatLngPairAdapter implements Adapter<C2368LatLngPair, Builder> {
        private LatLngPairAdapter() {
        }

        public void write(Protocol protocol, C2368LatLngPair struct) throws IOException {
            protocol.writeStructBegin("LatLngPair");
            protocol.writeFieldBegin("lat", 1, 4);
            protocol.writeDouble(struct.lat.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("lng", 2, 4);
            protocol.writeDouble(struct.lng.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2368LatLngPair(Builder builder) {
        this.lat = builder.lat;
        this.lng = builder.lng;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2368LatLngPair)) {
            return false;
        }
        C2368LatLngPair that = (C2368LatLngPair) other;
        if ((this.lat == that.lat || this.lat.equals(that.lat)) && (this.lng == that.lng || this.lng.equals(that.lng))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((16777619 ^ this.lat.hashCode()) * -2128831035) ^ this.lng.hashCode()) * -2128831035;
    }

    public String toString() {
        return "LatLngPair{lat=" + this.lat + ", lng=" + this.lng + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
