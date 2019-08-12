package com.airbnb.jitney.event.logging.Pricing.p207v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.constants.ManageListingArgConstants;
import com.airbnb.android.utils.AirbnbConstants;
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

/* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingBasePriceChangeEvent */
public final class PricingBasePriceChangeEvent implements Struct {
    public static final Adapter<PricingBasePriceChangeEvent, Builder> ADAPTER = new PricingBasePriceChangeEventAdapter();
    public final Long base_price;
    public final Context context;
    public final String currency;
    public final String event_name;
    public final Long listing_id;
    public final Long old_base_price;
    public final C2585PricingSettingsPageType page;
    public final String schema;
    public final C2586PricingSettingsSectionType section;
    public final Long suggested_price;

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingBasePriceChangeEvent$Builder */
    public static final class Builder implements StructBuilder<PricingBasePriceChangeEvent> {
        /* access modifiers changed from: private */
        public Long base_price;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String event_name = "pricing_base_price_change";
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public Long old_base_price;
        /* access modifiers changed from: private */
        public C2585PricingSettingsPageType page;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Pricing:PricingBasePriceChangeEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2586PricingSettingsSectionType section;
        /* access modifiers changed from: private */
        public Long suggested_price;

        private Builder() {
        }

        public Builder(Context context2, C2585PricingSettingsPageType page2, C2586PricingSettingsSectionType section2, Long listing_id2, String currency2, Long old_base_price2, Long suggested_price2, Long base_price2) {
            this.context = context2;
            this.page = page2;
            this.section = section2;
            this.listing_id = listing_id2;
            this.currency = currency2;
            this.old_base_price = old_base_price2;
            this.suggested_price = suggested_price2;
            this.base_price = base_price2;
        }

        public PricingBasePriceChangeEvent build() {
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
            } else if (this.old_base_price == null) {
                throw new IllegalStateException("Required field 'old_base_price' is missing");
            } else if (this.suggested_price == null) {
                throw new IllegalStateException("Required field 'suggested_price' is missing");
            } else if (this.base_price != null) {
                return new PricingBasePriceChangeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'base_price' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingBasePriceChangeEvent$PricingBasePriceChangeEventAdapter */
    private static final class PricingBasePriceChangeEventAdapter implements Adapter<PricingBasePriceChangeEvent, Builder> {
        private PricingBasePriceChangeEventAdapter() {
        }

        public void write(Protocol protocol, PricingBasePriceChangeEvent struct) throws IOException {
            protocol.writeStructBegin("PricingBasePriceChangeEvent");
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
            protocol.writeFieldBegin("old_base_price", 7, 10);
            protocol.writeI64(struct.old_base_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingArgConstants.ARG_SUGGESTED_PRICE, 8, 10);
            protocol.writeI64(struct.suggested_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("base_price", 9, 10);
            protocol.writeI64(struct.base_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PricingBasePriceChangeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.listing_id = builder.listing_id;
        this.currency = builder.currency;
        this.old_base_price = builder.old_base_price;
        this.suggested_price = builder.suggested_price;
        this.base_price = builder.base_price;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PricingBasePriceChangeEvent)) {
            return false;
        }
        PricingBasePriceChangeEvent that = (PricingBasePriceChangeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.currency == that.currency || this.currency.equals(that.currency)) && ((this.old_base_price == that.old_base_price || this.old_base_price.equals(that.old_base_price)) && ((this.suggested_price == that.suggested_price || this.suggested_price.equals(that.suggested_price)) && (this.base_price == that.base_price || this.base_price.equals(that.base_price))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.currency.hashCode()) * -2128831035) ^ this.old_base_price.hashCode()) * -2128831035) ^ this.suggested_price.hashCode()) * -2128831035) ^ this.base_price.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PricingBasePriceChangeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", listing_id=" + this.listing_id + ", currency=" + this.currency + ", old_base_price=" + this.old_base_price + ", suggested_price=" + this.suggested_price + ", base_price=" + this.base_price + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
