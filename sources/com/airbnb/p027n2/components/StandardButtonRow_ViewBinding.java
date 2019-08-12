package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.StandardButtonRow_ViewBinding */
public class StandardButtonRow_ViewBinding implements Unbinder {
    private StandardButtonRow target;

    public StandardButtonRow_ViewBinding(StandardButtonRow target2, View source) {
        this.target = target2;
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleView'", AirTextView.class);
        target2.subtitleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleView'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.primaryButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'primaryButton'", AirButton.class);
        target2.secondaryButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.secondary_button, "field 'secondaryButton'", AirButton.class);
    }

    public void unbind() {
        StandardButtonRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.subtitleView = null;
        target2.divider = null;
        target2.primaryButton = null;
        target2.secondaryButton = null;
    }
}
