package com.airbnb.android.places.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class LocalPerspectiveView_ViewBinding implements Unbinder {
    private LocalPerspectiveView target;

    public LocalPerspectiveView_ViewBinding(LocalPerspectiveView target2) {
        this(target2, target2);
    }

    public LocalPerspectiveView_ViewBinding(LocalPerspectiveView target2, View source) {
        this.target = target2;
        target2.sectionTitleView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.section_title, "field 'sectionTitleView'", AirTextView.class);
        target2.descriptionView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.description, "field 'descriptionView'", AirTextView.class);
        target2.userImageView = (HaloImageView) Utils.findRequiredViewAsType(source, C7627R.C7629id.user_image, "field 'userImageView'", HaloImageView.class);
        target2.userInfoView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.user_info, "field 'userInfoView'", AirTextView.class);
        target2.userInfoGenericView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.user_info_generic, "field 'userInfoGenericView'", AirTextView.class);
        target2.userInfoDetailedView = Utils.findRequiredView(source, C7627R.C7629id.user_info_detailed, "field 'userInfoDetailedView'");
        target2.sectionDivider = Utils.findRequiredView(source, C7627R.C7629id.section_divider, "field 'sectionDivider'");
    }

    public void unbind() {
        LocalPerspectiveView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sectionTitleView = null;
        target2.descriptionView = null;
        target2.userImageView = null;
        target2.userInfoView = null;
        target2.userInfoGenericView = null;
        target2.userInfoDetailedView = null;
        target2.sectionDivider = null;
    }
}
