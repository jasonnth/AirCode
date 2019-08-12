package com.airbnb.p027n2.components;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.HeroMarquee_ViewBinding */
public class HeroMarquee_ViewBinding implements Unbinder {
    private HeroMarquee target;

    public HeroMarquee_ViewBinding(HeroMarquee target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.contentView = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.content_container, "field 'contentView'", ViewGroup.class);
        target2.icon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", AirImageView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.captionText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.caption_text, "field 'captionText'", AirTextView.class);
        target2.buttonFirst = (AirButton) Utils.findRequiredViewAsType(source, R.id.button_first, "field 'buttonFirst'", AirButton.class);
        target2.buttonSecond = (AirButton) Utils.findRequiredViewAsType(source, R.id.button_second, "field 'buttonSecond'", AirButton.class);
        target2.gradient = Utils.findRequiredView(source, R.id.gradient, "field 'gradient'");
    }

    public void unbind() {
        HeroMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.contentView = null;
        target2.icon = null;
        target2.titleText = null;
        target2.captionText = null;
        target2.buttonFirst = null;
        target2.buttonSecond = null;
        target2.gradient = null;
    }
}
