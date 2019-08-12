package com.airbnb.android.lib.businesstravel;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.EditorialMarquee;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class BusinessTravelWelcomeFragment_ViewBinding implements Unbinder {
    private BusinessTravelWelcomeFragment target;

    public BusinessTravelWelcomeFragment_ViewBinding(BusinessTravelWelcomeFragment target2, View source) {
        this.target = target2;
        target2.bottomBar = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.bottom_bar, "field 'bottomBar'", LinearLayout.class);
        target2.gotItButton = (AirButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.got_it_button, "field 'gotItButton'", AirButton.class);
        target2.textRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.business_travel_welcome_legal_disclaimer, "field 'textRow'", SimpleTextRow.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.editorialMarquee = (EditorialMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.editorial_marquee, "field 'editorialMarquee'", EditorialMarquee.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C0880R.C0882id.loading_view, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        BusinessTravelWelcomeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.bottomBar = null;
        target2.gotItButton = null;
        target2.textRow = null;
        target2.toolbar = null;
        target2.editorialMarquee = null;
        target2.loadingView = null;
    }
}
