package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.RequirementChecklistRow_ViewBinding */
public class RequirementChecklistRow_ViewBinding implements Unbinder {
    private RequirementChecklistRow target;

    public RequirementChecklistRow_ViewBinding(RequirementChecklistRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.iconView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon_view, "field 'iconView'", AirImageView.class);
    }

    public void unbind() {
        RequirementChecklistRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.iconView = null;
    }
}
