package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.MovablePinMap;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class ManageListingExactLocationFragment_ViewBinding implements Unbinder {
    private ManageListingExactLocationFragment target;
    private View view2131755498;

    public ManageListingExactLocationFragment_ViewBinding(final ManageListingExactLocationFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7368R.C7370id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.movablePinMap = (MovablePinMap) Utils.findRequiredViewAsType(source, C7368R.C7370id.movable_pin_map, "field 'movablePinMap'", MovablePinMap.class);
        View view = Utils.findRequiredView(source, C7368R.C7370id.save_button, "field 'saveButton' and method 'onSave'");
        target2.saveButton = (AirButton) Utils.castView(view, C7368R.C7370id.save_button, "field 'saveButton'", AirButton.class);
        this.view2131755498 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSave();
            }
        });
    }

    public void unbind() {
        ManageListingExactLocationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.documentMarquee = null;
        target2.movablePinMap = null;
        target2.saveButton = null;
        this.view2131755498.setOnClickListener(null);
        this.view2131755498 = null;
    }
}
