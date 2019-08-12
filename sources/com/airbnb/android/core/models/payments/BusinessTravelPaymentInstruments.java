package com.airbnb.android.core.models.payments;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;
import com.google.common.collect.FluentIterable;

public class BusinessTravelPaymentInstruments extends OldPaymentInstrument {
    private long businessEntityId;
    private InstrumentType paymentInstrumentType;

    public enum BusinessTravelPaymentMethod {
        BusinessTravelInvoice("business_travel_invoice", InstrumentType.BusinessTravelInvoice),
        BusinessTravelCentralizedBilling("business_travel_centralized_billing", InstrumentType.BusinessTravelCentralizedBilling),
        Other("", InstrumentType.Other);
        
        private final InstrumentType instrumentType;
        private final String serverKey;

        private BusinessTravelPaymentMethod(String serverKey2, InstrumentType instrumentType2) {
            this.serverKey = serverKey2;
            this.instrumentType = instrumentType2;
        }

        public String getServerKey() {
            return this.serverKey;
        }

        public InstrumentType getInstrumentType() {
            return this.instrumentType;
        }

        public static InstrumentType findInstrumentTypeByServerKey(String paymentMethodType) {
            return ((BusinessTravelPaymentMethod) FluentIterable.from((E[]) values()).firstMatch(C6199x81f66b1d.lambdaFactory$(paymentMethodType)).mo41059or(Other)).getInstrumentType();
        }
    }

    public BusinessTravelPaymentInstruments(long businessEntityId2, String displayName, String paymentMethodType) {
        this.businessEntityId = businessEntityId2;
        this.paymentInstrumentType = BusinessTravelPaymentMethod.findInstrumentTypeByServerKey(paymentMethodType);
        setName(displayName);
    }

    public long getBusinessEntityId() {
        return this.businessEntityId;
    }

    public boolean isEditable() {
        return false;
    }

    public int getIcon() {
        return C0716R.C0717drawable.icon_creditcard;
    }

    public InstrumentType getType() {
        return this.paymentInstrumentType;
    }

    public String getDisplayName(Context context) {
        return getName();
    }

    public int hashCode() {
        return getType().ordinal();
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof BusinessTravelPaymentInstruments)) {
            return ((BusinessTravelPaymentInstruments) o).getType().equals(getType());
        }
        return false;
    }
}
