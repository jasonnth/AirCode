package com.airbnb.android.core.views.guestpicker;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.primitives.AirTextView;

public class GuestsPickerSwitchRowWhite_ViewBinding implements Unbinder {
    private GuestsPickerSwitchRowWhite target;

    public GuestsPickerSwitchRowWhite_ViewBinding(GuestsPickerSwitchRowWhite target2) {
        this(target2, target2);
    }

    public GuestsPickerSwitchRowWhite_ViewBinding(GuestsPickerSwitchRowWhite target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.switch_row_title, "field 'title'", AirTextView.class);
        target2.description = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.switch_row_subtitle, "field 'description'", AirTextView.class);
        target2.switchView = (GuestsPickerSwitchWhite) Utils.findRequiredViewAsType(source, C0716R.C0718id.switch_row_switch, "field 'switchView'", GuestsPickerSwitchWhite.class);
    }

    public void unbind() {
        GuestsPickerSwitchRowWhite target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.description = null;
        target2.switchView = null;
    }
}
