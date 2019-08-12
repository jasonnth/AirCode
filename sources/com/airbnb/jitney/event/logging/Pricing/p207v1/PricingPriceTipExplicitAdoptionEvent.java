package com.airbnb.jitney.event.logging.Pricing.p207v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.PriceSuggestionContext.p205v1.C2569PriceSuggestionContext;
import com.airbnb.jitney.event.logging.PriceTipDaysType.p206v1.C2571PriceTipDaysType;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
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

/* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingPriceTipExplicitAdoptionEvent */
public final class PricingPriceTipExplicitAdoptionEvent implements Struct {
    public static final Adapter<PricingPriceTipExplicitAdoptionEvent, Builder> ADAPTER = new PricingPriceTipExplicitAdoptionEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2585PricingSettingsPageType page;
    public final List<C2569PriceSuggestionContext> price_suggestions_with_context;
    public final C2571PriceTipDaysType price_tips_days_type;
    public final String schema;
    public final C2586PricingSettingsSectionType section;

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingPriceTipExplicitAdoptionEvent$Builder */
    public static final class Builder implements StructBuilder<PricingPriceTipExplicitAdoptionEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "pricing_price_tip_explicit_adoption";
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2585PricingSettingsPageType page;
        /* access modifiers changed from: private */
        public List<C2569PriceSuggestionContext> price_suggestions_with_context;
        /* access modifiers changed from: private */
        public C2571PriceTipDaysType price_tips_days_type;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Pricing:PricingPriceTipExplicitAdoptionEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2586PricingSettingsSectionType section;

        private Builder() {
        }

        public Builder(Context context2, C2585PricingSettingsPageType page2, C2586PricingSettingsSectionType section2, Long listing_id2, List<C2569PriceSuggestionContext> price_suggestions_with_context2, C2571PriceTipDaysType price_tips_days_type2) {
            this.context = context2;
            this.page = page2;
            this.section = section2;
            this.listing_id = listing_id2;
            this.price_suggestions_with_context = price_suggestions_with_context2;
            this.price_tips_days_type = price_tips_days_type2;
        }

        public PricingPriceTipExplicitAdoptionEvent build() {
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
            } else if (this.price_suggestions_with_context == null) {
                throw new IllegalStateException("Required field 'price_suggestions_with_context' is missing");
            } else if (this.price_tips_days_type != null) {
                return new PricingPriceTipExplicitAdoptionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'price_tips_days_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Pricing.v1.PricingPriceTipExplicitAdoptionEvent$PricingPriceTipExplicitAdoptionEventAdapter */
    private static final class PricingPriceTipExplicitAdoptionEventAdapter implements Adapter<PricingPriceTipExplicitAdoptionEvent, Builder> {
        private PricingPriceTipExplicitAdoptionEventAdapter() {
        }

        public void write(Protocol protocol, PricingPriceTipExplicitAdoptionEvent struct) throws IOException {
            protocol.writeStructBegin("PricingPriceTipExplicitAdoptionEvent");
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
            protocol.writeFieldBegin("price_suggestions_with_context", 6, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.price_suggestions_with_context.size());
            for (C2569PriceSuggestionContext item0 : struct.price_suggestions_with_context) {
                C2569PriceSuggestionContext.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("price_tips_days_type", 7, 8);
            protocol.writeI32(struct.price_tips_days_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private PricingPriceTipExplicitAdoptionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.listing_id = builder.listing_id;
        this.price_suggestions_with_context = Collections.unmodifiableList(builder.price_suggestions_with_context);
        this.price_tips_days_type = builder.price_tips_days_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PricingPriceTipExplicitAdoptionEvent)) {
            return false;
        }
        PricingPriceTipExplicitAdoptionEvent that = (PricingPriceTipExplicitAdoptionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.price_suggestions_with_context == that.price_suggestions_with_context || this.price_suggestions_with_context.equals(that.price_suggestions_with_context)) && (this.price_tips_days_type == that.price_tips_days_type || this.price_tips_days_type.equals(that.price_tips_days_type))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.price_suggestions_with_context.hashCode()) * -2128831035) ^ this.price_tips_days_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PricingPriceTipExplicitAdoptionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", listing_id=" + this.listing_id + ", price_suggestions_with_context=" + this.price_suggestions_with_context + ", price_tips_days_type=" + this.price_tips_days_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
