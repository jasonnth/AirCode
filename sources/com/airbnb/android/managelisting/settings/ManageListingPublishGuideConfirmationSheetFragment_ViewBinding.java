package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.HeroMarquee;

public class ManageListingPublishGuideConfirmationSheetFragment_ViewBinding implements Unbinder {
    private ManageListingPublishGuideConfirmationSheetFragment target;

    public ManageListingPublishGuideConfirmationSheetFragment_ViewBinding(ManageListingPublishGuideConfirmationSheetFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C7368R.C7370id.hero_marquee, "field 'heroMarquee'", HeroMarquee.class);
    }

    public void unbind() {
        ManageListingPublishGuideConfirmationSheetFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.heroMarquee = null;
    }
}
