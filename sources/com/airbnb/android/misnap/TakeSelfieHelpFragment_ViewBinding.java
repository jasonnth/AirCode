package com.airbnb.android.misnap;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;

public class TakeSelfieHelpFragment_ViewBinding implements Unbinder {
    private TakeSelfieHelpFragment target;

    public TakeSelfieHelpFragment_ViewBinding(TakeSelfieHelpFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7471R.C7473id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        TakeSelfieHelpFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
    }
}
