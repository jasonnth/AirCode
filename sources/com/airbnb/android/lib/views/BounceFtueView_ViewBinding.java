package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;

public class BounceFtueView_ViewBinding implements Unbinder {
    private BounceFtueView target;

    public BounceFtueView_ViewBinding(BounceFtueView target2) {
        this(target2, target2);
    }

    public BounceFtueView_ViewBinding(BounceFtueView target2, View source) {
        this.target = target2;
        target2.mOverlay = Utils.findRequiredView(source, C0880R.C0882id.ftue_overlay, "field 'mOverlay'");
        target2.mImageView = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.ftue_image, "field 'mImageView'", ImageView.class);
        target2.mHeaderTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.ftue_header_text, "field 'mHeaderTextView'", AirTextView.class);
        target2.mTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.ftue_text, "field 'mTextView'", AirTextView.class);
    }

    public void unbind() {
        BounceFtueView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mOverlay = null;
        target2.mImageView = null;
        target2.mHeaderTextView = null;
        target2.mTextView = null;
    }
}
