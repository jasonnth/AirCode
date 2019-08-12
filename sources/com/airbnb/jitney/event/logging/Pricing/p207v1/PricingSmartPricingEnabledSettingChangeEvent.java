package com.airbnb.jitney.event.logging.Pricing.p207v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
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

/* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingSmartPricingEnabledSettingChangeEvent */
public final class PricingSmartPricingEnabledSettingChangeEvent implements Struct {
    public static final Adapter<PricingSmartPricingEnabledSettingChangeEvent, Builder> ADAPTER = new PricingSmartPricingEnabledSettingChangeEventAdapter();
    public final Context context;
    public final String currency;
    public final String event_name;
    public final Boolean is_smart_pricing_enabled_listing;
    public final Long listing_id;
    public final Long max_price;
    public final Long min_price;
    public final String month_in_view;
    public final C2585PricingSettingsPageType page;
    public final String schema;
    public final C2586PricingSettingsSectionType section;
    public final Long smart_pricing_hosting_frequency;

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingSmartPricingEnabledSettingChangeEvent$Builder */
    public static final class Builder implements StructBuilder<PricingSmartPricingEnabledSettingChangeEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String event_name = "pricing_smart_pricing_enabled_setting_change";
        /* access modifiers changed from: private */
        public Boolean is_smart_pricing_enabled_listing;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public Long max_price;
        /* access modifiers changed from: private */
        public Long min_price;
        /* access modifiers changed from: private */
        public String month_in_view;
        /* access modifiers changed from: private */
        public C2585PricingSettingsPageType page;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Pricing:PricingSmartPricingEnabledSettingChangeEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2586PricingSettingsSectionType section;
        /* access modifiers changed from: private */
        public Long smart_pricing_hosting_frequency;

        private Builder() {
        }

        public Builder(Context context2, C2585PricingSettingsPageType page2, C2586PricingSettingsSectionType section2, Long listing_id2, String currency2, Long min_price2, Long max_price2, Boolean is_smart_pricing_enabled_listing2, Long smart_pricing_hosting_frequency2) {
            this.context = context2;
            this.page = page2;
            this.section = section2;
            this.listing_id = listing_id2;
            this.currency = currency2;
            this.min_price = min_price2;
            this.max_price = max_price2;
            this.is_smart_pricing_enabled_listing = is_smart_pricing_enabled_listing2;
            this.smart_pricing_hosting_frequency = smart_pricing_hosting_frequency2;
        }

        public PricingSmartPricingEnabledSettingChangeEvent build() {
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
            } else if (this.min_price == null) {
                throw new IllegalStateException("Required field 'min_price' is missing");
            } else if (this.max_price == null) {
                throw new IllegalStateException("Required field 'max_price' is missing");
            } else if (this.is_smart_pricing_enabled_listing == null) {
                throw new IllegalStateException("Required field 'is_smart_pricing_enabled_listing' is missing");
            } else if (this.smart_pricing_hosting_frequency != null) {
                return new PricingSmartPricingEnabledSettingChangeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'smart_pricing_hosting_frequency' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingSmartPricingEnabledSettingChangeEvent$PricingSmartPricingEnabledSettingChangeEventAdapter */
    private static final class PricingSmartPricingEnabledSettingChangeEventAdapter implements Adapter<PricingSmartPricingEnabledSettingChangeEvent, Builder> {
        private PricingSmartPricingEnabledSettingChangeEventAdapter() {
        }

        public void write(Protocol protocol, PricingSmartPricingEnabledSettingChangeEvent struct) throws IOException {
            protocol.writeStructBegin("PricingSmartPricingEnabledSettingChangeEvent");
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
            protocol.writeFieldBegin(ManageListingAnalytics.DBP_MIN_PRICE, 7, 10);
            protocol.writeI64(struct.min_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingAnalytics.DBP_MAX_PRICE, 8, 10);
            protocol.writeI64(struct.max_price.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_smart_pricing_enabled_listing", 9, 2);
            protocol.writeBool(struct.is_smart_pricing_enabled_listing.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("smart_pricing_hosting_frequency", 10, 10);
            protocol.writeI64(struct.smart_pricing_hosting_frequency.longValue());
            protocol.writeFieldEnd();
            if (struct.month_in_view != null) {
                protocol.writeFieldBegin("month_in_view", 11, PassportService.SF_DG11);
                protocol.writeString(struct.month_in_view);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PricingSmartPricingEnabledSettingChangeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.listing_id = builder.listing_id;
        this.currency = builder.currency;
        this.min_price = builder.min_price;
        this.max_price = builder.max_price;
        this.is_smart_pricing_enabled_listing = builder.is_smart_pricing_enabled_listing;
        this.smart_pricing_hosting_frequency = builder.smart_pricing_hosting_frequency;
        this.month_in_view = builder.month_in_view;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PricingSmartPricingEnabledSettingChangeEvent)) {
            return false;
        }
        PricingSmartPricingEnabledSettingChangeEvent that = (PricingSmartPricingEnabledSettingChangeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.currency == that.currency || this.currency.equals(that.currency)) && ((this.min_price == that.min_price || this.min_price.equals(that.min_price)) && ((this.max_price == that.max_price || this.max_price.equals(that.max_price)) && ((this.is_smart_pricing_enabled_listing == that.is_smart_pricing_enabled_listing || this.is_smart_pricing_enabled_listing.equals(that.is_smart_pricing_enabled_listing)) && (this.smart_pricing_hosting_frequency == that.smart_pricing_hosting_frequency || this.smart_pricing_hosting_frequency.equals(that.smart_pricing_hosting_frequency)))))))))))) {
            if (this.month_in_view == that.month_in_view) {
                return true;
            }
            if (this.month_in_view != null && this.month_in_view.equals(that.month_in_view)) {
                return true;
            }
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
        int code = (((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.currency.hashCode()) * -2128831035) ^ this.min_price.hashCode()) * -2128831035) ^ this.max_price.hashCode()) * -2128831035) ^ this.is_smart_pricing_enabled_listing.hashCode()) * -2128831035) ^ this.smart_pricing_hosting_frequency.hashCode()) * -2128831035;
        if (this.month_in_view != null) {
            i = this.month_in_view.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PricingSmartPricingEnabledSettingChangeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", listing_id=" + this.listing_id + ", currency=" + this.currency + ", min_price=" + this.min_price + ", max_price=" + this.max_price + ", is_smart_pricing_enabled_listing=" + this.is_smart_pricing_enabled_listing + ", smart_pricing_hosting_frequency=" + this.smart_pricing_hosting_frequency + ", month_in_view=" + this.month_in_view + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
