package com.airbnb.jitney.event.logging.Pricing.p207v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.jitney.event.logging.SinglePriceChangeContext.p258v1.C2742SinglePriceChangeContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingCalendarDailyPriceChangeEvent */
public final class PricingCalendarDailyPriceChangeEvent implements Struct {
    public static final Adapter<PricingCalendarDailyPriceChangeEvent, Builder> ADAPTER = new PricingCalendarDailyPriceChangeEventAdapter();
    public final Context context;
    public final String currency;
    public final String event_name;
    public final Boolean is_smart_pricing_enabled_listing;
    public final Long listing_id;
    public final C2585PricingSettingsPageType page;
    public final List<C2742SinglePriceChangeContext> price_changes_context;
    public final String schema;
    public final C2586PricingSettingsSectionType section;

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingCalendarDailyPriceChangeEvent$Builder */
    public static final class Builder implements StructBuilder<PricingCalendarDailyPriceChangeEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String event_name = "pricing_calendar_daily_price_change";
        /* access modifiers changed from: private */
        public Boolean is_smart_pricing_enabled_listing;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2585PricingSettingsPageType page;
        /* access modifiers changed from: private */
        public List<C2742SinglePriceChangeContext> price_changes_context;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Pricing:PricingCalendarDailyPriceChangeEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2586PricingSettingsSectionType section;

        private Builder() {
        }

        public Builder(Context context2, C2585PricingSettingsPageType page2, C2586PricingSettingsSectionType section2, Long listing_id2, String currency2, List<C2742SinglePriceChangeContext> price_changes_context2, Boolean is_smart_pricing_enabled_listing2) {
            this.context = context2;
            this.page = page2;
            this.section = section2;
            this.listing_id = listing_id2;
            this.currency = currency2;
            this.price_changes_context = price_changes_context2;
            this.is_smart_pricing_enabled_listing = is_smart_pricing_enabled_listing2;
        }

        public PricingCalendarDailyPriceChangeEvent build() {
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
            } else if (this.price_changes_context == null) {
                throw new IllegalStateException("Required field 'price_changes_context' is missing");
            } else if (this.is_smart_pricing_enabled_listing != null) {
                return new PricingCalendarDailyPriceChangeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'is_smart_pricing_enabled_listing' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingCalendarDailyPriceChangeEvent$PricingCalendarDailyPriceChangeEventAdapter */
    private static final class PricingCalendarDailyPriceChangeEventAdapter implements Adapter<PricingCalendarDailyPriceChangeEvent, Builder> {
        private PricingCalendarDailyPriceChangeEventAdapter() {
        }

        public void write(Protocol protocol, PricingCalendarDailyPriceChangeEvent struct) throws IOException {
            protocol.writeStructBegin("PricingCalendarDailyPriceChangeEvent");
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
            protocol.writeFieldBegin("price_changes_context", 7, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.price_changes_context.size());
            for (C2742SinglePriceChangeContext item0 : struct.price_changes_context) {
                C2742SinglePriceChangeContext.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_smart_pricing_enabled_listing", 8, 2);
            protocol.writeBool(struct.is_smart_pricing_enabled_listing.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PricingCalendarDailyPriceChangeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.listing_id = builder.listing_id;
        this.currency = builder.currency;
        this.price_changes_context = Collections.unmodifiableList(builder.price_changes_context);
        this.is_smart_pricing_enabled_listing = builder.is_smart_pricing_enabled_listing;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PricingCalendarDailyPriceChangeEvent)) {
            return false;
        }
        PricingCalendarDailyPriceChangeEvent that = (PricingCalendarDailyPriceChangeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.currency == that.currency || this.currency.equals(that.currency)) && ((this.price_changes_context == that.price_changes_context || this.price_changes_context.equals(that.price_changes_context)) && (this.is_smart_pricing_enabled_listing == that.is_smart_pricing_enabled_listing || this.is_smart_pricing_enabled_listing.equals(that.is_smart_pricing_enabled_listing)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.currency.hashCode()) * -2128831035) ^ this.price_changes_context.hashCode()) * -2128831035) ^ this.is_smart_pricing_enabled_listing.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PricingCalendarDailyPriceChangeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", listing_id=" + this.listing_id + ", currency=" + this.currency + ", price_changes_context=" + this.price_changes_context + ", is_smart_pricing_enabled_listing=" + this.is_smart_pricing_enabled_listing + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
