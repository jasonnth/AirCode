package com.airbnb.android.lib.postbooking;

import android.view.View;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class MTPostHomeBookingSplashFragment_ViewBinding extends MTBasePostHomeBookingFragment_ViewBinding {
    private MTPostHomeBookingSplashFragment target;

    public MTPostHomeBookingSplashFragment_ViewBinding(MTPostHomeBookingSplashFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'marquee'", DocumentMarquee.class);
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.background_image, "field 'imageView'", AirImageView.class);
    }

    public void unbind() {
        MTPostHomeBookingSplashFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.imageView = null;
        super.unbind();
    }
}
