package com.airbnb.android.login.p339ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.p339ui.views.OAuthOptionButton;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment_ViewBinding */
public class ExistingAccountFragment_ViewBinding implements Unbinder {
    private ExistingAccountFragment target;
    private View view2131755515;

    public ExistingAccountFragment_ViewBinding(final ExistingAccountFragment target2, View source) {
        this.target = target2;
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C7331R.C7333id.sheet_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        target2.userProfileImageView = (HaloImageView) Utils.findRequiredViewAsType(source, C7331R.C7333id.user_photo, "field 'userProfileImageView'", HaloImageView.class);
        target2.firstNameTextView = (AirTextView) Utils.findRequiredViewAsType(source, C7331R.C7333id.first_name, "field 'firstNameTextView'", AirTextView.class);
        target2.emailTextView = (AirTextView) Utils.findRequiredViewAsType(source, C7331R.C7333id.email, "field 'emailTextView'", AirTextView.class);
        View view = Utils.findRequiredView(source, C7331R.C7333id.sign_in_button, "field 'loginButton' and method 'logIn'");
        target2.loginButton = (AirButton) Utils.castView(view, C7331R.C7333id.sign_in_button, "field 'loginButton'", AirButton.class);
        this.view2131755515 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.logIn();
            }
        });
        target2.editPassword = (SheetInputText) Utils.findRequiredViewAsType(source, C7331R.C7333id.sign_in_password, "field 'editPassword'", SheetInputText.class);
        target2.oauthOptionButton = (OAuthOptionButton) Utils.findRequiredViewAsType(source, C7331R.C7333id.oauth_option_button, "field 'oauthOptionButton'", OAuthOptionButton.class);
        target2.createPasswordButton = (AirButton) Utils.findRequiredViewAsType(source, C7331R.C7333id.create_password_button, "field 'createPasswordButton'", AirButton.class);
    }

    public void unbind() {
        ExistingAccountFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sheetMarquee = null;
        target2.userProfileImageView = null;
        target2.firstNameTextView = null;
        target2.emailTextView = null;
        target2.loginButton = null;
        target2.editPassword = null;
        target2.oauthOptionButton = null;
        target2.createPasswordButton = null;
        this.view2131755515.setOnClickListener(null);
        this.view2131755515 = null;
    }
}
