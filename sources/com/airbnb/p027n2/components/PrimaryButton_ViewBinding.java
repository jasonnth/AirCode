package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.PrimaryButton_ViewBinding */
public class PrimaryButton_ViewBinding implements Unbinder {
    private PrimaryButton target;

    public PrimaryButton_ViewBinding(PrimaryButton target2, View source) {
        this.target = target2;
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text_view, "field 'textView'", AirTextView.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loading_view, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        PrimaryButton target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textView = null;
        target2.loadingView = null;
    }
}
