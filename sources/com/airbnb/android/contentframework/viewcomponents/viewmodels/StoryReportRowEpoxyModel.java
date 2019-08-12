package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.AirTextView;

public abstract class StoryReportRowEpoxyModel extends AirEpoxyModel<AirTextView> {
    private static final boolean DEFAULT_ENABLED = true;
    OnClickListener clickListener;
    boolean enabled = DEFAULT_ENABLED;
    int textRes;

    public void bind(AirTextView view) {
        super.bind(view);
        view.setText(this.textRes);
        view.setEnabled(this.enabled);
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(AirTextView view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
