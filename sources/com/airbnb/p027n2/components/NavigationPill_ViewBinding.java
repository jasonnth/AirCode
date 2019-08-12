package com.airbnb.p027n2.components;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.NavigationPill_ViewBinding */
public class NavigationPill_ViewBinding implements Unbinder {
    private NavigationPill target;

    public NavigationPill_ViewBinding(NavigationPill target2, View source) {
        this.target = target2;
        target2.leftButtonContainer = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.left_button_container, "field 'leftButtonContainer'", ViewGroup.class);
        target2.leftButton = (AirTextView) Utils.findRequiredViewAsType(source, R.id.left_button, "field 'leftButton'", AirTextView.class);
        target2.leftButtonBadge = (AirTextView) Utils.findRequiredViewAsType(source, R.id.left_button_badge, "field 'leftButtonBadge'", AirTextView.class);
        target2.leftButtonIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.left_button_icon, "field 'leftButtonIcon'", AirImageView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.rightButtonContainer = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.right_button_container, "field 'rightButtonContainer'", ViewGroup.class);
        target2.rightButton = (AirTextView) Utils.findRequiredViewAsType(source, R.id.right_button, "field 'rightButton'", AirTextView.class);
        target2.rightButtonBadge = (AirTextView) Utils.findRequiredViewAsType(source, R.id.right_button_badge, "field 'rightButtonBadge'", AirTextView.class);
        target2.rightButtonIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.right_button_icon, "field 'rightButtonIcon'", AirImageView.class);
        target2.middleButtonContainer = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.middle_button_container, "field 'middleButtonContainer'", ViewGroup.class);
        target2.middleButton = (AirTextView) Utils.findRequiredViewAsType(source, R.id.middle_button, "field 'middleButton'", AirTextView.class);
        target2.middleButtonBadge = (AirTextView) Utils.findRequiredViewAsType(source, R.id.middle_button_badge, "field 'middleButtonBadge'", AirTextView.class);
        target2.middleButtonIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.middle_button_icon, "field 'middleButtonIcon'", AirImageView.class);
        target2.middleButtonDivider = Utils.findRequiredView(source, R.id.middle_button_divider, "field 'middleButtonDivider'");
    }

    public void unbind() {
        NavigationPill target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.leftButtonContainer = null;
        target2.leftButton = null;
        target2.leftButtonBadge = null;
        target2.leftButtonIcon = null;
        target2.divider = null;
        target2.rightButtonContainer = null;
        target2.rightButton = null;
        target2.rightButtonBadge = null;
        target2.rightButtonIcon = null;
        target2.middleButtonContainer = null;
        target2.middleButton = null;
        target2.middleButtonBadge = null;
        target2.middleButtonIcon = null;
        target2.middleButtonDivider = null;
    }
}
