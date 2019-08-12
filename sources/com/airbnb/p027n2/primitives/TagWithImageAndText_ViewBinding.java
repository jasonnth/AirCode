package com.airbnb.p027n2.primitives;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.primitives.TagWithImageAndText_ViewBinding */
public class TagWithImageAndText_ViewBinding implements Unbinder {
    private TagWithImageAndText target;

    public TagWithImageAndText_ViewBinding(TagWithImageAndText target2, View source) {
        this.target = target2;
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon, "field 'image'", AirImageView.class);
        target2.label = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'label'", AirTextView.class);
    }

    public void unbind() {
        TagWithImageAndText target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.image = null;
        target2.label = null;
    }
}
