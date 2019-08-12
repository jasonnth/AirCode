package com.airbnb.android.core.views;

import android.support.p002v7.widget.LinearLayoutCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.MicroSectionHeader;

public class BedDetailsTray_ViewBinding implements Unbinder {
    private BedDetailsTray target;

    public BedDetailsTray_ViewBinding(BedDetailsTray target2) {
        this(target2, target2);
    }

    public BedDetailsTray_ViewBinding(BedDetailsTray target2, View source) {
        this.target = target2;
        target2.sectionHeader = (MicroSectionHeader) Utils.findRequiredViewAsType(source, C0716R.C0718id.header, "field 'sectionHeader'", MicroSectionHeader.class);
        target2.cardsContainer = (LinearLayoutCompat) Utils.findRequiredViewAsType(source, C0716R.C0718id.linear_layout, "field 'cardsContainer'", LinearLayoutCompat.class);
        target2.divider = Utils.findRequiredView(source, C0716R.C0718id.divider, "field 'divider'");
    }

    public void unbind() {
        BedDetailsTray target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sectionHeader = null;
        target2.cardsContainer = null;
        target2.divider = null;
    }
}
