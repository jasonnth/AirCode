package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.MicroRow_ViewBinding */
public final class MicroRow_ViewBinding implements Unbinder {
    private MicroRow target;

    public MicroRow_ViewBinding(MicroRow target2, View source) {
        this.target = target2;
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'textView'", AirTextView.class);
    }

    public void unbind() {
        MicroRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textView = null;
    }
}