package com.airbnb.android.core.views;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class LoopingViewPager_ViewBinding implements Unbinder {
    private LoopingViewPager target;

    public LoopingViewPager_ViewBinding(LoopingViewPager target2) {
        this(target2, target2);
    }

    public LoopingViewPager_ViewBinding(LoopingViewPager target2, View source) {
        this.target = target2;
        target2.mViewPager = (ClickableViewPager) Utils.findRequiredViewAsType(source, C0716R.C0718id.looping_view_pager, "field 'mViewPager'", ClickableViewPager.class);
        target2.mCurtainImageView = (ImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.looping_view_pager_image_curtain, "field 'mCurtainImageView'", ImageView.class);
        target2.mClickOverlay = Utils.findRequiredView(source, C0716R.C0718id.click_overlay, "field 'mClickOverlay'");
    }

    public void unbind() {
        LoopingViewPager target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mViewPager = null;
        target2.mCurtainImageView = null;
        target2.mClickOverlay = null;
    }
}
