package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.IconRow_ViewBinding */
public final class IconRow_ViewBinding implements Unbinder {
    private IconRow target;

    public IconRow_ViewBinding(IconRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.icon_row_title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.icon_row_subtitle, "field 'subtitleText'", AirTextView.class);
        target2.icon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon_row_icon, "field 'icon'", AirImageView.class);
        target2.badge = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon_row_badge, "field 'badge'", AirImageView.class);
    }

    public void unbind() {
        IconRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.icon = null;
        target2.badge = null;
    }
}
