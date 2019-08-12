package com.airbnb.android.insights.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.insights.C6552R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;

public class InsightsFragment_ViewBinding implements Unbinder {
    private InsightsFragment target;
    private View view2131755557;

    public InsightsFragment_ViewBinding(final InsightsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6552R.C6554id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6552R.C6554id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C6552R.C6554id.disclaimer_text, "field 'disclaimerText' and method 'onDisclaimerTextClicked'");
        target2.disclaimerText = (AirTextView) Utils.castView(view, C6552R.C6554id.disclaimer_text, "field 'disclaimerText'", AirTextView.class);
        this.view2131755557 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDisclaimerTextClicked();
            }
        });
        target2.container = (ViewGroup) Utils.findRequiredViewAsType(source, C6552R.C6554id.insights_container, "field 'container'", ViewGroup.class);
        target2.loader = (LoadingView) Utils.findRequiredViewAsType(source, C6552R.C6554id.loader, "field 'loader'", LoadingView.class);
    }

    public void unbind() {
        InsightsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.disclaimerText = null;
        target2.container = null;
        target2.loader = null;
        this.view2131755557.setOnClickListener(null);
        this.view2131755557 = null;
    }
}
