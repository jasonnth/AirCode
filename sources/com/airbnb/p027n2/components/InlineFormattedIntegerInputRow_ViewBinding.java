package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineFormattedIntegerInputRow_ViewBinding */
public class InlineFormattedIntegerInputRow_ViewBinding implements Unbinder {
    private InlineFormattedIntegerInputRow target;

    public InlineFormattedIntegerInputRow_ViewBinding(InlineFormattedIntegerInputRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.subTitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subTitleText'", AirTextView.class);
        target2.editPrice = (IntegerFormatInputView) Utils.findRequiredViewAsType(source, R.id.price_input, "field 'editPrice'", IntegerFormatInputView.class);
        target2.tip = (AirTextView) Utils.findRequiredViewAsType(source, R.id.tip, "field 'tip'", AirTextView.class);
    }

    public void unbind() {
        InlineFormattedIntegerInputRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subTitleText = null;
        target2.editPrice = null;
        target2.tip = null;
    }
}
