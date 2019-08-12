package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class EditProfileTextFieldFragment_ViewBinding implements Unbinder {
    private EditProfileTextFieldFragment target;

    public EditProfileTextFieldFragment_ViewBinding(EditProfileTextFieldFragment target2, View source) {
        this.target = target2;
        target2.mTooltip = Utils.findRequiredView(source, C0880R.C0882id.tooltip_section, "field 'mTooltip'");
        target2.mTitleText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.title_text, "field 'mTitleText'", TextView.class);
        target2.mEditableField = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.editable_field, "field 'mEditableField'", EditText.class);
        target2.mDescriptionText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.description_text, "field 'mDescriptionText'", TextView.class);
    }

    public void unbind() {
        EditProfileTextFieldFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTooltip = null;
        target2.mTitleText = null;
        target2.mEditableField = null;
        target2.mDescriptionText = null;
    }
}
