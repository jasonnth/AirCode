package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class EditProfileDualTextFieldsFragment_ViewBinding extends EditProfileTextFieldFragment_ViewBinding {
    private EditProfileDualTextFieldsFragment target;

    public EditProfileDualTextFieldsFragment_ViewBinding(EditProfileDualTextFieldsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.mTitleTextTwo = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.title_text_2, "field 'mTitleTextTwo'", TextView.class);
        target2.mEditableFieldTwo = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.editable_field_2, "field 'mEditableFieldTwo'", EditText.class);
    }

    public void unbind() {
        EditProfileDualTextFieldsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTitleTextTwo = null;
        target2.mEditableFieldTwo = null;
        super.unbind();
    }
}
