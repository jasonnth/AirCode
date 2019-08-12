package com.airbnb.android.booking.fragments;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;

public class CheckInMessageDialogFragment_ViewBinding implements Unbinder {
    private CheckInMessageDialogFragment target;

    public CheckInMessageDialogFragment_ViewBinding(CheckInMessageDialogFragment target2, View source) {
        this.target = target2;
        target2.messageInput = (EditText) Utils.findRequiredViewAsType(source, C0704R.C0706id.input_message, "field 'messageInput'", EditText.class);
    }

    public void unbind() {
        CheckInMessageDialogFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.messageInput = null;
    }
}
