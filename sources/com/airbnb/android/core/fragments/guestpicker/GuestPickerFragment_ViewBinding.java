package com.airbnb.android.core.fragments.guestpicker;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.guestpicker.GuestsPickerSheetWithButtonView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class GuestPickerFragment_ViewBinding implements Unbinder {
    private GuestPickerFragment target;

    public GuestPickerFragment_ViewBinding(GuestPickerFragment target2, View source) {
        this.target = target2;
        target2.guestsPickerView = (GuestsPickerSheetWithButtonView) Utils.findRequiredViewAsType(source, C0716R.C0718id.guests_picker, "field 'guestsPickerView'", GuestsPickerSheetWithButtonView.class);
        target2.jellyfishView = (JellyfishView) Utils.findOptionalViewAsType(source, C0716R.C0718id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        GuestPickerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.guestsPickerView = null;
        target2.jellyfishView = null;
        target2.toolbar = null;
    }
}
