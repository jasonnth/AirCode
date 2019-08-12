package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.StickyButton;

public class NPSFragment_ViewBinding implements Unbinder {
    private NPSFragment target;

    public NPSFragment_ViewBinding(NPSFragment target2, View source) {
        this.target = target2;
        target2.mSubmit = (StickyButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.submit_button, "field 'mSubmit'", StickyButton.class);
        target2.mGroup = (RadioGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.rating_group, "field 'mGroup'", RadioGroup.class);
    }

    public void unbind() {
        NPSFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mSubmit = null;
        target2.mGroup = null;
    }
}
