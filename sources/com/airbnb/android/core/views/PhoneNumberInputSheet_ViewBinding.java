package com.airbnb.android.core.views;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class PhoneNumberInputSheet_ViewBinding implements Unbinder {
    private PhoneNumberInputSheet target;

    public PhoneNumberInputSheet_ViewBinding(PhoneNumberInputSheet target2) {
        this(target2, target2);
    }

    public PhoneNumberInputSheet_ViewBinding(PhoneNumberInputSheet target2, View source) {
        this.target = target2;
        target2.hintText = (TextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.sheet_input_text_hint, "field 'hintText'", TextView.class);
        target2.callingCodeButton = (TextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.btn_calling_code, "field 'callingCodeButton'", TextView.class);
        target2.phoneNumberEditText = (EditText) Utils.findRequiredViewAsType(source, C0716R.C0718id.edittext_phone_number, "field 'phoneNumberEditText'", EditText.class);
        target2.editTextContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.sheet_input_edit_text_container, "field 'editTextContainer'", LinearLayout.class);
    }

    public void unbind() {
        PhoneNumberInputSheet target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.hintText = null;
        target2.callingCodeButton = null;
        target2.phoneNumberEditText = null;
        target2.editTextContainer = null;
    }
}
