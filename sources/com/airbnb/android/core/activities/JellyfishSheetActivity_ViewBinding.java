package com.airbnb.android.core.activities;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class JellyfishSheetActivity_ViewBinding implements Unbinder {
    private JellyfishSheetActivity target;

    public JellyfishSheetActivity_ViewBinding(JellyfishSheetActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public JellyfishSheetActivity_ViewBinding(JellyfishSheetActivity target2, View source) {
        this.target = target2;
        target2.rootView = (ViewGroup) Utils.findRequiredViewAsType(source, C0716R.C0718id.root_layout, "field 'rootView'", ViewGroup.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0716R.C0718id.sheet_loader_frame, "field 'loaderFrame'", LoaderFrame.class);
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0716R.C0718id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
    }

    public void unbind() {
        JellyfishSheetActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rootView = null;
        target2.toolbar = null;
        target2.loaderFrame = null;
        target2.jellyfishView = null;
    }
}
