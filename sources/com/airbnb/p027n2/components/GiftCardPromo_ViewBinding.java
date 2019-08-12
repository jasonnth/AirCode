package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.GiftCardPromo_ViewBinding */
public class GiftCardPromo_ViewBinding implements Unbinder {
    private GiftCardPromo target;

    public GiftCardPromo_ViewBinding(GiftCardPromo target2, View source) {
        this.target = target2;
        target2.promoImage = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'promoImage'", AirImageView.class);
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
    }

    public void unbind() {
        GiftCardPromo target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.promoImage = null;
        target2.bottomSpace = null;
    }
}
