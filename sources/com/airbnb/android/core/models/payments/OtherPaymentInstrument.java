package com.airbnb.android.core.models.payments;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;

public class OtherPaymentInstrument extends OldPaymentInstrument {
    private InstrumentType instrumentType = InstrumentType.Other;

    public boolean isEditable() {
        return false;
    }

    public int getIcon() {
        return C0716R.C0717drawable.icon_creditcard;
    }

    public InstrumentType getType() {
        return this.instrumentType;
    }

    public void setType(InstrumentType instrumentType2) {
        this.instrumentType = instrumentType2;
    }

    public String getDisplayName(Context context) {
        return context.getString(C0716R.string.booking_other_payment);
    }

    public int hashCode() {
        return getType().ordinal();
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof OtherPaymentInstrument)) {
            return ((OtherPaymentInstrument) o).getType().equals(getType());
        }
        return false;
    }
}
