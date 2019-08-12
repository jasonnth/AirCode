package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.analytics.SecurityCheckAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.SecurityCheck;
import com.airbnb.android.core.requests.GetSecurityCheckRequest;
import com.airbnb.android.core.responses.GetSecurityCheckResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity;
import com.airbnb.android.lib.utils.DialogUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;

public class PayoutWelcomeFragment extends AirFragment {
    public static final String ARG_COUNTRY_CODE = "arg_country_code";
    private static final int DIALOG_REQ_START_VERIF = 2346;
    private static final int REQUEST_CODE_ACCOUNT_VERIFICATION = 2345;

    public static PayoutWelcomeFragment withCountryCode(String mCountryCode) {
        return (PayoutWelcomeFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PayoutWelcomeFragment()).putString("arg_country_code", mCountryCode)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payout_welcome, container, false);
        if (!BuildHelper.isDebugFeaturesEnabled()) {
            startSecurityCheck();
        } else if (savedInstanceState == null) {
            ZenDialog.builder().withBodyText("Do you want to go through the security checkpoint? This option is only shown on non-prod builds.").withDualButton("Checkpoint", (int) DIALOG_REQ_START_VERIF, "Skip", 0, (Fragment) this).create().show(getFragmentManager(), (String) null);
        }
        view.findViewById(C0880R.C0882id.payout_faq_link).setOnClickListener(PayoutWelcomeFragment$$Lambda$1.lambdaFactory$(this));
        ((Button) view.findViewById(C0880R.C0882id.payout_start)).setOnClickListener(PayoutWelcomeFragment$$Lambda$2.lambdaFactory$(this));
        return view;
    }

    private void startSecurityCheck() {
        ((SolitAirActivity) getActivity()).showLoader(true);
        new GetSecurityCheckRequest(new NonResubscribableRequestListener<GetSecurityCheckResponse>() {
            public void onErrorResponse(AirRequestNetworkException error) {
                ((SolitAirActivity) PayoutWelcomeFragment.this.getActivity()).showLoader(false);
            }

            public void onResponse(GetSecurityCheckResponse response) {
                ((SolitAirActivity) PayoutWelcomeFragment.this.getActivity()).showLoader(false);
                if (response.securityCheck.getRequirements().isAddPayout()) {
                    String strategy = response.securityCheck.getStrategy();
                    SecurityCheckAnalytics.trackPayoutPresent(null);
                    if (SecurityCheck.STRATEGY_PHONE_VERIFICATION.equals(strategy)) {
                        PayoutWelcomeFragment.this.startActivityForResult(OldAccountVerificationActivity.intentForPayout(PayoutWelcomeFragment.this.getActivity()), PayoutWelcomeFragment.REQUEST_CODE_ACCOUNT_VERIFICATION);
                    } else {
                        DialogUtils.showContactDialog(PayoutWelcomeFragment.this, 1001, PayoutWelcomeFragment.this, C0880R.string.security_check_contact_body_add_payout);
                    }
                }
            }
        }).execute(this.requestManager);
    }

    public void onDestroyView() {
        super.onDestroyView();
        ((SolitAirActivity) getActivity()).showLoader(false);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1001:
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/html");
                intent.putExtra("android.intent.extra.EMAIL", getString(C0880R.string.account_verification_contact_email));
                startActivity(Intent.createChooser(intent, getString(C0880R.string.send_mail)));
                getActivity().finish();
                break;
            case REQUEST_CODE_ACCOUNT_VERIFICATION /*2345*/:
                SecurityCheckAnalytics.trackPayoutDismiss(Strap.make().mo11637kv("success", resultCode == -1 ? 1 : 0));
                if (resultCode != -1) {
                    getActivity().finish();
                    break;
                }
                break;
            case DIALOG_REQ_START_VERIF /*2346*/:
                startSecurityCheck();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
