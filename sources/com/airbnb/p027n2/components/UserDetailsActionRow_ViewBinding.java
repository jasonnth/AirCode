package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.n2.components.UserDetailsActionRow_ViewBinding */
public class UserDetailsActionRow_ViewBinding implements Unbinder {
    private UserDetailsActionRow target;

    public UserDetailsActionRow_ViewBinding(UserDetailsActionRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle_text, "field 'subtitleText'", AirTextView.class);
        target2.label = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'label'", AirTextView.class);
        target2.extraText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.extra_text, "field 'extraText'", AirTextView.class);
        target2.userImage = (HaloImageView) Utils.findRequiredViewAsType(source, R.id.user_image, "field 'userImage'", HaloImageView.class);
        target2.homeImage = (AirImageView) Utils.findRequiredViewAsType(source, R.id.home_image, "field 'homeImage'", AirImageView.class);
        target2.userImageContainer = (FrameLayout) Utils.findRequiredViewAsType(source, R.id.user_image_container, "field 'userImageContainer'", FrameLayout.class);
        target2.userStatusIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.user_status_icon, "field 'userStatusIcon'", AirImageView.class);
    }

    public void unbind() {
        UserDetailsActionRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.label = null;
        target2.extraText = null;
        target2.userImage = null;
        target2.homeImage = null;
        target2.userImageContainer = null;
        target2.userStatusIcon = null;
    }
}
