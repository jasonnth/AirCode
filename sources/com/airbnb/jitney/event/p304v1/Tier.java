package com.airbnb.jitney.event.p304v1;

import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.v1.Tier */
public final class Tier implements Struct {
    public static final Adapter<Tier, Builder> ADAPTER = new TierAdapter();
    public final String hostname;

    /* renamed from: id */
    public final String f2698id;
    public final String ip_address;
    public final List<Sequence> sequences;
    public final Long timestamp;
    public final String type;

    /* renamed from: com.airbnb.jitney.event.v1.Tier$Builder */
    public static final class Builder implements StructBuilder<Tier> {
        /* access modifiers changed from: private */
        public String hostname;
        /* access modifiers changed from: private */

        /* renamed from: id */
        public String f2699id;
        /* access modifiers changed from: private */
        public String ip_address;
        /* access modifiers changed from: private */
        public List<Sequence> sequences = new ArrayList();
        /* access modifiers changed from: private */
        public Long timestamp;
        /* access modifiers changed from: private */
        public String type;

        private Builder() {
        }

        public Builder(String id, String type2, String hostname2, String ip_address2, Long timestamp2) {
            this.f2699id = id;
            this.type = type2;
            this.hostname = hostname2;
            this.ip_address = ip_address2;
            this.timestamp = timestamp2;
        }

        public Tier build() {
            if (this.f2699id == null) {
                throw new IllegalStateException("Required field 'id' is missing");
            } else if (this.type == null) {
                throw new IllegalStateException("Required field 'type' is missing");
            } else if (this.hostname == null) {
                throw new IllegalStateException("Required field 'hostname' is missing");
            } else if (this.ip_address == null) {
                throw new IllegalStateException("Required field 'ip_address' is missing");
            } else if (this.timestamp == null) {
                throw new IllegalStateException("Required field 'timestamp' is missing");
            } else if (this.sequences != null) {
                return new Tier(this);
            } else {
                throw new IllegalStateException("Required field 'sequences' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.v1.Tier$TierAdapter */
    private static final class TierAdapter implements Adapter<Tier, Builder> {
        private TierAdapter() {
        }

        public void write(Protocol protocol, Tier struct) throws IOException {
            protocol.writeStructBegin("Tier");
            protocol.writeFieldBegin("id", 1, PassportService.SF_DG11);
            protocol.writeString(struct.f2698id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("type", 2, PassportService.SF_DG11);
            protocol.writeString(struct.type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("hostname", 3, PassportService.SF_DG11);
            protocol.writeString(struct.hostname);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("ip_address", 4, PassportService.SF_DG11);
            protocol.writeString(struct.ip_address);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ErfExperimentsModel.TIMESTAMP, 5, 10);
            protocol.writeI64(struct.timestamp.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("sequences", 6, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.sequences.size());
            for (Sequence item0 : struct.sequences) {
                Sequence.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private Tier(Builder builder) {
        this.f2698id = builder.f2699id;
        this.type = builder.type;
        this.hostname = builder.hostname;
        this.ip_address = builder.ip_address;
        this.timestamp = builder.timestamp;
        this.sequences = Collections.unmodifiableList(builder.sequences);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Tier)) {
            return false;
        }
        Tier that = (Tier) other;
        if ((this.f2698id == that.f2698id || this.f2698id.equals(that.f2698id)) && ((this.type == that.type || this.type.equals(that.type)) && ((this.hostname == that.hostname || this.hostname.equals(that.hostname)) && ((this.ip_address == that.ip_address || this.ip_address.equals(that.ip_address)) && ((this.timestamp == that.timestamp || this.timestamp.equals(that.timestamp)) && (this.sequences == that.sequences || this.sequences.equals(that.sequences))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ this.f2698id.hashCode()) * -2128831035) ^ this.type.hashCode()) * -2128831035) ^ this.hostname.hashCode()) * -2128831035) ^ this.ip_address.hashCode()) * -2128831035) ^ this.timestamp.hashCode()) * -2128831035) ^ this.sequences.hashCode()) * -2128831035;
    }

    public String toString() {
        return "Tier{id=" + this.f2698id + ", type=" + this.type + ", hostname=" + this.hostname + ", ip_address=" + this.ip_address + ", timestamp=" + this.timestamp + ", sequences=" + this.sequences + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
