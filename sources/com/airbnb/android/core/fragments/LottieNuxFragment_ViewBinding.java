package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.DocumentMarquee;

public class LottieNuxFragment_ViewBinding implements Unbinder {
    private LottieNuxFragment target;

    public LottieNuxFragment_ViewBinding(LottieNuxFragment target2, View source) {
        this.target = target2;
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0716R.C0718id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
    }

    public void unbind() {
        LottieNuxFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.documentMarquee = null;
    }
}
