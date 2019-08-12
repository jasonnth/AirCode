package com.airbnb.android.lib.fragments.verifications;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class EmailVerificationFragment_ViewBinding implements Unbinder {
    private EmailVerificationFragment target;
    private View view2131756876;
    private View view2131756878;
    private View view2131757735;

    public EmailVerificationFragment_ViewBinding(final EmailVerificationFragment target2, View source) {
        this.target = target2;
        target2.currentEmailContainer = Utils.findRequiredView(source, C0880R.C0882id.container_current_email, "field 'currentEmailContainer'");
        target2.changeEmailContainer = Utils.findRequiredView(source, C0880R.C0882id.container_change_email, "field 'changeEmailContainer'");
        target2.newEmailEditText = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.edittext_email, "field 'newEmailEditText'", EditText.class);
        target2.currentEmailTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_email, "field 'currentEmailTextView'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_verifications_skip, "field 'skipButton' and method 'skip'");
        target2.skipButton = view;
        this.view2131757735 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.skip();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_send_email, "field 'sendEmailButton' and method 'sendNewConfirmationEmail'");
        target2.sendEmailButton = (TextView) Utils.castView(view2, C0880R.C0882id.btn_send_email, "field 'sendEmailButton'", TextView.class);
        this.view2131756876 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.sendNewConfirmationEmail();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.btn_link_change_email, "method 'displayChangeEmailView'");
        this.view2131756878 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.displayChangeEmailView();
            }
        });
    }

    public void unbind() {
        EmailVerificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.currentEmailContainer = null;
        target2.changeEmailContainer = null;
        target2.newEmailEditText = null;
        target2.currentEmailTextView = null;
        target2.skipButton = null;
        target2.sendEmailButton = null;
        this.view2131757735.setOnClickListener(null);
        this.view2131757735 = null;
        this.view2131756876.setOnClickListener(null);
        this.view2131756876 = null;
        this.view2131756878.setOnClickListener(null);
        this.view2131756878 = null;
    }
}
