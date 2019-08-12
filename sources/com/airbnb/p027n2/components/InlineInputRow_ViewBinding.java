package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineInputRow_ViewBinding */
public class InlineInputRow_ViewBinding implements Unbinder {
    private InlineInputRow target;

    public InlineInputRow_ViewBinding(InlineInputRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.inline_input_row_title, "field 'titleText'", AirTextView.class);
        target2.subTitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.inline_input_row_subtitle, "field 'subTitleText'", AirTextView.class);
        target2.editText = (AirEditTextView) Utils.findRequiredViewAsType(source, R.id.inline_input_row_edit_text, "field 'editText'", AirEditTextView.class);
        target2.iconView = (ImageView) Utils.findRequiredViewAsType(source, R.id.inline_input_row_icon, "field 'iconView'", ImageView.class);
        target2.divider = Utils.findRequiredView(source, R.id.inline_input_row_divider, "field 'divider'");
        target2.error = (AirTextView) Utils.findRequiredViewAsType(source, R.id.inline_input_row_error, "field 'error'", AirTextView.class);
        target2.tip = (AirTextView) Utils.findRequiredViewAsType(source, R.id.inline_input_row_tip, "field 'tip'", AirTextView.class);
        target2.animationDuration = source.getContext().getResources().getInteger(17694720);
    }

    public void unbind() {
        InlineInputRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subTitleText = null;
        target2.editText = null;
        target2.iconView = null;
        target2.divider = null;
        target2.error = null;
        target2.tip = null;
    }
}
