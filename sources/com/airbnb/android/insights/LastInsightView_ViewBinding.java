package com.airbnb.android.insights;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class LastInsightView_ViewBinding implements Unbinder {
    private LastInsightView target;

    public LastInsightView_ViewBinding(LastInsightView target2) {
        this(target2, target2);
    }

    public LastInsightView_ViewBinding(LastInsightView target2, View source) {
        this.target = target2;
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C6552R.C6554id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.descriptionRow = (TextRow) Utils.findRequiredViewAsType(source, C6552R.C6554id.description_text, "field 'descriptionRow'", TextRow.class);
        target2.primaryButton = (AirButton) Utils.findRequiredViewAsType(source, C6552R.C6554id.primary_button, "field 'primaryButton'", AirButton.class);
    }

    public void unbind() {
        LastInsightView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.documentMarquee = null;
        target2.descriptionRow = null;
        target2.primaryButton = null;
    }
}
