package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.n2.components.CheckInGuideAddStepButton_ViewBinding */
public class CheckInGuideAddStepButton_ViewBinding implements Unbinder {
    private CheckInGuideAddStepButton target;

    public CheckInGuideAddStepButton_ViewBinding(CheckInGuideAddStepButton target2, View source) {
        this.target = target2;
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirButton.class);
    }

    public void unbind() {
        CheckInGuideAddStepButton target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.button = null;
    }
}
