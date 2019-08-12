package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;

public class ReviewThumbFragment_ViewBinding implements Unbinder {
    private ReviewThumbFragment target;
    private View view2131756705;
    private View view2131756707;

    public ReviewThumbFragment_ViewBinding(final ReviewThumbFragment target2, View source) {
        this.target = target2;
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.document_marquee, "field 'marquee'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.recommend_no, "method 'clickRecommendNo'");
        this.view2131756705 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickRecommendNo();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.recommend_yes, "method 'clickRecommendYes'");
        this.view2131756707 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickRecommendYes();
            }
        });
    }

    public void unbind() {
        ReviewThumbFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        this.view2131756705.setOnClickListener(null);
        this.view2131756705 = null;
        this.view2131756707.setOnClickListener(null);
        this.view2131756707 = null;
    }
}
