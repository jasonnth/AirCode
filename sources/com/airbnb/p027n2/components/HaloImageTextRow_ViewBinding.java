package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.n2.components.HaloImageTextRow_ViewBinding */
public class HaloImageTextRow_ViewBinding implements Unbinder {
    private HaloImageTextRow target;

    public HaloImageTextRow_ViewBinding(HaloImageTextRow target2, View source) {
        this.target = target2;
        target2.firstRowText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.first_row_text, "field 'firstRowText'", AirTextView.class);
        target2.secondRowText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.second_row_text, "field 'secondRowText'", AirTextView.class);
        target2.haloImage = (HaloImageView) Utils.findRequiredViewAsType(source, R.id.halo_image, "field 'haloImage'", HaloImageView.class);
    }

    public void unbind() {
        HaloImageTextRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.firstRowText = null;
        target2.secondRowText = null;
        target2.haloImage = null;
    }
}
