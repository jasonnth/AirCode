package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineInputWithContactPickerRow_ViewBinding */
public class InlineInputWithContactPickerRow_ViewBinding implements Unbinder {
    private InlineInputWithContactPickerRow target;

    public InlineInputWithContactPickerRow_ViewBinding(InlineInputWithContactPickerRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.editText = (AirEditTextView) Utils.findRequiredViewAsType(source, R.id.edit_text, "field 'editText'", AirEditTextView.class);
        target2.addButton = (ImageButton) Utils.findRequiredViewAsType(source, R.id.add_button, "field 'addButton'", ImageButton.class);
        target2.animationDuration = source.getContext().getResources().getInteger(17694720);
    }

    public void unbind() {
        InlineInputWithContactPickerRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.editText = null;
        target2.addButton = null;
    }
}
