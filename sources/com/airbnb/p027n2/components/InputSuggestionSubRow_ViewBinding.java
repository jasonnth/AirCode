package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InputSuggestionSubRow_ViewBinding */
public class InputSuggestionSubRow_ViewBinding implements Unbinder {
    private InputSuggestionSubRow target;

    public InputSuggestionSubRow_ViewBinding(InputSuggestionSubRow target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.verticalDivider = Utils.findRequiredView(source, R.id.vertical_divider, "field 'verticalDivider'");
        target2.subRowDivider = Utils.findRequiredView(source, R.id.sub_row_divider, "field 'subRowDivider'");
    }

    public void unbind() {
        InputSuggestionSubRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.divider = null;
        target2.verticalDivider = null;
        target2.subRowDivider = null;
    }
}
