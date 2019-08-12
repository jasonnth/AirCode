package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.LabelDocumentMarquee_ViewBinding */
public class LabelDocumentMarquee_ViewBinding implements Unbinder {
    private LabelDocumentMarquee target;

    public LabelDocumentMarquee_ViewBinding(LabelDocumentMarquee target2, View source) {
        this.target = target2;
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.captionTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.caption_text, "field 'captionTextView'", AirTextView.class);
        target2.labelTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label_text, "field 'labelTextView'", AirTextView.class);
    }

    public void unbind() {
        LabelDocumentMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleTextView = null;
        target2.captionTextView = null;
        target2.labelTextView = null;
    }
}
