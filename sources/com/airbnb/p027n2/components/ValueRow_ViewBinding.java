package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ValueRow_ViewBinding */
public class ValueRow_ViewBinding implements Unbinder {
    private ValueRow target;

    public ValueRow_ViewBinding(ValueRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.value_row_title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.value_row_subtitle, "field 'subtitleText'", AirTextView.class);
        target2.valueText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.value_row_value, "field 'valueText'", AirTextView.class);
    }

    public void unbind() {
        ValueRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.valueText = null;
    }
}
