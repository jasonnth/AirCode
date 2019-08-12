package com.airbnb.p027n2.primitives;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.primitives.CircleCollageImageView_ViewBinding */
public class CircleCollageImageView_ViewBinding implements Unbinder {
    private CircleCollageImageView target;

    public CircleCollageImageView_ViewBinding(CircleCollageImageView target2, View source) {
        this.target = target2;
        target2.rightContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.right_container, "field 'rightContainer'", LinearLayout.class);
        target2.imageViews = Utils.listOf((AirImageView) Utils.findRequiredViewAsType(source, R.id.image_top_left, "field 'imageViews'", AirImageView.class), (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_top_right, "field 'imageViews'", AirImageView.class), (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_bottom_right, "field 'imageViews'", AirImageView.class), (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_bottom_left, "field 'imageViews'", AirImageView.class));
    }

    public void unbind() {
        CircleCollageImageView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rightContainer = null;
        target2.imageViews = null;
    }
}
