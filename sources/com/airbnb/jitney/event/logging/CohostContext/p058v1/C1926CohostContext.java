package com.airbnb.jitney.event.logging.CohostContext.p058v1;

import com.airbnb.jitney.event.logging.CohostAttribute.p057v1.C1924CohostAttribute;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.CohostContext.v1.CohostContext */
public final class C1926CohostContext implements Struct {
    public static final Adapter<C1926CohostContext, Object> ADAPTER = new CohostContextAdapter();
    public final C1924CohostAttribute cohost_attributes;
    public final Long cohosting_owner_id;
    public final Long listing_id;
    public final Long n_cohosts;
    public final Long primary_host_id;
    public final Boolean primary_host_is_cohost;

    /* renamed from: com.airbnb.jitney.event.logging.CohostContext.v1.CohostContext$CohostContextAdapter */
    private static final class CohostContextAdapter implements Adapter<C1926CohostContext, Object> {
        private CohostContextAdapter() {
        }

        public void write(Protocol protocol, C1926CohostContext struct) throws IOException {
            protocol.writeStructBegin("CohostContext");
            if (struct.cohosting_owner_id != null) {
                protocol.writeFieldBegin("cohosting_owner_id", 1, 10);
                protocol.writeI64(struct.cohosting_owner_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 2, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("n_cohosts", 3, 10);
            protocol.writeI64(struct.n_cohosts.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("primary_host_id", 4, 10);
            protocol.writeI64(struct.primary_host_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("primary_host_is_cohost", 5, 2);
            protocol.writeBool(struct.primary_host_is_cohost.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohost_attributes", 6, PassportService.SF_DG12);
            C1924CohostAttribute.ADAPTER.write(protocol, struct.cohost_attributes);
            protocol.writeFieldEnd();
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
        if (!(other instanceof C1926CohostContext)) {
            return false;
        }
        C1926CohostContext that = (C1926CohostContext) other;
        if ((this.cohosting_owner_id == that.cohosting_owner_id || (this.cohosting_owner_id != null && this.cohosting_owner_id.equals(that.cohosting_owner_id))) && ((this.listing_id == that.listing_id || (this.listing_id != null && this.listing_id.equals(that.listing_id))) && ((this.n_cohosts == that.n_cohosts || this.n_cohosts.equals(that.n_cohosts)) && ((this.primary_host_id == that.primary_host_id || this.primary_host_id.equals(that.primary_host_id)) && ((this.primary_host_is_cohost == that.primary_host_is_cohost || this.primary_host_is_cohost.equals(that.primary_host_is_cohost)) && (this.cohost_attributes == that.cohost_attributes || this.cohost_attributes.equals(that.cohost_attributes))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (16777619 ^ (this.cohosting_owner_id == null ? 0 : this.cohosting_owner_id.hashCode())) * -2128831035;
        if (this.listing_id != null) {
            i = this.listing_id.hashCode();
        }
        return (((((((((code ^ i) * -2128831035) ^ this.n_cohosts.hashCode()) * -2128831035) ^ this.primary_host_id.hashCode()) * -2128831035) ^ this.primary_host_is_cohost.hashCode()) * -2128831035) ^ this.cohost_attributes.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostContext{cohosting_owner_id=" + this.cohosting_owner_id + ", listing_id=" + this.listing_id + ", n_cohosts=" + this.n_cohosts + ", primary_host_id=" + this.primary_host_id + ", primary_host_is_cohost=" + this.primary_host_is_cohost + ", cohost_attributes=" + this.cohost_attributes + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
