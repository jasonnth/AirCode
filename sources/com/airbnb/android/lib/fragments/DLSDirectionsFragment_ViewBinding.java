package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;

public class DLSDirectionsFragment_ViewBinding implements Unbinder {
    private DLSDirectionsFragment target;
    private View view2131755232;

    public DLSDirectionsFragment_ViewBinding(final DLSDirectionsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.textRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.directions_text, "field 'textRow'", SimpleTextRow.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.button, "method 'launchMap'");
        this.view2131755232 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.launchMap();
            }
        });
    }

    public void unbind() {
        DLSDirectionsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.textRow = null;
        this.view2131755232.setOnClickListener(null);
        this.view2131755232 = null;
    }
}
