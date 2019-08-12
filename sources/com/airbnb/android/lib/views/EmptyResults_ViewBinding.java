package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirButton;

public class EmptyResults_ViewBinding implements Unbinder {
    private EmptyResults target;

    public EmptyResults_ViewBinding(EmptyResults target2) {
        this(target2, target2);
    }

    public EmptyResults_ViewBinding(EmptyResults target2, View source) {
        this.target = target2;
        target2.mTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.no_results_title, "field 'mTitle'", TextView.class);
        target2.mSubTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.no_results_subtitle, "field 'mSubTitle'", TextView.class);
        target2.mActionButton = (AirButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.no_results_button, "field 'mActionButton'", AirButton.class);
    }

    public void unbind() {
        EmptyResults target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTitle = null;
        target2.mSubTitle = null;
        target2.mActionButton = null;
    }
}
