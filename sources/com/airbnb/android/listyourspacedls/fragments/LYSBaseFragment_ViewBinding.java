package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.listing.views.TipView;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSBaseFragment_ViewBinding implements Unbinder {
    private LYSBaseFragment target;

    public LYSBaseFragment_ViewBinding(LYSBaseFragment target2, View source) {
        this.target = target2;
        target2.nextButton = (AirButton) Utils.findOptionalViewAsType(source, C7251R.C7253id.next_btn, "field 'nextButton'", AirButton.class);
        target2.previewButton = (AirButton) Utils.findOptionalViewAsType(source, C7251R.C7253id.preview, "field 'previewButton'", AirButton.class);
        target2.tipView = (TipView) Utils.findOptionalViewAsType(source, C7251R.C7253id.tip, "field 'tipView'", TipView.class);
    }

    public void unbind() {
        LYSBaseFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.nextButton = null;
        target2.previewButton = null;
        target2.tipView = null;
    }
}
