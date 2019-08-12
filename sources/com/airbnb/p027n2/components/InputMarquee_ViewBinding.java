package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;

/* renamed from: com.airbnb.n2.components.InputMarquee_ViewBinding */
public class InputMarquee_ViewBinding implements Unbinder {
    private InputMarquee target;

    public InputMarquee_ViewBinding(InputMarquee target2, View source) {
        this.target = target2;
        target2.editTextView = (AirEditTextView) Utils.findRequiredViewAsType(source, R.id.edit_text, "field 'editTextView'", AirEditTextView.class);
    }

    public void unbind() {
        InputMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.editTextView = null;
    }
}
