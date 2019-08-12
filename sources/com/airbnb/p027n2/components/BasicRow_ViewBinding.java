package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BasicRow_ViewBinding */
public final class BasicRow_ViewBinding implements Unbinder {
    private BasicRow target;

    public BasicRow_ViewBinding(BasicRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.basic_row_title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.basic_row_subtitle, "field 'subtitleText'", AirTextView.class);
    }

    public void unbind() {
        BasicRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
    }
}
