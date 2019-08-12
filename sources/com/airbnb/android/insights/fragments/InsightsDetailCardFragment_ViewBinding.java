package com.airbnb.android.insights.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.insights.C6552R;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class InsightsDetailCardFragment_ViewBinding implements Unbinder {
    private InsightsDetailCardFragment target;
    private View view2131755562;
    private View view2131755563;
    private View view2131755564;

    public InsightsDetailCardFragment_ViewBinding(final InsightsDetailCardFragment target2, View source) {
        this.target = target2;
        target2.explanationTextRow = (TextRow) Utils.findRequiredViewAsType(source, C6552R.C6554id.explanation_text, "field 'explanationTextRow'", TextRow.class);
        View view = Utils.findRequiredView(source, C6552R.C6554id.action_button, "field 'actionButton' and method 'onActionButtonClicked'");
        target2.actionButton = (AirButton) Utils.castView(view, C6552R.C6554id.action_button, "field 'actionButton'", AirButton.class);
        this.view2131755562 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onActionButtonClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C6552R.C6554id.undo_button, "field 'undoButton' and method 'onUndoButtonClicked'");
        target2.undoButton = (AirButton) Utils.castView(view2, C6552R.C6554id.undo_button, "field 'undoButton'", AirButton.class);
        this.view2131755563 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onUndoButtonClicked();
            }
        });
        View view3 = Utils.findRequiredView(source, C6552R.C6554id.finish_button, "field 'finishButton' and method 'onFinishButtonClicked'");
        target2.finishButton = (AirButton) Utils.castView(view3, C6552R.C6554id.finish_button, "field 'finishButton'", AirButton.class);
        this.view2131755564 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onFinishButtonClicked();
            }
        });
        target2.container = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C6552R.C6554id.insights_container, "field 'container'", CoordinatorLayout.class);
        target2.fragmentHolder = (FrameLayout) Utils.findRequiredViewAsType(source, C6552R.C6554id.fragment_holder, "field 'fragmentHolder'", FrameLayout.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C6552R.C6554id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.infoContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C6552R.C6554id.info_container, "field 'infoContainer'", LinearLayout.class);
    }

    public void unbind() {
        InsightsDetailCardFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.explanationTextRow = null;
        target2.actionButton = null;
        target2.undoButton = null;
        target2.finishButton = null;
        target2.container = null;
        target2.fragmentHolder = null;
        target2.loadingView = null;
        target2.infoContainer = null;
        this.view2131755562.setOnClickListener(null);
        this.view2131755562 = null;
        this.view2131755563.setOnClickListener(null);
        this.view2131755563 = null;
        this.view2131755564.setOnClickListener(null);
        this.view2131755564 = null;
    }
}
