package com.airbnb.android.core.models.payments;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;

public class AlipayPaymentInstrument extends OldPaymentInstrument {
    public boolean isEditable() {
        return false;
    }

    public int getIcon() {
        return C0716R.C0717drawable.icon_alipay;
    }

    public InstrumentType getType() {
        return InstrumentType.Alipay;
    }

    public String getDisplayName(Context context) {
        return this.f1081id != 0 ? getName() : context.getString(C0716R.string.p4_payment_option_alipay);
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof AlipayPaymentInstrument) && this.f1081id == ((AlipayPaymentInstrument) o).getId()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return getType().ordinal();
    }
}
