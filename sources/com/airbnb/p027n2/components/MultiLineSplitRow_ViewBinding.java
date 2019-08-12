package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.MultiLineSplitRow_ViewBinding */
public class MultiLineSplitRow_ViewBinding implements Unbinder {
    private MultiLineSplitRow target;

    public MultiLineSplitRow_ViewBinding(MultiLineSplitRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.info_row_title, "field 'titleText'", AirTextView.class);
        target2.infoText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.info_row_info, "field 'infoText'", AirTextView.class);
    }

    public void unbind() {
        MultiLineSplitRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.infoText = null;
    }
}
