package com.airbnb.android.cityregistration.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationTermsAndConditionsFragment_ViewBinding extends CityRegistrationBaseSubmissionFragment_ViewBinding {
    private CityRegistrationTermsAndConditionsFragment target;
    private View view2131755475;
    private View view2131755482;

    public CityRegistrationTermsAndConditionsFragment_ViewBinding(final CityRegistrationTermsAndConditionsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        View view = Utils.findRequiredView(source, C5630R.C5632id.lys_save_button, "field 'lysSaveButton' and method 'lysOnSubmit'");
        target2.lysSaveButton = (AirButton) Utils.castView(view, C5630R.C5632id.lys_save_button, "field 'lysSaveButton'", AirButton.class);
        this.view2131755482 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.lysOnSubmit();
            }
        });
        target2.toggleActionRow = (ToggleActionRow) Utils.findRequiredViewAsType(source, C5630R.C5632id.tac_toggle, "field 'toggleActionRow'", ToggleActionRow.class);
        View view2 = Utils.findRequiredView(source, C5630R.C5632id.save_button, "method 'onSubmit'");
        this.view2131755475 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSubmit();
            }
        });
    }

    public void unbind() {
        CityRegistrationTermsAndConditionsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.lysSaveButton = null;
        target2.toggleActionRow = null;
        this.view2131755482.setOnClickListener(null);
        this.view2131755482 = null;
        this.view2131755475.setOnClickListener(null);
        this.view2131755475 = null;
        super.unbind();
    }
}
