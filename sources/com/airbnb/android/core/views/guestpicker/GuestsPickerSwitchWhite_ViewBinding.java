package com.airbnb.android.core.views.guestpicker;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public final class GuestsPickerSwitchWhite_ViewBinding implements Unbinder {
    private GuestsPickerSwitchWhite target;

    public GuestsPickerSwitchWhite_ViewBinding(GuestsPickerSwitchWhite target2) {
        this(target2, target2);
    }

    public GuestsPickerSwitchWhite_ViewBinding(GuestsPickerSwitchWhite target2, View source) {
        this.target = target2;
        target2.container = (FrameLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.container, "field 'container'", FrameLayout.class);
        target2.thumbView = (AirImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.thumb, "field 'thumbView'", AirImageView.class);
        target2.strokeWidth = source.getContext().getResources().getDimensionPixelSize(C0716R.dimen.n2_air_switch_stroke);
    }

    public void unbind() {
        GuestsPickerSwitchWhite target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.container = null;
        target2.thumbView = null;
    }
}
