package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class ReviewRecommendFragment_ViewBinding implements Unbinder {
    private ReviewRecommendFragment target;

    public ReviewRecommendFragment_ViewBinding(ReviewRecommendFragment target2, View source) {
        this.target = target2;
        target2.mRecommendTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recommend_title, "field 'mRecommendTitle'", TextView.class);
        target2.mRecommendSubtitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recommend_subtitle, "field 'mRecommendSubtitle'", TextView.class);
        target2.mRecommendYesHolder = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.recommend_yes_holder, "field 'mRecommendYesHolder'", ViewGroup.class);
        target2.mRecommendYes = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recommend_yes, "field 'mRecommendYes'", ImageView.class);
        target2.mRecommendNoHolder = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.recommend_no_holder, "field 'mRecommendNoHolder'", ViewGroup.class);
        target2.mRecommendNo = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recommend_no, "field 'mRecommendNo'", ImageView.class);
    }

    public void unbind() {
        ReviewRecommendFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mRecommendTitle = null;
        target2.mRecommendSubtitle = null;
        target2.mRecommendYesHolder = null;
        target2.mRecommendYes = null;
        target2.mRecommendNoHolder = null;
        target2.mRecommendNo = null;
    }
}
