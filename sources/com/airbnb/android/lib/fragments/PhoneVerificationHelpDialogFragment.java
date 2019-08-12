package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.lib.C0880R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PhoneVerificationHelpDialogFragment extends ZenDialog {
    private static final String ARGS_REQUEST_CODE = "args_request_code";
    public static final int CallPhone = 2;
    public static final int ChangePhone = 0;
    public static final String RESULT_EXTRA_HELP_CHOICE = "result_extra_help_choice";
    public static final int SendCode = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface HelpChoice {
    }

    public static PhoneVerificationHelpDialogFragment newInstance(int requestCode) {
        Bundle args = new Bundle();
        args.putInt(ARGS_REQUEST_CODE, requestCode);
        return (PhoneVerificationHelpDialogFragment) new ZenBuilder(new PhoneVerificationHelpDialogFragment()).setCustomLayout(C0880R.layout.dialog_fragment_phone_verification_help).withCancelButton().withArguments(args).create();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void changePhoneNumber() {
        selectHelpOption(0);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void sendCode() {
        selectHelpOption(1);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void callInstead() {
        selectHelpOption(2);
    }

    private void selectHelpOption(int helpChoice) {
        int requestCode = getArguments().getInt(ARGS_REQUEST_CODE);
        Intent data = new Intent();
        data.putExtra(RESULT_EXTRA_HELP_CHOICE, helpChoice);
        sendActivityResult(requestCode, -1, data);
        dismiss();
    }
}
