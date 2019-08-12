package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.PromotionMarquee_ViewBinding */
public class PromotionMarquee_ViewBinding implements Unbinder {
    private PromotionMarquee target;

    public PromotionMarquee_ViewBinding(PromotionMarquee target2, View source) {
        this.target = target2;
        target2.bannerTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.banner_text, "field 'bannerTextView'", AirTextView.class);
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.capitionTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.caption_text, "field 'capitionTextView'", AirTextView.class);
    }

    public void unbind() {
        PromotionMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.bannerTextView = null;
        target2.titleTextView = null;
        target2.capitionTextView = null;
    }
}
