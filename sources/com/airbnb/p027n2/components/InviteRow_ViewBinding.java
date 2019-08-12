package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.n2.components.InviteRow_ViewBinding */
public final class InviteRow_ViewBinding implements Unbinder {
    private InviteRow target;

    public InviteRow_ViewBinding(InviteRow target2, View source) {
        this.target = target2;
        target2.photo = (HaloImageView) Utils.findRequiredViewAsType(source, R.id.photo, "field 'photo'", HaloImageView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.invite_row_title, "field 'titleText'", AirTextView.class);
        target2.descriptionText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.invite_row_description, "field 'descriptionText'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.invite_row_button, "field 'button'", AirButton.class);
    }

    public void unbind() {
        InviteRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.photo = null;
        target2.titleText = null;
        target2.descriptionText = null;
        target2.button = null;
    }
}
