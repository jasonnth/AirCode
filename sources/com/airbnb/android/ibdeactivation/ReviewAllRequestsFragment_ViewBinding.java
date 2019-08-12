package com.airbnb.android.ibdeactivation;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class ReviewAllRequestsFragment_ViewBinding implements Unbinder {
    private ReviewAllRequestsFragment target;
    private View view2131755415;
    private View view2131755528;

    public ReviewAllRequestsFragment_ViewBinding(final ReviewAllRequestsFragment target2, View source) {
        this.target = target2;
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6454R.C6456id.review_marquee, "field 'marquee'", SheetMarquee.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6454R.C6456id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C6454R.C6456id.review_requests_button, "field 'reviewRequestsButton' and method 'clickReviewRequests'");
        target2.reviewRequestsButton = (AirButton) Utils.castView(view, C6454R.C6456id.review_requests_button, "field 'reviewRequestsButton'", AirButton.class);
        this.view2131755528 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickReviewRequests();
            }
        });
        View view2 = Utils.findRequiredView(source, C6454R.C6456id.cancel_button, "field 'cancelButton' and method 'clickCancel'");
        target2.cancelButton = (AirButton) Utils.castView(view2, C6454R.C6456id.cancel_button, "field 'cancelButton'", AirButton.class);
        this.view2131755415 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickCancel();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6454R.C6456id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C6454R.C6456id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
    }

    public void unbind() {
        ReviewAllRequestsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.recyclerView = null;
        target2.reviewRequestsButton = null;
        target2.cancelButton = null;
        target2.toolbar = null;
        target2.scrollView = null;
        this.view2131755528.setOnClickListener(null);
        this.view2131755528 = null;
        this.view2131755415.setOnClickListener(null);
        this.view2131755415 = null;
    }
}
