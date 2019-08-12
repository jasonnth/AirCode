package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CohostReasonMessageTextInputFragment_ViewBinding implements Unbinder {
    private CohostReasonMessageTextInputFragment target;

    public CohostReasonMessageTextInputFragment_ViewBinding(CohostReasonMessageTextInputFragment target2, View source) {
        this.target = target2;
        target2.editTextPageView = (AirEditTextPageView) Utils.findRequiredViewAsType(source, C5658R.C5660id.edit_text_page, "field 'editTextPageView'", AirEditTextPageView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, C5658R.C5660id.next_btn, "field 'button'", AirButton.class);
    }

    public void unbind() {
        CohostReasonMessageTextInputFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.editTextPageView = null;
        target2.toolbar = null;
        target2.button = null;
    }
}
