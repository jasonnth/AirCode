package com.airbnb.p027n2.components.decide.select;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectHomeSummaryRow_ViewBinding */
public class SelectHomeSummaryRow_ViewBinding implements Unbinder {
    private SelectHomeSummaryRow target;

    public SelectHomeSummaryRow_ViewBinding(SelectHomeSummaryRow target2, View source) {
        this.target = target2;
        target2.guestTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.guests_text, "field 'guestTextView'", AirTextView.class);
        target2.bedroomsTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.bedrooms_text, "field 'bedroomsTextView'", AirTextView.class);
        target2.bedsTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.beds_text, "field 'bedsTextView'", AirTextView.class);
        target2.bathroomsTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.baths_text, "field 'bathroomsTextView'", AirTextView.class);
    }

    public void unbind() {
        SelectHomeSummaryRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.guestTextView = null;
        target2.bedroomsTextView = null;
        target2.bedsTextView = null;
        target2.bathroomsTextView = null;
    }
}
