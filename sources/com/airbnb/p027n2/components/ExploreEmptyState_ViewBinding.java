package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ExploreEmptyState_ViewBinding */
public class ExploreEmptyState_ViewBinding implements Unbinder {
    private ExploreEmptyState target;

    public ExploreEmptyState_ViewBinding(ExploreEmptyState target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirButton.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        ExploreEmptyState target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.button = null;
        target2.divider = null;
    }
}
