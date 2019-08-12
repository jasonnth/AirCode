package com.airbnb.p027n2.primitives;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.WishListIconView_ViewBinding */
public class WishListIconView_ViewBinding implements Unbinder {
    private WishListIconView target;

    public WishListIconView_ViewBinding(WishListIconView target2, View source) {
        this.target = target2;
        target2.removedAnimation = (LottieAnimationView) Utils.findRequiredViewAsType(source, R.id.wl_removed_animation, "field 'removedAnimation'", LottieAnimationView.class);
        target2.savedAnimation = (LottieAnimationView) Utils.findRequiredViewAsType(source, R.id.wl_saved_animation, "field 'savedAnimation'", LottieAnimationView.class);
    }

    public void unbind() {
        WishListIconView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.removedAnimation = null;
        target2.savedAnimation = null;
    }
}
