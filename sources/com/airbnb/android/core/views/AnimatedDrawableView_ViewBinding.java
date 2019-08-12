package com.airbnb.android.core.views;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class AnimatedDrawableView_ViewBinding implements Unbinder {
    private AnimatedDrawableView target;

    public AnimatedDrawableView_ViewBinding(AnimatedDrawableView target2) {
        this(target2, target2);
    }

    public AnimatedDrawableView_ViewBinding(AnimatedDrawableView target2, View source) {
        this.target = target2;
        target2.imageView = (ImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.anim_image, "field 'imageView'", ImageView.class);
    }

    public void unbind() {
        AnimatedDrawableView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
    }
}
