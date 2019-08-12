package com.airbnb.android.lib.fragments.completeprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.requests.ConfirmEmailRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.utils.TextUtil;

public class CompleteProfileEmailChildFragment extends CompleteProfileBaseFragment implements VerifiedIdStrapper {
    private static final int DIALOG_REQ_CANCEL_CLEAR_FOCUS = 9223;
    private static final int DIALOG_REQ_CONTINUE = 9222;
    private static final int KEYBOARD_DELAY_MILLISECONDS = 50;
    private static final String SAVED_EMAIL = "email";
    private static final String SAVED_SENT_EMAIL = "sent_email";
    private EditText mEmailAddress;
    private TextView mEmailInstructions;
    private TextView mEmailStatus;
    private LinearLayout mFragment;
    /* access modifiers changed from: private */
    public Button mSendButton;
    private boolean mSentEmail;

    public static CompleteProfileEmailChildFragment newInstance() {
        return new CompleteProfileEmailChildFragment();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", this.mEmailAddress.getText().toString());
        outState.putBoolean(SAVED_SENT_EMAIL, this.mSentEmail);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0880R.layout.fragment_child_complete_profile_email, container, false);
        getCompleteProfileActivity().showProgressBar();
        this.mFragment = (LinearLayout) v.findViewById(C0880R.C0882id.confirm_email_fragment);
        this.mEmailAddress = (EditText) v.findViewById(C0880R.C0882id.email_edit_text);
        this.mSendButton = (Button) v.findViewById(C0880R.C0882id.email_send_button);
        this.mEmailStatus = (TextView) v.findViewById(C0880R.C0882id.email_status);
        this.mEmailInstructions = (TextView) v.findViewById(C0880R.C0882id.email_instructions);
        String currentEmail = null;
        if (savedInstanceState != null) {
            currentEmail = savedInstanceState.getString("email");
            this.mSentEmail = savedInstanceState.getBoolean(SAVED_SENT_EMAIL);
        }
        if (currentEmail == null) {
            currentEmail = this.mAccountManager.getCurrentUser().getEmailAddress();
        }
        this.mEmailAddress.setText(currentEmail);
        this.mEmailAddress.setOnEditorActionListener(CompleteProfileEmailChildFragment$$Lambda$1.lambdaFactory$(this));
        getCompleteProfileActivity().setUserEmailAddress(currentEmail);
        this.mEmailAddress.setOnFocusChangeListener(CompleteProfileEmailChildFragment$$Lambda$2.lambdaFactory$(this));
        this.mEmailAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                CompleteProfileEmailChildFragment.this.mSendButton.setEnabled(TextUtil.isValidEmail(s));
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
        this.mSendButton.setOnClickListener(CompleteProfileEmailChildFragment$$Lambda$3.lambdaFactory$(this));
        if (this.mSentEmail) {
            setSentEmailState();
        }
        return v;
    }

    static /* synthetic */ boolean lambda$onCreateView$0(CompleteProfileEmailChildFragment completeProfileEmailChildFragment, TextView view, int actionId, KeyEvent event) {
        if (2 == actionId) {
            completeProfileEmailChildFragment.mFragment.requestFocus();
            KeyboardUtils.dismissSoftKeyboard(completeProfileEmailChildFragment.getActivity(), completeProfileEmailChildFragment.mEmailAddress);
        }
        return false;
    }

    static /* synthetic */ void lambda$onCreateView$1(CompleteProfileEmailChildFragment completeProfileEmailChildFragment, View view, boolean hasFocus) {
        String email = completeProfileEmailChildFragment.mEmailAddress.getText().toString().trim();
        if (hasFocus && !TextUtils.isEmpty(email)) {
            ZenDialog.builder().withBodyText(C0880R.string.verified_id_email_confirm_email_change).withDualButton(C0880R.string.cancel, (int) DIALOG_REQ_CANCEL_CLEAR_FOCUS, C0880R.string.continue_text, (int) DIALOG_REQ_CONTINUE, (Fragment) completeProfileEmailChildFragment).create().show(completeProfileEmailChildFragment.getFragmentManager(), (String) null);
        }
    }

    static /* synthetic */ void lambda$onCreateView$2(CompleteProfileEmailChildFragment completeProfileEmailChildFragment, View view) {
        KeyboardUtils.dismissSoftKeyboard(completeProfileEmailChildFragment.getActivity(), completeProfileEmailChildFragment.mEmailAddress);
        completeProfileEmailChildFragment.mEmailAddress.clearFocus();
        String email = completeProfileEmailChildFragment.mEmailAddress.getText().toString();
        completeProfileEmailChildFragment.getCompleteProfileActivity().setUserEmailAddress(email);
        if (completeProfileEmailChildFragment.isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackEmailStartSend(completeProfileEmailChildFragment.getVerifiedIdAnalyticsStrap().mo11639kv("email", email));
        }
        ConfirmEmailRequest.newInstance(email).execute(NetworkUtil.singleFireExecutor());
        if (completeProfileEmailChildFragment.mSentEmail) {
            Toast.makeText(completeProfileEmailChildFragment.getContext(), C0880R.string.verified_id_email_sent, 0).show();
        }
        completeProfileEmailChildFragment.mSentEmail = true;
        completeProfileEmailChildFragment.setSentEmailState();
    }

    private void setSentEmailState() {
        this.mEmailStatus.setVisibility(0);
        this.mEmailInstructions.setText(C0880R.string.verified_id_email_tap_link);
        this.mSendButton.setText(C0880R.string.verified_id_resend_button);
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((CompleteProfileActivity) getActivity()).getReservationId());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_REQ_CONTINUE) {
            if (isVerifiedIdFlow()) {
                VerifiedIdAnalytics.trackEmailStartChange(getVerifiedIdAnalyticsStrap());
            }
            this.mEmailAddress.postDelayed(CompleteProfileEmailChildFragment$$Lambda$4.lambdaFactory$(this), 50);
        } else if (requestCode == DIALOG_REQ_CANCEL_CLEAR_FOCUS) {
            this.mEmailAddress.clearFocus();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
