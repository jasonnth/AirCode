package com.airbnb.jitney.event.logging.PaymentsContext.p189v1;

import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.PaymentInstrumentType.p183v1.C2504PaymentInstrumentType;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaymentsContext.v1.PaymentsContext */
public final class C2543PaymentsContext implements Struct {
    public static final Adapter<C2543PaymentsContext, Builder> ADAPTER = new PaymentsContextAdapter();
    public final String currency;
    public final Long payment_instrument_id;
    public final C2504PaymentInstrumentType payment_instrument_type;
    public final Long price_displayed;

    /* renamed from: com.airbnb.jitney.event.logging.PaymentsContext.v1.PaymentsContext$Builder */
    public static final class Builder implements StructBuilder<C2543PaymentsContext> {
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public Long payment_instrument_id;
        /* access modifiers changed from: private */
        public C2504PaymentInstrumentType payment_instrument_type;
        /* access modifiers changed from: private */
        public Long price_displayed;

        private Builder() {
        }

        public Builder(String currency2, Long price_displayed2) {
            this.currency = currency2;
            this.price_displayed = price_displayed2;
        }

        public Builder payment_instrument_id(Long payment_instrument_id2) {
            this.payment_instrument_id = payment_instrument_id2;
            return this;
        }

        public Builder payment_instrument_type(C2504PaymentInstrumentType payment_instrument_type2) {
            this.payment_instrument_type = payment_instrument_type2;
            return this;
        }

        public C2543PaymentsContext build() {
            if (this.currency == null) {
                throw new IllegalStateException("Required field 'currency' is missing");
            } else if (this.price_displayed != null) {
                return new C2543PaymentsContext(this);
            } else {
                throw new IllegalStateException("Required field 'price_displayed' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.PaymentsContext.v1.PaymentsContext$PaymentsContextAdapter */
    private static final class PaymentsContextAdapter implements Adapter<C2543PaymentsContext, Builder> {
        private PaymentsContextAdapter() {
        }

        public void write(Protocol protocol, C2543PaymentsContext struct) throws IOException {
            protocol.writeStructBegin("PaymentsContext");
            if (struct.payment_instrument_id != null) {
                protocol.writeFieldBegin("payment_instrument_id", 1, 10);
                protocol.writeI64(struct.payment_instrument_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.payment_instrument_type != null) {
                protocol.writeFieldBegin("payment_instrument_type", 2, 8);
                protocol.writeI32(struct.payment_instrument_type.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(AirbnbConstants.PREFS_CURRENCY, 3, PassportService.SF_DG11);
            protocol.writeString(struct.currency);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("price_displayed", 4, 10);
            protocol.writeI64(struct.price_displayed.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2543PaymentsContext(Builder builder) {
        this.payment_instrument_id = builder.payment_instrument_id;
        this.payment_instrument_type = builder.payment_instrument_type;
        this.currency = builder.currency;
        this.price_displayed = builder.price_displayed;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2543PaymentsContext)) {
            return false;
        }
        C2543PaymentsContext that = (C2543PaymentsContext) other;
        if ((this.payment_instrument_id == that.payment_instrument_id || (this.payment_instrument_id != null && this.payment_instrument_id.equals(that.payment_instrument_id))) && ((this.payment_instrument_type == that.payment_instrument_type || (this.payment_instrument_type != null && this.payment_instrument_type.equals(that.payment_instrument_type))) && ((this.currency == that.currency || this.currency.equals(that.currency)) && (this.price_displayed == that.price_displayed || this.price_displayed.equals(that.price_displayed))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (16777619 ^ (this.payment_instrument_id == null ? 0 : this.payment_instrument_id.hashCode())) * -2128831035;
        if (this.payment_instrument_type != null) {
            i = this.payment_instrument_type.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.currency.hashCode()) * -2128831035) ^ this.price_displayed.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaymentsContext{payment_instrument_id=" + this.payment_instrument_id + ", payment_instrument_type=" + this.payment_instrument_type + ", currency=" + this.currency + ", price_displayed=" + this.price_displayed + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
