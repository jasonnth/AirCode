package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class WriteSavedMessageBodyFragment_ViewBinding implements Unbinder {
    private WriteSavedMessageBodyFragment target;

    public WriteSavedMessageBodyFragment_ViewBinding(WriteSavedMessageBodyFragment target2, View source) {
        this.target = target2;
        target2.editText = (AirEditTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_text, "field 'editText'", AirEditTextView.class);
    }

    public void unbind() {
        WriteSavedMessageBodyFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.editText = null;
    }
}
