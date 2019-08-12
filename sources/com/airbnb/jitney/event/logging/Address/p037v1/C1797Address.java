package com.airbnb.jitney.event.logging.Address.p037v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Address.v1.Address */
public final class C1797Address implements Struct {
    public static final Adapter<C1797Address, Builder> ADAPTER = new AddressAdapter();
    public final String apt_num;
    public final String city;
    public final String country_code;
    public final String geo_name;
    public final String state;
    public final String street_address;
    public final String zip;

    /* renamed from: com.airbnb.jitney.event.logging.Address.v1.Address$AddressAdapter */
    private static final class AddressAdapter implements Adapter<C1797Address, Builder> {
        private AddressAdapter() {
        }

        public void write(Protocol protocol, C1797Address struct) throws IOException {
            protocol.writeStructBegin("Address");
            if (struct.geo_name != null) {
                protocol.writeFieldBegin("geo_name", 1, PassportService.SF_DG11);
                protocol.writeString(struct.geo_name);
                protocol.writeFieldEnd();
            }
            if (struct.street_address != null) {
                protocol.writeFieldBegin("street_address", 2, PassportService.SF_DG11);
                protocol.writeString(struct.street_address);
                protocol.writeFieldEnd();
            }
            if (struct.apt_num != null) {
                protocol.writeFieldBegin("apt_num", 3, PassportService.SF_DG11);
                protocol.writeString(struct.apt_num);
                protocol.writeFieldEnd();
            }
            if (struct.country_code != null) {
                protocol.writeFieldBegin("country_code", 4, PassportService.SF_DG11);
                protocol.writeString(struct.country_code);
                protocol.writeFieldEnd();
            }
            if (struct.city != null) {
                protocol.writeFieldBegin("city", 5, PassportService.SF_DG11);
                protocol.writeString(struct.city);
                protocol.writeFieldEnd();
            }
            if (struct.state != null) {
                protocol.writeFieldBegin("state", 6, PassportService.SF_DG11);
                protocol.writeString(struct.state);
                protocol.writeFieldEnd();
            }
            if (struct.zip != null) {
                protocol.writeFieldBegin("zip", 7, PassportService.SF_DG11);
                protocol.writeString(struct.zip);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Address.v1.Address$Builder */
    public static final class Builder implements StructBuilder<C1797Address> {
        /* access modifiers changed from: private */
        public String apt_num;
        /* access modifiers changed from: private */
        public String city;
        /* access modifiers changed from: private */
        public String country_code;
        /* access modifiers changed from: private */
        public String geo_name;
        /* access modifiers changed from: private */
        public String state;
        /* access modifiers changed from: private */
        public String street_address;
        /* access modifiers changed from: private */
        public String zip;

        public Builder street_address(String street_address2) {
            this.street_address = street_address2;
            return this;
        }

        public Builder apt_num(String apt_num2) {
            this.apt_num = apt_num2;
            return this;
        }

        public Builder country_code(String country_code2) {
            this.country_code = country_code2;
            return this;
        }

        public Builder city(String city2) {
            this.city = city2;
            return this;
        }

        public Builder state(String state2) {
            this.state = state2;
            return this;
        }

        public Builder zip(String zip2) {
            this.zip = zip2;
            return this;
        }

        public C1797Address build() {
            return new C1797Address(this);
        }
    }

    private C1797Address(Builder builder) {
        this.geo_name = builder.geo_name;
        this.street_address = builder.street_address;
        this.apt_num = builder.apt_num;
        this.country_code = builder.country_code;
        this.city = builder.city;
        this.state = builder.state;
        this.zip = builder.zip;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C1797Address)) {
            return false;
        }
        C1797Address that = (C1797Address) other;
        if ((this.geo_name == that.geo_name || (this.geo_name != null && this.geo_name.equals(that.geo_name))) && ((this.street_address == that.street_address || (this.street_address != null && this.street_address.equals(that.street_address))) && ((this.apt_num == that.apt_num || (this.apt_num != null && this.apt_num.equals(that.apt_num))) && ((this.country_code == that.country_code || (this.country_code != null && this.country_code.equals(that.country_code))) && ((this.city == that.city || (this.city != null && this.city.equals(that.city))) && (this.state == that.state || (this.state != null && this.state.equals(that.state)))))))) {
            if (this.zip == that.zip) {
                return true;
            }
            if (this.zip != null && this.zip.equals(that.zip)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((16777619 ^ (this.geo_name == null ? 0 : this.geo_name.hashCode())) * -2128831035) ^ (this.street_address == null ? 0 : this.street_address.hashCode())) * -2128831035) ^ (this.apt_num == null ? 0 : this.apt_num.hashCode())) * -2128831035) ^ (this.country_code == null ? 0 : this.country_code.hashCode())) * -2128831035) ^ (this.city == null ? 0 : this.city.hashCode())) * -2128831035) ^ (this.state == null ? 0 : this.state.hashCode())) * -2128831035;
        if (this.zip != null) {
            i = this.zip.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "Address{geo_name=" + this.geo_name + ", street_address=" + this.street_address + ", apt_num=" + this.apt_num + ", country_code=" + this.country_code + ", city=" + this.city + ", state=" + this.state + ", zip=" + this.zip + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
