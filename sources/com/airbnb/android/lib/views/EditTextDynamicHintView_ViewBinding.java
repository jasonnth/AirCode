package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.ClickableViewPager;
import com.airbnb.android.lib.C0880R;

public final class EditTextDynamicHintView_ViewBinding implements Unbinder {
    private EditTextDynamicHintView target;

    public EditTextDynamicHintView_ViewBinding(EditTextDynamicHintView target2) {
        this(target2, target2);
    }

    public EditTextDynamicHintView_ViewBinding(EditTextDynamicHintView target2, View source) {
        this.target = target2;
        target2.mHintViewPager = (ClickableViewPager) Utils.findRequiredViewAsType(source, C0880R.C0882id.hint_view_pager, "field 'mHintViewPager'", ClickableViewPager.class);
        target2.mPrevHintButton = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.left_arrow, "field 'mPrevHintButton'", ImageView.class);
        target2.mNextHintButton = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.right_arrow, "field 'mNextHintButton'", ImageView.class);
    }

    public void unbind() {
        EditTextDynamicHintView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mHintViewPager = null;
        target2.mPrevHintButton = null;
        target2.mNextHintButton = null;
    }
}
