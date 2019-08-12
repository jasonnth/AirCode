package com.airbnb.android.registration;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class PhoneNumberRegistrationFragment_ViewBinding implements Unbinder {
    private PhoneNumberRegistrationFragment target;
    private View view2131755546;
    private View view2131755547;

    public PhoneNumberRegistrationFragment_ViewBinding(final PhoneNumberRegistrationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C1562R.C1564id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.rootView = Utils.findRequiredView(source, C1562R.C1564id.registration_phone_number_root, "field 'rootView'");
        target2.phoneNumberInputSheet = (PhoneNumberInputSheet) Utils.findRequiredViewAsType(source, C1562R.C1564id.phone_number_input_sheet, "field 'phoneNumberInputSheet'", PhoneNumberInputSheet.class);
        View view = Utils.findRequiredView(source, C1562R.C1564id.registration_phone_btn_next, "field 'nextButton' and method 'onNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C1562R.C1564id.registration_phone_btn_next, "field 'nextButton'", AirButton.class);
        this.view2131755547 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNext();
            }
        });
        View view2 = Utils.findRequiredView(source, C1562R.C1564id.registration_phone_btn_swap_to_email, "method 'swapToEmail'");
        this.view2131755546 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.swapToEmail();
            }
        });
    }

    public void unbind() {
        PhoneNumberRegistrationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.rootView = null;
        target2.phoneNumberInputSheet = null;
        target2.nextButton = null;
        this.view2131755547.setOnClickListener(null);
        this.view2131755547 = null;
        this.view2131755546.setOnClickListener(null);
        this.view2131755546 = null;
    }
}
