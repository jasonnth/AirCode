package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.LeftIconRow_ViewBinding */
public class LeftIconRow_ViewBinding implements Unbinder {
    private LeftIconRow target;

    public LeftIconRow_ViewBinding(LeftIconRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleText'", AirTextView.class);
        target2.icon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", AirImageView.class);
    }

    public void unbind() {
        LeftIconRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.icon = null;
    }
}
