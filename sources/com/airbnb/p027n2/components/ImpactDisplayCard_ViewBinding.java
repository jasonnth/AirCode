package com.airbnb.p027n2.components;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.ImpactDisplayCardContentContainer;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ImpactDisplayCard_ViewBinding */
public class ImpactDisplayCard_ViewBinding implements Unbinder {
    private ImpactDisplayCard target;

    public ImpactDisplayCard_ViewBinding(ImpactDisplayCard target2, View source) {
        this.target = target2;
        target2.clickContainer = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.click_container, "field 'clickContainer'", ViewGroup.class);
        target2.contentContainer = (ImpactDisplayCardContentContainer) Utils.findRequiredViewAsType(source, R.id.content_container, "field 'contentContainer'", ImpactDisplayCardContentContainer.class);
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.subtitleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle_text, "field 'subtitleTextView'", AirTextView.class);
        target2.labelTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'labelTextView'", AirTextView.class);
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        ImpactDisplayCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.clickContainer = null;
        target2.contentContainer = null;
        target2.imageView = null;
        target2.titleTextView = null;
        target2.subtitleTextView = null;
        target2.labelTextView = null;
        target2.bottomSpace = null;
        target2.divider = null;
    }
}
