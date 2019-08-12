package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.ExpandableTextView;

/* renamed from: com.airbnb.n2.components.TextRow_ViewBinding */
public class TextRow_ViewBinding implements Unbinder {
    private TextRow target;

    public TextRow_ViewBinding(TextRow target2, View source) {
        this.target = target2;
        target2.textView = (ExpandableTextView) Utils.findRequiredViewAsType(source, R.id.text_row_expandable_text_view, "field 'textView'", ExpandableTextView.class);
    }

    public void unbind() {
        TextRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textView = null;
    }
}
