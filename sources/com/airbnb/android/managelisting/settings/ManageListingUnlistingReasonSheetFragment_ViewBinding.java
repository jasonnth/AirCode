package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.HeroMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class ManageListingUnlistingReasonSheetFragment_ViewBinding implements Unbinder {
    private ManageListingUnlistingReasonSheetFragment target;

    public ManageListingUnlistingReasonSheetFragment_ViewBinding(ManageListingUnlistingReasonSheetFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C7368R.C7370id.hero_marquee, "field 'heroMarquee'", HeroMarquee.class);
        target2.firstButton = (AirButton) Utils.findRequiredViewAsType(source, C7368R.C7370id.button_first, "field 'firstButton'", AirButton.class);
        target2.secondButton = (AirButton) Utils.findRequiredViewAsType(source, C7368R.C7370id.button_second, "field 'secondButton'", AirButton.class);
        target2.captionText = (AirTextView) Utils.findRequiredViewAsType(source, C7368R.C7370id.caption_text, "field 'captionText'", AirTextView.class);
    }

    public void unbind() {
        ManageListingUnlistingReasonSheetFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.heroMarquee = null;
        target2.firstButton = null;
        target2.secondButton = null;
        target2.captionText = null;
    }
}
