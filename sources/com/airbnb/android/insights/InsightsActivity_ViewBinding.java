package com.airbnb.android.insights;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;

public class InsightsActivity_ViewBinding implements Unbinder {
    private InsightsActivity target;

    public InsightsActivity_ViewBinding(InsightsActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public InsightsActivity_ViewBinding(InsightsActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loading_view, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        InsightsActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.loadingView = null;
    }
}
