package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ProfileLinkRow_ViewBinding */
public class ProfileLinkRow_ViewBinding implements Unbinder {
    private ProfileLinkRow target;

    public ProfileLinkRow_ViewBinding(ProfileLinkRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleText'", AirTextView.class);
        target2.linkText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.link_text, "field 'linkText'", AirTextView.class);
    }

    public void unbind() {
        ProfileLinkRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.linkText = null;
    }
}
