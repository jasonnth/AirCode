package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.SecurityCheckAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment.ProgressDialogListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.SecurityCheck;
import com.airbnb.android.core.requests.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity.VerificationType;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.rxgroups.RequestSubscription;
import p032rx.Observer;

public class PayoutAchFragment extends AirFragment {
    private static final String ACH_ACCOUNT_TYPE = "ach_account_type";
    private static final int ACH_ROUTING_LENGTH = 9;
    private static final int DIALOG_REQ_SUBMIT_ACH = 663;
    /* access modifiers changed from: private */
    public RequestSubscription achCall;
    private EditText mAccountNumber;
    private String mAchAccountType;
    private EditText mPersonName;
    private EditText mRoutingNumber;

    public static PayoutAchFragment newInstance(String accountType) {
        PayoutAchFragment f = new PayoutAchFragment();
        Bundle args = new Bundle();
        args.putString(ACH_ACCOUNT_TYPE, accountType);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0880R.layout.fragment_payout_ach, container, false);
        this.mAchAccountType = getArguments().getString(ACH_ACCOUNT_TYPE);
        this.mRoutingNumber = (EditText) v.findViewById(C0880R.C0882id.payout_ach_routing);
        this.mAccountNumber = (EditText) v.findViewById(C0880R.C0882id.payout_ach_account);
        this.mPersonName = (EditText) v.findViewById(C0880R.C0882id.payout_ach_person_name);
        ((Button) v.findViewById(C0880R.C0882id.continue_button)).setOnClickListener(PayoutAchFragment$$Lambda$1.lambdaFactory$(this));
        return v;
    }

    static /* synthetic */ void lambda$onCreateView$0(PayoutAchFragment payoutAchFragment, View view) {
        if (payoutAchFragment.validateAchInfo()) {
            ZenDialog.builder().withTitle(C0880R.string.payout_confirm_ach_title).withBodyText(payoutAchFragment.getString(C0880R.string.payout_confirm_ach_body, payoutAchFragment.mAchAccountType, payoutAchFragment.mRoutingNumber.getText().toString(), payoutAchFragment.mAccountNumber.getText().toString(), payoutAchFragment.mPersonName.getText().toString())).withSingleButton(C0880R.string.payout_confirm_correct, (int) DIALOG_REQ_SUBMIT_ACH, (Fragment) payoutAchFragment).create().show(payoutAchFragment.getFragmentManager(), (String) null);
        }
        KeyboardUtils.dismissSoftKeyboard(payoutAchFragment.getActivity(), payoutAchFragment.getView());
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
    }

    private void submitAchInfo() {
        final ProgressDialogFragment progressDialog = ProgressDialogFragment.newInstance(getContext(), C0880R.string.payout_method_adding, 0);
        progressDialog.setProgressDialogListener(new ProgressDialogListener() {
            public void onProgressCompleted() {
                if (PayoutAchFragment.this.getActivity() != null) {
                    ((LegacyAddPayoutActivity) PayoutAchFragment.this.getActivity()).goToManagePayoutMethods();
                }
            }

            public void onProgressCancelled() {
                if (PayoutAchFragment.this.achCall != null) {
                    PayoutAchFragment.this.achCall.cancel();
                }
            }
        });
        AirDialogFragments.showDialog(getFragmentManager(), progressDialog, null);
        this.achCall = CreatePaymentInstrumentRequest.forAch(((LegacyAddPayoutActivity) getActivity()).getNewTrustParameters(), this.mPersonName.getText().toString(), this.mAchAccountType, this.mRoutingNumber.getText().toString(), this.mAccountNumber.getText().toString()).withListener((Observer) new NonResubscribableRequestListener<PaymentInstrumentResponse>() {
            public void onResponse(PaymentInstrumentResponse response) {
                if (PayoutAchFragment.this.getActivity() != null) {
                    progressDialog.onProgressComplete(PayoutAchFragment.this.getString(C0880R.string.payout_method_added), PayoutAchFragment.this.getString(C0880R.string.payout_added_info), C0880R.C0881drawable.icon_complete, 3000);
                    AirbnbEventLogger.track(LegacyAddPayoutActivity.AIREVENT_PAYOUTS, Strap.make().mo11639kv("sub_event", "payout_success").mo11639kv("payout_type", "ach"));
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                progressDialog.dismiss();
                if (SecurityCheck.ERROR_SECURITY_CHECK_REQUIRED.equals(NetworkUtil.error(error))) {
                    SecurityCheckAnalytics.trackPayoutPresent(Strap.make().mo11639kv("type", "ach"));
                    PayoutAchFragment.this.startActivity(OldAccountVerificationActivity.intentForVerificationType(PayoutAchFragment.this.getActivity(), VerificationType.Payout.ordinal()));
                    return;
                }
                NetworkUtil.toastNetworkError((Context) PayoutAchFragment.this.getActivity(), (NetworkException) error);
            }
        }).execute(this.requestManager);
    }

    private boolean validateAchInfo() {
        boolean routingOk;
        boolean accountOk;
        boolean nameOk;
        String routing = this.mRoutingNumber.getText().toString();
        String account = this.mAccountNumber.getText().toString();
        String name = this.mPersonName.getText().toString();
        if (!TextUtils.isDigitsOnly(routing) || routing.length() != 9) {
            routingOk = false;
        } else {
            routingOk = true;
        }
        if (!TextUtils.isDigitsOnly(account) || account.length() <= 5) {
            accountOk = false;
        } else {
            accountOk = true;
        }
        if (TextUtils.isEmpty(name) || !MiscUtils.isValidAchName(name)) {
            nameOk = false;
        } else {
            nameOk = true;
        }
        String toastText = null;
        if (!routingOk) {
            toastText = getString(C0880R.string.payout_ach_routing_invalid);
        } else if (!accountOk) {
            toastText = getString(C0880R.string.payout_ach_account_invalid);
        } else if (!nameOk) {
            toastText = getString(C0880R.string.payout_ach_name_invalid);
        }
        if (toastText != null) {
            Toast.makeText(getActivity(), toastText, 1).show();
        }
        if (!routingOk || !accountOk || !nameOk) {
            return false;
        }
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DIALOG_REQ_SUBMIT_ACH /*663*/:
                submitAchInfo();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }
}
