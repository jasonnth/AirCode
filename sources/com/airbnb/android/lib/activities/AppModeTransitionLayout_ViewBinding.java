package com.airbnb.android.lib.activities;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.lottie.LottieAnimationView;

public class AppModeTransitionLayout_ViewBinding implements Unbinder {
    private AppModeTransitionLayout target;

    public AppModeTransitionLayout_ViewBinding(AppModeTransitionLayout target2) {
        this(target2, target2);
    }

    public AppModeTransitionLayout_ViewBinding(AppModeTransitionLayout target2, View source) {
        this.target = target2;
        target2.contentLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.content_layout, "field 'contentLayout'", LinearLayout.class);
        target2.modeSwitchTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_mode_switch, "field 'modeSwitchTextView'", TextView.class);
        target2.animationView = (LottieAnimationView) Utils.findRequiredViewAsType(source, C0880R.C0882id.animation_view, "field 'animationView'", LottieAnimationView.class);
        Context context = source.getContext();
        target2.guestModeBackgroundColor = ContextCompat.getColor(context, C0880R.color.n2_white);
        target2.guestTextColor = ContextCompat.getColor(context, C0880R.color.n2_foggy);
        target2.hostModeBackgroundColor = ContextCompat.getColor(context, C0880R.color.n2_foggy);
        target2.hostTextColor = ContextCompat.getColor(context, C0880R.color.n2_white);
        target2.tripHostModeBackgroundColor = ContextCompat.getColor(context, C0880R.color.n2_white);
        target2.tripHostTextColor = ContextCompat.getColor(context, C0880R.color.n2_rausch);
    }

    public void unbind() {
        AppModeTransitionLayout target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.contentLayout = null;
        target2.modeSwitchTextView = null;
        target2.animationView = null;
    }
}
