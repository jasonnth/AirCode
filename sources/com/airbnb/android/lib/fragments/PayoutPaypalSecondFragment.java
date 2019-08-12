package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity.VerificationType;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.rxgroups.RequestSubscription;
import java.util.List;
import p032rx.Observer;

public class PayoutPaypalSecondFragment extends AirFragment {
    private static final String ARG_HAS_PAYPAL = "has_paypal";
    private static final int DIALOG_REQ_SUBMIT_PAYPAL = 235;
    private static final String PRESSED_GO_TO_PAYPAL = "went_to_paypal";
    private Spinner mCurrencySpinner;
    private boolean mGoBackToPaypalFirstFrag;
    private boolean mHasPaypal;
    private EditText mPaypalEmailField;
    private boolean mWentToPaypal;
    /* access modifiers changed from: private */
    public RequestSubscription paypalCall;

    public static PayoutPaypalSecondFragment newInstance(boolean hasPayPal) {
        PayoutPaypalSecondFragment f = new PayoutPaypalSecondFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_HAS_PAYPAL, hasPayPal);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mWentToPaypal = false;
        if (savedInstanceState != null) {
            this.mGoBackToPaypalFirstFrag = savedInstanceState.getBoolean(PRESSED_GO_TO_PAYPAL, false);
        }
        this.mHasPaypal = getArguments().getBoolean(ARG_HAS_PAYPAL, false);
        View v = inflater.inflate(this.mHasPaypal ? C0880R.layout.fragment_payout_paypal_yes : C0880R.layout.fragment_payout_paypal_no, container, false);
        this.mPaypalEmailField = (EditText) v.findViewById(C0880R.C0882id.pp_email);
        if (this.mHasPaypal) {
            this.mCurrencySpinner = (Spinner) v.findViewById(C0880R.C0882id.currency_spinner);
            if (((LegacyAddPayoutActivity) getActivity()).getSupportedCurrencies().size() > 1) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), 17367048, getCurrenciesForSpinnerAdapter());
                adapter.setDropDownViewResource(17367049);
                this.mCurrencySpinner.setAdapter(adapter);
            } else {
                this.mCurrencySpinner.setVisibility(8);
                this.mCurrencySpinner = null;
            }
        }
        ((Button) v.findViewById(C0880R.C0882id.button)).setOnClickListener(PayoutPaypalSecondFragment$$Lambda$1.lambdaFactory$(this));
        return v;
    }

    static /* synthetic */ void lambda$onCreateView$0(PayoutPaypalSecondFragment payoutPaypalSecondFragment, View view) {
        if (!payoutPaypalSecondFragment.mHasPaypal) {
            payoutPaypalSecondFragment.mWentToPaypal = true;
            payoutPaypalSecondFragment.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(payoutPaypalSecondFragment.getString(C0880R.string.paypal_website))));
        } else if (!TextUtil.isValidEmail(payoutPaypalSecondFragment.mPaypalEmailField.getText().toString())) {
            Toast.makeText(payoutPaypalSecondFragment.getActivity(), C0880R.string.payout_invalid_email, 1).show();
        } else if (payoutPaypalSecondFragment.mCurrencySpinner == null || payoutPaypalSecondFragment.isCurrencySelected()) {
            ZenDialog.builder().withTitle(C0880R.string.payout_confirm_paypal_title).withBodyText(payoutPaypalSecondFragment.getString(C0880R.string.payout_confirm_paypal_body, payoutPaypalSecondFragment.mPaypalEmailField.getText().toString(), payoutPaypalSecondFragment.getSelectedCurrency())).withSingleButton(C0880R.string.payout_confirm_correct, (int) DIALOG_REQ_SUBMIT_PAYPAL, (Fragment) payoutPaypalSecondFragment).create().show(payoutPaypalSecondFragment.getFragmentManager(), (String) null);
        } else {
            Toast.makeText(payoutPaypalSecondFragment.getActivity(), C0880R.string.payout_select_a_currency, 1).show();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mGoBackToPaypalFirstFrag || this.mWentToPaypal) {
            getFragmentManager().popBackStack();
        }
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mWentToPaypal) {
            outState.putBoolean(PRESSED_GO_TO_PAYPAL, true);
        }
    }

    private void submitPaypalInfo() {
        final ProgressDialogFragment progressDialog = ProgressDialogFragment.newInstance(getContext(), C0880R.string.payout_method_adding, 0);
        progressDialog.setProgressDialogListener(new ProgressDialogListener() {
            public void onProgressCompleted() {
                if (PayoutPaypalSecondFragment.this.getActivity() != null) {
                    ((LegacyAddPayoutActivity) PayoutPaypalSecondFragment.this.getActivity()).goToManagePayoutMethods();
                }
            }

            public void onProgressCancelled() {
                if (PayoutPaypalSecondFragment.this.paypalCall != null) {
                    PayoutPaypalSecondFragment.this.paypalCall.cancel();
                }
            }
        });
        AirDialogFragments.showDialog(getFragmentManager(), progressDialog, null);
        this.paypalCall = CreatePaymentInstrumentRequest.forPayPal(((LegacyAddPayoutActivity) getActivity()).getNewTrustParameters(), this.mPaypalEmailField.getText().toString(), getSelectedCurrency()).withListener((Observer) new NonResubscribableRequestListener<PaymentInstrumentResponse>() {
            public void onResponse(PaymentInstrumentResponse response) {
                if (PayoutPaypalSecondFragment.this.getActivity() != null) {
                    progressDialog.onProgressComplete(PayoutPaypalSecondFragment.this.getString(C0880R.string.payout_method_added), PayoutPaypalSecondFragment.this.getString(C0880R.string.payout_added_info), C0880R.C0881drawable.icon_complete, 3000);
                    AirbnbEventLogger.track(LegacyAddPayoutActivity.AIREVENT_PAYOUTS, Strap.make().mo11639kv("sub_event", "payout_success").mo11639kv("payout_type", "paypal"));
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                progressDialog.dismiss();
                if (SecurityCheck.ERROR_SECURITY_CHECK_REQUIRED.equals(NetworkUtil.error(error))) {
                    SecurityCheckAnalytics.trackPayoutPresent(Strap.make().mo11639kv("type", "paypal"));
                    PayoutPaypalSecondFragment.this.startActivity(OldAccountVerificationActivity.intentForVerificationType(PayoutPaypalSecondFragment.this.getActivity(), VerificationType.Payout.ordinal()));
                    return;
                }
                NetworkUtil.toastNetworkError((Context) PayoutPaypalSecondFragment.this.getActivity(), (NetworkException) error);
            }
        }).execute(this.requestManager);
    }

    private String[] getCurrenciesForSpinnerAdapter() {
        List<String> supportedCurrencies = ((LegacyAddPayoutActivity) getActivity()).getSupportedCurrencies();
        String[] array = new String[(supportedCurrencies.size() + 1)];
        array[0] = getResources().getString(C0880R.string.select_currency);
        for (int i = 0; i < supportedCurrencies.size(); i++) {
            array[i + 1] = (String) supportedCurrencies.get(i);
        }
        return array;
    }

    private String getSelectedCurrency() {
        if (this.mCurrencySpinner == null) {
            List<String> supportedCurrencies = ((LegacyAddPayoutActivity) getActivity()).getSupportedCurrencies();
            if (supportedCurrencies.size() == 1) {
                return (String) supportedCurrencies.get(0);
            }
            throw new IllegalStateException("if there was more than 1 currency, it should come from the spinner");
        } else if (isCurrencySelected()) {
            return (String) this.mCurrencySpinner.getSelectedItem();
        } else {
            throw new IllegalStateException("currency was not selected yet");
        }
    }

    private boolean isCurrencySelected() {
        return ((LegacyAddPayoutActivity) getActivity()).getSupportedCurrencies().contains((String) this.mCurrencySpinner.getSelectedItem());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DIALOG_REQ_SUBMIT_PAYPAL /*235*/:
                submitPaypalInfo();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }
}
