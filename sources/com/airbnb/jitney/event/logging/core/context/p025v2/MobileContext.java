package com.airbnb.jitney.event.logging.core.context.p025v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

/* renamed from: com.airbnb.jitney.event.logging.core.context.v2.MobileContext */
public final class MobileContext implements Struct {
    public static final Adapter<MobileContext, Builder> ADAPTER = new MobileContextAdapter();
    public final MobileBuildType build_type;
    public final String carrier_country;
    public final String carrier_name;
    public final String device_id;
    public final String device_type;
    public final String network_type;
    public final ScreenOrientation screen_orientation;
    public final Long version_code;

    /* renamed from: com.airbnb.jitney.event.logging.core.context.v2.MobileContext$Builder */
    public static final class Builder implements StructBuilder<MobileContext> {
        /* access modifiers changed from: private */
        public MobileBuildType build_type;
        /* access modifiers changed from: private */
        public String carrier_country;
        /* access modifiers changed from: private */
        public String carrier_name;
        /* access modifiers changed from: private */
        public String device_id;
        /* access modifiers changed from: private */
        public String device_type;
        /* access modifiers changed from: private */
        public String network_type;
        /* access modifiers changed from: private */
        public ScreenOrientation screen_orientation;
        /* access modifiers changed from: private */
        public Long version_code;

        private Builder() {
        }

        public Builder(String device_type2, String device_id2, Long version_code2, ScreenOrientation screen_orientation2, String network_type2) {
            this.device_type = device_type2;
            this.device_id = device_id2;
            this.version_code = version_code2;
            this.screen_orientation = screen_orientation2;
            this.network_type = network_type2;
        }

        public Builder carrier_name(String carrier_name2) {
            this.carrier_name = carrier_name2;
            return this;
        }

        public Builder carrier_country(String carrier_country2) {
            this.carrier_country = carrier_country2;
            return this;
        }

        public Builder build_type(MobileBuildType build_type2) {
            this.build_type = build_type2;
            return this;
        }

        public MobileContext build() {
            if (this.device_type == null) {
                throw new IllegalStateException("Required field 'device_type' is missing");
            } else if (this.device_id == null) {
                throw new IllegalStateException("Required field 'device_id' is missing");
            } else if (this.version_code == null) {
                throw new IllegalStateException("Required field 'version_code' is missing");
            } else if (this.screen_orientation == null) {
                throw new IllegalStateException("Required field 'screen_orientation' is missing");
            } else if (this.network_type != null) {
                return new MobileContext(this);
            } else {
                throw new IllegalStateException("Required field 'network_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.core.context.v2.MobileContext$MobileContextAdapter */
    private static final class MobileContextAdapter implements Adapter<MobileContext, Builder> {
        private MobileContextAdapter() {
        }

        public void write(Protocol protocol, MobileContext struct) throws IOException {
            protocol.writeStructBegin("MobileContext");
            protocol.writeFieldBegin("device_type", 1, PassportService.SF_DG11);
            protocol.writeString(struct.device_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("device_id", 2, PassportService.SF_DG11);
            protocol.writeString(struct.device_id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("version_code", 3, 10);
            protocol.writeI64(struct.version_code.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("screen_orientation", 4, 8);
            protocol.writeI32(struct.screen_orientation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(JPushReportInterface.NETWORK_TYPE, 5, PassportService.SF_DG11);
            protocol.writeString(struct.network_type);
            protocol.writeFieldEnd();
            if (struct.carrier_name != null) {
                protocol.writeFieldBegin("carrier_name", 6, PassportService.SF_DG11);
                protocol.writeString(struct.carrier_name);
                protocol.writeFieldEnd();
            }
            if (struct.carrier_country != null) {
                protocol.writeFieldBegin("carrier_country", 7, PassportService.SF_DG11);
                protocol.writeString(struct.carrier_country);
                protocol.writeFieldEnd();
            }
            if (struct.build_type != null) {
                protocol.writeFieldBegin("build_type", 8, 8);
                protocol.writeI32(struct.build_type.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private MobileContext(Builder builder) {
        this.device_type = builder.device_type;
        this.device_id = builder.device_id;
        this.version_code = builder.version_code;
        this.screen_orientation = builder.screen_orientation;
        this.network_type = builder.network_type;
        this.carrier_name = builder.carrier_name;
        this.carrier_country = builder.carrier_country;
        this.build_type = builder.build_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof MobileContext)) {
            return false;
        }
        MobileContext that = (MobileContext) other;
        if ((this.device_type == that.device_type || this.device_type.equals(that.device_type)) && ((this.device_id == that.device_id || this.device_id.equals(that.device_id)) && ((this.version_code == that.version_code || this.version_code.equals(that.version_code)) && ((this.screen_orientation == that.screen_orientation || this.screen_orientation.equals(that.screen_orientation)) && ((this.network_type == that.network_type || this.network_type.equals(that.network_type)) && ((this.carrier_name == that.carrier_name || (this.carrier_name != null && this.carrier_name.equals(that.carrier_name))) && (this.carrier_country == that.carrier_country || (this.carrier_country != null && this.carrier_country.equals(that.carrier_country))))))))) {
            if (this.build_type == that.build_type) {
                return true;
            }
            if (this.build_type != null && this.build_type.equals(that.build_type)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((16777619 ^ this.device_type.hashCode()) * -2128831035) ^ this.device_id.hashCode()) * -2128831035) ^ this.version_code.hashCode()) * -2128831035) ^ this.screen_orientation.hashCode()) * -2128831035) ^ this.network_type.hashCode()) * -2128831035) ^ (this.carrier_name == null ? 0 : this.carrier_name.hashCode())) * -2128831035) ^ (this.carrier_country == null ? 0 : this.carrier_country.hashCode())) * -2128831035;
        if (this.build_type != null) {
            i = this.build_type.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "MobileContext{device_type=" + this.device_type + ", device_id=" + this.device_id + ", version_code=" + this.version_code + ", screen_orientation=" + this.screen_orientation + ", network_type=" + this.network_type + ", carrier_name=" + this.carrier_name + ", carrier_country=" + this.carrier_country + ", build_type=" + this.build_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
