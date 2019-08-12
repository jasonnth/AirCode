package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.LabeledSectionRow_ViewBinding */
public class LabeledSectionRow_ViewBinding implements Unbinder {
    private LabeledSectionRow target;

    public LabeledSectionRow_ViewBinding(LabeledSectionRow target2, View source) {
        this.target = target2;
        target2.labelText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'labelText'", AirTextView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.bodyText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.body, "field 'bodyText'", AirTextView.class);
        target2.actionText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.action, "field 'actionText'", AirTextView.class);
    }

    public void unbind() {
        LabeledSectionRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.labelText = null;
        target2.titleText = null;
        target2.bodyText = null;
        target2.actionText = null;
    }
}
