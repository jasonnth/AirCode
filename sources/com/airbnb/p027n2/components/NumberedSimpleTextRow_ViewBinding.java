package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.NumberedSimpleTextRow_ViewBinding */
public class NumberedSimpleTextRow_ViewBinding implements Unbinder {
    private NumberedSimpleTextRow target;

    public NumberedSimpleTextRow_ViewBinding(NumberedSimpleTextRow target2, View source) {
        this.target = target2;
        target2.stepNumber = (AirTextView) Utils.findRequiredViewAsType(source, R.id.step_number, "field 'stepNumber'", AirTextView.class);
        target2.content = (AirTextView) Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", AirTextView.class);
    }

    public void unbind() {
        NumberedSimpleTextRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.stepNumber = null;
        target2.content = null;
    }
}
