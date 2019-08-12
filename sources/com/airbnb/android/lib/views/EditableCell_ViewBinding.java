package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class EditableCell_ViewBinding implements Unbinder {
    private EditableCell target;

    public EditableCell_ViewBinding(EditableCell target2) {
        this(target2, target2);
    }

    public EditableCell_ViewBinding(EditableCell target2, View source) {
        this.target = target2;
        target2.mTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.title, "field 'mTitle'", TextView.class);
        target2.mContent = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.content, "field 'mContent'", TextView.class);
        target2.mPrefixContent = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.prefix_content, "field 'mPrefixContent'", TextView.class);
        target2.mEditText = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_text, "field 'mEditText'", EditText.class);
        target2.mTopBorder = Utils.findRequiredView(source, C0880R.C0882id.top_border, "field 'mTopBorder'");
    }

    public void unbind() {
        EditableCell target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTitle = null;
        target2.mContent = null;
        target2.mPrefixContent = null;
        target2.mEditText = null;
        target2.mTopBorder = null;
    }
}
