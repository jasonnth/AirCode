package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingManageListingClickTarget.p065v2.C1955CohostingManageListingClickTarget;
import com.airbnb.jitney.event.logging.CohostingManageListingPage.p067v2.C1957CohostingManageListingPage;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingShareEarningManageListingEvent */
public final class CohostingShareEarningManageListingEvent implements Struct {
    public static final Adapter<CohostingShareEarningManageListingEvent, Builder> ADAPTER = new CohostingShareEarningManageListingEventAdapter();
    public final String amount_currency;
    public final C1955CohostingManageListingClickTarget cohost_click_target;
    public final C1957CohostingManageListingPage cohost_page;
    public final C1951CohostingContext cohosting_context;
    public final Context context;
    public final String event_name;
    public final Long fixed_fee;
    public final Long maximum_fee;
    public final Long minimum_fee;
    public final C2451Operation operation;
    public final Boolean pay_cleaning_fees;
    public final Long percent_of_shared_earnings;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingShareEarningManageListingEvent$Builder */
    public static final class Builder implements StructBuilder<CohostingShareEarningManageListingEvent> {
        /* access modifiers changed from: private */
        public String amount_currency;
        /* access modifiers changed from: private */
        public C1955CohostingManageListingClickTarget cohost_click_target;
        /* access modifiers changed from: private */
        public C1957CohostingManageListingPage cohost_page;
        /* access modifiers changed from: private */
        public C1951CohostingContext cohosting_context;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long fixed_fee;
        /* access modifiers changed from: private */
        public Long maximum_fee;
        /* access modifiers changed from: private */
        public Long minimum_fee;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public Boolean pay_cleaning_fees;
        /* access modifiers changed from: private */
        public Long percent_of_shared_earnings;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingShareEarningManageListingEvent:1.0.0";
            this.event_name = "cohosting_share_earning_manage_listing";
            this.cohost_page = C1957CohostingManageListingPage.ShareEarningsModal;
            this.cohost_click_target = C1955CohostingManageListingClickTarget.ShareEarningsButton;
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, Long percent_of_shared_earnings2, Boolean pay_cleaning_fees2, Long fixed_fee2, Long maximum_fee2, Long minimum_fee2, String amount_currency2, C1951CohostingContext cohosting_context2) {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingShareEarningManageListingEvent:1.0.0";
            this.event_name = "cohosting_share_earning_manage_listing";
            this.context = context2;
            this.cohost_page = C1957CohostingManageListingPage.ShareEarningsModal;
            this.cohost_click_target = C1955CohostingManageListingClickTarget.ShareEarningsButton;
            this.operation = C2451Operation.Click;
            this.percent_of_shared_earnings = percent_of_shared_earnings2;
            this.pay_cleaning_fees = pay_cleaning_fees2;
            this.fixed_fee = fixed_fee2;
            this.maximum_fee = maximum_fee2;
            this.minimum_fee = minimum_fee2;
            this.amount_currency = amount_currency2;
            this.cohosting_context = cohosting_context2;
        }

        public CohostingShareEarningManageListingEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.cohost_page == null) {
                throw new IllegalStateException("Required field 'cohost_page' is missing");
            } else if (this.cohost_click_target == null) {
                throw new IllegalStateException("Required field 'cohost_click_target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.percent_of_shared_earnings == null) {
                throw new IllegalStateException("Required field 'percent_of_shared_earnings' is missing");
            } else if (this.pay_cleaning_fees == null) {
                throw new IllegalStateException("Required field 'pay_cleaning_fees' is missing");
            } else if (this.fixed_fee == null) {
                throw new IllegalStateException("Required field 'fixed_fee' is missing");
            } else if (this.maximum_fee == null) {
                throw new IllegalStateException("Required field 'maximum_fee' is missing");
            } else if (this.minimum_fee == null) {
                throw new IllegalStateException("Required field 'minimum_fee' is missing");
            } else if (this.amount_currency == null) {
                throw new IllegalStateException("Required field 'amount_currency' is missing");
            } else if (this.cohosting_context != null) {
                return new CohostingShareEarningManageListingEvent(this);
            } else {
                throw new IllegalStateException("Required field 'cohosting_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingShareEarningManageListingEvent$CohostingShareEarningManageListingEventAdapter */
    private static final class CohostingShareEarningManageListingEventAdapter implements Adapter<CohostingShareEarningManageListingEvent, Builder> {
        private CohostingShareEarningManageListingEventAdapter() {
        }

        public void write(Protocol protocol, CohostingShareEarningManageListingEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingShareEarningManageListingEvent");
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
            protocol.writeFieldBegin("cohost_page", 3, 8);
            protocol.writeI32(struct.cohost_page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohost_click_target", 4, 8);
            protocol.writeI32(struct.cohost_click_target.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("percent_of_shared_earnings", 6, 10);
            protocol.writeI64(struct.percent_of_shared_earnings.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("pay_cleaning_fees", 7, 2);
            protocol.writeBool(struct.pay_cleaning_fees.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("fixed_fee", 8, 10);
            protocol.writeI64(struct.fixed_fee.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("maximum_fee", 9, 10);
            protocol.writeI64(struct.maximum_fee.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("minimum_fee", 10, 10);
            protocol.writeI64(struct.minimum_fee.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("amount_currency", 11, PassportService.SF_DG11);
            protocol.writeString(struct.amount_currency);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohosting_context", 12, PassportService.SF_DG12);
            C1951CohostingContext.ADAPTER.write(protocol, struct.cohosting_context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CohostingShareEarningManageListingEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.cohost_page = builder.cohost_page;
        this.cohost_click_target = builder.cohost_click_target;
        this.operation = builder.operation;
        this.percent_of_shared_earnings = builder.percent_of_shared_earnings;
        this.pay_cleaning_fees = builder.pay_cleaning_fees;
        this.fixed_fee = builder.fixed_fee;
        this.maximum_fee = builder.maximum_fee;
        this.minimum_fee = builder.minimum_fee;
        this.amount_currency = builder.amount_currency;
        this.cohosting_context = builder.cohosting_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CohostingShareEarningManageListingEvent)) {
            return false;
        }
        CohostingShareEarningManageListingEvent that = (CohostingShareEarningManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_page == that.cohost_page || this.cohost_page.equals(that.cohost_page)) && ((this.cohost_click_target == that.cohost_click_target || this.cohost_click_target.equals(that.cohost_click_target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.percent_of_shared_earnings == that.percent_of_shared_earnings || this.percent_of_shared_earnings.equals(that.percent_of_shared_earnings)) && ((this.pay_cleaning_fees == that.pay_cleaning_fees || this.pay_cleaning_fees.equals(that.pay_cleaning_fees)) && ((this.fixed_fee == that.fixed_fee || this.fixed_fee.equals(that.fixed_fee)) && ((this.maximum_fee == that.maximum_fee || this.maximum_fee.equals(that.maximum_fee)) && ((this.minimum_fee == that.minimum_fee || this.minimum_fee.equals(that.minimum_fee)) && ((this.amount_currency == that.amount_currency || this.amount_currency.equals(that.amount_currency)) && (this.cohosting_context == that.cohosting_context || this.cohosting_context.equals(that.cohosting_context)))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_page.hashCode()) * -2128831035) ^ this.cohost_click_target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.percent_of_shared_earnings.hashCode()) * -2128831035) ^ this.pay_cleaning_fees.hashCode()) * -2128831035) ^ this.fixed_fee.hashCode()) * -2128831035) ^ this.maximum_fee.hashCode()) * -2128831035) ^ this.minimum_fee.hashCode()) * -2128831035) ^ this.amount_currency.hashCode()) * -2128831035) ^ this.cohosting_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingShareEarningManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_page=" + this.cohost_page + ", cohost_click_target=" + this.cohost_click_target + ", operation=" + this.operation + ", percent_of_shared_earnings=" + this.percent_of_shared_earnings + ", pay_cleaning_fees=" + this.pay_cleaning_fees + ", fixed_fee=" + this.fixed_fee + ", maximum_fee=" + this.maximum_fee + ", minimum_fee=" + this.minimum_fee + ", amount_currency=" + this.amount_currency + ", cohosting_context=" + this.cohosting_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
