package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class AnnotatedAirProgressBar_ViewBinding implements Unbinder {
    private AnnotatedAirProgressBar target;

    public AnnotatedAirProgressBar_ViewBinding(AnnotatedAirProgressBar target2) {
        this(target2, target2);
    }

    public AnnotatedAirProgressBar_ViewBinding(AnnotatedAirProgressBar target2, View source) {
        this.target = target2;
        target2.mTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.title, "field 'mTitle'", TextView.class);
        target2.mProgressText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.progress_text, "field 'mProgressText'", TextView.class);
        target2.mProgressBar = (AirProgressBar) Utils.findRequiredViewAsType(source, C0880R.C0882id.progress_bar, "field 'mProgressBar'", AirProgressBar.class);
        target2.mGoalPopupContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.goal_popup_container, "field 'mGoalPopupContainer'", LinearLayout.class);
        target2.mGoalPopupText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.goal_popup_text, "field 'mGoalPopupText'", TextView.class);
    }

    public void unbind() {
        AnnotatedAirProgressBar target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTitle = null;
        target2.mProgressText = null;
        target2.mProgressBar = null;
        target2.mGoalPopupContainer = null;
        target2.mGoalPopupText = null;
    }
}
