package com.airbnb.android.login.p339ui;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.p339ui.views.OAuthOptionButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.android.login.ui.LoginLandingFragment_ViewBinding */
public class LoginLandingFragment_ViewBinding implements Unbinder {
    private LoginLandingFragment target;
    private View view2131755427;
    private View view2131755555;
    private View view2131755556;

    public LoginLandingFragment_ViewBinding(final LoginLandingFragment target2, View source) {
        this.target = target2;
        target2.agreementText = (TextView) Utils.findRequiredViewAsType(source, C7331R.C7333id.txt_agreement, "field 'agreementText'", TextView.class);
        View view = Utils.findRequiredView(source, C7331R.C7333id.primary_sign_in_option_button, "field 'primaryOptionButton' and method 'onPrimaryLoginOptionClick'");
        target2.primaryOptionButton = (OAuthOptionButton) Utils.castView(view, C7331R.C7333id.primary_sign_in_option_button, "field 'primaryOptionButton'", OAuthOptionButton.class);
        this.view2131755555 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onPrimaryLoginOptionClick();
            }
        });
        target2.loginLandingHeader = (AirTextView) Utils.findRequiredViewAsType(source, C7331R.C7333id.signin_landing_header, "field 'loginLandingHeader'", AirTextView.class);
        View view2 = Utils.findRequiredView(source, C7331R.C7333id.create_account_button, "method 'onCreateAccountSelected'");
        this.view2131755427 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCreateAccountSelected();
            }
        });
        View view3 = Utils.findRequiredView(source, C7331R.C7333id.sign_in_landing_more_options, "method 'onMoreOptionsClick'");
        this.view2131755556 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onMoreOptionsClick();
            }
        });
    }

    public void unbind() {
        LoginLandingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.agreementText = null;
        target2.primaryOptionButton = null;
        target2.loginLandingHeader = null;
        this.view2131755555.setOnClickListener(null);
        this.view2131755555 = null;
        this.view2131755427.setOnClickListener(null);
        this.view2131755427 = null;
        this.view2131755556.setOnClickListener(null);
        this.view2131755556 = null;
    }
}
