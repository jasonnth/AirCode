package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.NuxCoverCard_ViewBinding */
public class NuxCoverCard_ViewBinding implements Unbinder {
    private NuxCoverCard target;

    public NuxCoverCard_ViewBinding(NuxCoverCard target2, View source) {
        this.target = target2;
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, R.id.nux_cover_card_image, "field 'image'", AirImageView.class);
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.nux_cover_card_title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.nux_cover_card_subtitle, "field 'subtitle'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.nux_cover_card_button, "field 'button'", AirButton.class);
    }

    public void unbind() {
        NuxCoverCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.image = null;
        target2.title = null;
        target2.subtitle = null;
        target2.button = null;
    }
}
