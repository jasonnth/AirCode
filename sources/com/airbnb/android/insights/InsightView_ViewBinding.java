package com.airbnb.android.insights;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class InsightView_ViewBinding implements Unbinder {
    private InsightView target;

    public InsightView_ViewBinding(InsightView target2) {
        this(target2, target2);
    }

    public InsightView_ViewBinding(InsightView target2, View source) {
        this.target = target2;
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C6552R.C6554id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.descriptionRow = (TextRow) Utils.findRequiredViewAsType(source, C6552R.C6554id.description_text, "field 'descriptionRow'", TextRow.class);
        target2.increaseGraphView = (InsightIncreaseGraphView) Utils.findRequiredViewAsType(source, C6552R.C6554id.increase_graph, "field 'increaseGraphView'", InsightIncreaseGraphView.class);
        target2.trendGraphView = (InsightTrendGraphView) Utils.findRequiredViewAsType(source, C6552R.C6554id.trend_graph, "field 'trendGraphView'", InsightTrendGraphView.class);
        target2.primaryButton = (AirButton) Utils.findRequiredViewAsType(source, C6552R.C6554id.primary_button, "field 'primaryButton'", AirButton.class);
        target2.secondaryButton = (AirTextView) Utils.findRequiredViewAsType(source, C6552R.C6554id.secondary_button, "field 'secondaryButton'", AirTextView.class);
        target2.dismissTextView = (AirTextView) Utils.findRequiredViewAsType(source, C6552R.C6554id.dismiss_button, "field 'dismissTextView'", AirTextView.class);
    }

    public void unbind() {
        InsightView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.documentMarquee = null;
        target2.descriptionRow = null;
        target2.increaseGraphView = null;
        target2.trendGraphView = null;
        target2.primaryButton = null;
        target2.secondaryButton = null;
        target2.dismissTextView = null;
    }
}
