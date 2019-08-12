package com.airbnb.android.core.views.guestpicker;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.interfaces.StepperRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;

public class GuestsPickerView_ViewBinding implements Unbinder {
    private GuestsPickerView target;

    public GuestsPickerView_ViewBinding(GuestsPickerView target2) {
        this(target2, target2);
    }

    public GuestsPickerView_ViewBinding(GuestsPickerView target2, View source) {
        this.target = target2;
        target2.adultsStepperRow = (StepperRowInterface) Utils.findRequiredViewAsType(source, C0716R.C0718id.adult_stepper, "field 'adultsStepperRow'", StepperRowInterface.class);
        target2.infantsStepperRow = (StepperRowInterface) Utils.findRequiredViewAsType(source, C0716R.C0718id.infant_stepper, "field 'infantsStepperRow'", StepperRowInterface.class);
        target2.childrenStepperRow = (StepperRowInterface) Utils.findRequiredViewAsType(source, C0716R.C0718id.child_stepper, "field 'childrenStepperRow'", StepperRowInterface.class);
        target2.petsSwitch = (SwitchRowInterface) Utils.findRequiredViewAsType(source, C0716R.C0718id.pets_switch, "field 'petsSwitch'", SwitchRowInterface.class);
        target2.noPetsTextView = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0716R.C0718id.no_pets, "field 'noPetsTextView'", SimpleTextRow.class);
    }

    public void unbind() {
        GuestsPickerView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.adultsStepperRow = null;
        target2.infantsStepperRow = null;
        target2.childrenStepperRow = null;
        target2.petsSwitch = null;
        target2.noPetsTextView = null;
    }
}
