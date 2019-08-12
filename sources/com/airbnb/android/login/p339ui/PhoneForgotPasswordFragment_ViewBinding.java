package com.airbnb.android.login.p339ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.login.C7331R;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment_ViewBinding */
public class PhoneForgotPasswordFragment_ViewBinding implements Unbinder {
    private PhoneForgotPasswordFragment target;
    private View view2131755501;

    public PhoneForgotPasswordFragment_ViewBinding(final PhoneForgotPasswordFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C7331R.C7333id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.phoneNumberInputSheet = (PhoneNumberInputSheet) Utils.findRequiredViewAsType(source, C7331R.C7333id.phone_number_input_sheet, "field 'phoneNumberInputSheet'", PhoneNumberInputSheet.class);
        View view = Utils.findRequiredView(source, C7331R.C7333id.forgot_password_button, "field 'submitButton' and method 'submit'");
        target2.submitButton = (AirButton) Utils.castView(view, C7331R.C7333id.forgot_password_button, "field 'submitButton'", AirButton.class);
        this.view2131755501 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.submit();
            }
        });
    }

    public void unbind() {
        PhoneForgotPasswordFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.phoneNumberInputSheet = null;
        target2.submitButton = null;
        this.view2131755501.setOnClickListener(null);
        this.view2131755501 = null;
    }
}