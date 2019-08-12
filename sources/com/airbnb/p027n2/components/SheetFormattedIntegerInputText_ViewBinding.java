package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SheetFormattedIntegerInputText_ViewBinding */
public class SheetFormattedIntegerInputText_ViewBinding implements Unbinder {
    private SheetFormattedIntegerInputText target;

    public SheetFormattedIntegerInputText_ViewBinding(SheetFormattedIntegerInputText target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'title'", AirTextView.class);
        target2.input = (IntegerFormatInputView) Utils.findRequiredViewAsType(source, R.id.input, "field 'input'", IntegerFormatInputView.class);
    }

    public void unbind() {
        SheetFormattedIntegerInputText target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.input = null;
    }
}
