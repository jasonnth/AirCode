package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.EmptyStateCard_ViewBinding */
public class EmptyStateCard_ViewBinding implements Unbinder {
    private EmptyStateCard target;

    public EmptyStateCard_ViewBinding(EmptyStateCard target2, View source) {
        this.target = target2;
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, R.id.empty_state_card_image, "field 'image'", AirImageView.class);
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.empty_state_card_text, "field 'textView'", AirTextView.class);
    }

    public void unbind() {
        EmptyStateCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.image = null;
        target2.textView = null;
    }
}
