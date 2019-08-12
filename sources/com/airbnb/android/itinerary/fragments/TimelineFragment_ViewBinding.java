package com.airbnb.android.itinerary.fragments;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;

public class TimelineFragment_ViewBinding implements Unbinder {
    private TimelineFragment target;
    private View view2131755534;

    public TimelineFragment_ViewBinding(final TimelineFragment target2, View source) {
        this.target = target2;
        target2.pendingHeader = (LinearLayout) Utils.findRequiredViewAsType(source, C5755R.C5757id.pending_header, "field 'pendingHeader'", LinearLayout.class);
        target2.pendingTitle = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.pending_title, "field 'pendingTitle'", AirTextView.class);
        target2.pendingAction = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.pending_action, "field 'pendingAction'", AirTextView.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C5755R.C5757id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.emptyState = (LinearLayout) Utils.findRequiredViewAsType(source, C5755R.C5757id.empty_state, "field 'emptyState'", LinearLayout.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C5755R.C5757id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.animationImage = (LottieAnimationView) Utils.findRequiredViewAsType(source, C5755R.C5757id.animation_image, "field 'animationImage'", LottieAnimationView.class);
        View view = Utils.findRequiredView(source, C5755R.C5757id.empty_state_text, "method 'onEmptyStateClicked'");
        this.view2131755534 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onEmptyStateClicked();
            }
        });
    }

    public void unbind() {
        TimelineFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.pendingHeader = null;
        target2.pendingTitle = null;
        target2.pendingAction = null;
        target2.recyclerView = null;
        target2.emptyState = null;
        target2.loadingView = null;
        target2.animationImage = null;
        this.view2131755534.setOnClickListener(null);
        this.view2131755534 = null;
    }
}
