package com.airbnb.android.listyourspacedls;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetProgressBar;

public class LYSHomeActivity_ViewBinding implements Unbinder {
    private LYSHomeActivity target;

    public LYSHomeActivity_ViewBinding(LYSHomeActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public LYSHomeActivity_ViewBinding(LYSHomeActivity target2, View source) {
        this.target = target2;
        target2.progressBar = (SheetProgressBar) Utils.findRequiredViewAsType(source, C7251R.C7253id.sheet_progress_bar, "field 'progressBar'", SheetProgressBar.class);
        target2.modalContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C7251R.C7253id.modal_container, "field 'modalContainer'", ViewGroup.class);
        target2.loadingOverlay = Utils.findRequiredView(source, C7251R.C7253id.loading_overlay, "field 'loadingOverlay'");
        target2.opaqueLoader = Utils.findRequiredView(source, C7251R.C7253id.opaque_loader, "field 'opaqueLoader'");
    }

    public void unbind() {
        LYSHomeActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.progressBar = null;
        target2.modalContainer = null;
        target2.loadingOverlay = null;
        target2.opaqueLoader = null;
    }
}
