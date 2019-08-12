package com.airbnb.android.registration;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.primitives.AirButton;

public class CreateSocialAccountFragment_ViewBinding implements Unbinder {
    private CreateSocialAccountFragment target;
    private View view2131755480;
    private View view2131755483;

    public CreateSocialAccountFragment_ViewBinding(final CreateSocialAccountFragment target2, View source) {
        this.target = target2;
        target2.email = (SheetInputText) Utils.findRequiredViewAsType(source, C1562R.C1564id.registration_create_social_account_email, "field 'email'", SheetInputText.class);
        target2.firstName = (SheetInputText) Utils.findRequiredViewAsType(source, C1562R.C1564id.registration_create_social_account_first_name, "field 'firstName'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C1562R.C1564id.birthday_input_field_text, "field 'birthday' and method 'launchBirthdayPicker'");
        target2.birthday = (SheetInputText) Utils.castView(view, C1562R.C1564id.birthday_input_field_text, "field 'birthday'", SheetInputText.class);
        this.view2131755480 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.launchBirthdayPicker();
            }
        });
        target2.lastName = (SheetInputText) Utils.findRequiredViewAsType(source, C1562R.C1564id.registration_create_social_account_last_name, "field 'lastName'", SheetInputText.class);
        View view2 = Utils.findRequiredView(source, C1562R.C1564id.registration_create_social_account_next, "field 'nextBtn' and method 'submit'");
        target2.nextBtn = (AirButton) Utils.castView(view2, C1562R.C1564id.registration_create_social_account_next, "field 'nextBtn'", AirButton.class);
        this.view2131755483 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.submit();
            }
        });
        target2.loader = (LoaderFrame) Utils.findRequiredViewAsType(source, C1562R.C1564id.loading_overlay, "field 'loader'", LoaderFrame.class);
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C1562R.C1564id.sheet_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        target2.rootView = (FrameLayout) Utils.findRequiredViewAsType(source, C1562R.C1564id.social_signup_single_form_root, "field 'rootView'", FrameLayout.class);
        target2.promoEmailOptInSwitch = (SwitchRow) Utils.findRequiredViewAsType(source, C1562R.C1564id.promo_email_opt_in_switch, "field 'promoEmailOptInSwitch'", SwitchRow.class);
    }

    public void unbind() {
        CreateSocialAccountFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.email = null;
        target2.firstName = null;
        target2.birthday = null;
        target2.lastName = null;
        target2.nextBtn = null;
        target2.loader = null;
        target2.sheetMarquee = null;
        target2.rootView = null;
        target2.promoEmailOptInSwitch = null;
        this.view2131755480.setOnClickListener(null);
        this.view2131755480 = null;
        this.view2131755483.setOnClickListener(null);
        this.view2131755483 = null;
    }
}
