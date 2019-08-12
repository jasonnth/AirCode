package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;

public enum PaymentMethod {
    CreditCard(C0716R.string.p4_payment_option_credit_card),
    PayPal(C0716R.string.p4_payment_option_paypal),
    Alipay(C0716R.string.alipay);
    
    private final int nameResource;

    private PaymentMethod(int nameResource2) {
        this.nameResource = nameResource2;
    }

    public int getNameResource() {
        return this.nameResource;
    }
}
