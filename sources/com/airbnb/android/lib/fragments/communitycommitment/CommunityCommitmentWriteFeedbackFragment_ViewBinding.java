package com.airbnb.android.lib.fragments.communitycommitment;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class CommunityCommitmentWriteFeedbackFragment_ViewBinding implements Unbinder {
    private CommunityCommitmentWriteFeedbackFragment target;

    public CommunityCommitmentWriteFeedbackFragment_ViewBinding(CommunityCommitmentWriteFeedbackFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.editText = (AirEditTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_text, "field 'editText'", AirEditTextView.class);
    }

    public void unbind() {
        CommunityCommitmentWriteFeedbackFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.editText = null;
    }
}
