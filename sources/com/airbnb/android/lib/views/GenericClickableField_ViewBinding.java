package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class GenericClickableField_ViewBinding implements Unbinder {
    private GenericClickableField target;

    public GenericClickableField_ViewBinding(GenericClickableField target2) {
        this(target2, target2);
    }

    public GenericClickableField_ViewBinding(GenericClickableField target2, View source) {
        this.target = target2;
        target2.textView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.clickable_field_text, "field 'textView'", TextView.class);
    }

    public void unbind() {
        GenericClickableField target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textView = null;
    }
}
