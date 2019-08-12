package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.analytics.SecurityCheckAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.SecurityCheck;
import com.airbnb.android.core.models.SecurityCheckPhoneNumber;
import com.airbnb.android.core.requests.GetSecurityCheckRequest;
import com.airbnb.android.core.requests.PostSecurityCheckRequest;
import com.airbnb.android.core.responses.GetSecurityCheckResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity.VerificationType;
import com.airbnb.android.lib.adapters.PhoneNumberPickerAdapter;
import com.airbnb.android.lib.utils.DialogUtils;
import com.airbnb.android.lib.views.LinearListView;
import java.util.List;

public class AccountVerificationPhonePickerFragment extends AirFragment {
    private static final int REQUEST_CANCEL_VERIFICATION = 3884;
    private static final int REQUEST_RETRY_VERIFICATION = 7040;
    private long mPhoneNumberId;
    /* access modifiers changed from: private */
    public List<SecurityCheckPhoneNumber> mPhoneNumbers;
    @BindView
    LinearListView mPhonePickerList;
    @BindView
    View mPhoneSMSOptions;
    @BindView
    TextView mSendPhoneCallButton;
    @BindView
    TextView mSendSMSButton;
    @BindView
    TextView mTitleText;
    VerificationType mVerificationType;

    public enum PhoneVerificationType {
        PHONE_CALL,
        SMS
    }

    public static Fragment newInstance(int verificationType) {
        Fragment f = new AccountVerificationPhonePickerFragment();
        Bundle args = new Bundle();
        args.putInt(OldAccountVerificationActivity.EXTRA_VERIFICATION_TYPE, verificationType);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mVerificationType = VerificationType.values()[getArguments().getInt(OldAccountVerificationActivity.EXTRA_VERIFICATION_TYPE)];
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_account_verification_phone_picker, container, false);
        SecurityCheckAnalytics.trackSelectPhoneImpression(null);
        bindViews(view);
        OldAccountVerificationActivity activity = (OldAccountVerificationActivity) getActivity();
        showLoader(true);
        getSecurityCheck();
        this.mPhonePickerList.setOnItemClickListener(AccountVerificationPhonePickerFragment$$Lambda$1.lambdaFactory$(this));
        OnClickListener clickListener = AccountVerificationPhonePickerFragment$$Lambda$2.lambdaFactory$(this, activity);
        this.mSendPhoneCallButton.setOnClickListener(clickListener);
        this.mSendSMSButton.setOnClickListener(clickListener);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(AccountVerificationPhonePickerFragment accountVerificationPhonePickerFragment, LinearListView parent, View v, int position, long id) {
        accountVerificationPhonePickerFragment.mPhonePickerList.setVisibility(8);
        accountVerificationPhonePickerFragment.mPhoneSMSOptions.setVisibility(0);
        accountVerificationPhonePickerFragment.mTitleText.setText(C0880R.string.account_verification_phone_picker_pick_method);
        accountVerificationPhonePickerFragment.mPhoneNumberId = ((SecurityCheckPhoneNumber) accountVerificationPhonePickerFragment.mPhoneNumbers.get(position)).getId();
    }

    static /* synthetic */ void lambda$onCreateView$1(AccountVerificationPhonePickerFragment accountVerificationPhonePickerFragment, OldAccountVerificationActivity activity, View v) {
        int id = -1;
        int viewId = v.getId();
        if (viewId == C0880R.C0882id.btn_sms) {
            id = PhoneVerificationType.SMS.ordinal();
            SecurityCheckAnalytics.trackSelectSMS(accountVerificationPhonePickerFragment.mPhoneNumberId);
        } else if (viewId == C0880R.C0882id.btn_phone_call) {
            id = PhoneVerificationType.PHONE_CALL.ordinal();
            SecurityCheckAnalytics.trackSelectPhoneCall(accountVerificationPhonePickerFragment.mPhoneNumberId);
        }
        new PostSecurityCheckRequest(accountVerificationPhonePickerFragment.mPhoneNumberId, id).execute(NetworkUtil.singleFireExecutor());
        activity.phoneNumberSelected(accountVerificationPhonePickerFragment.mPhoneNumberId);
    }

    private void getSecurityCheck() {
        new GetSecurityCheckRequest(new NonResubscribableRequestListener<GetSecurityCheckResponse>() {
            public void onErrorResponse(AirRequestNetworkException error) {
                AccountVerificationPhonePickerFragment.this.showLoader(false);
                AccountVerificationPhonePickerFragment.this.showErrorDialog();
            }

            public void onResponse(GetSecurityCheckResponse response) {
                AccountVerificationPhonePickerFragment.this.showLoader(false);
                SecurityCheck securityCheck = response.securityCheck;
                if (!securityCheck.getRequirements().isAddPayout() && !securityCheck.getRequirements().isManageListing()) {
                    return;
                }
                if (SecurityCheck.STRATEGY_PHONE_VERIFICATION.equals(securityCheck.getStrategy())) {
                    AccountVerificationPhonePickerFragment.this.mPhoneNumbers = securityCheck.getData().getPhoneNumbers();
                    AccountVerificationPhonePickerFragment.this.setupPhoneNumbersList();
                    return;
                }
                DialogUtils.showContactDialog(AccountVerificationPhonePickerFragment.this, 1001, AccountVerificationPhonePickerFragment.this, AccountVerificationPhonePickerFragment.this.mVerificationType.mBody);
            }
        }).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void showErrorDialog() {
        ZenDialog.builder().withTitle(C0880R.string.error).withBodyText(C0880R.string.account_verification_error_dialog).withDualButton(C0880R.string.cancel, (int) REQUEST_CANCEL_VERIFICATION, C0880R.string.retry, (int) REQUEST_RETRY_VERIFICATION, (Fragment) this).create().show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: private */
    public void setupPhoneNumbersList() {
        this.mPhonePickerList.setAdapter(new PhoneNumberPickerAdapter(getActivity(), 0, this.mPhoneNumbers));
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
            case REQUEST_CANCEL_VERIFICATION /*3884*/:
                getActivity().finish();
                break;
            case REQUEST_RETRY_VERIFICATION /*7040*/:
                showLoader(true);
                getSecurityCheck();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onDestroyView() {
        super.onDestroyView();
        showLoader(false);
    }
}
