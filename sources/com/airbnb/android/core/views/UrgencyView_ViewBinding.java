package com.airbnb.android.core.views;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.p027n2.primitives.AirTextView;

public class UrgencyView_ViewBinding implements Unbinder {
    private UrgencyView target;

    public UrgencyView_ViewBinding(UrgencyView target2) {
        this(target2, target2);
    }

    public UrgencyView_ViewBinding(UrgencyView target2, View source) {
        this.target = target2;
        target2.text = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.text, "field 'text'", AirTextView.class);
        target2.image = (LottieAnimationView) Utils.findRequiredViewAsType(source, C0716R.C0718id.image, "field 'image'", LottieAnimationView.class);
        target2.divider = Utils.findRequiredView(source, C0716R.C0718id.divider, "field 'divider'");
        target2.textContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C0716R.C0718id.text_container, "field 'textContainer'", ViewGroup.class);
        target2.contentContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C0716R.C0718id.content_container, "field 'contentContainer'", ViewGroup.class);
    }

    public void unbind() {
        UrgencyView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.text = null;
        target2.image = null;
        target2.divider = null;
        target2.textContainer = null;
        target2.contentContainer = null;
    }
}
