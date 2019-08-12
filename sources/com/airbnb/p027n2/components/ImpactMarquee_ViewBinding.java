package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ImpactMarquee_ViewBinding */
public class ImpactMarquee_ViewBinding implements Unbinder {
    private ImpactMarquee target;

    public ImpactMarquee_ViewBinding(ImpactMarquee target2, View source) {
        this.target = target2;
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleTextView'", AirTextView.class);
        target2.subtitleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleTextView'", AirTextView.class);
    }

    public void unbind() {
        ImpactMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleTextView = null;
        target2.subtitleTextView = null;
    }
}
