package com.airbnb.android.lib.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;

public class SuperhostView_ViewBinding implements Unbinder {
    private SuperhostView target;

    public SuperhostView_ViewBinding(SuperhostView target2) {
        this(target2, target2);
    }

    public SuperhostView_ViewBinding(SuperhostView target2, View source) {
        this.target = target2;
        target2.mTooltip = (ColorizedIconView) Utils.findRequiredViewAsType(source, C0880R.C0882id.superhost_tooltip, "field 'mTooltip'", ColorizedIconView.class);
    }

    public void unbind() {
        SuperhostView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTooltip = null;
    }
}
