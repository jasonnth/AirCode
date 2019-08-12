package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class TitleContentLayout_ViewBinding implements Unbinder {
    private TitleContentLayout target;

    public TitleContentLayout_ViewBinding(TitleContentLayout target2) {
        this(target2, target2);
    }

    public TitleContentLayout_ViewBinding(TitleContentLayout target2, View source) {
        this.target = target2;
        target2.mSubtitleImage = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.subtitle_image, "field 'mSubtitleImage'", HaloImageView.class);
        target2.mTitleText = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.layout_title, "field 'mTitleText'", AirTextView.class);
        target2.mContentText = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.layout_content, "field 'mContentText'", AirTextView.class);
        target2.mCustomLayout = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.custom_layout_placeholder, "field 'mCustomLayout'", FrameLayout.class);
    }

    public void unbind() {
        TitleContentLayout target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mSubtitleImage = null;
        target2.mTitleText = null;
        target2.mContentText = null;
        target2.mCustomLayout = null;
    }
}
