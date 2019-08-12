package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class TextInputSheetFragment_ViewBinding implements Unbinder {
    private TextInputSheetFragment target;

    public TextInputSheetFragment_ViewBinding(TextInputSheetFragment target2, View source) {
        this.target = target2;
        target2.inputEditText = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.input_editText, "field 'inputEditText'", EditText.class);
    }

    public void unbind() {
        TextInputSheetFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.inputEditText = null;
    }
}
