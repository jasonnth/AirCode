package com.airbnb.android.core.activities;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.SheetProgressBar;

public class SheetFlowActivity_ViewBinding implements Unbinder {
    private SheetFlowActivity target;

    public SheetFlowActivity_ViewBinding(SheetFlowActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public SheetFlowActivity_ViewBinding(SheetFlowActivity target2, View source) {
        this.target = target2;
        target2.progressBar = (SheetProgressBar) Utils.findRequiredViewAsType(source, C0716R.C0718id.sheet_progress_bar, "field 'progressBar'", SheetProgressBar.class);
        target2.rootView = (ViewGroup) Utils.findRequiredViewAsType(source, C0716R.C0718id.root_layout, "field 'rootView'", ViewGroup.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0716R.C0718id.sheet_loader_frame, "field 'loaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        SheetFlowActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.progressBar = null;
        target2.rootView = null;
        target2.loaderFrame = null;
    }
}
