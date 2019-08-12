package com.airbnb.jitney.event.logging.Pricing.p207v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.jitney.event.logging.SmartPricingSettingsContext.p259v1.C2744SmartPricingSettingsContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingSmartPricingMinPriceTipExplicitAdoptionEvent */
public final class PricingSmartPricingMinPriceTipExplicitAdoptionEvent implements Struct {
    public static final Adapter<PricingSmartPricingMinPriceTipExplicitAdoptionEvent, Builder> ADAPTER = new PricingSmartPricingMinPriceTipExplicitAdoptionEventAdapter();
    public final Context context;
    public final String currency;
    public final String event_name;
    public final Long listing_id;
    public final Long min_price;
    public final Long old_min_price;
    public final C2585PricingSettingsPageType page;
    public final String schema;
    public final C2586PricingSettingsSectionType section;
    public final C2744SmartPricingSettingsContext smart_pricing_settings;
    public final Long suggested_min_price;

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingSmartPricingMinPriceTipExplicitAdoptionEvent$Builder */
    public static final class Builder implements StructBuilder<PricingSmartPricingMinPriceTipExplicitAdoptionEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String event_name = "pricing_smart_pricing_min_price_tip_explicit_adoption";
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public Long min_price;
        /* access modifiers changed from: private */
        public Long old_min_price;
        /* access modifiers changed from: private */
        public C2585PricingSettingsPageType page;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Pricing:PricingSmartPricingMinPriceTipExplicitAdoptionEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2586PricingSettingsSectionType section;
        /* access modifiers changed from: private */
        public C2744SmartPricingSettingsContext smart_pricing_settings;
        /* access modifiers changed from: private */
        public Long suggested_min_price;

        private Builder() {
        }

        public Builder(Context context2, C2585PricingSettingsPageType page2, C2586PricingSettingsSectionType section2, Long listing_id2, String currency2, Long suggested_min_price2, Long min_price2, C2744SmartPricingSettingsContext smart_pricing_settings2) {
            this.context = context2;
            this.page = page2;
            this.section = section2;
            this.listing_id = listing_id2;
            this.currency = currency2;
            this.suggested_min_price = suggested_min_price2;
            this.min_price = min_price2;
            this.smart_pricing_settings = smart_pricing_settings2;
        }

        public PricingSmartPricingMinPriceTipExplicitAdoptionEvent build() {
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
            } else if (this.currency == null) {
                throw new IllegalStateException("Required field 'currency' is missing");
            } else if (this.suggested_min_price == null) {
                throw new IllegalStateException("Required field 'suggested_min_price' is missing");
            } else if (this.min_price == null) {
                throw new IllegalStateException("Required field 'min_price' is missing");
            } else if (this.smart_pricing_settings != null) {
                return new PricingSmartPricingMinPriceTipExplicitAdoptionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'smart_pricing_settings' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingSmartPricingMinPriceTipExplicitAdoptionEvent$PricingSmartPricingMinPriceTipExplicitAdoptionEventAdapter */
    private static final class PricingSmartPricingMinPriceTipExplicitAdoptionEventAdapter implements Adapter<PricingSmartPricingMinPriceTipExplicitAdoptionEvent, Builder> {
        private PricingSmartPricingMinPriceTipExplicitAdoptionEventAdapter() {
        }

        public void write(Protocol protocol, PricingSmartPricingMinPriceTipExplicitAdoptionEvent struct) throws IOException {
            protocol.writeStructBegin("PricingSmartPricingMinPriceTipExplicitAdoptionEvent");
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
            protocol.writeFieldBegin(AirbnbConstants.PREFS_CURRENCY, 6, PassportService.SF_DG11);
            protocol.writeString(struct.currency);
            protocol.writeFieldEnd();
            if (struct.old_min_price != null) {
                protocol.writeFieldBegin("old_min_price", 7, 10);
                protocol.writeI64(struct.old_min_price.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("suggested_min_price", 8, 10);
            protocol.writeI64(struct.suggested_min_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingAnalytics.DBP_MIN_PRICE, 9, 10);
            protocol.writeI64(struct.min_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("smart_pricing_settings", 10, PassportService.SF_DG12);
            C2744SmartPricingSettingsContext.ADAPTER.write(protocol, struct.smart_pricing_settings);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PricingSmartPricingMinPriceTipExplicitAdoptionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.listing_id = builder.listing_id;
        this.currency = builder.currency;
        this.old_min_price = builder.old_min_price;
        this.suggested_min_price = builder.suggested_min_price;
        this.min_price = builder.min_price;
        this.smart_pricing_settings = builder.smart_pricing_settings;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PricingSmartPricingMinPriceTipExplicitAdoptionEvent)) {
            return false;
        }
        PricingSmartPricingMinPriceTipExplicitAdoptionEvent that = (PricingSmartPricingMinPriceTipExplicitAdoptionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.currency == that.currency || this.currency.equals(that.currency)) && ((this.old_min_price == that.old_min_price || (this.old_min_price != null && this.old_min_price.equals(that.old_min_price))) && ((this.suggested_min_price == that.suggested_min_price || this.suggested_min_price.equals(that.suggested_min_price)) && ((this.min_price == that.min_price || this.min_price.equals(that.min_price)) && (this.smart_pricing_settings == that.smart_pricing_settings || this.smart_pricing_settings.equals(that.smart_pricing_settings)))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.currency.hashCode()) * -2128831035;
        if (this.old_min_price != null) {
            i = this.old_min_price.hashCode();
        }
        return (((((((code ^ i) * -2128831035) ^ this.suggested_min_price.hashCode()) * -2128831035) ^ this.min_price.hashCode()) * -2128831035) ^ this.smart_pricing_settings.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PricingSmartPricingMinPriceTipExplicitAdoptionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", listing_id=" + this.listing_id + ", currency=" + this.currency + ", old_min_price=" + this.old_min_price + ", suggested_min_price=" + this.suggested_min_price + ", min_price=" + this.min_price + ", smart_pricing_settings=" + this.smart_pricing_settings + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
