package com.airbnb.jitney.event.logging.SmartPricingSettingsContext.p259v1;

import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.SmartPricingSettingsContext.v1.SmartPricingSettingsContext */
public final class C2744SmartPricingSettingsContext implements Struct {
    public static final Adapter<C2744SmartPricingSettingsContext, Builder> ADAPTER = new SmartPricingSettingsContextAdapter();
    public final Boolean is_smart_pricing_enabled_listing;
    public final Long max_price;
    public final Long min_price;
    public final Long smart_pricing_hosting_frequency;

    /* renamed from: com.airbnb.jitney.event.logging.SmartPricingSettingsContext.v1.SmartPricingSettingsContext$Builder */
    public static final class Builder implements StructBuilder<C2744SmartPricingSettingsContext> {
        /* access modifiers changed from: private */
        public Boolean is_smart_pricing_enabled_listing;
        /* access modifiers changed from: private */
        public Long max_price;
        /* access modifiers changed from: private */
        public Long min_price;
        /* access modifiers changed from: private */
        public Long smart_pricing_hosting_frequency;

        private Builder() {
        }

        public Builder(Long min_price2, Long max_price2, Long smart_pricing_hosting_frequency2, Boolean is_smart_pricing_enabled_listing2) {
            this.min_price = min_price2;
            this.max_price = max_price2;
            this.smart_pricing_hosting_frequency = smart_pricing_hosting_frequency2;
            this.is_smart_pricing_enabled_listing = is_smart_pricing_enabled_listing2;
        }

        public C2744SmartPricingSettingsContext build() {
            if (this.min_price == null) {
                throw new IllegalStateException("Required field 'min_price' is missing");
            } else if (this.max_price == null) {
                throw new IllegalStateException("Required field 'max_price' is missing");
            } else if (this.smart_pricing_hosting_frequency == null) {
                throw new IllegalStateException("Required field 'smart_pricing_hosting_frequency' is missing");
            } else if (this.is_smart_pricing_enabled_listing != null) {
                return new C2744SmartPricingSettingsContext(this);
            } else {
                throw new IllegalStateException("Required field 'is_smart_pricing_enabled_listing' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.SmartPricingSettingsContext.v1.SmartPricingSettingsContext$SmartPricingSettingsContextAdapter */
    private static final class SmartPricingSettingsContextAdapter implements Adapter<C2744SmartPricingSettingsContext, Builder> {
        private SmartPricingSettingsContextAdapter() {
        }

        public void write(Protocol protocol, C2744SmartPricingSettingsContext struct) throws IOException {
            protocol.writeStructBegin("SmartPricingSettingsContext");
            protocol.writeFieldBegin(ManageListingAnalytics.DBP_MIN_PRICE, 1, 10);
            protocol.writeI64(struct.min_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingAnalytics.DBP_MAX_PRICE, 2, 10);
            protocol.writeI64(struct.max_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("smart_pricing_hosting_frequency", 3, 10);
            protocol.writeI64(struct.smart_pricing_hosting_frequency.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_smart_pricing_enabled_listing", 4, 2);
            protocol.writeBool(struct.is_smart_pricing_enabled_listing.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2744SmartPricingSettingsContext(Builder builder) {
        this.min_price = builder.min_price;
        this.max_price = builder.max_price;
        this.smart_pricing_hosting_frequency = builder.smart_pricing_hosting_frequency;
        this.is_smart_pricing_enabled_listing = builder.is_smart_pricing_enabled_listing;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2744SmartPricingSettingsContext)) {
            return false;
        }
        C2744SmartPricingSettingsContext that = (C2744SmartPricingSettingsContext) other;
        if ((this.min_price == that.min_price || this.min_price.equals(that.min_price)) && ((this.max_price == that.max_price || this.max_price.equals(that.max_price)) && ((this.smart_pricing_hosting_frequency == that.smart_pricing_hosting_frequency || this.smart_pricing_hosting_frequency.equals(that.smart_pricing_hosting_frequency)) && (this.is_smart_pricing_enabled_listing == that.is_smart_pricing_enabled_listing || this.is_smart_pricing_enabled_listing.equals(that.is_smart_pricing_enabled_listing))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((16777619 ^ this.min_price.hashCode()) * -2128831035) ^ this.max_price.hashCode()) * -2128831035) ^ this.smart_pricing_hosting_frequency.hashCode()) * -2128831035) ^ this.is_smart_pricing_enabled_listing.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SmartPricingSettingsContext{min_price=" + this.min_price + ", max_price=" + this.max_price + ", smart_pricing_hosting_frequency=" + this.smart_pricing_hosting_frequency + ", is_smart_pricing_enabled_listing=" + this.is_smart_pricing_enabled_listing + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
