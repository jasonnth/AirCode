package com.airbnb.p027n2.primitives;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.primitives.AirSwitch_ViewBinding */
public final class AirSwitch_ViewBinding implements Unbinder {
    private AirSwitch target;

    public AirSwitch_ViewBinding(AirSwitch target2, View source) {
        this.target = target2;
        target2.container = (FrameLayout) Utils.findRequiredViewAsType(source, R.id.container, "field 'container'", FrameLayout.class);
        target2.thumbView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.thumb, "field 'thumbView'", AirImageView.class);
        target2.strokeWidth = source.getContext().getResources().getDimensionPixelSize(R.dimen.n2_air_switch_stroke);
    }

    public void unbind() {
        AirSwitch target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.container = null;
        target2.thumbView = null;
    }
}
