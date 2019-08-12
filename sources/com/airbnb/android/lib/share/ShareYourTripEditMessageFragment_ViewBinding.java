package com.airbnb.android.lib.share;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class ShareYourTripEditMessageFragment_ViewBinding implements Unbinder {
    private ShareYourTripEditMessageFragment target;

    public ShareYourTripEditMessageFragment_ViewBinding(ShareYourTripEditMessageFragment target2, View source) {
        this.target = target2;
        target2.editText = (AirEditTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_edit_text, "field 'editText'", AirEditTextView.class);
    }

    public void unbind() {
        ShareYourTripEditMessageFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.editText = null;
    }
}
