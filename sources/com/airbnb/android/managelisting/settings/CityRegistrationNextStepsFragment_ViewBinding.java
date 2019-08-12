package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationNextStepsFragment_ViewBinding implements Unbinder {
    private CityRegistrationNextStepsFragment target;
    private View view2131755476;

    public CityRegistrationNextStepsFragment_ViewBinding(final CityRegistrationNextStepsFragment target2, View source) {
        this.target = target2;
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C7368R.C7370id.sheet_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C7368R.C7370id.done_button, "field 'doneButton' and method 'onDone'");
        target2.doneButton = (AirButton) Utils.castView(view, C7368R.C7370id.done_button, "field 'doneButton'", AirButton.class);
        this.view2131755476 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDone();
            }
        });
    }

    public void unbind() {
        CityRegistrationNextStepsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sheetMarquee = null;
        target2.doneButton = null;
        this.view2131755476.setOnClickListener(null);
        this.view2131755476 = null;
    }
}
