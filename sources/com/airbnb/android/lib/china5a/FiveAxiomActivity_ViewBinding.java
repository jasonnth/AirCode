package com.airbnb.android.lib.china5a;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetProgressBar;

public class FiveAxiomActivity_ViewBinding implements Unbinder {
    private FiveAxiomActivity target;

    public FiveAxiomActivity_ViewBinding(FiveAxiomActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public FiveAxiomActivity_ViewBinding(FiveAxiomActivity target2, View source) {
        this.target = target2;
        target2.progressBar = (SheetProgressBar) Utils.findRequiredViewAsType(source, C0880R.C0882id.sheet_progress_bar, "field 'progressBar'", SheetProgressBar.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        FiveAxiomActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.progressBar = null;
        target2.toolbar = null;
    }
}
