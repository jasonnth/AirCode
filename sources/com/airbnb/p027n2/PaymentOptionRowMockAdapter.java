package com.airbnb.p027n2;

import com.airbnb.p027n2.components.PaymentOptionRow;

/* renamed from: com.airbnb.n2.PaymentOptionRowMockAdapter */
public final class PaymentOptionRowMockAdapter implements DLSMockAdapter<PaymentOptionRow> {
    public int getItemCount() {
        return 1;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(PaymentOptionRow view, int position) {
        switch (position) {
            case 0:
                PaymentOptionRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
