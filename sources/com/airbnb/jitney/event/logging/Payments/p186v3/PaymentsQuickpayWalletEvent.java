package com.airbnb.jitney.event.logging.Payments.p186v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1.C2504PaymentInstrumentType;
import com.airbnb.jitney.event.logging.QuickpayConfig.p216v1.C2597QuickpayConfig;
import com.airbnb.jitney.event.logging.QuickpayWalletSection.p218v1.C2599QuickpayWalletSection;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Payments.v3.PaymentsQuickpayWalletEvent */
public final class PaymentsQuickpayWalletEvent implements Struct {
    public static final Adapter<PaymentsQuickpayWalletEvent, Object> ADAPTER = new PaymentsQuickpayWalletEventAdapter();
    public final String billing_country;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Long payment_instrument_id;
    public final C2504PaymentInstrumentType payment_instrument_type;
    public final C2597QuickpayConfig quickpay_config;
    public final C2599QuickpayWalletSection quickpay_wallet_section;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Payments.v3.PaymentsQuickpayWalletEvent$PaymentsQuickpayWalletEventAdapter */
    private static final class PaymentsQuickpayWalletEventAdapter implements Adapter<PaymentsQuickpayWalletEvent, Object> {
        private PaymentsQuickpayWalletEventAdapter() {
        }

        public void write(Protocol protocol, PaymentsQuickpayWalletEvent struct) throws IOException {
            protocol.writeStructBegin("PaymentsQuickpayWalletEvent");
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
            protocol.writeFieldBegin("page", 3, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("quickpay_wallet_section", 4, 8);
            protocol.writeI32(struct.quickpay_wallet_section.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("quickpay_config", 6, 8);
            protocol.writeI32(struct.quickpay_config.value);
            protocol.writeFieldEnd();
            if (struct.payment_instrument_id != null) {
                protocol.writeFieldBegin("payment_instrument_id", 7, 10);
                protocol.writeI64(struct.payment_instrument_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.payment_instrument_type != null) {
                protocol.writeFieldBegin("payment_instrument_type", 8, 8);
                protocol.writeI32(struct.payment_instrument_type.value);
                protocol.writeFieldEnd();
            }
            if (struct.billing_country != null) {
                protocol.writeFieldBegin("billing_country", 9, PassportService.SF_DG11);
                protocol.writeString(struct.billing_country);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof PaymentsQuickpayWalletEvent)) {
            return false;
        }
        PaymentsQuickpayWalletEvent that = (PaymentsQuickpayWalletEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.quickpay_wallet_section == that.quickpay_wallet_section || this.quickpay_wallet_section.equals(that.quickpay_wallet_section)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.quickpay_config == that.quickpay_config || this.quickpay_config.equals(that.quickpay_config)) && ((this.payment_instrument_id == that.payment_instrument_id || (this.payment_instrument_id != null && this.payment_instrument_id.equals(that.payment_instrument_id))) && (this.payment_instrument_type == that.payment_instrument_type || (this.payment_instrument_type != null && this.payment_instrument_type.equals(that.payment_instrument_type))))))))))) {
            if (this.billing_country == that.billing_country) {
                return true;
            }
            if (this.billing_country != null && this.billing_country.equals(that.billing_country)) {
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
        int code = (((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.quickpay_wallet_section.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.quickpay_config.hashCode()) * -2128831035) ^ (this.payment_instrument_id == null ? 0 : this.payment_instrument_id.hashCode())) * -2128831035) ^ (this.payment_instrument_type == null ? 0 : this.payment_instrument_type.hashCode())) * -2128831035;
        if (this.billing_country != null) {
            i = this.billing_country.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PaymentsQuickpayWalletEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", quickpay_wallet_section=" + this.quickpay_wallet_section + ", operation=" + this.operation + ", quickpay_config=" + this.quickpay_config + ", payment_instrument_id=" + this.payment_instrument_id + ", payment_instrument_type=" + this.payment_instrument_type + ", billing_country=" + this.billing_country + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
