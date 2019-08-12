package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.RecommendationCard_ViewBinding */
public class RecommendationCard_ViewBinding implements Unbinder {
    private RecommendationCard target;

    public RecommendationCard_ViewBinding(RecommendationCard target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.subText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtext, "field 'subText'", AirTextView.class);
        target2.descriptionTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.description_text, "field 'descriptionTextView'", AirTextView.class);
    }

    public void unbind() {
        RecommendationCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.titleTextView = null;
        target2.subText = null;
        target2.descriptionTextView = null;
    }
}
