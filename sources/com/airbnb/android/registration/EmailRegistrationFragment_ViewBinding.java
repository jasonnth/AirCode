package com.airbnb.android.registration;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class EmailRegistrationFragment_ViewBinding implements Unbinder {
    private EmailRegistrationFragment target;
    private View view2131755494;
    private View view2131755495;

    public EmailRegistrationFragment_ViewBinding(final EmailRegistrationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C1562R.C1564id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.rootView = Utils.findRequiredView(source, C1562R.C1564id.registration_edit_email_root, "field 'rootView'");
        target2.emailInput = (SheetInputText) Utils.findRequiredViewAsType(source, C1562R.C1564id.registration_edit_email_input, "field 'emailInput'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C1562R.C1564id.registration_email_btn_next, "field 'nextButton' and method 'next'");
        target2.nextButton = (AirButton) Utils.castView(view, C1562R.C1564id.registration_email_btn_next, "field 'nextButton'", AirButton.class);
        this.view2131755495 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.next();
            }
        });
        View view2 = Utils.findRequiredView(source, C1562R.C1564id.registration_email_btn_swap_to_phone, "field 'swapToPhoneButton' and method 'swapToPhone'");
        target2.swapToPhoneButton = (AirButton) Utils.castView(view2, C1562R.C1564id.registration_email_btn_swap_to_phone, "field 'swapToPhoneButton'", AirButton.class);
        this.view2131755494 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.swapToPhone();
            }
        });
        target2.promoEmailOptInSwitch = (SwitchRow) Utils.findRequiredViewAsType(source, C1562R.C1564id.promo_email_opt_in_switch, "field 'promoEmailOptInSwitch'", SwitchRow.class);
    }

    public void unbind() {
        EmailRegistrationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.rootView = null;
        target2.emailInput = null;
        target2.nextButton = null;
        target2.swapToPhoneButton = null;
        target2.promoEmailOptInSwitch = null;
        this.view2131755495.setOnClickListener(null);
        this.view2131755495 = null;
        this.view2131755494.setOnClickListener(null);
        this.view2131755494 = null;
    }
}
