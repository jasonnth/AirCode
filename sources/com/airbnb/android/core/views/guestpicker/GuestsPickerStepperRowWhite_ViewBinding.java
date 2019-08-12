package com.airbnb.android.core.views.guestpicker;

import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.primitives.AirTextView;

public final class GuestsPickerStepperRowWhite_ViewBinding implements Unbinder {
    private GuestsPickerStepperRowWhite target;

    public GuestsPickerStepperRowWhite_ViewBinding(GuestsPickerStepperRowWhite target2) {
        this(target2, target2);
    }

    public GuestsPickerStepperRowWhite_ViewBinding(GuestsPickerStepperRowWhite target2, View source) {
        this.target = target2;
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.title, "field 'titleView'", AirTextView.class);
        target2.valueView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.value, "field 'valueView'", AirTextView.class);
        target2.descriptionView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.description, "field 'descriptionView'", AirTextView.class);
        target2.minusButton = (ImageButton) Utils.findRequiredViewAsType(source, C0716R.C0718id.minus_button, "field 'minusButton'", ImageButton.class);
        target2.plusButton = (ImageButton) Utils.findRequiredViewAsType(source, C0716R.C0718id.plus_button, "field 'plusButton'", ImageButton.class);
    }

    public void unbind() {
        GuestsPickerStepperRowWhite target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.valueView = null;
        target2.descriptionView = null;
        target2.minusButton = null;
        target2.plusButton = null;
    }
}
