package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.LargeIconRow_ViewBinding */
public class LargeIconRow_ViewBinding implements Unbinder {
    private LargeIconRow target;

    public LargeIconRow_ViewBinding(LargeIconRow target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_view, "field 'imageView'", AirImageView.class);
    }

    public void unbind() {
        LargeIconRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
    }
}
