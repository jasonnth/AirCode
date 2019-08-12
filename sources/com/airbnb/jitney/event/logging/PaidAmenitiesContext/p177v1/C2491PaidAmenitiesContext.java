package com.airbnb.jitney.event.logging.PaidAmenitiesContext.p177v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaidAmenitiesContext.v1.PaidAmenitiesContext */
public final class C2491PaidAmenitiesContext implements Struct {
    public static final Adapter<C2491PaidAmenitiesContext, Builder> ADAPTER = new PaidAmenitiesContextAdapter();
    public final Long listing_id;
    public final String paid_amenity_description;
    public final Long paid_amenity_id;
    public final Boolean paid_amenity_is_complimentary;
    public final Long paid_amenity_price_native_micros;
    public final String paid_amenity_title;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.PaidAmenitiesContext.v1.PaidAmenitiesContext$Builder */
    public static final class Builder implements StructBuilder<C2491PaidAmenitiesContext> {
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String paid_amenity_description;
        /* access modifiers changed from: private */
        public Long paid_amenity_id;
        /* access modifiers changed from: private */
        public Boolean paid_amenity_is_complimentary;
        /* access modifiers changed from: private */
        public Long paid_amenity_price_native_micros;
        /* access modifiers changed from: private */
        public String paid_amenity_title;
        /* access modifiers changed from: private */
        public Long user_id;

        public C2491PaidAmenitiesContext build() {
            return new C2491PaidAmenitiesContext(this);
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.PaidAmenitiesContext.v1.PaidAmenitiesContext$PaidAmenitiesContextAdapter */
    private static final class PaidAmenitiesContextAdapter implements Adapter<C2491PaidAmenitiesContext, Builder> {
        private PaidAmenitiesContextAdapter() {
        }

        public void write(Protocol protocol, C2491PaidAmenitiesContext struct) throws IOException {
            protocol.writeStructBegin("PaidAmenitiesContext");
            if (struct.paid_amenity_id != null) {
                protocol.writeFieldBegin("paid_amenity_id", 1, 10);
                protocol.writeI64(struct.paid_amenity_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.paid_amenity_title != null) {
                protocol.writeFieldBegin("paid_amenity_title", 2, PassportService.SF_DG11);
                protocol.writeString(struct.paid_amenity_title);
                protocol.writeFieldEnd();
            }
            if (struct.paid_amenity_description != null) {
                protocol.writeFieldBegin("paid_amenity_description", 3, PassportService.SF_DG11);
                protocol.writeString(struct.paid_amenity_description);
                protocol.writeFieldEnd();
            }
            if (struct.paid_amenity_is_complimentary != null) {
                protocol.writeFieldBegin("paid_amenity_is_complimentary", 4, 2);
                protocol.writeBool(struct.paid_amenity_is_complimentary.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.paid_amenity_price_native_micros != null) {
                protocol.writeFieldBegin("paid_amenity_price_native_micros", 5, 10);
                protocol.writeI64(struct.paid_amenity_price_native_micros.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 6, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.user_id != null) {
                protocol.writeFieldBegin("user_id", 7, 10);
                protocol.writeI64(struct.user_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2491PaidAmenitiesContext(Builder builder) {
        this.paid_amenity_id = builder.paid_amenity_id;
        this.paid_amenity_title = builder.paid_amenity_title;
        this.paid_amenity_description = builder.paid_amenity_description;
        this.paid_amenity_is_complimentary = builder.paid_amenity_is_complimentary;
        this.paid_amenity_price_native_micros = builder.paid_amenity_price_native_micros;
        this.listing_id = builder.listing_id;
        this.user_id = builder.user_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2491PaidAmenitiesContext)) {
            return false;
        }
        C2491PaidAmenitiesContext that = (C2491PaidAmenitiesContext) other;
        if ((this.paid_amenity_id == that.paid_amenity_id || (this.paid_amenity_id != null && this.paid_amenity_id.equals(that.paid_amenity_id))) && ((this.paid_amenity_title == that.paid_amenity_title || (this.paid_amenity_title != null && this.paid_amenity_title.equals(that.paid_amenity_title))) && ((this.paid_amenity_description == that.paid_amenity_description || (this.paid_amenity_description != null && this.paid_amenity_description.equals(that.paid_amenity_description))) && ((this.paid_amenity_is_complimentary == that.paid_amenity_is_complimentary || (this.paid_amenity_is_complimentary != null && this.paid_amenity_is_complimentary.equals(that.paid_amenity_is_complimentary))) && ((this.paid_amenity_price_native_micros == that.paid_amenity_price_native_micros || (this.paid_amenity_price_native_micros != null && this.paid_amenity_price_native_micros.equals(that.paid_amenity_price_native_micros))) && (this.listing_id == that.listing_id || (this.listing_id != null && this.listing_id.equals(that.listing_id)))))))) {
            if (this.user_id == that.user_id) {
                return true;
            }
            if (this.user_id != null && this.user_id.equals(that.user_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((16777619 ^ (this.paid_amenity_id == null ? 0 : this.paid_amenity_id.hashCode())) * -2128831035) ^ (this.paid_amenity_title == null ? 0 : this.paid_amenity_title.hashCode())) * -2128831035) ^ (this.paid_amenity_description == null ? 0 : this.paid_amenity_description.hashCode())) * -2128831035) ^ (this.paid_amenity_is_complimentary == null ? 0 : this.paid_amenity_is_complimentary.hashCode())) * -2128831035) ^ (this.paid_amenity_price_native_micros == null ? 0 : this.paid_amenity_price_native_micros.hashCode())) * -2128831035) ^ (this.listing_id == null ? 0 : this.listing_id.hashCode())) * -2128831035;
        if (this.user_id != null) {
            i = this.user_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PaidAmenitiesContext{paid_amenity_id=" + this.paid_amenity_id + ", paid_amenity_title=" + this.paid_amenity_title + ", paid_amenity_description=" + this.paid_amenity_description + ", paid_amenity_is_complimentary=" + this.paid_amenity_is_complimentary + ", paid_amenity_price_native_micros=" + this.paid_amenity_price_native_micros + ", listing_id=" + this.listing_id + ", user_id=" + this.user_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
