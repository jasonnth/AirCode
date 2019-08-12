package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.PaymentInputLayout_ViewBinding */
public class PaymentInputLayout_ViewBinding implements Unbinder {
    private PaymentInputLayout target;

    public PaymentInputLayout_ViewBinding(PaymentInputLayout target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.paymentLogo = (AirImageView) Utils.findRequiredViewAsType(source, R.id.payment_logo, "field 'paymentLogo'", AirImageView.class);
        target2.inputText = (AirEditTextView) Utils.findRequiredViewAsType(source, R.id.input_text, "field 'inputText'", AirEditTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        PaymentInputLayout target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.paymentLogo = null;
        target2.inputText = null;
        target2.divider = null;
    }
}
