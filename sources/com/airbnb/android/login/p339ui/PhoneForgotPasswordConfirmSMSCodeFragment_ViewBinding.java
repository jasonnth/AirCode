package com.airbnb.android.login.p339ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.login.C7331R;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordConfirmSMSCodeFragment_ViewBinding */
public class PhoneForgotPasswordConfirmSMSCodeFragment_ViewBinding implements Unbinder {
    private PhoneForgotPasswordConfirmSMSCodeFragment target;
    private View view2131755580;

    public PhoneForgotPasswordConfirmSMSCodeFragment_ViewBinding(final PhoneForgotPasswordConfirmSMSCodeFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C7331R.C7333id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        View view = Utils.findRequiredView(source, C7331R.C7333id.btn_next, "field 'nextButton' and method 'onNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C7331R.C7333id.btn_next, "field 'nextButton'", AirButton.class);
        this.view2131755580 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNext();
            }
        });
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C7331R.C7333id.sheet_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        target2.inputText = (SheetInputText) Utils.findRequiredViewAsType(source, C7331R.C7333id.phone_number_input_view, "field 'inputText'", SheetInputText.class);
    }

    public void unbind() {
        PhoneForgotPasswordConfirmSMSCodeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.nextButton = null;
        target2.sheetMarquee = null;
        target2.inputText = null;
        this.view2131755580.setOnClickListener(null);
        this.view2131755580 = null;
    }
}
