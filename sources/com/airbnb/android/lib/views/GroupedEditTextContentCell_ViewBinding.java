package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class GroupedEditTextContentCell_ViewBinding extends GroupedCell_ViewBinding {
    private GroupedEditTextContentCell target;

    public GroupedEditTextContentCell_ViewBinding(GroupedEditTextContentCell target2) {
        this(target2, target2);
    }

    public GroupedEditTextContentCell_ViewBinding(GroupedEditTextContentCell target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.editText = (EditText) Utils.findOptionalViewAsType(source, C0880R.C0882id.grouped_cell_edit_text, "field 'editText'", EditText.class);
    }

    public void unbind() {
        GroupedEditTextContentCell target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.editText = null;
        super.unbind();
    }
}
