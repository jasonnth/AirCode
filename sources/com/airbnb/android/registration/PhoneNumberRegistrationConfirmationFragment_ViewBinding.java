package com.airbnb.android.registration;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class PhoneNumberRegistrationConfirmationFragment_ViewBinding implements Unbinder {
    private PhoneNumberRegistrationConfirmationFragment target;
    private View view2131755550;

    public PhoneNumberRegistrationConfirmationFragment_ViewBinding(final PhoneNumberRegistrationConfirmationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C1562R.C1564id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        View view = Utils.findRequiredView(source, C1562R.C1564id.btn_next, "field 'nextButton' and method 'onNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C1562R.C1564id.btn_next, "field 'nextButton'", AirButton.class);
        this.view2131755550 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNext();
            }
        });
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C1562R.C1564id.sheet_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        target2.inputText = (SheetInputText) Utils.findRequiredViewAsType(source, C1562R.C1564id.phone_number_input_view, "field 'inputText'", SheetInputText.class);
        target2.rootView = Utils.findRequiredView(source, C1562R.C1564id.registration_edit_phone_root, "field 'rootView'");
    }

    public void unbind() {
        PhoneNumberRegistrationConfirmationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.nextButton = null;
        target2.sheetMarquee = null;
        target2.inputText = null;
        target2.rootView = null;
        this.view2131755550.setOnClickListener(null);
        this.view2131755550 = null;
    }
}
