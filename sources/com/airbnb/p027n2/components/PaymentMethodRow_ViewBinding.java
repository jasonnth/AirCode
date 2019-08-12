package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.PaymentMethodRow_ViewBinding */
public class PaymentMethodRow_ViewBinding implements Unbinder {
    private PaymentMethodRow target;

    public PaymentMethodRow_ViewBinding(PaymentMethodRow target2, View source) {
        this.target = target2;
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", AirImageView.class);
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
        target2.rowDrawable = (AirImageView) Utils.findRequiredViewAsType(source, R.id.row_drawable, "field 'rowDrawable'", AirImageView.class);
    }

    public void unbind() {
        PaymentMethodRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.image = null;
        target2.title = null;
        target2.divider = null;
        target2.rowDrawable = null;
    }
}
