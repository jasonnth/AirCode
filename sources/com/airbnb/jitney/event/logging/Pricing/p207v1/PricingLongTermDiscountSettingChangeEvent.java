package com.airbnb.jitney.event.logging.Pricing.p207v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.LongTermPriceDiscountTypes.p139v1.C2376LongTermPriceDiscountTypes;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingLongTermDiscountSettingChangeEvent */
public final class PricingLongTermDiscountSettingChangeEvent implements Struct {
    public static final Adapter<PricingLongTermDiscountSettingChangeEvent, Builder> ADAPTER = new PricingLongTermDiscountSettingChangeEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2376LongTermPriceDiscountTypes long_term_price_discount_length_type;
    public final Double long_term_pricing_discount_factor;
    public final Double old_long_term_pricing_discount_factor;
    public final C2585PricingSettingsPageType page;
    public final String schema;
    public final C2586PricingSettingsSectionType section;
    public final Double suggested_long_term_pricing_discount_factor;

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingLongTermDiscountSettingChangeEvent$Builder */
    public static final class Builder implements StructBuilder<PricingLongTermDiscountSettingChangeEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "pricing_long_term_discount_setting_change";
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2376LongTermPriceDiscountTypes long_term_price_discount_length_type;
        /* access modifiers changed from: private */
        public Double long_term_pricing_discount_factor;
        /* access modifiers changed from: private */
        public Double old_long_term_pricing_discount_factor;
        /* access modifiers changed from: private */
        public C2585PricingSettingsPageType page;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Pricing:PricingLongTermDiscountSettingChangeEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2586PricingSettingsSectionType section;
        /* access modifiers changed from: private */
        public Double suggested_long_term_pricing_discount_factor;

        private Builder() {
        }

        public Builder(Context context2, C2585PricingSettingsPageType page2, C2586PricingSettingsSectionType section2, Long listing_id2, Double long_term_pricing_discount_factor2, Double suggested_long_term_pricing_discount_factor2, Double old_long_term_pricing_discount_factor2, C2376LongTermPriceDiscountTypes long_term_price_discount_length_type2) {
            this.context = context2;
            this.page = page2;
            this.section = section2;
            this.listing_id = listing_id2;
            this.long_term_pricing_discount_factor = long_term_pricing_discount_factor2;
            this.suggested_long_term_pricing_discount_factor = suggested_long_term_pricing_discount_factor2;
            this.old_long_term_pricing_discount_factor = old_long_term_pricing_discount_factor2;
            this.long_term_price_discount_length_type = long_term_price_discount_length_type2;
        }

        public PricingLongTermDiscountSettingChangeEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.section == null) {
                throw new IllegalStateException("Required field 'section' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.long_term_pricing_discount_factor == null) {
                throw new IllegalStateException("Required field 'long_term_pricing_discount_factor' is missing");
            } else if (this.suggested_long_term_pricing_discount_factor == null) {
                throw new IllegalStateException("Required field 'suggested_long_term_pricing_discount_factor' is missing");
            } else if (this.old_long_term_pricing_discount_factor == null) {
                throw new IllegalStateException("Required field 'old_long_term_pricing_discount_factor' is missing");
            } else if (this.long_term_price_discount_length_type != null) {
                return new PricingLongTermDiscountSettingChangeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'long_term_price_discount_length_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingLongTermDiscountSettingChangeEvent$PricingLongTermDiscountSettingChangeEventAdapter */
    private static final class PricingLongTermDiscountSettingChangeEventAdapter implements Adapter<PricingLongTermDiscountSettingChangeEvent, Builder> {
        private PricingLongTermDiscountSettingChangeEventAdapter() {
        }

        public void write(Protocol protocol, PricingLongTermDiscountSettingChangeEvent struct) throws IOException {
            protocol.writeStructBegin("PricingLongTermDiscountSettingChangeEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("event_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 2, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 3, 8);
            protocol.writeI32(struct.page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.SECTION, 4, 8);
            protocol.writeI32(struct.section.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 5, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("long_term_pricing_discount_factor", 6, 4);
            protocol.writeDouble(struct.long_term_pricing_discount_factor.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("suggested_long_term_pricing_discount_factor", 7, 4);
            protocol.writeDouble(struct.suggested_long_term_pricing_discount_factor.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("old_long_term_pricing_discount_factor", 8, 4);
            protocol.writeDouble(struct.old_long_term_pricing_discount_factor.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("long_term_price_discount_length_type", 9, 8);
            protocol.writeI32(struct.long_term_price_discount_length_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PricingLongTermDiscountSettingChangeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.listing_id = builder.listing_id;
        this.long_term_pricing_discount_factor = builder.long_term_pricing_discount_factor;
        this.suggested_long_term_pricing_discount_factor = builder.suggested_long_term_pricing_discount_factor;
        this.old_long_term_pricing_discount_factor = builder.old_long_term_pricing_discount_factor;
        this.long_term_price_discount_length_type = builder.long_term_price_discount_length_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PricingLongTermDiscountSettingChangeEvent)) {
            return false;
        }
        PricingLongTermDiscountSettingChangeEvent that = (PricingLongTermDiscountSettingChangeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.long_term_pricing_discount_factor == that.long_term_pricing_discount_factor || this.long_term_pricing_discount_factor.equals(that.long_term_pricing_discount_factor)) && ((this.suggested_long_term_pricing_discount_factor == that.suggested_long_term_pricing_discount_factor || this.suggested_long_term_pricing_discount_factor.equals(that.suggested_long_term_pricing_discount_factor)) && ((this.old_long_term_pricing_discount_factor == that.old_long_term_pricing_discount_factor || this.old_long_term_pricing_discount_factor.equals(that.old_long_term_pricing_discount_factor)) && (this.long_term_price_discount_length_type == that.long_term_price_discount_length_type || this.long_term_price_discount_length_type.equals(that.long_term_price_discount_length_type))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.long_term_pricing_discount_factor.hashCode()) * -2128831035) ^ this.suggested_long_term_pricing_discount_factor.hashCode()) * -2128831035) ^ this.old_long_term_pricing_discount_factor.hashCode()) * -2128831035) ^ this.long_term_price_discount_length_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PricingLongTermDiscountSettingChangeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", listing_id=" + this.listing_id + ", long_term_pricing_discount_factor=" + this.long_term_pricing_discount_factor + ", suggested_long_term_pricing_discount_factor=" + this.suggested_long_term_pricing_discount_factor + ", old_long_term_pricing_discount_factor=" + this.old_long_term_pricing_discount_factor + ", long_term_price_discount_length_type=" + this.long_term_price_discount_length_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
