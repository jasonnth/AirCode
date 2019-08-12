package com.airbnb.android.core.fragments;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class ProgressDialogFragment_ViewBinding implements Unbinder {
    private ProgressDialogFragment target;

    public ProgressDialogFragment_ViewBinding(ProgressDialogFragment target2, View source) {
        this.target = target2;
        target2.mProgressDialogContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.progress_dialog_container, "field 'mProgressDialogContainer'", LinearLayout.class);
        target2.mDualButtonsContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.dual_buttons_container, "field 'mDualButtonsContainer'", LinearLayout.class);
        target2.mQuestion = (TextView) Utils.findOptionalViewAsType(source, C0716R.C0718id.txt_question, "field 'mQuestion'", TextView.class);
        target2.mPositiveButton = (TextView) Utils.findOptionalViewAsType(source, C0716R.C0718id.positive_button, "field 'mPositiveButton'", TextView.class);
        target2.mNegativeButton = (TextView) Utils.findOptionalViewAsType(source, C0716R.C0718id.negative_button, "field 'mNegativeButton'", TextView.class);
    }

    public void unbind() {
        ProgressDialogFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mProgressDialogContainer = null;
        target2.mDualButtonsContainer = null;
        target2.mQuestion = null;
        target2.mPositiveButton = null;
        target2.mNegativeButton = null;
    }
}
