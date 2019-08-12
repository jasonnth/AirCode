package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class ConfirmProfileInfoFragment_ViewBinding implements Unbinder {
    private ConfirmProfileInfoFragment target;

    public ConfirmProfileInfoFragment_ViewBinding(ConfirmProfileInfoFragment target2, View source) {
        this.target = target2;
        target2.mFirstName = (AirEditTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.editable_field_first_name, "field 'mFirstName'", AirEditTextView.class);
        target2.mLastName = (AirEditTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.editable_field_last_name, "field 'mLastName'", AirEditTextView.class);
        target2.mEmail = (AirEditTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.editable_field_email, "field 'mEmail'", AirEditTextView.class);
        target2.mStickyButton = (StickyButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.sticky_continue_button, "field 'mStickyButton'", StickyButton.class);
    }

    public void unbind() {
        ConfirmProfileInfoFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mFirstName = null;
        target2.mLastName = null;
        target2.mEmail = null;
        target2.mStickyButton = null;
    }
}
