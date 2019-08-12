package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class TextEntryFragment_ViewBinding implements Unbinder {
    private TextEntryFragment target;
    private View view2131756164;

    public TextEntryFragment_ViewBinding(final TextEntryFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.editText = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_text, "field 'editText'", EditText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.save_button, "field 'saveButton' and method 'onClickSave'");
        target2.saveButton = (AirButton) Utils.castView(view, C0880R.C0882id.save_button, "field 'saveButton'", AirButton.class);
        this.view2131756164 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSave();
            }
        });
    }

    public void unbind() {
        TextEntryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.editText = null;
        target2.saveButton = null;
        this.view2131756164.setOnClickListener(null);
        this.view2131756164 = null;
    }
}
