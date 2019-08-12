package com.airbnb.p027n2.components.photorearranger;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangablePhotoRow_ViewBinding */
public class RearrangablePhotoRow_ViewBinding implements Unbinder {
    private RearrangablePhotoRow target;

    public RearrangablePhotoRow_ViewBinding(RearrangablePhotoRow target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.photo, "field 'imageView'", AirImageView.class);
        target2.errorIconView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.error_icon, "field 'errorIconView'", AirImageView.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loading, "field 'loadingView'", LoadingView.class);
        target2.labelView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'labelView'", AirTextView.class);
    }

    public void unbind() {
        RearrangablePhotoRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.errorIconView = null;
        target2.loadingView = null;
        target2.labelView = null;
    }
}
