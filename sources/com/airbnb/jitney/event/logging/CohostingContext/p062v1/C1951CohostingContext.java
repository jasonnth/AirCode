package com.airbnb.jitney.event.logging.CohostingContext.p062v1;

import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import com.airbnb.jitney.event.logging.ListingManagerAttribute.p135v1.C2371ListingManagerAttribute;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.CohostingContext.v1.CohostingContext */
public final class C1951CohostingContext implements Struct {
    public static final Adapter<C1951CohostingContext, Builder> ADAPTER = new CohostingContextAdapter();
    public final Long cohosting_owner_id;
    public final Long listing_id;
    public final C2371ListingManagerAttribute listing_manager_attribute;
    public final Long n_cohosts;
    public final Long primary_host_id;
    public final C1958CohostingSourceFlow source_flow;

    /* renamed from: com.airbnb.jitney.event.logging.CohostingContext.v1.CohostingContext$Builder */
    public static final class Builder implements StructBuilder<C1951CohostingContext> {
        /* access modifiers changed from: private */
        public Long cohosting_owner_id;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2371ListingManagerAttribute listing_manager_attribute;
        /* access modifiers changed from: private */
        public Long n_cohosts;
        /* access modifiers changed from: private */
        public Long primary_host_id;
        /* access modifiers changed from: private */
        public C1958CohostingSourceFlow source_flow;

        private Builder() {
        }

        public Builder(Long cohosting_owner_id2, Long listing_id2, Long n_cohosts2, Long primary_host_id2) {
            this.cohosting_owner_id = cohosting_owner_id2;
            this.listing_id = listing_id2;
            this.n_cohosts = n_cohosts2;
            this.primary_host_id = primary_host_id2;
        }

        public Builder(C1951CohostingContext struct) {
            this.cohosting_owner_id = struct.cohosting_owner_id;
            this.listing_id = struct.listing_id;
            this.n_cohosts = struct.n_cohosts;
            this.primary_host_id = struct.primary_host_id;
            this.listing_manager_attribute = struct.listing_manager_attribute;
            this.source_flow = struct.source_flow;
        }

        public Builder listing_manager_attribute(C2371ListingManagerAttribute listing_manager_attribute2) {
            this.listing_manager_attribute = listing_manager_attribute2;
            return this;
        }

        public Builder source_flow(C1958CohostingSourceFlow source_flow2) {
            this.source_flow = source_flow2;
            return this;
        }

        public C1951CohostingContext build() {
            if (this.cohosting_owner_id == null) {
                throw new IllegalStateException("Required field 'cohosting_owner_id' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.n_cohosts == null) {
                throw new IllegalStateException("Required field 'n_cohosts' is missing");
            } else if (this.primary_host_id != null) {
                return new C1951CohostingContext(this);
            } else {
                throw new IllegalStateException("Required field 'primary_host_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.CohostingContext.v1.CohostingContext$CohostingContextAdapter */
    private static final class CohostingContextAdapter implements Adapter<C1951CohostingContext, Builder> {
        private CohostingContextAdapter() {
        }

        public void write(Protocol protocol, C1951CohostingContext struct) throws IOException {
            protocol.writeStructBegin("CohostingContext");
            protocol.writeFieldBegin("cohosting_owner_id", 1, 10);
            protocol.writeI64(struct.cohosting_owner_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 2, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("n_cohosts", 3, 10);
            protocol.writeI64(struct.n_cohosts.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("primary_host_id", 4, 10);
            protocol.writeI64(struct.primary_host_id.longValue());
            protocol.writeFieldEnd();
            if (struct.listing_manager_attribute != null) {
                protocol.writeFieldBegin("listing_manager_attribute", 5, PassportService.SF_DG12);
                C2371ListingManagerAttribute.ADAPTER.write(protocol, struct.listing_manager_attribute);
                protocol.writeFieldEnd();
            }
            if (struct.source_flow != null) {
                protocol.writeFieldBegin("source_flow", 6, 8);
                protocol.writeI32(struct.source_flow.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C1951CohostingContext(Builder builder) {
        this.cohosting_owner_id = builder.cohosting_owner_id;
        this.listing_id = builder.listing_id;
        this.n_cohosts = builder.n_cohosts;
        this.primary_host_id = builder.primary_host_id;
        this.listing_manager_attribute = builder.listing_manager_attribute;
        this.source_flow = builder.source_flow;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C1951CohostingContext)) {
            return false;
        }
        C1951CohostingContext that = (C1951CohostingContext) other;
        if ((this.cohosting_owner_id == that.cohosting_owner_id || this.cohosting_owner_id.equals(that.cohosting_owner_id)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.n_cohosts == that.n_cohosts || this.n_cohosts.equals(that.n_cohosts)) && ((this.primary_host_id == that.primary_host_id || this.primary_host_id.equals(that.primary_host_id)) && (this.listing_manager_attribute == that.listing_manager_attribute || (this.listing_manager_attribute != null && this.listing_manager_attribute.equals(that.listing_manager_attribute))))))) {
            if (this.source_flow == that.source_flow) {
                return true;
            }
            if (this.source_flow != null && this.source_flow.equals(that.source_flow)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ this.cohosting_owner_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.n_cohosts.hashCode()) * -2128831035) ^ this.primary_host_id.hashCode()) * -2128831035) ^ (this.listing_manager_attribute == null ? 0 : this.listing_manager_attribute.hashCode())) * -2128831035;
        if (this.source_flow != null) {
            i = this.source_flow.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "CohostingContext{cohosting_owner_id=" + this.cohosting_owner_id + ", listing_id=" + this.listing_id + ", n_cohosts=" + this.n_cohosts + ", primary_host_id=" + this.primary_host_id + ", listing_manager_attribute=" + this.listing_manager_attribute + ", source_flow=" + this.source_flow + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
