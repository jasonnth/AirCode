package com.airbnb.jitney.event.logging.SinglePriceChangeContext.p258v1;

import com.airbnb.android.core.constants.ManageListingArgConstants;
import com.airbnb.jitney.event.logging.DsNightAvailabilityStatus.p086v1.C1987DsNightAvailabilityStatus;
import com.airbnb.jitney.event.logging.SuggestedPriceBucketLevel.p261v1.C2747SuggestedPriceBucketLevel;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.SinglePriceChangeContext.v1.SinglePriceChangeContext */
public final class C2742SinglePriceChangeContext implements Struct {
    public static final Adapter<C2742SinglePriceChangeContext, Builder> ADAPTER = new SinglePriceChangeContextAdapter();
    public final Long daily_price;
    public final String date;
    public final C1987DsNightAvailabilityStatus ds_night_availability;
    public final Boolean is_smart_pricing_overridden_night;
    public final Long price_before_change;
    public final Long suggested_price;
    public final C2747SuggestedPriceBucketLevel suggested_price_bucket;

    /* renamed from: com.airbnb.jitney.event.logging.SinglePriceChangeContext.v1.SinglePriceChangeContext$Builder */
    public static final class Builder implements StructBuilder<C2742SinglePriceChangeContext> {
        /* access modifiers changed from: private */
        public Long daily_price;
        /* access modifiers changed from: private */
        public String date;
        /* access modifiers changed from: private */
        public C1987DsNightAvailabilityStatus ds_night_availability;
        /* access modifiers changed from: private */
        public Boolean is_smart_pricing_overridden_night;
        /* access modifiers changed from: private */
        public Long price_before_change;
        /* access modifiers changed from: private */
        public Long suggested_price;
        /* access modifiers changed from: private */
        public C2747SuggestedPriceBucketLevel suggested_price_bucket;

        private Builder() {
        }

        public Builder(String date2, C1987DsNightAvailabilityStatus ds_night_availability2, Long price_before_change2, Long suggested_price2, Long daily_price2, C2747SuggestedPriceBucketLevel suggested_price_bucket2, Boolean is_smart_pricing_overridden_night2) {
            this.date = date2;
            this.ds_night_availability = ds_night_availability2;
            this.price_before_change = price_before_change2;
            this.suggested_price = suggested_price2;
            this.daily_price = daily_price2;
            this.suggested_price_bucket = suggested_price_bucket2;
            this.is_smart_pricing_overridden_night = is_smart_pricing_overridden_night2;
        }

        public C2742SinglePriceChangeContext build() {
            if (this.date == null) {
                throw new IllegalStateException("Required field 'date' is missing");
            } else if (this.ds_night_availability == null) {
                throw new IllegalStateException("Required field 'ds_night_availability' is missing");
            } else if (this.price_before_change == null) {
                throw new IllegalStateException("Required field 'price_before_change' is missing");
            } else if (this.suggested_price == null) {
                throw new IllegalStateException("Required field 'suggested_price' is missing");
            } else if (this.daily_price == null) {
                throw new IllegalStateException("Required field 'daily_price' is missing");
            } else if (this.suggested_price_bucket == null) {
                throw new IllegalStateException("Required field 'suggested_price_bucket' is missing");
            } else if (this.is_smart_pricing_overridden_night != null) {
                return new C2742SinglePriceChangeContext(this);
            } else {
                throw new IllegalStateException("Required field 'is_smart_pricing_overridden_night' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.SinglePriceChangeContext.v1.SinglePriceChangeContext$SinglePriceChangeContextAdapter */
    private static final class SinglePriceChangeContextAdapter implements Adapter<C2742SinglePriceChangeContext, Builder> {
        private SinglePriceChangeContextAdapter() {
        }

        public void write(Protocol protocol, C2742SinglePriceChangeContext struct) throws IOException {
            protocol.writeStructBegin("SinglePriceChangeContext");
            protocol.writeFieldBegin("date", 1, PassportService.SF_DG11);
            protocol.writeString(struct.date);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("ds_night_availability", 2, 8);
            protocol.writeI32(struct.ds_night_availability.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("price_before_change", 3, 10);
            protocol.writeI64(struct.price_before_change.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingArgConstants.ARG_SUGGESTED_PRICE, 4, 10);
            protocol.writeI64(struct.suggested_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("daily_price", 5, 10);
            protocol.writeI64(struct.daily_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("suggested_price_bucket", 6, 8);
            protocol.writeI32(struct.suggested_price_bucket.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_smart_pricing_overridden_night", 7, 2);
            protocol.writeBool(struct.is_smart_pricing_overridden_night.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2742SinglePriceChangeContext(Builder builder) {
        this.date = builder.date;
        this.ds_night_availability = builder.ds_night_availability;
        this.price_before_change = builder.price_before_change;
        this.suggested_price = builder.suggested_price;
        this.daily_price = builder.daily_price;
        this.suggested_price_bucket = builder.suggested_price_bucket;
        this.is_smart_pricing_overridden_night = builder.is_smart_pricing_overridden_night;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2742SinglePriceChangeContext)) {
            return false;
        }
        C2742SinglePriceChangeContext that = (C2742SinglePriceChangeContext) other;
        if ((this.date == that.date || this.date.equals(that.date)) && ((this.ds_night_availability == that.ds_night_availability || this.ds_night_availability.equals(that.ds_night_availability)) && ((this.price_before_change == that.price_before_change || this.price_before_change.equals(that.price_before_change)) && ((this.suggested_price == that.suggested_price || this.suggested_price.equals(that.suggested_price)) && ((this.daily_price == that.daily_price || this.daily_price.equals(that.daily_price)) && ((this.suggested_price_bucket == that.suggested_price_bucket || this.suggested_price_bucket.equals(that.suggested_price_bucket)) && (this.is_smart_pricing_overridden_night == that.is_smart_pricing_overridden_night || this.is_smart_pricing_overridden_night.equals(that.is_smart_pricing_overridden_night)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ this.date.hashCode()) * -2128831035) ^ this.ds_night_availability.hashCode()) * -2128831035) ^ this.price_before_change.hashCode()) * -2128831035) ^ this.suggested_price.hashCode()) * -2128831035) ^ this.daily_price.hashCode()) * -2128831035) ^ this.suggested_price_bucket.hashCode()) * -2128831035) ^ this.is_smart_pricing_overridden_night.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SinglePriceChangeContext{date=" + this.date + ", ds_night_availability=" + this.ds_night_availability + ", price_before_change=" + this.price_before_change + ", suggested_price=" + this.suggested_price + ", daily_price=" + this.daily_price + ", suggested_price_bucket=" + this.suggested_price_bucket + ", is_smart_pricing_overridden_night=" + this.is_smart_pricing_overridden_night + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
