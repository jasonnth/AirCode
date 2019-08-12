package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.ToggleView;

/* renamed from: com.airbnb.n2.components.ToggleActionRow_ViewBinding */
public final class ToggleActionRow_ViewBinding implements Unbinder {
    private ToggleActionRow target;

    public ToggleActionRow_ViewBinding(ToggleActionRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleText'", AirTextView.class);
        target2.label = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'label'", AirTextView.class);
        target2.toggle = (ToggleView) Utils.findRequiredViewAsType(source, R.id.toggle, "field 'toggle'", ToggleView.class);
    }

    public void unbind() {
        ToggleActionRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.label = null;
        target2.toggle = null;
    }
}
