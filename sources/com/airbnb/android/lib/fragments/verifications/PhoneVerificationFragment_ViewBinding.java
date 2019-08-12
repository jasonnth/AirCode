package com.airbnb.android.lib.fragments.verifications;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class PhoneVerificationFragment_ViewBinding implements Unbinder {
    private PhoneVerificationFragment target;
    private View view2131756882;
    private View view2131756884;
    private View view2131756886;
    private View view2131757735;

    public PhoneVerificationFragment_ViewBinding(final PhoneVerificationFragment target2, View source) {
        this.target = target2;
        target2.phoneNumberEditText = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.edittext_phone_number, "field 'phoneNumberEditText'", EditText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.edittext_confirmation_code, "field 'confirmationCodeEditText' and method 'onFocusChangeConfirmationCode'");
        target2.confirmationCodeEditText = (EditText) Utils.castView(view, C0880R.C0882id.edittext_confirmation_code, "field 'confirmationCodeEditText'", EditText.class);
        this.view2131756884 = view;
        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View p0, boolean p1) {
                target2.onFocusChangeConfirmationCode(p1);
            }
        });
        target2.phoneNumberEntryContainer = Utils.findRequiredView(source, C0880R.C0882id.container_phone_number_entry, "field 'phoneNumberEntryContainer'");
        target2.confirmationCodeEntryContainer = Utils.findRequiredView(source, C0880R.C0882id.container_confirmation_code_entry, "field 'confirmationCodeEntryContainer'");
        target2.callingCodeButton = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.btn_calling_code, "field 'callingCodeButton'", TextView.class);
        target2.confirmationCodeNotification = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_confirmation_code_notification, "field 'confirmationCodeNotification'", TextView.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_text_code, "field 'textCodeButton' and method 'sendConfirmationCode'");
        target2.textCodeButton = (TextView) Utils.castView(view2, C0880R.C0882id.btn_text_code, "field 'textCodeButton'", TextView.class);
        this.view2131756882 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.sendConfirmationCode();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.btn_verifications_skip, "field 'skipButton' and method 'skip'");
        target2.skipButton = view3;
        this.view2131757735 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.skip();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.btn_having_trouble, "method 'showHelpDialog'");
        this.view2131756886 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showHelpDialog();
            }
        });
    }

    public void unbind() {
        PhoneVerificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.phoneNumberEditText = null;
        target2.confirmationCodeEditText = null;
        target2.phoneNumberEntryContainer = null;
        target2.confirmationCodeEntryContainer = null;
        target2.callingCodeButton = null;
        target2.confirmationCodeNotification = null;
        target2.textCodeButton = null;
        target2.skipButton = null;
        this.view2131756884.setOnFocusChangeListener(null);
        this.view2131756884 = null;
        this.view2131756882.setOnClickListener(null);
        this.view2131756882 = null;
        this.view2131757735.setOnClickListener(null);
        this.view2131757735 = null;
        this.view2131756886.setOnClickListener(null);
        this.view2131756886 = null;
    }
}
